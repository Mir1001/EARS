package org.um.feri.ears.problems.constrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;


public class TLBOBenchmarkFunction4 extends Problem {
	// http://www-optima.amp.i.kyoto-u.ac.jp/member/student/hedar/Hedar_files/TestGO_files/Page3235.htm
	// public final static double best_x[]={579.3167, 1359.943, 5110.071,
	// 182.0174, 295.5985, 217.9799, 286.4162, 395.5979};
	public final static double best_x[] = { 579.306685017979589,
			1359.97067807935605, 5109.97065743133317, 182.01769963061534,
			295.601173702746792, 217.982300369384632, 286.41652592786852,
			395.601173702746735 };

	
	public TLBOBenchmarkFunction4() {
		minimum = true;
		dim = 8;
		interval = new double[dim];
		intervalL = new double[dim];
		interval[0] = 9900;
		intervalL[0] = 100;
		interval[1] = interval[2] = 9000;
		intervalL[1] = intervalL[2] = 1000;
		Arrays.fill(intervalL, 3, dim, 10);
		Arrays.fill(interval, 3, dim, 990);
		constrains = 6;
		max_constrains = new double[constrains];
		min_constrains = new double[constrains];
		count_constrains  = new double[constrains];
		sum_constrains  = new double[constrains];
		normalization_constrains_factor = new double[constrains];
		// System.out.println(Arrays.toString(interval)+"\n"+Arrays.toString(intervalL));
		name ="TLBOBenchmarkFunction4 (G10) cec-g10";
		
	}
	public double[] calc_constrains(double x[]) {

		double[] g = new double[constrains];
		g[0] = -1.0 + 0.0025 * (x[3] + x[5]);
		g[1] = -1.0 + 0.0025 * (x[4] + x[6] - x[3]);
		g[2] = -1.0 + 0.01 * (x[7] - x[4]);
		g[3] = -x[0] * x[5] + 833.33252 * x[3] + 100.0 * x[0] - 83333.333;
		g[4] = -x[1] * x[6] + 1250.0 * x[4] + x[1] * x[3] - 1250.0 * x[3];
		g[5] = -x[2] * x[7] + 1250000.0 + x[2] * x[4] - 2500.0 * x[4];
		return g;
	}
	
	/*public double[] calc_constrains(double x[]) {

		double[] g = new double[constrains];
		g[0] = -1.0 + 0.0025 * (x[3] + x[5]);
		g[1] = -1.0 + 0.0025 * (x[4] + x[6] - x[3]);
		g[2] = -1.0 + 0.01 * (x[7] - x[4]);
		//g[3] = -x[0] * x[5] + 833.33252 * x[3] + 100.0 * x[0] - 83333.333;
		//g[4] = -x[1] * x[6] + 1250.0 * x[4] + x[1] * x[3] - 1250.0 * x[3];
		//g[5] = -x[2] * x[7] + 1250000.0 + x[2] * x[4] - 2500.0 * x[4];
		return g;
	}
	*/



	public double eval(double x[]) {
		double v = x[0] + x[1] + x[2];
		return v;
	}

	/*
	 * @Override public boolean isFirstBetter(double[] x, double eval_x,
	 * double[] y, double eval_y) { boolean cons_x = (constrainsOK(x)==0);
	 * boolean cons_y = (constrainsOK(y)==0); if (cons_x) { if (cons_y) { return
	 * eval_x<eval_y; //min } return true; } if (cons_y) { return false; }
	 * return constrainsOK(x)<constrainsOK(y); }
	 * 
	 * 
	 * public double constrainsOKs(double x[]) { if (-1.+0.0025*(x[3]+x[5])>0)
	 * return 1; if (-1.+0.0025*(-x[3]+x[4]+x[6])>0) return 2; if
	 * (-1.+0.01*(-x[4]+x[7])>0) return 3; if
	 * (100.*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333>0) return 4; if
	 * (x[1]*x[3]-x[1]*x[6]-1250.*x[3]+1250.*x[4]>0) return 5; if
	 * (x[2]*x[4]-x[2]*x[7]-2500.*x[4]+1250000.>0) return 6; return 0; }
	 * 
	 * public double constrainsOKss(double x[]) { double d=0; if
	 * (-1.+0.0025*(x[3]+x[5])>0) d+=-1+0.0025*(x[3]+x[5]); if
	 * (-1.+0.0025*(-x[3]+x[4]+x[6])>0) d+=-1+0.0025*(-x[3]+x[4]+x[6]); if
	 * (-1.+0.01*(-x[4]+x[7])>0) d+=-1+0.01*(-x[4]+x[7]); if
	 * (100.*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333>0)
	 * d+=100*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333; if
	 * (x[1]*x[3]-x[1]*x[6]-1250.*x[3]+1250.*x[4]>0)
	 * d+=x[1]*x[3]-x[1]*x[6]-1250*x[3]+1250*x[4]; if
	 * (x[2]*x[4]-x[2]*x[7]-2500.*x[4]+1250000.>0)
	 * d+=x[2]*x[4]-x[2]*x[7]-2500*x[4]+1250000; if
	 * (d/constrainsOK3(x)<0.000001) return 0; //? return d;///constrainsOK3(x);
	 * } public double constrainsOK(double x[]) { double
	 * d1=0,d2=0,d3=0,d4=0,d5=0,d6=0,d=0; if (-1.+0.0025*(x[3]+x[5])>0) {
	 * d+=-1+0.0025*(x[3]+x[5]); } if (-1.+0.0025*(-x[3]+x[4]+x[6])>0)
	 * d+=-1+0.0025*(-x[3]+x[4]+x[6]); if (-1.+0.01*(-x[4]+x[7])>0)
	 * d+=-1+0.01*(-x[4]+x[7]); if
	 * (100.*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333>0)
	 * d+=100*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333; if
	 * (x[1]*x[3]-x[1]*x[6]-1250.*x[3]+1250.*x[4]>0)
	 * d+=x[1]*x[3]-x[1]*x[6]-1250*x[3]+1250*x[4]; if
	 * (x[2]*x[4]-x[2]*x[7]-2500.*x[4]+1250000.>0)
	 * d+=x[2]*x[4]-x[2]*x[7]-2500*x[4]+1250000; //if (d/6<0.000001) return 0;
	 * //? return d/6;///constrainsOK3(x); }
	 * 
	 * 
	 * public double constrainsOK3(double x[]) { double d=0; if
	 * (-1+0.0025*(x[3]+x[5])>0.0000001) d+=1; if
	 * (-1+0.0025*(-x[3]+x[4]+x[6])>0.0000001) d+=1; if
	 * (-1+0.01*(-x[4]+x[7])>0.0000001) d+=1; if
	 * (100*x[0]-x[0]*x[5]+833.33252*x[3]-83333.333>0.0000001) d+=1; if
	 * (x[1]*x[3]-x[1]*x[6]-1250*x[3]+1250*x[4]>0.0000001) d+=1; if
	 * (x[2]*x[4]-x[2]*x[7]-2500*x[4]+1250000>0.0000001) d+=1; return d; }
	 */
	public double getOptimumEval() {
		return 7049.3307;
	}
}
