package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Branin extends Problem {
	
	public double[][] a;
	
	public Branin(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 20);
		Arrays.fill(intervalL, -5);
		name = "Branin";
		characteristic = "MS";
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = Math.pow(x[1] - (5.1/(4*Math.PI*Math.PI))*x[0]*x[0] + (5.0/Math.PI)*x[0] - 6, 2)+10*(1 - 1.0/(8.0*Math.PI))*Math.cos(x[0])+10;
		return v;
	}

	public double getOptimumEval() {
		return 0.397887;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
