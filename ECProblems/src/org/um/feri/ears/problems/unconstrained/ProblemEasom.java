package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemEasom  extends Problem{

	public ProblemEasom() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "Easom";
	}
	//fEaso(x1,x2)=-cos(x1)ácos(x2)áexp(-((x1-pi)^2+(x2-pi)^2))
	public double eval(double x[]) {
		double v=
			-Math.cos(x[0])*Math.cos(x[1])*Math.exp(-Math.pow((x[0]-Math.PI),2)-Math.pow(x[1]-Math.PI,2));
		return v;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], Math.PI); 
		return v;
	}
	public double getOptimumEval() {
		return -1.;
	}
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
}
