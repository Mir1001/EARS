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
public class ProblemEasom  extends Problem{

	public ProblemEasom() {
		dim = 2;
		interval = new double[dim];
		intervalL = new double[dim];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "Easom";
	}
	//fEaso(x1,x2)=-cos(x1)�cos(x2)�exp(-((x1-pi)^2+(x2-pi)^2))
	public double eval(double x[]) {
		double v=
			-Math.cos(x[0])*Math.cos(x[1])*Math.exp(-Math.pow((x[0]-Math.PI),2)-Math.pow(x[1]-Math.PI,2));
		return v;
	}
	public double[][] getOptimalVector() {
		double[][] v = new double[1][dim];
		Arrays.fill(v[0], Math.PI); 
		return v;
	}
	public double getOptimumEval() {
		return -1.;
	}
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
}
