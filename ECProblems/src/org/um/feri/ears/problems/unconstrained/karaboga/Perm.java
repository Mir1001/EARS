package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Perm extends Problem {
	
	double beta = 0.5;
	
	public Perm(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*dim);
		Arrays.fill(intervalL, -1*dim);
		name = "Perm";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int i = 0; i < dim; i++){
			v_1 = 0;
			for (int j = 0; j < dim; j++){
				v_1 = v_1 + (Math.pow(j+1, i) + beta)*(Math.pow(x[j]/(j+1), i)-1);
			}
			v = v + Math.pow(v_1, 2);
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
