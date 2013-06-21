package org.um.feri.ears.problems.unconstrained.cec2010.base;


public class AckleyShifted{
	
	public int dim;
	public double[] shifted_optimum;

	public AckleyShifted(int d) {
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 0.43;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end) {
		double F=0;
		double sphere_sum=0;
		double cos_sum=0;
		double[] z = new double[dim];
		for (int i=start; i<end; i++){
			int j = P[i];
			z[i] = x[j] - shifted_optimum[j];	
			sphere_sum += z[i]*z[i];
			cos_sum    += Math.cos(2*Math.PI*z[i]);
		}
		F = -20*Math.exp(-0.2*Math.sqrt(1.0/(end-start) * sphere_sum)) - Math.exp(1.0/(end-start) * cos_sum) + 20.0 + Math.E;
		return F;
	}
}