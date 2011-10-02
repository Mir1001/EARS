package org.um.feri.ears.problems.constrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;


public class TLBOBenchmarkFunction2 extends Problem {
	// http://www-optima.amp.i.kyoto-u.ac.jp/member/student/hedar/Hedar_files/TestGO_files/Page2613.htm
	public final static double best_x[] = {0.31622776601683794, 0.31622776601683794,0.31622776601683794,
		0.31622776601683794, 0.31622776601683794, 0.31622776601683794, 0.31622776601683794,0.31622776601683794, 0.31622776601683794,
		0.31622776601683794 };

	public TLBOBenchmarkFunction2() {
		dim = 10;
		minimum = false;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, 0, dim, 0);
		Arrays.fill(interval, 0, dim, 1);
		constrains = 1;
		max_constrains = new double[constrains];
		min_constrains = new double[constrains];
		count_constrains  = new double[constrains];
		sum_constrains  = new double[constrains];
		normalization_constrains_factor = new double[constrains];
		// System.out.println(Arrays.toString(interval)+"\n"+Arrays.toString(intervalL));
		name = "TLBOBenchmarkFunction2 cec-g03";
	
	}



	public double eval(double x[]) {
		double t1 = 1;
		for (int i = 0; i < dim; i++)
			t1 *= x[i];
		double v = Math.pow(Math.sqrt(dim), dim) * t1;
		return v;
	}

	public double[] calc_constrains(double x[]) {
		double[] g = new double[constrains];
		double d = -1;
		for (int i = 0; i < dim; i++) {
			d += x[i] * x[i];
		}
		d = Math.abs(d);
		if (d <= 0.001)
			g[0] = 0;
		else
			g[0] = d;
		return g;
	}
	
	/*
	 * public double constrainsOK(double x[]) { //h1 double d=-1; for (int i=0;
	 * i<dim; i++) { d+=x[i]*x[i]; } if (Math.abs(d)>0.001) return Math.abs(d);
	 * return 0; }
	 */
	public String constrainsOKtoString(double x[]) {
		// h1
		double d = -1.;
		for (int i = 0; i < dim; i++) {
			d += x[i] * x[i];
		}
		if (Math.abs(d) >= 0.000001)
			return "1 " + d;
		return "0 OK";
	}

	public double getOptimumEval() {
		return 1;
	}
}
