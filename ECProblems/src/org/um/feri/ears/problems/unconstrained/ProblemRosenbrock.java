package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemRosenbrock  extends Problem{
	// Rosenbrock
	//http://www.geatbx.com/docu/fcnindex-01.html#P86_3059
	public ProblemRosenbrock(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 60);
		Arrays.fill(intervalL, -30);
		name = "Rosenbrock";
	}
	public ProblemRosenbrock(int d, double j) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*j);
		Arrays.fill(intervalL, -j);
		name = "Rosenbrock";
	}
	public double eval(double x[]) {
		double v=0;
		for (int i=0; i<(dim-1); i++) {
			v+=100*(x[i+1]-x[i]*x[i])*(x[i+1]-x[i]*x[i])+(1-x[i])*(1-x[i]);
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
