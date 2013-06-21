package org.um.feri.ears.problems.unconstrained.cec2010.base;

public class AckleyRotated{
	
	public int dim;
	public double[] shifted_optimum;
	
	public AckleyRotated(int d){
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 0.43;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end, double[][] rot_matrix) {
		double F = 0;
		double[] z = new double[dim];
		int j;
		for (int i=start; i<end; i++){
			j = P[i];
			z[i] = x[j] - shifted_optimum[j];
		}
		z = multiply(z, rot_matrix, start, end);
		
		double sphere_sum=0;
		double cos_sum=0;
		
		for (int i=start; i<end; i++){
			sphere_sum += z[i]*z[i];
			cos_sum    += Math.cos(2*Math.PI*z[i]);
		}
		F = -20*Math.exp(-0.2*Math.sqrt(1.0/(end-start) * sphere_sum)) - Math.exp(1.0/(end-start) * cos_sum) + 20.0 + Math.E;
		
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