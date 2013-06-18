package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.EllipticRotated;
import org.um.feri.ears.problems.unconstrained.cec2010.base.EllipticShifted;

public class F4 extends Problem {
	
	int[] P;
	int m;
	public double[][] rot_matrix;
	
	// F4 CEC 2010
	// Single-group Shifted and m-rotated Elliptic Function
	public F4(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F04 Single-group Shifted and m-rotated Elliptic Function";
		
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
		EllipticShifted elliptic_shifted = new EllipticShifted(dim);
		EllipticRotated elliptic_rotated= new EllipticRotated(dim);
		F = elliptic_rotated.eval(x,P,0,m,rot_matrix)*1000000 + elliptic_shifted.eval(x,P,m+1,dim);
		
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