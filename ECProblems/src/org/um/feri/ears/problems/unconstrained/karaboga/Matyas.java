package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Matyas extends Problem {
	
	public Matyas(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*10);
		Arrays.fill(intervalL, -10);
		name = "Matyas";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = 0.26*(Math.pow(x[0],2) + Math.pow(x[1],2)) - 0.48*x[0]*x[1];
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
