package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Shubert extends Problem {
	public Shubert(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*10);
		Arrays.fill(intervalL, -10);
		name = "Shubert";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double m1 = 0, m2 = 0;
		for (int i = 0; i < 5; i++) {
			m1 = m1 + (i+1)*Math.cos(((i+1)+1)*x[0]+(i+1));
			m2 = m2 + (i+1)*Math.cos(((i+1)+1)*x[1]+(i+1));
		}
		v = m1*m2;
		return v;
	}

	public double getOptimumEval() {
		return -186.73;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
