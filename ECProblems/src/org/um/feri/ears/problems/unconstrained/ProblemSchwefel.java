package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemSchwefel  extends Problem{

	public ProblemSchwefel(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 1000);
		Arrays.fill(intervalL, -500);
		name = "Schwefel 2.26";
	}

	public double eval(double x[]) {
		double v=0;
		for (int i=0; i<(dim); i++) {
			v+=-x[i]*Math.sin(Math.sqrt(Math.abs(x[i])));
		}
		return v;
	}
	
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], 420.9687); 
		return v;
	}
	
	public double getOptimumEval() {
		return -12569.5;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
}
