package org.um.feri.ears.problems.unconstrained.cec2010;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.SchwefelShifted;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F17 extends Problem {
	
	int[] P;
	int m;
	SchwefelShifted schwefel_shifted;
	
	// F17 CEC 2010
	// D/m-group Shifted and m-rotated Schwefel's Problem 1.2
	public F17(int d) {
		dim = d;
		schwefel_shifted = new SchwefelShifted(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F17 D/m-group Shifted and m-dimensional Schwefel's Problem 1.2";
		
		P = new int[dim];
		Random rand = new Random();
		int rand_place = 0;
		for (int i=dim-1; i>0; i--){
			rand_place = rand.nextInt(dim);
			P[i] = rand_place;			
		}
		
		m = 2;
	}
	
	public double eval(double x[]) {
		double F = 0;
		for (int k=0; k<dim/m; k++){
			F = F + schwefel_shifted.eval(x,P,k*m+1,(k+1)*m);
		}
		return F;
	}

	public double getOptimumEval() {
		return 0;
	}

	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x < eval_y;
	}

}