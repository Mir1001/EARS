package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Hartman6 extends Problem {
	
	public double[][] a;
	public double[][] p;
	public double[] c;
	
	public Hartman6(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 1);
		Arrays.fill(intervalL, 0);
		name = "Hartman6";
		characteristic = "MN";
		a = new double[][] {
				{10,3,17,3.5,1.7,8},
				{0.05,10,17,0.1,8,14},
				{3,3.5,1.7,10,17,8},
				{17,8,0.05,10,0.1,14}
		};
		p = new double[][] {
				{0.1312, 0.1696, 0.5569, 0.0124, 0.8283, 0.5886},	
				{0.2329, 0.4135, 0.8307, 0.3736, 0.1004, 0.9991},	
				{0.2348, 0.1415, 0.3522, 0.2883, 0.3047, 0.6650},	
				{0.4047, 0.8828, 0.8732, 0.5743, 0.1091, 0.0381}
		};
		c = new double[] {1, 1.2, 3, 3.2};

	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1 = 0;
		for (int i = 0; i < 4; i++){
			v_1 = 0;
			for (int j = 0; j < dim; j++){
			   v_1 = v_1 + a[i][j]*Math.pow(x[j]-p[i][j],2);
			}
			v = v + c[i]*Math.exp(v_1*(-1));
		}
		v = v * (-1);
		return v;
	}

	public double getOptimumEval() {
		return -3.32;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
