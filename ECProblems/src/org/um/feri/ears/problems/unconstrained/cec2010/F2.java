package org.um.feri.ears.problems.unconstrained.cec2010;

import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RastriginShifted;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/	

public class F2 extends Problem {
	
	int[] P;
	RastriginShifted rastrigin_shifted;
	
	// F2 CEC 2010
	// Shifted Rastrigin's Function
	public F2(int d) {
		dim = d;
		rastrigin_shifted = new RastriginShifted(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 10);
		Arrays.fill(intervalL, -5);
		name = "F02 Shifted Rastrigin's Function";
		
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
	}
	
	public double eval(double x[]) {
		double F = 0;
		F = rastrigin_shifted.eval(x,P,0,dim);
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