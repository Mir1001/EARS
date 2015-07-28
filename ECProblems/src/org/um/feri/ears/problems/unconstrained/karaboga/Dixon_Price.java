package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Dixon_Price extends Problem {
	public Dixon_Price(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*10);
		Arrays.fill(intervalL, -10);
		name = "Dixon_Price";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		for (int i = 1; i < dim; i++) {
			v = v + (i+1)*Math.pow(2*x[i]*x[i]-x[i-1], 2);
		}
		v = Math.pow(x[0] - 1, 2) + v;
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
