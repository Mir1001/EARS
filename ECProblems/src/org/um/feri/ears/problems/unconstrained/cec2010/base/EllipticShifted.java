package org.um.feri.ears.problems.unconstrained.cec2010.base;


public class EllipticShifted{
	
	public int dim;
	public double[] shifted_optimum;
	
	public EllipticShifted(int d) {
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 2.7;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end) {
		double F = 0;
		
		double[] z = new double[dim];
		double k=1.0;
		for (int i=start; i<end; i++){
			int j = P[i];
			z[i] = x[j] - shifted_optimum[j];	
			F += Math.pow(1000000, (k-1.0)/((end-start)-1.0))*z[i]*z[i];
			k = k + 1.0;
		}
		
		return F;
	}

}