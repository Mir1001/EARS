package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Beale extends Problem {
	
	public Beale(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*4.5);
		Arrays.fill(intervalL, -4.5);
		name = "Beale";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v =   Math.pow(1.500 - x[0] + x[0]*x[1], 2)
			+ Math.pow(2.250 - x[0] + x[0]*x[1]*x[1], 2)
			+ Math.pow(2.625 - x[0] + x[0]*x[1]*x[1]*x[1], 2);
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
