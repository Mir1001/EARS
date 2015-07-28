package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Powell extends Problem {
	
	public Powell(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 9);
		Arrays.fill(intervalL, -4);
		name = "Powell";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		int k = 4;
		for (int i = 0; i < dim/k; i++) {
			v = v
				+ Math.pow(x[4*(i+1)-3-1] + 10*x[4*(i+1)-2-1],2)
				+ 5*Math.pow(x[4*(i+1)-1-1] - x[4*(i+1)-1],2)
				+ Math.pow(x[4*(i+1)-2-1] - x[4*(i+1)-1-1],4)
				+ Math.pow(x[4*(i+1)-3-1] - x[4*(i+1)-1],4);
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
