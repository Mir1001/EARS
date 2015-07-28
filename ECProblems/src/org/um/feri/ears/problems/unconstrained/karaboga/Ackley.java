package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Ackley extends Problem {

	public Ackley(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*32);
		Arrays.fill(intervalL, -32);
		name = "Ackley";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double sphere_sum=0;
		double cos_sum=0;
		for (int i=0; i<dim; i++){
			sphere_sum += x[i]*x[i];
			cos_sum    += Math.cos(2*Math.PI*x[i]);
		}
		v = -20*Math.exp(-0.2*Math.sqrt(1.0/dim * sphere_sum)) - Math.exp(1.0/dim * cos_sum) + 20.0 + Math.E;
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
