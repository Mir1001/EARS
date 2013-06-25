package org.um.feri.ears.problems.unconstrained.cec2010;


import java.util.Arrays;
import java.util.Random;

import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.RandomMatrices;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.unconstrained.cec2010.base.AckleyRotated;
import org.um.feri.ears.problems.unconstrained.cec2010.base.AckleyShifted;

/**
 * Problem function!
 * 
 * @author Niki Vecek
 * @version 1
 * 
 **/

public class F6 extends Problem {
	
	int[] P;
	
	int properties_multimodal;
	int properties_shifted;
	int properties_separable;
	int properties_scalable;
	int properties_rotated;
	
	int m;
	public double[][] rot_matrix;
	AckleyShifted ackley_shifted;
	AckleyRotated ackley_rotated;
	
	// F6 CEC 2010
	// Single-group Shifted and m-rotated Ackley's Function
	public F6(int d) {
		
		properties_multimodal=Problem.MULTIMODAL;
		properties_shifted=Problem.SHIFTED;
		properties_separable=Problem.NOT_SEPARABLE;
		properties_scalable=Problem.NOT_SCALABLE;
		properties_rotated=Problem.ROTATED;
		
		dim = d;
		ackley_shifted = new AckleyShifted(dim);
		ackley_rotated= new AckleyRotated(dim);
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 64);
		Arrays.fill(intervalL, -32);
		name = "F06 Single-group Shifted and m-rotated Ackley's Function";
		
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
		F = ackley_rotated.eval(x,P,0,m,rot_matrix)*1000000.0 + ackley_shifted.eval(x,P,m,dim);
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