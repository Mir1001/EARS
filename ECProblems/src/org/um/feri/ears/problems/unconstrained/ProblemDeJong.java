package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemDeJong  extends Problem{
	public ProblemDeJong() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -2.048);
		Arrays.fill(interval, 4.096);
		name ="De Jong";
		this.minimum = false; //search maximum
	}
	

	
	public double eval(double x[]) {
		double v = 3905.93-100*Math.pow(x[0]*x[0] -x[1],2)-Math.pow(1-x[0],2);
		return v;
	}
	
	public double getOptimumEval() {
		return 3905.93;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], 1); 
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x>eval_y;
	}
	
}
