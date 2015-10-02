package org.gaia.simulationService.distribution;

import jade.core.event.NotificationService;
import jade.core.mobility.AgentMobilityService;
import jade.util.leap.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.server.Application;
import org.gaia.jade.server.GaiaAgentCreator;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.agents.ServerSlaveAgent;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This class is only used by the {@link ServerSlaveAgent} of the background
 * system. It enables the agent to start a new process in order to extend a
 * remote JADE platform and join that platform with a new container.
 *
 *
 */
public class JadeRemoteStart extends Thread {

    private static final Logger logger = Logger.getLogger(JadeRemoteStart.class);
    /**
     * Constant for a memory of 16 MB.
     */
    public static final String jvmMemo0016MB = "16m";
    /**
     * Constant for a memory of 32 MB.
     */
    public static final String jvmMemo0032MB = "32m";
    /**
     * Constant for a memory of 64 MB.
     */
    public static final String jvmMemo0064MB = "64m";
    /**
     * Constant for a memory of 128 MB.
     */
    public static final String jvmMemo0128MB = "128m";
    /**
     * Constant for a memory of 256 MB.
     */
    public static final String jvmMemo0256MB = "256m";
    /**
     * Constant for a memory of 512 MB.
     */
    public static final String jvmMemo0512MB = "512m";
    /**
     * Constant for a memory of 1024 MB.
     */
    public static final String jvmMemo1024MB = "1024m";
    private boolean jvmMemAllocUseDefaults = true;
    private String jvmMemAllocInitial = JadeRemoteStart.jvmMemo0032MB;
    private String jvmMemAllocMaximum = JadeRemoteStart.jvmMemo0128MB;
    private boolean jadeIsRemoteContainer = true;
    private boolean jadeShowGUI = false;
    private String jadeShowGUIAgentName = "rma.";
    private String jadeServices;
    private String jadeHost = Application.getGlobalInfo().getServerMasterURL();
    private String jadePort = Application.getGlobalInfo().getJadeLocalPort().toString();
    private String jadeContainerName = "remote";
    private int localPort;
    private ArrayList jadeJarInclude = null;
    private File extJarFolder = null;
    private String pathBaseDir = Application.getGlobalInfo().PathBaseDir();

    /**
     * Default constructor.
     *
     * @param reCoCo the RemoteContainerConfig
     */
    public JadeRemoteStart(RemoteContainerConfig reCoCo, int localPort) {
        this.localPort = localPort;
        StringBuilder serviceJade = new StringBuilder(NotificationService.class.getName());
        serviceJade.append(";");
        serviceJade.append(AgentMobilityService.class.getName());
        serviceJade.append(";");
        serviceJade.append(SimulationService.class.getName());
        jadeServices = serviceJade.toString();
        jadeIsRemoteContainer = reCoCo.getJadeIsRemoteContainer();
        if (reCoCo.getJvmMemAllocInitial() == null && reCoCo.getJvmMemAllocMaximum() == null) {
            this.jvmMemAllocUseDefaults = true;
            this.jvmMemAllocInitial = JadeRemoteStart.jvmMemo0064MB;
            this.jvmMemAllocMaximum = JadeRemoteStart.jvmMemo0512MB;
        } else {
            this.jvmMemAllocUseDefaults = false;
            this.jvmMemAllocInitial = reCoCo.getJvmMemAllocInitial();
            this.jvmMemAllocMaximum = reCoCo.getJvmMemAllocMaximum();
        }

        if (reCoCo.getJadeServices() != null) {
            this.jadeServices = reCoCo.getJadeServices();
        }
        if (reCoCo.getJadeHost() != null) {
            this.jadeHost = reCoCo.getJadeHost();
        }
        if (reCoCo.getJadePort() != null) {
            this.jadePort = reCoCo.getJadePort();
        }
        if (reCoCo.getJadeContainerName() != null) {
            this.jadeContainerName = reCoCo.getJadeContainerName();
        }

    }

