/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.pricing.pricers.gaia;

/**
 *
 * @author Benjamin Frerejean
 */
public class OSJavaQuant {

    public static double ACCURACY = 1.0e-6;

    public static double n(double z) {  // normal distribution function
        return (1.0 / Math.sqrt(2.0 * Math.PI)) * Math.exp(-0.5 * z * z);
    }

    public static double N(double z) {
        if (z > 6.0) {
            return 1.0;
        } // this guards against overflow
        if (z < -6.0) {
            return 0.0;
        }
        double b1 = 0.31938153;
        double b2 = -0.356563782;
        double b3 = 1.781477937;
        double b4 = -1.821255978;
        double b5 = 1.330274429;
        double p = 0.2316419;
        double c2 = 0.3989423;
        double a = Math.abs(z);
        double t = 1.0 / (1.0 + a * p);
        double b = c2 * Math.exp((-z) * (z / 2.0));
        double n = ((((b5 * t + b4) * t + b3) * t + b2) * t + b1) * t;
        n = 1.0 - b * n;
        if (z < 0.0) {
            n = 1.0 - n;
        }
        return n;
    }

    public static double option_price_european_call_payout(double S, double K, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return S * Math.exp(-q * time) * N(d1) - K * Math.exp(-r * time) * N(d2);
    }

    public static double option_delta_european_call_payout(double S, double K, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        if (time_sqrt!=0){
            double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
            return Math.exp(-q * time) * N(d1);
        } else {
            return 0;
        }
    }

    public static double option_delta_european_put_payout(double S, double K, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        return Math.exp(-q * time) * N(d1) - 1;
    }

    public static double inverse_option_price_european_call_payout(double S, double K, double r, double q, double sigma, double time, double callPrice) {
        int z = 1;
        boolean isPut = false;
        if (isPut) {
            sigma = -sigma;
            z = -1;
        }
        double tmpPrice = z * option_price_european_call_payout(S, K, r, q, sigma, time);
        double delta = z * option_delta_european_call_payout(S, K, r, q, sigma, time);
        double epsilon = 1e-12;
        int counter = 0;
        while (Math.abs(callPrice - tmpPrice) > epsilon) {
            counter++;
            if (counter > 30) {
                return 0;
            }
            S = S - (tmpPrice - callPrice) / delta;
            tmpPrice = z * option_price_european_call_payout(S, K, r, q, sigma, time);
            delta = z * option_delta_european_call_payout(S, K, r, q, sigma, time);
        }
        return S;
    }

    public static double option_price_euro_call_down_in(double S, double K, double H, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double lambda = (r - q + sigma * sigma / 2.0) / (sigma * sigma);
        double d1 = Math.log(H * H / (S * K)) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return S * Math.exp(-q * time) * N(d1) * Math.pow(H / S, 2.0 * lambda)
                - K * Math.exp(-r * time) * N(d2) * Math.pow(H / S, 2.0 * lambda - 2.0);
    }

    public static double option_price_euro_call_down_out(double S, double K, double H, double r, double q, double sigma, double time) {
        return option_price_european_call_payout(S, K, r, q, sigma, time)
                - option_price_euro_call_down_in(S, K, H, r, q, sigma, time);
    }

    public static double option_price_euro_call_up_in(double S, double K, double H, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double lambda = (r - q + sigma * sigma / 2.0) / (sigma * sigma);
        double x1 = Math.log(S / H) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double y = Math.log(H * H / (S * K)) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double y1 = Math.log(H / S) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        return S * Math.exp(-q * time) * N(x1) - K * Math.exp(-r * time) * N(x1 - sigma * time_sqrt)
                - S * Math.exp(-q * time) * Math.pow(H / S, 2.0 * lambda) * (N(-y) - N(-y1))
                + K * Math.exp(-r * time) * Math.pow(H / S, 2 * lambda - 2) * (N(-y + sigma * time_sqrt) - N(-y1 + sigma * time_sqrt));
    }

