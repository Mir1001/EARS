package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Quatric extends Problem {
	
	public Quatric(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*1.28);
		Arrays.fill(intervalL, -1.28);
		name = "Quatric";
		characteristic = "US";
	}
	
	public double eval(double x[]) {
		double v = 0;
		for (int i = 0; i < dim; i++) {
			v = v + (i+1)*Math.pow(x[i],4);
		}
		v = v + Math.random();
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
