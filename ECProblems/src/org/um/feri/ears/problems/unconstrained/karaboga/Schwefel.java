package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Schwefel extends Problem {
	
	public double[][] a;
	
	public Schwefel(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*500);
		Arrays.fill(intervalL, -500);
		name = "Schwefel";
		characteristic = "MS";
	}
	
	public double eval(double x[]) {
		double v = 0;
		for (int i = 0; i < dim; i++){
			v = v + (-1)*x[i]*Math.sin(Math.sqrt(Math.abs(x[i])));
		}
		return v;
	}

	public double getOptimumEval() {
		return -12569.5;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