    public static double option_price_euro_call_up_out(double S, double K, double H, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double lambda = (r - q + sigma * sigma / 2.0) / (sigma * sigma);
        double d1 = Math.log(H * H / (S * K)) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return option_price_european_call_payout(S, K, r, q, sigma, time)
                - option_price_euro_call_up_in(S, K, H, r, q, sigma, time);
    }

    public static double option_price_euro_put_down_in(double S, double K, double H, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double lambda = (r - q + sigma * sigma / 2.0) / (sigma * sigma);
        double x1 = Math.log(S / H) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double y = Math.log(H * H / (S * K)) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double y1 = Math.log(H / S) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        return -S * Math.exp(-q * time) * N(-x1) + K * Math.exp(-r * time) * N(-x1 + sigma * time_sqrt)
                + S * Math.exp(-q * time) * Math.pow(H / S, 2.0 * lambda) * (N(y) - N(y1))
                - K * Math.exp(-r * time) * Math.pow(H / S, 2 * lambda - 2) * (N(y - sigma * time_sqrt) - N(y1 - sigma * time_sqrt));

    }

    public static double option_price_euro_put_down_out(double S, double K, double H, double r, double q, double sigma, double time) {
        return option_price_european_put_payout(S, K, r, q, sigma, time)
                - option_price_euro_put_down_in(S, K, H, r, q, sigma, time);
    }

    public static double option_price_euro_put_up_in(double S, double K, double H, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double lambda = (r - q + sigma * sigma / 2.0) / (sigma * sigma);
        double d1 = Math.log(H * H / (S * K)) / (sigma * time_sqrt) + lambda * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return -S * Math.exp(-q * time) * N(-d1) * Math.pow(H / S, 2.0 * lambda)
                + K * Math.exp(-r * time) * N(-d2) * Math.pow(H / S, 2.0 * lambda - 2.0);
    }

    public static double option_price_euro_put_up_out(double S, double K, double H, double r, double q, double sigma, double time) {
        return option_price_european_put_payout(S, K, r, q, sigma, time)
                - option_price_euro_put_up_in(S, K, H, r, q, sigma, time);
    }

    public static double option_price_european_put_payout(double S, double K, double r, double q, double sigma, double time) {
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return K * Math.exp(-r * time) * N(-d2) - S * Math.exp(-q * time) * N(-d1);
    }

    public static double option_price_american_call_approximated_baw(double S, double X, double r, double b, double sigma, double time) {
        double sigma_sqr = sigma * sigma;
        double time_sqrt = Math.sqrt(time);
        double nn = 2.0 * b / sigma_sqr;
        double m = 2.0 * r / sigma_sqr;
        double K = 1.0 - Math.exp(-r * time);
        double q2 = (-(nn - 1) + Math.sqrt(Math.pow((nn - 1), 2.0) + (4 * m / K))) * 0.5;

        double q2_inf = 0.5 * (-(nn - 1) + Math.sqrt(Math.pow((nn - 1), 2.0) + 4.0 * m));    // seed value from paper
        double S_star_inf = X / (1.0 - 1.0 / q2_inf);
        double h2 = -(b * time + 2.0 * sigma * time_sqrt) * (X / (S_star_inf - X));
        double S_seed = X + (S_star_inf - X) * (1.0 - Math.exp(h2));

        int no_iterations = 0; // iterate on S to find S_star, using Newton steps
        double Si = S_seed;
        double g = 1;
        double gprime = 1.0;
        while ((Math.abs(g) > ACCURACY)
                && (Math.abs(gprime) > ACCURACY) // to avoid exploding Newton's
                && (no_iterations++ < 500)
                && (Si > 0.0)) {
            double c = option_price_european_call_payout(Si, X, r, b, sigma, time);
            double d1 = (Math.log(Si / X) + (b + 0.5 * sigma_sqr) * time) / (sigma * time_sqrt);
            g = (1.0 - 1.0 / q2) * Si - X - c + (1.0 / q2) * Si * Math.exp((b - r) * time) * N(d1);
            gprime = (1.0 - 1.0 / q2) * (1.0 - Math.exp((b - r) * time) * N(d1))
                    + (1.0 / q2) * Math.exp((b - r) * time) * n(d1) * (1.0 / (sigma * time_sqrt));
            Si = Si - (g / gprime);
        }
        double S_star;
        if (Math.abs(g) > ACCURACY) {
            S_star = S_seed;
        } // did not converge
        else {
            S_star = Si;
        }
        double C;
        double c = option_price_european_call_payout(S, X, r, b, sigma, time);
        if (S >= S_star) {
            C = S - X;
        } else {
            double d1 = (Math.log(S_star / X) + (b + 0.5 * sigma_sqr) * time) / (sigma * time_sqrt);
            double A2 = (1.0 - Math.exp((b - r) * time) * N(d1)) * (S_star / q2);
            C = c + A2 * Math.pow((S / S_star), q2);
        }
        return Math.max(C, c); // know value will never be less than BS value
    }

