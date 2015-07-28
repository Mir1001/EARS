package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class GoldStein_Price extends Problem {
	public GoldStein_Price(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*2);
		Arrays.fill(intervalL, -2);
		name = "GoldStein_Price";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		
		v = (1+Math.pow(x[0]+x[1]+1,2)*(19-14*x[0]+3*x[0]*x[0]-14*x[1]+6*x[0]*x[1]+3*x[1]*x[1]))*
			(30+Math.pow(2*x[0]-3*x[1],2)*(18-32*x[0]+12*x[0]*x[0]+48*x[1]-36*x[0]*x[1]+27*x[1]*x[1]));
		
		return v;
	}

	public double getOptimumEval() {
		return 3;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
