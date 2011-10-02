package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemGriewank  extends Problem{

	public ProblemGriewank(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(intervalL, -50);
		Arrays.fill(interval, 100);
		name ="Griewank";
	}
	public ProblemGriewank(int d, double i) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(intervalL, -i);
		Arrays.fill(interval, i*2);
		name ="Griewank";
	}
	
	public double eval(double x[]) {
		double v=0;
		double a = 0; 
		double b = 1;
		for (int i=0; i<dim;i++) {
			  a += x[i]*x[i];
			  b *= Math.cos(x[i]/Math.sqrt(i+1));
			}
		v = a/4000.- b+1;		
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
