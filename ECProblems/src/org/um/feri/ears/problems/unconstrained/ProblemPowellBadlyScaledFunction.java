package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemPowellBadlyScaledFunction  extends Problem{

	public ProblemPowellBadlyScaledFunction() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];

		Arrays.fill(intervalL, -50);
		Arrays.fill(interval, 100);
		name ="Powell badly scaled function";

	}
	

	
	public double eval(double x[]) {
		double v = Math.pow(10.*x[0]*x[1] -1,2)+Math.pow(Math.exp(-x[0])+Math.exp(-x[1])-1.0001,2);
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		v[0][0] = 1.09815933e-5; 
		v[0][1] = 9.106146738;
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
