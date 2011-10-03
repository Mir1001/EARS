package org.um.feri.ears.algorithms.random;

import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.IAlgorithm;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;

/**
 * Similar as Random walk only that in case of finding new best the arithmetic mean of individuals is
 * new best and prior best is calculated and tested.   
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
public class RandomWalkAMAlgorithm implements IAlgorithm {
	Individual i;
	boolean debug=false;
	public RandomWalkAMAlgorithm() {
		this.debug = false; 
	}
	public RandomWalkAMAlgorithm(boolean d) {
		setDebug(d); 
	}
	private double[] xArithmeticMeanOf(double[] x, double[] y) {
	    double[] am = new double[x.length];
	    for (int i=0; i<x.length; i++){
	        am[i] = (x[i]+y[i])*0.5;
	    }
	    return am;
	}
	@Override
	public Individual run(Task taskProblem) throws StopCriteriaException{
		Individual ii, iAritmetic;
			i = taskProblem.getRandomIndividual();
			if (debug) System.out.println(taskProblem.getNumberOfEvaluations()+" "+i);
			while (!taskProblem.isStopCriteria()) {
				ii = taskProblem.getRandomIndividual();
				if (taskProblem.isFirstBetter(ii, i)) {
				    if (!taskProblem.isStopCriteria()) { //try also arithmetic mean
				        iAritmetic = taskProblem.eval(xArithmeticMeanOf(i.getX(), ii.getX()));
		                if (taskProblem.isFirstBetter(iAritmetic, ii)) ii = iAritmetic; //even better				        
				    }
					i = ii;
					if (debug) System.out.println(taskProblem.getNumberOfEvaluations()+" "+i);
				}
			}
		return i;

	}
	@Override
	public void setDebug(boolean d) {
		debug = d;
	}
	@Override
	public Author getImplementationAuthor() {
		return new Author("matej", "matej.crepinsek at uni-mb.si");
	}
	@Override
	public AlgorithmInfo getAlgorithmInfo() {
		return new AlgorithmInfo("RWS","","RWAMS","Random Walk Arithmetic Mean Simple");
	}

}
