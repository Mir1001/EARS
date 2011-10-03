package org.um.feri.ears.problems.unconstrained;

import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

/**
* Problem function!
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
public class ProblemRosenbrockD2b  extends Problem{
// Rosenbrock
	//http://www.geatbx.com/docu/fcnindex-01.html#P86_3059
	public ProblemRosenbrockD2b() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(intervalL, -10);
		Arrays.fill(interval, 20);
		name = "Rosenbrock d2b";
	}
	public double eval(double x[]) {
		double v=0;
		for (int i=0; i<(dim-1); i++) {
			v+=100*(x[i+1]-x[i]*x[i])*(x[i+1]-x[i]*x[i])+(1-x[i])*(1-x[i]);
			//v+=Math.pow(1-ds[i],2)+100*Math.pow(ds[i+1]-ds[i]*ds[i],2);
		}
		return v;
	}
	public double getOptimumEval() {
		return 0;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], 1); 
		return v;
	}
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
}
