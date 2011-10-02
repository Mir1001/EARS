package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class ProblemRastrigin  extends Problem{

	public ProblemRastrigin(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 10.24);
		Arrays.fill(intervalL, -5.12);
		name = "Rastrigin";
	}
	
	public ProblemRastrigin(int d, double j) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, j*2);
		Arrays.fill(intervalL, j*-1);
		name = "Rastrigin";
	}

	public double eval(double x[]) {
		double v=0;
		for (int i=0; i<(dim); i++) {
			//10án+sum(x(i)^2-10ácos(2ápiáx(i)))
			v+=(x[i]*x[i]-10*Math.cos(2*Math.PI*x[i]));
			//v+=Math.pow(1-ds[i],2)+100*Math.pow(ds[i+1]-ds[i]*ds[i],2);
		}
		return 10*dim+v;
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
