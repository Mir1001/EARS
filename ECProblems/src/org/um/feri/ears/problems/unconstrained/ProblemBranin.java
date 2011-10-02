package org.um.feri.ears.problems.unconstrained;


import org.um.feri.ears.problems.Problem;

public class ProblemBranin  extends Problem{
	public ProblemBranin() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];

		intervalL[0] = -5;
		interval[0] = 15;
		intervalL[1] = 0;   //diff than in paper
		interval[1] = 15;
		name ="Branin function";
	}
	
	public int getNumberOfGlobalOpt() {
		return 3;
	}
	
	public double eval(double x[]) {
		double v = 0;
		v = Math.pow(x[1]-(5.1/(4*Math.PI*Math.PI))*x[0]*x[0]+5*x[0]/Math.PI-6,2)+10*(1-1/(8*Math.PI))*Math.cos(x[0])+10;
		return v;
	}
	
	public double getOptimumEval() {
		return  0.39788735772973816;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[3][dim]; 
		//-¹ , 12.275), (¹ , 2.275), (9.42478, 2.475
		v[0][0] = -Math.PI; 
		v[0][1] = 12.275;
		v[1][0] = Math.PI; 
		v[1][1] = 2.275;
		v[2][0] = 9.42478; 
		v[2][1] = 2.475;
		return v;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
