package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemRosenbrockD2b  extends Problem{
// Rosenbrock
	//http://www.geatbx.com/docu/fcnindex-01.html#P86_3059
	public ProblemRosenbrockD2b() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -10);
		Arrays.fill(interval, 20);
		name = "Rosenbrock d2b";
	}
	public double eval(double x[]) {
		double v=0;
		for (int i=0; i<(dim-1); i++) {
			v+=100*(x[i+1]-x[i]*x[i])*(x[i+1]-x[i]*x[i])+(1-x[i])*(1-x[i]);
			//v+=Math.pow(1-ds[i],2)+100*Math.pow(ds[i+1]-ds[i]*ds[i],2);
		}
		return v;
	}
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], 1); 
		return v;
	}
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
}
