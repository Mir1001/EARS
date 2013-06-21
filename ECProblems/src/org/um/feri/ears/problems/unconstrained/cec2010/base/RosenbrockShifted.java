package org.um.feri.ears.problems.unconstrained.cec2010.base;


public class RosenbrockShifted{
	
	public int dim;
	public double[] shifted_optimum;

	public RosenbrockShifted(int d) {
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 0.75;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end) {
		double F=0;
		double[] z = new double[dim];
		int j,k;
		for (int i=start; i<end-1; i++) {
			j = P[i];
			k = P[i+1];
			z[i] = x[j] - shifted_optimum[j];
			z[i+1] = x[k] - shifted_optimum[k];
			F += 100.0*(z[i+1]-z[i]*z[i])*(z[i+1]-z[i]*z[i]) + (1.0-z[i])*(1.0-z[i]);
		}
		
		return F;
	}
}