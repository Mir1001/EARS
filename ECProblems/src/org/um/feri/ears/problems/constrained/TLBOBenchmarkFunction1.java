package org.um.feri.ears.problems.constrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;


public class TLBOBenchmarkFunction1 extends Problem {
	// http://www-optima.amp.i.kyoto-u.ac.jp/member/student/hedar/Hedar_files/TestGO_files/Page506.htm
	public final static double best_x[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3,
			1 };

	public TLBOBenchmarkFunction1() {
		minimum = true;
		constrains = 9; //9;//6;
		max_constrains = new double[constrains];
		min_constrains = new double[constrains];
		count_constrains  = new double[constrains];
		sum_constrains  = new double[constrains];
		normalization_constrains_factor = new double[constrains];
		dim = 13;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, 0, 9, 0.); // i=0-8=1
		Arrays.fill(interval, 0, 9, 1.); // i=0-8=1
		Arrays.fill(intervalL, 9, 12, 0.);
		Arrays.fill(interval, 9, 12, 100.);
		Arrays.fill(intervalL, 12, 13, 0.);
		Arrays.fill(interval, 12, 13, 1.);
		name = "TLBOBenchmarkFunction1 (TP7) cec-g01";
		// System.out.println(Arrays.toString(interval)+"\n"+Arrays.toString(intervalL));
	}



	public double eval(double x[]) {
		double v = 0;
		v = 5.0 * (x[0] + x[1] + x[2] + x[3]) - 5.0
				* (x[0] * x[0] + x[1] * x[1] + x[2] * x[2] + x[3] * x[3]);
		for (int j = 4; j < 13; j++) {
			v = v - x[j];
		}
		return v;
	}

	
	public double[] calc_constrains(double x[]) {

		double[] g = new double[constrains];
		g[0] = 2.0 * x[0] + 2.0 * x[1] + x[9] + x[10] - 10.;
		g[1] = 2.0 * x[0] + 2.0 * x[2] + x[9] + x[11] - 10.;
		g[2] = 2.0 * x[1] + 2.0 * x[2] + x[10] + x[11] - 10.;
		g[3] = -8.0 * x[0] + x[9];
		g[4] = -8.0 * x[1] + x[10];
		g[5] = -8.0 * x[2] + x[11];
		g[6] = -2.0 * x[3] - x[4] + x[9];
		g[7] = -2.0 * x[5] - x[6] + x[10];
		g[8] = -2.0 * x[7] - x[8] + x[11];
		return g;
	}
	
	
	public double[] calc_constrains2(double x[]) {

		double[] g = new double[constrains];
		g[0] = 2.0 * x[0] + 2.0 * x[1] + x[9] + x[10] - 10.;
		g[1] = 2.0 * x[0] + 2.0 * x[2] + x[9] + x[11] - 10.;
		g[2] = 2.0 * x[1] + 2.0 * x[2] + x[10] + x[11] - 10.;
		g[3] = -2.0 * x[3] - x[4] + x[9];
		g[4] = -2.0 * x[5] - x[6] + x[10];
		g[5] = -2.0 * x[7] - x[8] + x[11];
		return g;
	}


	public double getOptimumEval() {
		return -15;
	}
	
}
