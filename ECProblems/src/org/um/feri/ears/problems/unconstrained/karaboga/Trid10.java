package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Trid10 extends Problem {
	
	public Trid10(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*d*d);
		Arrays.fill(intervalL, -1*d*d);
		name = "Trid10";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0, v_2 = 0;
		for (int i = 0; i < dim; i++) {
			v_1 = v_1 + Math.pow((x[i]-1),2);
		}
		for (int i = 1; i < dim; i++) {
			v_2 = v_2 + x[i]*x[i-1];
		}
		v = v_1 - v_2;
		return v;
	}

	public double getOptimumEval() {
		return -210;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
