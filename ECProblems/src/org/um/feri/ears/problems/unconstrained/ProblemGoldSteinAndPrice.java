package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemGoldSteinAndPrice  extends Problem{
	public ProblemGoldSteinAndPrice() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -2);
		Arrays.fill(interval, 4);
		name ="Goldstein And Price";
	}
	

	
	public double eval(double x[]) {
		double a,b;
		a = 1+Math.pow(x[0]+x[1]+1,2)*(19-14*x[0]+3*x[0]*x[0]-14*x[1]+6*x[0]*x[1]+3*x[1]*x[1]);
		b = 30+Math.pow(2*x[0]-3*x[1],2)*(18-32*x[0]+12*x[0]*x[0]+48*x[1]-36*x[0]*x[1]+27*x[1]*x[1]);
		double v = a*b;
		return v;
	}
	
	public double getOptimumEval() {
		return 3;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		v[0][0] =0;
		v[0][1] =-1;
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
