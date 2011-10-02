package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemBeale  extends Problem{
	public ProblemBeale() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -4.5);
		Arrays.fill(interval, 9);
		name ="Beale function";
	}
	

	
	public double eval(double x[]) {
		double y[] ={1.5, 2.25, 2.625};
		double v = 0;
		for (int i=0; i<3; i++) {
		  v+=Math.pow(y[i] - x[0]*(1-Math.pow(x[1],i+1)),2);
		}
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim]; 
		v[0][0] = 3; 
		v[0][1] = 0.5;
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
