package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RastriginRotated;
import org.um.feri.ears.problems.unconstrained.cec2010.base.RastriginShifted;

public class F10 extends Problem {
	
	int[] P;
	int m;
	public double[][] rot_matrix;
	
	// F10 CEC 2010
	// D/2m-group Shifted and m-rotated Rastrigin's Function
	public F10(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 10);
		Arrays.fill(intervalL, -5);
		name = "F10 D/2m-group Shifted and m-rotated Rastrigin's Function";
		
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
		for (int k=0; k<dim/(2*m); k++){
			F = F + rastrigin_rotated.eval(x,P,k*m+1,(k+1)*m,rot_matrix);
		}
		F = F + rastrigin_shifted.eval(x,P,dim/2,dim);
		
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