package org.um.feri.ears.problems;

import java.util.Arrays;

import org.um.feri.ears.util.Util;

/**
* Main common class for constrained and unconstrained problems.
* <p>
* 
* @author Matej Crepinsek
* @version 1
* 
*          <h3>License</h3>
* 
*          Copyright (c) 2011 by Matej Crepinsek. <br>
*          All rights reserved. <br>
* 
*          <p>
*          Redistribution and use in source and binary forms, with or without
*          modification, are permitted provided that the following conditions
*          are met:
*          <ul>
*          <li>Redistributions of source code must retain the above copyright
*          notice, this list of conditions and the following disclaimer.
*          <li>Redistributions in binary form must reproduce the above
*          copyright notice, this list of conditions and the following
*          disclaimer in the documentation and/or other materials provided with
*          the distribution.
*          <li>Neither the name of the copyright owners, their employers, nor
*          the names of its contributors may be used to endorse or promote
*          products derived from this software without specific prior written
*          permission.
*          </ul>
*          <p>
*          THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
*          "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
*          LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
*          FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
*          COPYRIGHT OWNERS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
*          INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
*          BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
*          LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
*          CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
*          LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
*          ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
*          POSSIBILITY OF SUCH DAMAGE.
* 
*/
public abstract class Problem {
	public double interval[];
	public double intervalL[];
	protected int dim;
	protected boolean minimum = true;
	public int constrains = 0;
	public double max_constrains[]; 
	public double min_constrains[]; 
	public double count_constrains[]; 
	public double sum_constrains[]; 
	public double normalization_constrains_factor[]; // used for normalization
	public String name;
	public static final int CONSTRAINED_TYPE_COUNT=1;
	public static final int CONSTRAINED_TYPE_SUM=2;
	public static final int CONSTRAINED_TYPE_NORMALIZATION=3;
	public static int constrained_type = CONSTRAINED_TYPE_SUM;
	// http://www.ints.info.hiroshima-cu.ac.jp/~takahama/eng/index.html
	
