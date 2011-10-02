package org.um.feri.ears.problems;

import org.um.feri.ears.util.Util;

public class Individual {
	private double[] x;
	private double eval;
	public Individual(Individual i) {
		x = new double[i.x.length];
		System.arraycopy(i.x, 0, x, 0, x.length);
		eval = i.eval;
	}
	public Individual(double[] x, double eval) {
		this.x = new double[x.length];
		System.arraycopy(x, 0, this.x, 0, x.length);
		this.eval = eval;
	}
	public double getEval() {
		return eval;
	}
	public double[] getNewX() {
		double[] xx = new double[x.length];
		System.arraycopy(x, 0, xx, 0, x.length);
		return xx;
	}
	public double[] getX() {
		return x;
	}
	
	public String toString() {
		return Util.dfc2.format(eval)+" ["+Util.arrayToString(x)+"]";
	}

	
}
