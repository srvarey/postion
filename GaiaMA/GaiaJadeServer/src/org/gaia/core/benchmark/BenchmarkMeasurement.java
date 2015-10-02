/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.core.benchmark;

import java.net.InetAddress;
import java.net.UnknownHostException;
import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.kernel;
import org.apache.log4j.Logger;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.load.LoadMeasureThread;

/**
 * This thread is doing the actual benchmark measurements, which results to the
 * capability of a computer system to crunch numbers.<br> This will be
 * comparable through a benchmark value in Mflops (Millions of floating point
 * operations per second).<br><br>
 *
 * The benchmark value can be obtained by using the method<br>
 * 'Application.RunInfo.getBenchValue();'
 *
 */
public class BenchmarkMeasurement extends Thread {

    private static final Logger logger = Logger.getLogger(BenchmarkMeasurement.class);
    private float benchValueOld = Application.getGlobalInfo().getBenchValue();
    private boolean forceBench = false;
    private String benchExecOn = Application.getGlobalInfo().getBenchExecOn();
    private String nowExecOn = null;
    private double min_time = Constants.RESOLUTION_DEFAULT;
    private int FFT_size = Constants.FFT_SIZE;
    private int SOR_size = Constants.SOR_SIZE;
    private int Sparse_size_M = Constants.SPARSE_SIZE_M;
    private int Sparse_size_nz = Constants.SPARSE_SIZE_nz;
    private int LU_size = Constants.LU_SIZE;

    /**
     * The constructor of this class.<br> If <i>forceBenchmark</i> is set to
     * true the benchmark will be executed regardless of the settings in the
     * property file or if the system was executed in the same network already
     * or not.
     *
     * @param forceBenchmark the force benchmark
     */
    public BenchmarkMeasurement(boolean forceBenchmark) {
        this.forceBench = forceBenchmark;
    }

    /**
     * Depending on the FileProperties this will measure the ability> of this
     * computer to crunch numbers.<br> The results will be given in Mflops
     * (Millions of floating point operations per second)
     */
    @Override
    public void run() {
        super.run();
        this.setName("SciMark2-Benchmark");

        LoadMeasureThread.setCompositeBenchmarkValue(benchValueOld);
        setLocalComputerName();
        if (this.benchValueOld > 0 && this.nowExecOn.equalsIgnoreCase(this.benchExecOn) && !forceBench ) {
            Application.setBenchmarkRunning(false);
            return;
        }

        logger.debug("Start benchmark tests ");
        double res[] = new double[6];
        Random R = new Random(Constants.RANDOM_SEED);
        res[1] = kernel.measureFFT(FFT_size, min_time, R);
        res[2] = kernel.measureSOR(SOR_size, min_time, R);
        res[3] = kernel.measureMonteCarlo(min_time, R);
        res[4] = kernel.measureSparseMatmult(Sparse_size_M, Sparse_size_nz, min_time, R);
        res[5] = kernel.measureLU(LU_size, min_time, R);
        res[0] = (res[1] + res[2] + res[3] + res[4] + res[5]) / 5;

        logger.info("=> Average Benchmark Result: " + Math.round(res[0]) + " Mflops");

        float result = (float) Math.round((float) res[0] * 100) / 100;
        LoadMeasureThread.setCompositeBenchmarkValue(result);
        Application.getGlobalInfo().setBenchValue(result);
        Application.getGlobalInfo().setBenchExecOn(nowExecOn);
        Application.getGlobalInfo().getFileProperties().save();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e);
        }

        Application.setBenchmarkRunning(false);
    }

    /**
     * set the local computer name
     * 
     * 
     */
    private void setLocalComputerName() {
        try {
            nowExecOn = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            logger.error(e);
        }

    }
}