    public static double option_price_american_put_approximated_baw(double S, double X, double r, double b, double sigma, double time) {
        double sigma_sqr = sigma * sigma;
        double time_sqrt = Math.sqrt(time);
        double M = 2.0 * r / sigma_sqr;
        double NN = 2.0 * b / sigma_sqr;
        double K = 1.0 - Math.exp(-r * time);
        double q1 = 0.5 * (-(NN - 1) - Math.sqrt(Math.pow((NN - 1), 2.0) + (4.0 * M / K)));

        // now find initial S value
        double q1_inf = 0.5 * (-(NN - 1) - Math.sqrt(Math.pow((NN - 1), 2.0) + 4.0 * M));
        double S_star_star_inf = X / (1.0 - 1.0 / q1_inf);
        double h1 = (b * time - 2 * sigma * time_sqrt) * (X / (X - S_star_star_inf));
        double S_seed = S_star_star_inf + (X - S_star_star_inf) * Math.exp(h1);

        double Si = S_seed;  // now do Newton iterations to solve for S**
        int no_iterations = 0;
        double g = 1;
        double gprime = 1;
        while ((Math.abs(g) > ACCURACY)
                && (Math.abs(gprime) > ACCURACY) // to avoid non-convergence
                && (no_iterations++ < 500)
                && Si > 0.0) {
            double p = option_price_european_put_payout(Si, X, r, b, sigma, time);
            double d1 = (Math.log(Si / X) + (b + 0.5 * sigma_sqr) * time) / (sigma * time_sqrt);
            g = X - Si - p + (1 - Math.exp((b - r) * time) * N(-d1)) * Si / q1;
            gprime = (1.0 / q1 - 1.0) * (1.0 - Math.exp((b - r) * time) * N(-d1))
                    + (1.0 / q1) * Math.exp((b - r) * time) * (1.0 / (sigma * time_sqrt)) * n(-d1);
            Si = Si - (g / gprime);
        }
        double S_star_star = Si;
        if (g > ACCURACY) {
            S_star_star = S_seed;
        }  // if not found g**
        double P;
        double p = option_price_european_put_payout(S, X, r, b, sigma, time);
        if (S > S_star_star) {
            double d1 = (Math.log(S_star_star / X)
                    + (b + 0.5 * sigma_sqr) * time) / (sigma * time_sqrt);
            double A1 = -(S_star_star / q1) * (1 - Math.exp((b - r) * time) * N(-d1));
            P = p + A1 * Math.pow((S / S_star_star), q1);
        } else {
            P = X - S;
        }
        return Math.max(P, p);  // should not be lower than Black Scholes value
    }

