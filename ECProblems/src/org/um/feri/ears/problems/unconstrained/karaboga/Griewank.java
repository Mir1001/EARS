package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Griewank extends Problem {

	public Griewank(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*600);
		Arrays.fill(intervalL, -600);
		name = "Griewank";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		double m_1 = 1;
		for (int i = 0; i < dim; i++){
			v_1 = v_1 + x[i]*x[i];
			m_1 = m_1 * Math.cos(x[i]/Math.sqrt(i+1));
		}
		v = (1.0/4000.0)*v_1 - m_1 + 1;
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
