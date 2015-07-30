package org.um.feri.ears.problems.realworld.cec2011;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.EllipticShifted;
import org.um.feri.ears.util.Util;

/**
 * Problem function!
 * 
 * @author Matej Črepinšek
 * @version 1
 * 
 **/	
public class CEC2011_Problem1 extends Problem {
	/*
	 * fun_num=1   Parameter Estimation for Frequency-Modulated (FM) Sound Waves,initialization range=[0,6.35], bound=[-6.4,6.35] , length of x=6. 
	 * 
	 */
	public CEC2011_Problem1() {
		dim = 6;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(interval, 12.75);
		Arrays.fill(intervalL, -6.4);//6.4 + 6.35
		name = "RWP_1";
		desc = "RWP_1 Parameter Estimation for Frequency-Modulated (FM) Sound Waves";
	}
	
	public double eval(double x[]) {
	      double theta=2.*Math.PI/100;
	      double f=0;
	      double y_t, y_0_t;
	        for (int t=0; t<=100; t++){
	            y_t=x[0]*Math.sin(x[1]*t*theta+x[2]*Math.sin(x[3]*t*theta+x[4]*Math.sin(x[5]*t*theta)));
	            y_0_t=1*Math.sin(5*t*theta-1.5*Math.sin(4.8*t*theta+2*Math.sin(4.9*t*theta)));
	            f=f+(y_t-y_0_t)*(y_t-y_0_t);
	        }
		return f;
	}

	public double getOptimumEval() {
		return 0; //OK
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x < eval_y;
	}
	
	@Override
	public double[] getRandomInitVectorX() {
		//initialization range=[0,6.35]
		double[] sol=new double[dim];
		for (int j = 0; j < dim; j++) {
			sol[j] = Util.rnd.nextDouble() * 6.35;
		}
		return sol;
	}
	

}