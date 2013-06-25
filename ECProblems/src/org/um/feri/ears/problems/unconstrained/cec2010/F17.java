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
	
	int properties_multimodal;
	int properties_shifted;
	int properties_separable;
	int properties_scalable;
	int properties_rotated;
	
	int m;
	SchwefelShifted schwefel_shifted;
	
	// F17 CEC 2010
	// D/m-group Shifted and m-rotated Schwefel's Problem 1.2
	public F17(int d) {
		
		properties_multimodal=Problem.UNIMODAL;
		properties_shifted=Problem.SHIFTED;
		properties_separable=Problem.NOT_SEPARABLE;
		properties_scalable=Problem.NOT_SCALABLE;
		properties_rotated=Problem.NOT_ROTATED;
		
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
		for (int k=0; k<dim/m; k++){
			F = F + schwefel_shifted.eval(x,P,k*m,(k+1)*m);
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