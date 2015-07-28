package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class PowerSum extends Problem {
	
	public double[] b;
	
	public PowerSum(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, dim);
		Arrays.fill(intervalL, 0);
		name = "PowerSum";
		characteristic = "MN";
		b = new double[] {8,18,44,114};

	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int i = 0; i < dim; i++){
			v_1 = 0;
			for (int j = 0; j < dim; j++){
			   v_1 = v_1 + Math.pow(x[j],i+1);
			}
			v = v + Math.pow(v_1 - b[i], 2);
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
