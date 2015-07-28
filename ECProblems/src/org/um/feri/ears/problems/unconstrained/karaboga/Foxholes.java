package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Foxholes extends Problem {
	
	public double[][] a;
	
	public Foxholes(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*65.536);
		Arrays.fill(intervalL, -65.536);
		name = "Foxholes";
		characteristic = "MS";
		a = new double[][] {{-32,-32},
				            {-16,-32},
				            {0,-32},
				            {16,-32},
				            {32,-32},
				            {-32,-16},
				            {-16,-16},
				            {0,-16},
				            {16,-16},
				            {32,-16},
				            {-32,0},
				            {-16,0},
				            {0,0},
				            {16,0},
				            {32,0},
				            {-32,16},
				            {-16,16},
				            {0,16},
				            {16,16},
				            {32,16},
				            {-32,32},
				            {-16,32},
				            {0,32},
				            {16,32},
				            {32,32}
				           };
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int j = 0; j < 25; j++) {
			v_1 = 0;
			for (int i = 0; i < dim; i++) {
				v_1 = v_1 + 6*(x[i] - a[j][i]);
			}
			v = v + 1.0/v_1;
		}
		v = v + 1.0/500.0;
		v = Math.pow(v, -1);
		return v;
	}

	public double getOptimumEval() {
		return 0.998;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
