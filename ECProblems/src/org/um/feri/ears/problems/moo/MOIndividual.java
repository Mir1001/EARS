package org.um.feri.ears.problems.moo;

import java.util.Comparator;

import org.um.feri.ears.rating.Player;
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
public class MOIndividual {
	private double[] x;
    private double eval[]; //more than one objective
	private double[] constrains; //TODO refactor 2 types of individual for constrained optimization
	private boolean feasible; //Feasible checks constrains
	public int getObjectives(){
	    return eval.length;
	}
	public MOIndividual(MOIndividual i) {
		x = new double[i.x.length];
		eval = new double[i.eval.length];
		System.arraycopy(i.x, 0, x, 0, x.length);
	      System.arraycopy(i.eval, 0, eval, 0, eval.length);
		this.feasible = i.feasible;
		if (i.constrains!=null) {
		  constrains = new double[i.constrains.length];
		  System.arraycopy(i.constrains, 0, constrains, 0, constrains.length);
		}
	}
	
	/**
	 * !!!This constructor is for unconstrained optimization!
	 * 
	 * @param x
	 * @param eval
	 * @deprecated
	 */
	public MOIndividual(double[] x, double[] eval) {
		this.x = new double[x.length];
		System.arraycopy(x, 0, this.x, 0, x.length);
		this.eval = eval;
		feasible = true;
	}
	/**
	 * Use this constructor only in case of constrained optimization 
	 * 
	 * @param x
	 * @param eval
	 * @param constrains
	 */
	public MOIndividual(double[] x, double eval[], double[] constrains) {
	        this.x = new double[x.length];
	        System.arraycopy(x, 0, this.x, 0, x.length);
	        setFeasible(constrains);
	        this.eval = new double[eval.length];
	          System.arraycopy(eval, 0, this.eval, 0, eval.length);
	}
	
	public double[] getConstrains() {
        return constrains;
    }
    public boolean isFeasible() {
        return feasible;
    }
    private void setFeasible(double[] constrains) {
        feasible = true;
	    for (int i=0;i<constrains.length; i++) {
	        if (constrains[i]>0) { //equal constrained needs to be solve in Problem (set 0 if<=0.001)
	            feasible = false;
	            this.constrains= new double[constrains.length];
	            System.arraycopy(constrains, 0, this.constrains, 0, constrains.length);
	        }
	    }
	}
    
	public double[] getEval() {
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
	    StringBuffer sb = new StringBuffer();
	    for (int i=0; i<eval.length; i++)
	     sb.append(Util.dfcshort.format(eval[i])).append(" ");
	    sb.append(" ["+Util.arrayToString(x)+"]");
		return sb.toString();
	}
	   public String toStringFitness() {
	        StringBuffer sb = new StringBuffer();
	        for (int i=0; i<eval.length; i++)
	         sb.append(Util.df0.format(eval[i])).append("\t");
	        return sb.toString();
	    }
 
	
}
