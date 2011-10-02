package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemMartinAndGaddy  extends Problem{
	public ProblemMartinAndGaddy() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, 0);
		Arrays.fill(interval, 10);
		name ="Martin And Gaddy";
	}
	

	
	public double eval(double x[]) {

		double v = Math.pow(x[0]-x[1], 2)+Math.pow((x[0]+x[1]-10.)/3.,2);
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0],5); 
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
