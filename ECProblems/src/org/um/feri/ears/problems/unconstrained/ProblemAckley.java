package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemAckley extends Problem {
	// Ackley
	// http://www-optima.amp.i.kyoto-u.ac.jp/member/student/hedar/Hedar_files/TestGO_files/Page295.htm
	public ProblemAckley(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 64);
		Arrays.fill(intervalL, -32);
		name = "Ackley";
	}

	public double eval(double x[]) {
		double v = 0;
		double a = 20;
		double b = 0.2;
		double c = 2 * Math.PI;
		double s1 = 0;
		double s2 = 0;
		for (int i = 0; i < dim; i++) {
			s1 = s1 + x[i] * x[i];
			s2 = s2 + Math.cos(c * x[i]);
		}
		v = -a * Math.exp(-b * Math.sqrt(1. / dim * s1))
				- Math.exp(1. / dim * s2) + a + Math.exp(1.);
		return v;
	}

	public double getOptimumEval() {
		return 0;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x < eval_y;
	}

}
