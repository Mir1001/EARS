/**
 * Rating benchmark for unconstrained problems, small dimensions, evaluation is limited with maximum evaluations.
 * Results that are E-10 different are treated as same.
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
package org.um.feri.ears.benchmark;

import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem1;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem2;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem3;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem4;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem5;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem6;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem7;
import org.um.feri.ears.problems.unconstrained.cec2010.*;

//TODO calculate CD for rating
public class RatingCEC2009 extends RatingBenchmark{
    public static final String name="Benchmark CEC 2009";
    protected int evaluationsOnDimension=3000;
    protected int dimension=30;
    private double draw_limit=0.0000001;
    
    public boolean resultEqual(Individual a, Individual b) {
        if ((a==null) &&(b==null)) return true;
        if (a==null) return false;
        if (b==null) return false;
        if (Math.abs(a.getEval()-b.getEval())<draw_limit) return true;
        return false;
    }
    public RatingCEC2009(){
    	this(0.0000001);
    }
    public RatingCEC2009(double draw_limit) {
        super();
        this.draw_limit = draw_limit;
        evaluationsOnDimension=3000;
        dimension=30;
        initFullProblemList();
        addParameter(EnumBenchmarkInfoParameters.DIMENSION,"30");
        addParameter(EnumBenchmarkInfoParameters.EVAL,String.valueOf(evaluationsOnDimension));
        addParameter(EnumBenchmarkInfoParameters.DRAW_PARAM,"abs(evaluation_diff) < "+draw_limit);
    }
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#registerTask(org.um.feri.ears.problems.Problem)
     */
    @Override
    protected void registerTask(Problem p, EnumStopCriteria sc, int eval, double epsilon) {
        listOfProblems.add(new TaskWithReset(sc, eval, epsilon, p));
    }
    
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#initFullProblemList()
     */
    @Override
    protected void initFullProblemList() {
    	
    	registerTask(new UnconstrainedProblem1(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem2(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem3(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem4(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem5(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem6(),stopCriteria, evaluationsOnDimension, 0.001);
    	registerTask(new UnconstrainedProblem7(),stopCriteria, evaluationsOnDimension, 0.001);

    }
        
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#getAcronym()
     */
    @Override
    public String getAcronym() {
        return "CEC2009";
    }
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#getInfo()
     */
    @Override
    public String getInfo() {
        return "";
    }
    
}
