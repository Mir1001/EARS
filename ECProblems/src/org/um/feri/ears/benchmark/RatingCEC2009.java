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
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.problems.moo.MOProblem;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem1;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem10;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem2;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem3;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem4;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem5;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem6;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem7;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem8;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem9;
import org.um.feri.ears.problems.unconstrained.cec2010.*;
import org.um.feri.ears.quality_indicator.Hypervolume;
import org.um.feri.ears.quality_indicator.QualityIndicator;
import org.um.feri.ears.quality_indicator.QualityIndicator.IndicatorType;

//TODO calculate CD for rating
public class RatingCEC2009 extends MORatingBenchmark{
    public static final String name="Benchmark CEC 2009";
    protected int evaluationsOnDimension=3000;
    protected int dimension=3;
    private double draw_limit=0.0000001;
    
	@Override
	public boolean resultEqual(MOParetoIndividual a, MOParetoIndividual b) {
		if ((a==null) &&(b==null)) return true;
        if (a==null) return false;
        if (b==null) return false;
        return a.isEqual(b,draw_limit, qi);
	}
    
    public RatingCEC2009(){
    	this(new Hypervolume(),0.0000001);
    }
    public RatingCEC2009(QualityIndicator qi, double draw_limit) {
        super(qi);
        this.draw_limit = draw_limit;
        evaluationsOnDimension=30000;
        dimension=3;
        initFullProblemList();
        addParameter(EnumBenchmarkInfoParameters.DIMENSION,"2");
        addParameter(EnumBenchmarkInfoParameters.EVAL,String.valueOf(evaluationsOnDimension));
        addParameter(EnumBenchmarkInfoParameters.DRAW_PARAM,"abs(evaluation_diff) < "+draw_limit);
    }
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#registerTask(org.um.feri.ears.problems.Problem)
     */
    @Override
    protected void registerTask(MOProblem p, EnumStopCriteria sc, int eval, double epsilon, QualityIndicator qi) {
        listOfProblems.add(new TaskWithReset(sc, eval, epsilon, p, qi));
    }
    
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#initFullProblemList()
     */
    @Override
    protected void initFullProblemList() {
    	
    	registerTask(new UnconstrainedProblem1(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem2(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem3(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	/*registerTask(new UnconstrainedProblem4(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem5(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem6(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem7(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem8(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem9(),stopCriteria, evaluationsOnDimension, 0.001, qi);
    	registerTask(new UnconstrainedProblem10(),stopCriteria, evaluationsOnDimension, 0.001, qi);*/

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
