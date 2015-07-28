package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class SixHumpCamelBack extends Problem {
	public SixHumpCamelBack(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*5);
		Arrays.fill(intervalL, -5);
		name = "SixHumpCamelBack";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = 4*Math.pow(x[0],2)
			- 2.1*Math.pow(x[0],4)
			+ (1.0/3.0)*Math.pow(x[0],6)
			+ x[0]*x[1]
			- 4*Math.pow(x[1],2)
			+ 4*Math.pow(x[1],4);
		return v;
	}

	public double getOptimumEval() {
		return -1.03163;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