	/**
	 * It is 2 dimensional, because some problems can have more
	 * than one global optimum.
	 * 
	 * @return global optimum
	 */
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], 0); // default is [0, 0, ...., 0, 0]
		return v;
	}
	/**
	 * Allows to set different name. That can be used in report generating process.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * if there is more than one global optimum, you need to override this method.
	 * 
	 * @return number of global optimum-s
	 */
	public int getNumberOfGlobalOpt() {
		return 1;
	}
	
	/**
	 * Calculates Euclidian Distance that is normalized by dimension interval.  
	 * 
	 * @param x_i
	 * @param x_j
	 * @return
	 */
	public double normalizedEuclidianDistance(double x_i[], double x_j[]) {
		double r = 0;
		for (int i = 0; i < dim; i++) {
			r += (x_i[i] - x_j[i]) / interval[i] * (x_i[i] - x_j[i])
					/ interval[i];
		}
		r = Math.sqrt(1. / dim * r);
		return r;
	}
	
	/**
	 * 
	 * @param tmp_constrains
	 */
	public void setMaxConstrains(double tmp_constrains[]) {
		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > max_constrains[i]) {
				max_constrains[i] = tmp_constrains[i];
			}
		}
	}

	public void setSumConstrains(double tmp_constrains[]) {
		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > 0) {
				sum_constrains[i] += tmp_constrains[i];
			}
		}
	}

	public void setMinConstrains(double tmp_constrains[]) {
		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > 0) {
				if ((tmp_constrains[i] < min_constrains[i])
						|| (min_constrains[i] == 0)) {
					min_constrains[i] = tmp_constrains[i];
				}
			}
		}
	}

	public void setCountConstrains(double tmp_constrains[]) {
		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > 0) {

				count_constrains[i]++;
			}

		}
	}

	public void countConstrains(double tmp_constrains[]) {
		// if (max_constrains==null) max_constrains = new double[constrains];
		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > max_constrains[i]) {
				max_constrains[i]++;
			} else {
				max_constrains[i]--;
				if (max_constrains[i] < 0) {
					max_constrains[i] = 0;
				}
			}

		}
	}

	public int countConstraintViolation(double tmp_constrains[]) {
		int c = 0;

		for (int i = 0; i < constrains; i++) {
			if (tmp_constrains[i] > 0) {
				c++;
			}
		}
		return c;
	}

	public double[] normalizeConstrain(double tmp_constrains[]) {
		for (int i = 0; i < constrains; i++) {

			tmp_constrains[i] = tmp_constrains[i] / max_constrains[i];
		}
		return tmp_constrains;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
	

	/**
	 * When overriding, call incEvaluate!
	 * Implement problem!  
	 * @param ds
	 * @return
	 */
	public abstract double eval(double[] ds);
	
	/**
	 * with no evaluations just checks
	 * if algorithm result is in interval.
	 * This is not checking constrains, just basic intervals!  
	 * 
	 * @param ds vector of possible solution
	 * @return
	 */
	public boolean areDimensionsInFeasableInterval(double[] ds) {
	    for (int i=0; i<dim; i++) {
        if (ds[i] < intervalL[i])
            return false;
        if (ds[i] > (intervalL[i] + interval[i]))
            return false;
	    }
        return true;
	    
	}
	/**
	 * If selected value in interval is not feasible  
	 * 
	 * @param d
	 * @param i
	 * @return
	 */
	public double feasible(double d, int i) {
		if (d < intervalL[i])
			return intervalL[i];
		if (d > (intervalL[i] + interval[i]))
			return intervalL[i] + interval[i];
		return d;

	}
	/**
	 * Important! Do not use this function for constrained problems,
	 * if fitness is not reflecting feasibility of the solution.
	 * 
	 * @param a first fitness
	 * @param b second fitness
	 * @return
	 */
	public boolean isFirstBetter(double a, double b) {
		if (minimum)
			return a < b;
		return a > b;
	}
	/**
	 * Default value
	 * @return
	 */
	public double getHitEpsilon() {
		return 0.1;  
	}

	public double getOptimumEval() {
		return 0;
	}

	public String getName() {
		return name;
	}
	/**
	 * Default it sets to zero!
	 * This method needs to be override in constrained based problems. 
	 * 
	 * @param x
	 * @return
	 */
	public double[] calc_constrains(double x[]) {
		double[] tmp = new double[0];
		return tmp;
	}

	/**
	 * 
	 * 
	 * @param x - solution
	 * @return
	 */
	public double constrainsEvaluations(double x[]) {
		if (constrains == 0)
			return 0;
		double[] g = calc_constrains(x); //calculate for every constrain (problem depended)
		double d = 0;
		for (int j = 0; j < constrains; j++) {
			if (g[j] > 0) {
				if (constrained_type== CONSTRAINED_TYPE_COUNT) d++;
				if (constrained_type== CONSTRAINED_TYPE_SUM) d += g[j];
				if (constrained_type== CONSTRAINED_TYPE_NORMALIZATION)
				  d += g[j] * normalization_constrains_factor[j];// *(count_constrains[j]+1);
														
			}
		}
		return d;
	}
	
	public double[] getRandomVectorX() {
		double[] sol=new double[dim];
		for (int j = 0; j < dim; j++) {
			sol[j] = intervalL[j] + Util.rnd.nextDouble() * interval[j];
		}
		return sol;
	}
	/**
	 * Not just fitness value but also constrained. 
	 * 
	 * @param x
	 * @param eval_x
	 * @param y
	 * @param eval_y
	 * @return
	 */
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		double cons_x = constrainsEvaluations(x);
		double cons_y = constrainsEvaluations(y);
		if (cons_x == 0) {
			if (cons_y == 0) {
				if (minimum)
					return eval_x < eval_y;
				return eval_x > eval_y;
			}
			return true; // y is not feasible
		}
		if (cons_y == 0) {
			return false;
		}
		return cons_x < cons_y; // less constrain is better

	}
}
