package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Rosenbrock extends Problem {
	public Rosenbrock(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*30);
		Arrays.fill(intervalL, -30);
		name = "Rosenbrock";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		for (int i = 0; i < dim-1; i++) {
			v = v + (100*Math.pow(x[i+1]-x[i]*x[i], 2) + Math.pow(x[i]-1,2));
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
