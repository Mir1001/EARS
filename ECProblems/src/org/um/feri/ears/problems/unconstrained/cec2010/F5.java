package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RastriginRotated;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RastriginShifted;

public class F5 extends Problem {
	
	int[] P;
	int m;
	public double[][] rot_matrix;
	
	// F5 CEC 2010
	// Single-group Shifted and m-rotated Rastrigin's Function
	public F5(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 10);
		Arrays.fill(intervalL, -5);
		name = "F05 Single-group Shifted and m-rotated Rastrigin's Function";
		
		P = new int[dim];
		Random rand = new Random();
		int rand_place = 0;
		for (int i=dim-1; i>0; i--){
			rand_place = rand.nextInt(dim);
			P[i] = rand_place;			
		}
		
		m = 2;
		
		rot_matrix = new double[dim][dim];
		
		//Random rand = new Random();
		//DenseMatrix64F A = RandomMatrices.createOrthogonal(dim, dim, rand);
		
		for (int i=0; i<dim; i++){
			for (int j=0; j<dim; j++){
				if (i==j)rot_matrix[i][j] = 1;
				else rot_matrix[i][j] = 0;
			}
		}
	}
	
	public double eval(double x[]) {
		double F = 0;
		RastriginShifted rastrigin_shifted = new RastriginShifted(dim);
		RastriginRotated rastrigin_rotated= new RastriginRotated(dim);
		F = rastrigin_rotated.eval(x,P,0,m, rot_matrix)*1000000 + rastrigin_shifted.eval(x,P,m+1,dim);
		
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