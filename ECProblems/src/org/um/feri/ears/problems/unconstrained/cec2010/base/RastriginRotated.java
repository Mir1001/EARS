package org.um.feri.ears.problems.unconstrained.cec2010.base;


public class RastriginRotated{
	
	public int dim;
	public double[] shifted_optimum;
	
	public RastriginRotated(int d){
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 0.5;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end, double[][] rot_matrix) {
		double F = 0;
		int dim = x.length;
		double[] z = new double[dim];
		
		for (int i=start; i<end; i++){
			int j = P[i];
			z[i] = x[j] - shifted_optimum[j];
		}
		z = multiply(z, rot_matrix, start, end);
		
		for (int i=start; i<end; i++){
			F+=z[i]*z[i] - 10*Math.cos(2*Math.PI*z[i]) + 10;
		}
		
		return F;
	}
	
	public double[] multiply(double[] v, double[][] m, int start, int end){
		double newv[] = new double[dim];
		newv = v.clone();
		double sum = 0;
		int vr = 0;
		int st = 0;
		for (int j=start; j<end; j++){
			for (int i=start; i<end; i++){
				sum = sum + v[i]*m[vr][st];
				vr++;
			}
			newv[j] = sum;
			sum = 0;
			vr = 0;
			st++;
		}
		return newv;
	}

}