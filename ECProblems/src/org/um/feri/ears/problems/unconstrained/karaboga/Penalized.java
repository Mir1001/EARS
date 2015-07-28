package org.um.feri.ears.problems.unconstrained.karaboga;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class Penalized extends Problem {

	public Penalized(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 2*50);
		Arrays.fill(intervalL, -50);
		name = "Penalized";
		characteristic = "MN";
	}
	
	public double eval(double x[]) {
		double v = 0;
		double v_1=0,v_2=0,v_3=0,v_4=0;
		double[] y = new double[dim];
		for (int i=0; i<dim; i++){
			y[i] = 1 + (1.0/4.0)*(x[i]+1);
		}
		v_1 = 10*Math.pow(Math.sin(Math.PI*y[0]),2);
		for (int i=0; i<dim-1; i++){
			v_2 = v_2 + Math.pow(y[i]-1, 2)*(1+10*Math.pow(Math.sin(Math.PI*y[i+1]),2));
		}
		v_3 = Math.pow(y[dim-1]-1,2);
		for (int i=0; i<dim; i++){
			v_4 = v_4 + u(x[i],10,100,4);
		}
		v = Math.PI/dim*(v_1  + v_2  + v_3) + v_4;
		return v;
	}
	
	private double u(double x, double a, double k, double m){
		if (x>a) return  k*Math.pow(x-a,m);
		else if (x<a && x>-1*a) return 0;
		else  return  k*Math.pow(-1*x-a,m);
	}

	public double getOptimumEval() {
		return 0;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return (Math.abs(eval_x - getOptimumEval()) < (Math.abs(eval_y - getOptimumEval())));
	}

}
