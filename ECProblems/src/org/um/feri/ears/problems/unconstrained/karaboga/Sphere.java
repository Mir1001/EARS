package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Sphere extends Problem {
	public Sphere(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*100);
		Arrays.fill(intervalL, -100);
		name = "Sphere";
		characteristic = "US";
	}
	
	public double eval(double x[]) {
		double v = 0;
		for (int i = 0; i < dim; i++) {
			v = v + Math.pow(x[i],2);
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
