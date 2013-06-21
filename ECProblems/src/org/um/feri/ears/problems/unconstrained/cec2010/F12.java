package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.SchwefelShifted;
import org.um.feri.ears.problems.unconstrained.cec2010.base.SphereShifted;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F12 extends Problem {
	
	int[] P;
	int m;
	SchwefelShifted schwefel_shifted;
	SphereShifted sphere_shifted;
	
	// F12 CEC 2010
	// D/2m-group Shifted m-dimensional Schwefel's Problem 1.2
	public F12(int d) {
		dim = d;
		schwefel_shifted = new SchwefelShifted(dim);
		sphere_shifted= new SphereShifted(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F12 D/2m-group Shifted m-dimensional Schwefel's Problem 1.2";
		
		P = new int[dim];
		Random rand = new Random();
		int rand_place = 0;
		
		for (int i=0; i<dim; i++) P[i] = i;
		
		int temp;
		for (int i=dim-1; i>=0; i--){
			rand_place = rand.nextInt(dim);
			temp = P[i];
			P[i] = P[rand_place];	
			P[rand_place] = temp;
		}
		
		m = 2;
	}
	
	public double eval(double x[]) {
		double F = 0;
		for (int k=0; k<dim/(2*m); k++){
			F = F + schwefel_shifted.eval(x,P,k*m+1,(k+1)*m);
		}
		F = F + sphere_shifted.eval(x,P,dim/2,dim);
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