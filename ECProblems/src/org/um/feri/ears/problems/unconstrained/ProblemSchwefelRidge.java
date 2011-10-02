package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemSchwefelRidge  extends Problem{
	// Rosenbrock
	//http://www.geatbx.com/docu/fcnindex-01.html#P86_3059
	public ProblemSchwefelRidge(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 128);
		Arrays.fill(intervalL, -64);
		name = "Schwefel Rigle ("+d+")";
	}

	public double eval(double x[]) {
		double v=0;
		double v1=0;
		for (int i=0; i<(dim); i++) {
			for (int j=0; j<=i; j++) {
				v1+=x[j];
			}
			v+=v1*v1;

		}
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
