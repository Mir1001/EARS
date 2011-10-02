package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemBooth  extends Problem{
	public ProblemBooth() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -50);
		Arrays.fill(interval, 100);
		name ="Booth function";
	}
	

	
	public double eval(double x[]) {
		double v = Math.pow(x[0]+2*x[1]-7,2)+Math.pow(2*x[0]+x[1]-5,2);
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim]; 
		v[0][0] = 1; 
		v[0][1] = 3;
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
