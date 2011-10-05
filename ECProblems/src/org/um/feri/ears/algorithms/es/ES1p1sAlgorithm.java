package org.um.feri.ears.algorithms.es;

import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.IAlgorithm;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.util.Util;

/**
 * ES(1+1)
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
public class ES1p1sAlgorithm implements IAlgorithm {
    private Individual one;
    private double varianceOne;
    boolean debug = false;
    private AlgorithmInfo ai;
    private int k; // every k aVariance is calculated again
    private double c;
    Task task;
    //source http://natcomp.liacs.nl/EA/slides/es_basic_algorithm.pdf
    public ES1p1sAlgorithm() {
        this.debug = false;
        ai = new AlgorithmInfo("ES", "@book{Rechenberg1973,\n author = {Rechenberg, I.}, \n publisher = {Frommann-Holzboog}, \n title = {Evolutionsstrategie: optimierung technischer systeme nach prinzipien der biologischen evolution},\n year = {1973}}", "ES(1+1)", "ES(1+1) 1/5 rule");
        resetDefault();
    }
    private void resetDefault() {
        k=40; //every k is recalculated
        c= 0.9;  //0.8<=c<=1
        varianceOne = 1.;        
    }
    public ES1p1sAlgorithm(boolean d) {
        this();
        setDebug(d);
    }

    private double getGaussian(double aMean, double aVariance) {
        return aMean + Util.rnd.nextGaussian() * aVariance;
    }

    @Override
    public Individual run(Task taskProblem) throws StopCriteriaException {
        resetDefault(); // init starting values!!
        task = taskProblem;
        Individual ii;
        one = taskProblem.getRandomIndividual();
        int everyK=0; //recalculate variance
        int succ=0;
        double oneplus[];
        if (debug)
            System.out.println(taskProblem.getNumberOfEvaluations() + " start " + one);
        while (!taskProblem.isStopCriteria()) {
            everyK++;
            everyK = everyK%k;
            if (everyK==0) { //1/5 rule
                if ((succ/k)>0.2) varianceOne = varianceOne / c;
                else if ((succ/k)<0.2) varianceOne = varianceOne * c;
                succ = 0;
            }
            oneplus = one.getNewX();
            mutate(oneplus,varianceOne);
            ii = taskProblem.eval(oneplus);
            if (taskProblem.isFirstBetter(ii, one)) {
                succ++; //for 1/5 rule
                one = ii;
                if (debug)
                    System.out.println(taskProblem.getNumberOfEvaluations() + " " + one);
            }
        }
        return one;
    }

    /**
     * @param oneplus
     * @param varianceOne
     */
    private void mutate(double[] oneplus, double varianceOne) {
        for (int i=0; i<oneplus.length; i++) {
            oneplus[i] = task.feasible(oneplus[i]+getGaussian(0, varianceOne),1); 
        }
        
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
        return ai;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.um.feri.ears.algorithms.IAlgorithm#getID()
     */
    @Override
    public String getID() {
        return getAlgorithmInfo().getVersionAcronym();
    }

}
