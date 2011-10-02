package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemSphere extends Problem {
	
	public ProblemSphere(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "Sphere";
	}
	
	public ProblemSphere(int d, double intervalRight) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(intervalL, intervalRight*-1);
		Arrays.fill(interval, intervalRight*2);
		name = "Sphere";
	}
	
	public double eval(double ds[]) {
		double v=0;
		for (int i=0; i<dim; i++) {
			v+=ds[i]*ds[i];
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
