package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Shekel10 extends Problem {
	
	public double[][] a;
	public double[] c;
	
	public Shekel10(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 10);
		Arrays.fill(intervalL, 0);
		name = "Shekel10";
		characteristic = "MN";
		
		a = new double[][] {
				{4,4,4,4},	
				{1,1,1,1},	
				{8,8,8,8},	
				{6,6,6,6},	
				{3,7,3,7},	
				{2,9,2,9},	
				{5,5,3,3},	
				{8,1,8,1},	
				{6,2,6,2},	
				{7,3.6,7,3.6},	
		};
		c = new double[] {0.1,0.2,0.2,0.4,0.4,0.6,0.3,0.7,0.5,0.5};

	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int i = 0; i < 10; i++){
			v_1 = 0;
			for (int j=0;j<dim;j++){
				v_1 = v_1 + Math.pow(x[j]-a[i][j],2);
			}
			v = v + Math.pow(c[i] + v_1,-1);
		}
		v = v * (-1);
		return v;
	}

	public double getOptimumEval() {
		return -10.53;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
