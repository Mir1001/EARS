package org.um.feri.ears.problems;

/**
* Task is main class, for communication between algorithm and problem  
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
public class Task {
	protected EnumStopCriteria stopCriteria;
	protected int maxEvaluations; // for Stop criteria
	protected int numberOfEvaluations = 0; // for Stop criteria
	protected double epsilon; // for Stop criteria
	private boolean isStop;
	private boolean isGlobal;
	private Problem p;

	public Task(EnumStopCriteria stop, int eval, double epsilon, Problem p) {
		stopCriteria = stop;
		maxEvaluations = eval;
		numberOfEvaluations = 0;
		this.epsilon = epsilon;
		isStop = false;
		isGlobal = false;
		this.p = p;
	}
	
	public double[] getIntervalLength(){
		return p.interval;
	}
	
	public double[] getIntervalLeft(){
		return p.intervalL;
	}
	
	public double[] getIntervalRight(){
		double intervalR[] = new double[p.interval.length];
		for (int i=0; i<intervalR.length;i++) {
			intervalR[i] = p.intervalL[i]+p.interval[i];
		}
		return intervalR;
	}
	
	public int getDimensions() {
		return p.getDim();
	}
	
	public Individual getRandomIndividual() throws StopCriteriaException {
		return eval(p.getRandomVectorX()); 
	}
	
	public boolean isFirstBetter(Individual x, Individual y) {
		return p.isFirstBetter(x.getX(), x.getEval(), y.getX(), y.getEval());
	}
	
	private void incEvaluate() throws StopCriteriaException {
		if (numberOfEvaluations >= maxEvaluations)
			throw new StopCriteriaException("Max evaluations");
		numberOfEvaluations++;
		if (numberOfEvaluations >= maxEvaluations)
			isStop = true;
	}
	
	public int getNumberOfEvaluations(){
		return numberOfEvaluations;
	}
	
	public boolean isStopCriteria() {
		return isStop||isGlobal;
	}


	public Individual eval(double[] ds) throws StopCriteriaException {
		if (stopCriteria == EnumStopCriteria.EVALUATIONS) {
			incEvaluate();
			return new Individual(ds,p.eval(ds));
		}
		if (stopCriteria == EnumStopCriteria.GLOBAL_OPTIMUM_OR_EVALUATIONS) {
			if (isGlobal)
				throw new StopCriteriaException("Global optimum already found");
			incEvaluate();
			double d = p.eval(ds);
			if (Math.abs(d - p.getOptimumEval()) <= epsilon) {
				isGlobal = true;
			}
			return new Individual(ds,d);
		}
		assert false; // Execution should never reach this point!
		return null; //error
	}
}
