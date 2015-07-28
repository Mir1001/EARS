package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Booth extends Problem {
	
	public double[][] a;
	
	public Booth(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*10);
		Arrays.fill(intervalL, -10);
		name = "Booth";
		characteristic = "MS";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = Math.pow(x[0] + 2*x[1] - 7, 2)
		  + Math.pow(2*x[0] + x[1] - 5, 2);
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
