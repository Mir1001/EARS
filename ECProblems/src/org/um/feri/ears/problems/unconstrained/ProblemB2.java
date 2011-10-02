package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemB2  extends Problem{
		public ProblemB2() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -50);
		Arrays.fill(interval, 100);
		name ="B2 function";
	}
	

	
	public double eval(double x[]) {
		double v = x[0]*x[0] + 2*x[1]*x[1] - 0.3*Math.cos(3*Math.PI*x[0])-0.4*Math.cos(4*Math.PI*x[1]) + 0.7;
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}

	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
