package org.um.feri.ears.problems.unconstrained.cec2010.base;


public class SphereShifted{
	
	public int dim;
	public double[] shifted_optimum;

	public SphereShifted(int d) {
		dim = d;
		shifted_optimum = new double[dim];
		for (int i=0; i<dim; i++){
			shifted_optimum[i] = 3.6;
		}
	}
	
	public double eval(double x[], int[] P, int start, int end) {
		double F=0;
		int dim = x.length;
		double[] z = new double[dim];
		for (int i=start; i<end; i++){
			int j = P[i];
			z[i] = x[j] - shifted_optimum[j];	
			F+=z[i]*z[i];
		}
		return F;		
	}
}