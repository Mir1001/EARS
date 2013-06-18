package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RosenbrockShifted;
import org.um.feri.ears.problems.unconstrained.cec2010.base.SphereShifted;

public class F8 extends Problem {
	
	int[] P;
	int m;
	
	// F8 CEC 2010
	// Single-group Shifted m-dimensional Rosenbrock's Function
	public F8(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F08 Single-group Shifted m-dimensional Rosenbrock's Function";
		
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
		RosenbrockShifted rosenbrock_shifted = new RosenbrockShifted(dim);
		SphereShifted sphere_shifted= new SphereShifted(dim);
		F = rosenbrock_shifted.eval(x,P,0,m)*1000000 + sphere_shifted.eval(x,P,m+1,dim);
		
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