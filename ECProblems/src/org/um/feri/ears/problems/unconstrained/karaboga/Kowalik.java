package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Kowalik extends Problem {
	
	public double[] a;
	public double[] b;
	
	public Kowalik(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*5);
		Arrays.fill(intervalL, -5);
		name = "Kowalik";
		characteristic = "MN";
		
		a = new double[] {0.1957,0.1947,0.1735,0.1600,0.0844,0.0627,0.0456,0.0342,0.0323,0.0235,0.0246};
		b = new double[] {0.25,0.5,1,2,4,6,8,10,12,14,16};
		
	}
	
	public double eval(double x[]) {
		double v = 0;
		
		for (int i = 0; i < 11; i++){
			v = v + Math.pow(a[i] - (x[0]*((1.0/b[i])*(1.0/b[i])+(1.0/b[i])*x[1]))/((1.0/b[i])*(1.0/b[i])+(1.0/b[i])*x[2]+x[3]),2);
		}
		
		return v;
	}

	public double getOptimumEval() {
		return 0.00031;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
