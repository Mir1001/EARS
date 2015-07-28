package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Schwefel_1_2 extends Problem {
	public Schwefel_1_2(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*100);
		Arrays.fill(intervalL, -100);
		name = "Schwefel_1_2";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int i = 0; i < dim; i++) {
			v_1 = 0;
			for (int j = 0; j < i; j++) {
				v_1 = v_1 + x[j];
			}
			v = v + Math.pow(v_1, 2);
		}
		return v;
	}

	public double getOptimumEval() {
		return 0;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