    /**
     * Deletes a folder and all sub elements.
     *
     * @param directory the directory
     */
    private void deleteFolder(File directory) {

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                deleteFolder(file);
            }
            file.delete();
        }
    }

    /**
     * Action for the Thread-Start. Starts a new JVM with a new Jade-Instance
     */
    @Override
    public void run() {
        //this.setName("JADE Remote: " + jadeContainerName);
        this.startJade();
    }

    /**
     * This Method starts a Jade-Platform within a new Java Virtual Machine.
     */
    public void startJade() {

        String os = System.getProperty("os.name");

        String java = "java";
        String javaVMArgs = "";
        String classPath;
        StringBuilder jade;
        String jadeArgs = "";

        // --- Class-Path configuration ---------
        classPath = getClassPath();
        if (os.toLowerCase().contains("linux")) {
            classPath = classPath.replaceAll(";", ":");
        }

        // debug
//        jade = new StringBuilder("-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=5858,suspend=n");
        jade = new StringBuilder("");

        // library path
        jade.append(" -Djava.library.path=.");

        // main
        jade.append(" -jar dist/GaiaJadeSlaveServer.jar -jade");
        if (jadeServices != null) {
            jadeArgs += "-services " + jadeServices + StringUtils.SPACE;
        }
        if (jadeIsRemoteContainer) {
            jadeArgs += "-container ";
            jadeArgs += "-container-name " + jadeContainerName + StringUtils.SPACE;
        }
        jadeArgs += "-host " + jadeHost + StringUtils.SPACE;
        jadeArgs += "-port " + jadePort + StringUtils.SPACE;

        jadeArgs += "-local-port " + localPort + StringUtils.SPACE;

        String sIndex = jadeContainerName.substring(6);
        Integer id = Integer.parseInt(sIndex);
        jadeArgs += "-agents " + "agentCreator" + id + ":" + GaiaAgentCreator.class.getName() + StringUtils.SPACE;

        // please no gui
        jadeShowGUI = false;
        if (jadeShowGUI) {
            jadeArgs += jadeShowGUIAgentName + jadeContainerName + ":jade.tools.rma.rma ";
        }
        jadeArgs = jadeArgs.trim();
        String execute = java + StringUtils.SPACE + javaVMArgs + StringUtils.SPACE + classPath + StringUtils.SPACE + jade + StringUtils.SPACE + jadeArgs;
        execute = execute.replace("  ", StringUtils.SPACE);
        logger.info("Execute: " + execute);
        try {
            String[] arg = execute.split(StringUtils.SPACE);
            ProcessBuilder processBuilder = new ProcessBuilder(arg);
            processBuilder.redirectErrorStream(true);
            if (pathBaseDir.equals("")) {
                pathBaseDir = ".";
                logger.info("Current path :" + System.getProperty("user.dir"));
            }
            processBuilder.directory(new File(pathBaseDir));

            Process process = processBuilder.start();

            Scanner in = new Scanner(process.getInputStream()).useDelimiter("\\Z");
            Scanner err = new Scanner(process.getErrorStream()).useDelimiter("\\Z");

            while (in.hasNextLine() || err.hasNextLine()) {
                if (in.hasNextLine()) {
                    logger.info("[" + jadeContainerName + "]: " + in.nextLine());
                }
                if (err.hasNextLine()) {
                    logger.error("[" + jadeContainerName + "]: " + err.nextLine());
                }
            }
            logger.info("Killed Container [" + jadeContainerName + "]");

            // --- Remove external jars from the download-folder ----
            if (extJarFolder != null) {
                if (extJarFolder.exists() == true) {
                    deleteFolder(extJarFolder);
                    extJarFolder.delete();
                }
            }

        } catch (IOException e) {
            logger.error(e);
        }

    }

    /**
     * This method configures the CLASSPATH for the remote-container.
     *
     * @param ServiceList the service list
     * @return the new class path
     */
    private String getClassPath() {
        StringBuilder classPath;
        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            classPath = new StringBuilder("-classpath ");
        } else {
            classPath = new StringBuilder("-cp ");
        }
        classPath.append(".;./lib");

