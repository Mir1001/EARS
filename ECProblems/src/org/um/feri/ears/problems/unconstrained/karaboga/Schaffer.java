package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Schaffer extends Problem {
	public Schaffer(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*100);
		Arrays.fill(intervalL, -100);
		name = "Schaffer";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = 0.5 + (Math.pow(Math.sin(Math.sqrt(x[0]*x[0] + x[1]*x[1])),2) - 0.5)/(Math.pow(1+0.001*(x[0]*x[0] + x[1]*x[1]),2));
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
