package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Easom extends Problem {
	
	public Easom(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*100);
		Arrays.fill(intervalL, -100);
		name = "Easom";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = -1*Math.cos(x[0])*Math.cos(x[1])*Math.exp(-1*Math.pow(x[0]-Math.PI, 2)-Math.pow(x[1]-Math.PI, 2));
		return v;
	}

	public double getOptimumEval() {
		return -1;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
