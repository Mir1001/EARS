package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Schwefel_2_22 extends Problem {
	public Schwefel_2_22(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*10);
		Arrays.fill(intervalL, -10);
		name = "Schwefel_2_22";
		characteristic = "UN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0, m_1 = 1;
		for (int i = 0; i < dim; i++) {
			v_1 = v_1 + Math.abs(x[i]);
			m_1 = m_1 * Math.abs(x[i]);
		}
		v = v_1 + m_1;
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
