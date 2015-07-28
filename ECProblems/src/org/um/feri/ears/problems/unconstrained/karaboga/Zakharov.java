package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Zakharov extends Problem {
	
	public Zakharov(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 15);
		Arrays.fill(intervalL, -5);
		name = "Zakharov";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0, v_2 = 0, v_3 = 0;
		for (int i = 0; i < dim; i++) {
			v_1 = v_1 + Math.pow(x[i],2);
		}
		for (int i = 0; i < dim; i++) {
			v_2 = v_2 + 0.5*(i+1)*x[i];
		}
		v_2 = Math.pow(v_2,2);
		for (int i = 0; i < dim; i++) {
			v_3 = v_3 + 0.5*(i+1)*x[i];
		}
		v_3 = Math.pow(v_3,4);
		v = v_1 + v_2 + v_3;
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
