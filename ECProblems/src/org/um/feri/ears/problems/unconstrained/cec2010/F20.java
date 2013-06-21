package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RosenbrockShifted;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F20 extends Problem {
	
	int[] P;
	RosenbrockShifted rosenbrock_shifted;
	
	// F20 CEC 2010
	// Shifted Rosenbrock's Function
	public F20(int d) {
		dim = d;
		rosenbrock_shifted = new RosenbrockShifted(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F20 Shifted Rosenbrock's Function";
		
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
		F = rosenbrock_shifted.eval(x, P, 0, dim);
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