    public static double option_price_asian_geometric_average_price_call(double S, double X, double r, double q, double sigma, double time) {
        double sigma_sqr = Math.pow(sigma, 2);
        double adj_div_yield = 0.5 * (r + q + sigma_sqr);
        double adj_sigma = sigma / Math.sqrt(3.0);
        double adj_sigma_sqr = Math.pow(adj_sigma, 2);
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / X) + (r - adj_div_yield + 0.5 * adj_sigma_sqr) * time) / (adj_sigma * time_sqrt);
        double d2 = d1 - (adj_sigma * time_sqrt);
        double call_price = S * Math.exp(-adj_div_yield * time) * N(d1) - X * Math.exp(-r * time) * N(d2);
        return call_price;
    }

    public static double option_price_asian_geometric_average_price_put(double S, double X, double r, double q, double sigma, double time) {
        double sigma_sqr = Math.pow(sigma, 2);
        double adj_div_yield = 0.5 * (r + q + sigma_sqr);
        double adj_sigma = sigma / Math.sqrt(3.0);
        double adj_sigma_sqr = Math.pow(adj_sigma, 2);
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / X) + (r - adj_div_yield + 0.5 * adj_sigma_sqr) * time) / (adj_sigma * time_sqrt);
        double d2 = d1 - (adj_sigma * time_sqrt);
        double call_price = X * Math.exp(-r * time) * N(-d2) - S * Math.exp(-adj_div_yield * time) * N(-d1);
        return call_price;
    }

    public static double option_price_european_lookback_call(double S, double Smin, double r, double q, double sigma, double time) {
        if (r == q) {
            return 0;
        }
        double sigma_sqr = sigma * sigma;
        double time_sqrt = Math.sqrt(time);
        double a1 = (Math.log(S / Smin) + (r - q + sigma_sqr / 2.0) * time) / (sigma * time_sqrt);
        double a2 = a1 - sigma * time_sqrt;
        double a3 = (Math.log(S / Smin) + (-r + q + sigma_sqr / 2.0) * time) / (sigma * time_sqrt);
        double Y1 = 2.0 * (r - q - sigma_sqr / 2.0) * Math.log(S / Smin) / sigma_sqr;
        return S * Math.exp(-q * time) * N(a1) - S * Math.exp(-q * time) * (sigma_sqr / (2.0 * (r - q))) * N(-a1)
                - Smin * Math.exp(-r * time) * (N(a2) - (sigma_sqr / (2 * (r - q))) * Math.exp(Y1) * N(-a3));
    }

    public static double option_price_european_lookback_put(double S, double Smax, double r, double q, double sigma, double time) {
        if (r == q) {
            return 0;
        }
        double sigma_sqr = sigma * sigma;
        double time_sqrt = Math.sqrt(time);
        double b1 = (Math.log(S / Smax) + (-r + q + sigma_sqr / 2.0) * time) / (sigma * time_sqrt);
        double b2 = b1 - sigma * time_sqrt;
        double b3 = (Math.log(S / Smax) + (r - q - sigma_sqr / 2.0) * time) / (sigma * time_sqrt);
        double Y2 = (2.0 * (r - q - sigma_sqr / 2.0) * Math.log(Smax / S)) / sigma_sqr;
        double p
                = Smax * Math.exp(-r * time) * (N(b1) - (sigma_sqr / (2 * (r - q))) * Math.exp(Y2) * N(-b3))
                + S * Math.exp(-q * time) * (sigma_sqr / (2.0 * (r - q))) * N(-b2)
                - S * Math.exp(-q * time) * N(b2);
        return p;
    }

    public static double option_price_european_cash_or_nothing_call(double S, double K, double r, double q, double sigma, double time, double cashQ) {
        if (r == q) {
            return 0;
        }
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
        return cashQ * Math.exp(-r * time) * N(d2);
    }

    public static double option_price_european_cash_or_nothing_put(double S, double K, double r, double q, double sigma, double time, double cashQ) {
        if (r == q) {
            return 0;
        }
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        double d2 = d1 - (sigma * time_sqrt);
//        double c = option_price_european_cash_or_nothing_call(S, K, r, q, sigma, time, cashQ);
        // Using call-put parity for european options
        //  return Math.max(c - S + K*Math.exp(-r*time),0.0);
        return cashQ * Math.exp(-r * time) * (1 - N(d2));
    }

    public static double option_price_european_asset_or_nothing_call(double S, double K, double r, double q, double sigma, double time) {
        if (r == q) {
            return 0;
        }
        double time_sqrt = Math.sqrt(time);
        double d1 = (Math.log(S / K) + (r - q) * time) / (sigma * time_sqrt) + 0.5 * sigma * time_sqrt;
        return S * N(d1);
    }

    public static double option_price_call_european_binomial(double S, double X, double r, double y, double sigma, double t, int steps) {    // no steps in binomial tree
        double R = Math.exp(r * (t / steps));            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(t / steps));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - y) * (t / steps)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        prices[0] = S * Math.pow(d, steps);
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1]; // fill in the endnodes.
        }
        double[] call_values = new double[steps + 1];       // value of corresponding call
        for (int i = 0; i <= steps; ++i) {
            call_values[i] = Math.max(0.0, (prices[i] - X)); // call payoffs at maturity
        }
        for (int step = steps - 1; step >= 0; --step) {
            for (int i = 0; i <= step; ++i) {
                call_values[i] = (p_up * call_values[i + 1] + p_down * call_values[i]) * Rinv;
            }
        }
        return call_values[0];
    }

    public static double option_price_put_european_binomial(double S, double X, double r, double y, double sigma, double t, int steps) {  // no steps in binomial tree
        double R = Math.exp(r * (t / steps));            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(t / steps));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - y) * (t / steps)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        double[] put_values = new double[steps + 1];       // value of corresponding put

        prices[0] = S * Math.pow(d, steps);  // fill in the endnodes.
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1];
        }
        for (int i = 0; i <= steps; ++i) {
            put_values[i] = Math.max(0.0, (X - prices[i])); // put payoffs at maturity
        }
        for (int step = steps - 1; step >= 0; --step) {
            for (int i = 0; i <= step; ++i) {
                put_values[i] = (p_up * put_values[i + 1] + p_down * put_values[i]) * Rinv;
            }
        }
        return put_values[0];
    }

    public static double option_price_call_american_binomial(double S, double X, double r, double y, double sigma, double t, int steps) {    // no steps in binomial tree
        double R = Math.exp(r * (t / steps));            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(t / steps));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - y) * (t / steps)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        prices[0] = S * Math.pow(d, steps);
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1]; // fill in the endnodes.
        }
        double[] call_values = new double[steps + 1];       // value of corresponding call
        for (int i = 0; i <= steps; ++i) {
            call_values[i] = Math.max(0.0, (prices[i] - X)); // call payoffs at maturity
        }
        for (int step = steps - 1; step >= 0; --step) {
            for (int i = 0; i <= step; ++i) {
                call_values[i] = (p_up * call_values[i + 1] + p_down * call_values[i]) * Rinv;
                prices[i] = d * prices[i + 1];
                double aux = prices[i] - X;
                call_values[i] = Math.max(call_values[i], prices[i] - X);       // check for exercise
            }
        }
        return call_values[0];
    }

    public static double put_american_binomial(double S, double X, double r, double y, double sigma, double t, int steps) {  // no steps in binomial tree
        double R = Math.exp(r * (t / steps));            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(t / steps));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - y) * (t / steps)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        double[] put_values = new double[steps + 1];       // value of corresponding put

        prices[0] = S * Math.pow(d, steps);  // fill in the endnodes.
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1];
        }
        for (int i = 0; i <= steps; ++i) {
            put_values[i] = Math.max(0.0, (X - prices[i])); // put payoffs at maturity
        }
        for (int step = steps - 1; step >= 0; --step) {
            for (int i = 0; i <= step; ++i) {
                put_values[i] = (p_up * put_values[i + 1] + p_down * put_values[i]) * Rinv;
                prices[i] = d * prices[i + 1];
                put_values[i] = Math.max(put_values[i], (X - prices[i]));       // check for exercise
            }
        }
        return put_values[0];
    }

    public static double option_price_call_bermudan_binomial(double S,
            double X, // exercice price
            double r, // interest rate
            double q, // continous payout
            double sigma, // volatility
            double time, // time to maturity
            int number_ex_times,
            int steps) {    // no steps in binomial tree
        double delta_t = time / steps;
        double R = Math.exp(r * delta_t);            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(delta_t));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - q) * (delta_t)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        double[] put_values = new double[steps + 1];       // value of corresponding call

        double[] potential_exercise_times = new double[steps + 1]; // create list of steps at which exercise may happen
        for (int i = 0; i <= number_ex_times; ++i) {
            double t = (1.0 * i) / (1.0 * number_ex_times);
            if ((t > 0.0) && (t < time)) {
                potential_exercise_times[i] = t;
            }
        }
        prices[0] = S * Math.pow(d, steps);  // fill in the endnodes.
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1];
        }
        for (int i = 0; i <= steps; ++i) {
            put_values[i] = Math.max(0.0, (prices[i] - X));
        }
        for (int step = steps - 1; step >= 0; --step) {
            boolean check_exercise_this_step = false;
            for (int j = 0; j <= number_ex_times; ++j) {
                double tt = (1.0 * step) / (1.0 * steps);
                if (tt == potential_exercise_times[j]) {
                    check_exercise_this_step = true;
                }
            }
            for (int i = 0; i <= step; ++i) {
                put_values[i] = (p_up * put_values[i + 1] + p_down * put_values[i]) * Rinv;
                prices[i] = d * prices[i + 1];
                // check if there is potential for exercise at this step
                if (check_exercise_this_step) {
                    put_values[i] = Math.max(put_values[i], prices[i] - X);
                }
            }
        }
        return put_values[0];
    }

    public static double option_price_put_bermudan_binomial(double S,
            double X, // exercice price
            double r, // interest rate
            double q, // continous payout
            double sigma, // volatility
            double time, // time to maturity
            int number_ex_times,
            int steps) {    // no steps in binomial tree
        double delta_t = time / steps;
        double R = Math.exp(r * delta_t);            // interest rate for each step
        double Rinv = 1.0 / R;                    // inverse of interest rate
        double u = Math.exp(sigma * Math.sqrt(delta_t));    // up movement
        double uu = u * u;
        double d = 1.0 / u;
        double p_up = (Math.exp((r - q) * (delta_t)) - d) / (u - d);
        double p_down = 1.0 - p_up;
        double[] prices = new double[steps + 1];       // price of underlying
        double[] put_values = new double[steps + 1];       // value of corresponding call

        double[] potential_exercise_times = new double[steps + 1]; // create list of steps at which exercise may happen
        for (int i = 0; i <= number_ex_times; ++i) {
            double t = (1.0 * i) / (1.0 * number_ex_times);
            if ((t > 0.0) && (t < time)) {
                potential_exercise_times[i] = t;
            }
        }
        prices[0] = S * Math.pow(d, steps);  // fill in the endnodes.
        for (int i = 1; i <= steps; ++i) {
            prices[i] = uu * prices[i - 1];
        }
        for (int i = 0; i <= steps; ++i) {
            put_values[i] = Math.max(0.0, (X - prices[i]));
        }
        for (int step = steps - 1; step >= 0; --step) {
            boolean check_exercise_this_step = false;
            for (int j = 0; j <= number_ex_times; ++j) {
                double tt = (1.0 * step) / (1.0 * steps);
                if (tt == potential_exercise_times[j]) {
                    check_exercise_this_step = true;
                }
            }
            for (int i = 0; i <= step; ++i) {
                put_values[i] = (p_up * put_values[i + 1] + p_down * put_values[i]) * Rinv;
                prices[i] = d * prices[i + 1];
                // check if there is potential for exercise at this step
                if (check_exercise_this_step) {
                    put_values[i] = Math.max(put_values[i], X - prices[i]);
                }
            }
        }
        return put_values[0];
    }
}
