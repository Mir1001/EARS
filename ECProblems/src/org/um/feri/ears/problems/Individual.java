package org.um.feri.ears.problems;

import org.um.feri.ears.util.Util;

/**
* In EA we often have population of Individuals (population based)!
* Individual has genotype (binary in our case vector of real values).
* Evaluation or fitness of genotype is eval value.
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
		return Util.dfc1.format(eval)+" ["+Util.arrayToString(x)+"]";
	}

	
}