//        classPath.append("./../GaiaMA/build/cluster/modules/ext;");
//        if (jadeJarIncludeClassPath.size() > 0) {
//            for (int i = 0; jadeJarIncludeClassPath.size() > i; i++) {
//                String jar = (String) jadeJarIncludeClassPath.get(i);
//                classPath.append(jar);
//            }
//        }
        return classPath.toString();
    }

    /**
     * Checks if is memory allocation for the new JVM will be the default one.
     *
     * @return the jvmMemAllocUseDefaults
     */
    public boolean isJVMMemAllocUseDefaults() {
        return jvmMemAllocUseDefaults;
    }

    /**
     * Sets the memory allocation for the new JVM to be the default value or
     * not.
     *
     * @param useDefaults the new jVM mem alloc use defaults
     */
    public void setJVMMemAllocUseDefaults(boolean useDefaults) {
        this.jvmMemAllocUseDefaults = useDefaults;
    }

    /**
     * @return the jvmMemAllocInitial
     */
    public String getJVMMemAllocInitial() {
        return jvmMemAllocInitial;
    }

    /**
     * @param jvmMemAllocInitial the jvmMemAllocInitial to set
     */
    public void setJVMMemAllocInitial(String jvmMemAllocInitial) {
        this.jvmMemAllocInitial = jvmMemAllocInitial;
    }

    /**
     * @return the jvmMemAllocMaximum
     */
    public String getJVMMemAllocMaximum() {
        return jvmMemAllocMaximum;
    }

    /**
     * @param jvmMemAllocMaximum the jvmMemAllocMaximum to set
     */
    public void setJVMMemAllocMaximum(String jvmMemAllocMaximum) {
        this.jvmMemAllocMaximum = jvmMemAllocMaximum;
    }

    /**
     * @return the jadeIsRemoteContainer
     */
    public boolean isJADEIsRemoteContainer() {
        return jadeIsRemoteContainer;
    }

    /**
     * @param jadeIsRemoteContainer the jadeIsRemoteContainer to set
     */
    public void setJADEIsRemoteContainer(boolean jadeIsRemoteContainer) {
        this.jadeIsRemoteContainer = jadeIsRemoteContainer;
    }

    /**
     * @return the jadeShowGUI
     */
    public boolean isJADEShowGUI() {
        return jadeShowGUI;
    }

    /**
     * @param jadeShowGUI the jadeShowGUI to set
     */
    public void setJADEShowGUI(boolean jadeShowGUI) {
        this.jadeShowGUI = jadeShowGUI;
    }

    /**
     * @return the jadeShowGUIAgentName
     */
    public String getJadeShowGUIAgentName() {
        return jadeShowGUIAgentName;
    }

    /**
     * @param jadeShowGUIAgentName the jadeShowGUIAgentName to set
     */
    public void setJadeShowGUIAgentName(String jadeShowGUIAgentName) {
        this.jadeShowGUIAgentName = jadeShowGUIAgentName;
    }

    /**
     * @return the jadeHost
     */
    public String getJADEHost() {
        return jadeHost;
    }

    /**
     * @param jadeHost the jadeHost to set
     */
    public void setJADEHost(String jadeHost) {
        this.jadeHost = jadeHost;
    }

    /**
     * @return the jadePort
     */
    public String getJADEPort() {
        return jadePort;
    }

    /**
     * @param jadePort the jadePort to set
     */
    public void setJADEPort(String jadePort) {
        this.jadePort = jadePort;
    }

    /**
     * @return the jadeContainerName
     */
    public String getJADEContainerName() {
        return jadeContainerName;
    }

    /**
     * @param jadeContainerName the jadeContainerName to set
     */
    public void setJADEContainerName(String jadeContainerName) {
        this.jadeContainerName = jadeContainerName;
    }

    /**
     * @param jadeServices the jadeServices to set
     */
    public void setJADEServices(String jadeServices) {
        this.jadeServices = jadeServices;
    }

    /**
     * @return the jadeServices
     */
    public String getJADEServices() {
        return jadeServices;
    }
}
