package org.um.feri.ears.problems.constrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;


public class TLBOBenchmarkFunction5  extends Problem{
	//http://www.sciencedirect.com/science/article/pii/S0305054811000955
	public final static double best_x[]={5,5,5};
	
	public TLBOBenchmarkFunction5() {
		minimum = false;
		dim = 3;
		interval = new double[dim];
		intervalL = new double[dim];			
		Arrays.fill(intervalL,0,dim,0); 
		Arrays.fill(interval,0,dim,10);
		//System.out.println(Arrays.toString(interval)+"\n"+Arrays.toString(intervalL));
		name = "TLBOBenchmarkFunction5 (TP11)";
		
	}
	
	
	public double eval(double x[]) {
		double v= (100-Math.pow(x[0]-5,2)-Math.pow(x[1]-5,2)-Math.pow(x[2]-5,2))/100.;
		return v;
	}
	
	/*
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		boolean cons_x = (constrainsOK(x)==0);
		boolean cons_y = (constrainsOK(y)==0);
		if (cons_x) {
			if (cons_y) {
				return eval_x>eval_y;
			}
			return true;
		}
		if (cons_y) {
			return false;
		}
		return eval_x>eval_y;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		double cons_x = constrainsOK(x);
		double cons_y = constrainsOK(y);
		if (cons_x < 0.000001) {
			if (cons_y < 0.000001) {
				return eval_x>eval_y; //max
			}
			return true;
		}
		if (cons_y < 0.000001) {
			return false;
		}
		return constrainsOK(x) < constrainsOK(y);
	
	}
	*/
	
	public double constrainsOKS(double x[]) {
		//optimized version!
		double mindx1 =Double.MAX_VALUE;
		double mindx2 =Double.MAX_VALUE;
		double mindx3 =Double.MAX_VALUE;
		for (int i=1; i<10; i++) {
			if (Math.abs((x[0]-i))<mindx1) mindx1=Math.abs(x[0]-i);
			if (Math.abs((x[1]-i))<mindx2) mindx2=Math.abs(x[1]-i);
			if (Math.abs((x[2]-i))<mindx3) mindx3=Math.abs(x[2]-i);
		}
		//System.out.println(mindx1+" "+mindx2+" "+mindx3+" "+(mindx1*mindx1+mindx2*mindx2+mindx3*mindx3-0.0625));
		if ((mindx1*mindx1+mindx2*mindx2+mindx3*mindx3-0.0625)<=0) return 0; //ok
		return (mindx1*mindx1+mindx2*mindx2+mindx3*mindx3); //constraints
	}
	
	public double constrainsEvaluations(double x[]) {
		//optimized version!
		double mindx1 =Double.MAX_VALUE;
		double mindx2 =Double.MAX_VALUE;
		double mindx3 =Double.MAX_VALUE;
		for (int i=1; i<10; i++) {
			if (Math.abs((x[0]-i))<mindx1) mindx1=Math.abs(x[0]-i);
			if (Math.abs((x[1]-i))<mindx2) mindx2=Math.abs(x[1]-i);
			if (Math.abs((x[2]-i))<mindx3) mindx3=Math.abs(x[2]-i);
		}
		//System.out.println(mindx1+" "+mindx2+" "+mindx3+" "+(mindx1*mindx1+mindx2*mindx2+mindx3*mindx3-0.0625));
		if ((mindx1*mindx1+mindx2*mindx2+mindx3*mindx3-0.0625)<=0) return 0; //ok
		return (mindx1*mindx1+mindx2*mindx2+mindx3*mindx3); //constraints
	}
	
	public double getOptimumEval() {
		return 1;
	}
}
