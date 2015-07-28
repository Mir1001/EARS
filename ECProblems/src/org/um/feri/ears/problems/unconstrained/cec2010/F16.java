package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.RandomMatrices;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.AckleyRotated;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F16 extends Problem {
	
	int[] P;
	int m;
	public double[][] rot_matrix;
	AckleyRotated ackley_rotated;
	
	// F16 CEC 2010
	// D/m-group Shifted and m-rotated Ackley's Function
	public F16(int d) {
		dim = d;
		ackley_rotated = new AckleyRotated(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 64);
		Arrays.fill(intervalL, -32);
		name = "F16 D/m-group Shifted and m-rotated Ackley's Function";
		
		P = new int[dim];
		Random rand = new Random();
		int rand_place = 0;
		for (int i=dim-1; i>0; i--){
			rand_place = rand.nextInt(dim);
			P[i] = rand_place;			
		}
		
		m = 2;
		
		rot_matrix = new double[m][m];
		
		Random rand1 = new Random();
		DenseMatrix64F A = RandomMatrices.createOrthogonal(m, m, rand1);
		
		for (int i=0; i<m; i++){
			for (int j=0; j<m; j++){
				rot_matrix[i][j] = A.get(i, j);
			}
		}
	}
	
	public double eval(double x[]) {
		double F = 0;
		for (int k=0; k<dim/m; k++){
			F = F + ackley_rotated.eval(x,P,k*m+1,(k+1)*m, rot_matrix);
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