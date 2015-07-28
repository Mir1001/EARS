package org.um.feri.ears.algorithms;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.um.feri.ears.benchmark.EnumBenchmarkInfoParameters;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;

/**
 * Every new algorithm needs to implement this interface.
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
public abstract class Algorithm {
	/**
	 * Search for best solution.
	 * 
	 * if StopCriteriaException is thrown tasks isStopCriteria method is not used properly.
	 * 
	 * @param taskProblem
	 * @return best solution
	 * @throws StopCriteriaException 
	 */
    protected ArrayList<Double> controlParameters; //only for tuning!
    protected boolean debug;
    protected boolean played=false;
    protected int age; //for tunning
    protected Author au;
    protected AlgorithmInfo ai;
    protected AlgorithmInfo tmpAi;
    protected AlgorithmRunTime art;
    /**
     * Used for tuning!!!!
     * @return
     */
    public ArrayList<Double> getControlParameters(){
    	return this.controlParameters;
    }
    public void setPlayed(boolean aBool){
    	this.played = aBool;
    }
    public boolean getPlayed(){
    	return played;
    }
    public void addRunDuration(long duration) {
        if (art==null) {
            art = new AlgorithmRunTime();
        }
        art.addRunDuration(duration);
    }
    public void setAlgorithmTmpInfo(AlgorithmInfo aii) {
        tmpAi = ai;
        ai = aii;
    }
    public void setAlgorithmInfoFromTmp() {
        ai = tmpAi;
    }
    
    public void setAlgorithmInfo(AlgorithmInfo aii) {
        ai = aii;
    }
    public int getAge(){
    	return this.age;
    }
    public void setAge(int aAge){
    	this.age = aAge;
    }
    public void increaseAge(){
    	this.age = this.age + 1;
    }
    /**
     * 
     * 
     * @param taskProblem
     * @return
     * @throws StopCriteriaException
     */
	public abstract Individual run(Task taskProblem) throws StopCriteriaException;
	
	/**
	 * It is called every time before every run! 
	 */
	public abstract void resetDefaultsBeforNewRun();
	public boolean isDebug() {
        return debug;
	}

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Author getImplementationAuthor() {
        return au;
    }
	public AlgorithmInfo getAlgorithmInfo(){
	    return ai;
	}
	public String getID() {
	    return ai.getVersionAcronym();
	}
	/**
	 * Returns algorithms with different settings for selecting the best one!
	 * maxCombinations is usually set to 8!
	 * If maxCombinations==1 than return combination that is expected to perform best!
	 * 
	 * NOTE not static because jave doesnt support abstract static!
	 * 
	 * @param taskProblem
	 * @return
	 */
	public List<Algorithm> getAlgorithmParameterTest(EnumMap<EnumBenchmarkInfoParameters, String> parameters, int maxCombinations) {
	    List<Algorithm> noAlternative = new ArrayList<Algorithm>();
	    noAlternative.add(this);
	    return noAlternative;
	}
    public void resetDuration() {
        art = new AlgorithmRunTime();
    }
	public String getIDFull() {
		return getID()+"-"+ai.paramsToStringShort();
	}
}
