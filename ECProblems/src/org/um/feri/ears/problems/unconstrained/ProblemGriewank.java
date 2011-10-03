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
public class ProblemGriewank  extends Problem{

	public ProblemGriewank(int d) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(intervalL, -50);
		Arrays.fill(interval, 100);
		name ="Griewank";
	}
	public ProblemGriewank(int d, double i) {
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(intervalL, -i);
		Arrays.fill(interval, i*2);
		name ="Griewank";
	}
	
	public double eval(double x[]) {
		double v=0;
		double a = 0; 
		double b = 1;
		for (int i=0; i<dim;i++) {
			  a += x[i]*x[i];
			  b *= Math.cos(x[i]/Math.sqrt(i+1));
			}
		v = a/4000.- b+1;		
		return v;
	}
	
	public double getOptimumEval() {
		return 0;
	}
	
	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x<eval_y;
	}
	
}
