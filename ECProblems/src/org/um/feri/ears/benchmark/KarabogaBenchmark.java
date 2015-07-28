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
import org.um.feri.ears.problems.unconstrained.karaboga.*;

//TODO calculate CD for rating
public class KarabogaBenchmark extends RatingBenchmark{
    public static final String name="Karaboa Benchmark";
    protected int evaluationsOnDimension=100000;
    protected int dimension=30;
    private double draw_limit=0.000001;
    
    public boolean resultEqual(Individual a, Individual b) {
        if ((a==null) &&(b==null)) return true;
        if (a==null) return false;
        if (b==null) return false;
        if (Math.abs(a.getEval()-b.getEval())<draw_limit) return true;
        return false;
    }
    public KarabogaBenchmark(){
    	this(0.0000001);
    }
    public KarabogaBenchmark(double draw_limit) {
        super();
        this.draw_limit = draw_limit;
        evaluationsOnDimension=100000;
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
    	
    	/* First wave */
    	registerTask(new Stepint(5),stopCriteria, evaluationsOnDimension, 0.000001);          // US
    	registerTask(new Step(30),stopCriteria, evaluationsOnDimension, 0.000001);            // US
    	registerTask(new Sphere(30),stopCriteria, evaluationsOnDimension, 0.000001);          // US
    	registerTask(new SumSquares(30),stopCriteria, evaluationsOnDimension, 0.000001);      // US
    	registerTask(new Quatric(30),stopCriteria, evaluationsOnDimension, 0.000001);         // US
    	
    	registerTask(new Beale(2),stopCriteria, evaluationsOnDimension, 0.000001);            // UN
    	registerTask(new Easom(2),stopCriteria, evaluationsOnDimension, 0.000001);            // UN
    	registerTask(new Matyas(2),stopCriteria, evaluationsOnDimension, 0.000001);           // UN
    	registerTask(new Colville(4),stopCriteria, evaluationsOnDimension, 0.000001);         // UN
    	registerTask(new Trid6(6),stopCriteria, evaluationsOnDimension, 0.000001);            // UN
    	registerTask(new Trid10(10),stopCriteria, evaluationsOnDimension, 0.000001);          // UN
    	registerTask(new Zakharov(10),stopCriteria, evaluationsOnDimension, 0.000001);        // UN
    	registerTask(new Powell(24),stopCriteria, evaluationsOnDimension, 0.000001);          // UN
    	registerTask(new Schwefel_2_22(30),stopCriteria, evaluationsOnDimension, 0.000001);   // UN
    	registerTask(new Schwefel_1_2(30),stopCriteria, evaluationsOnDimension, 0.000001);    // UN
    	registerTask(new Rosenbrock(30),stopCriteria, evaluationsOnDimension, 0.000001);      // UN
    	registerTask(new Dixon_Price(30),stopCriteria, evaluationsOnDimension, 0.000001);     // UN
    	
    	registerTask(new Foxholes(2),stopCriteria, evaluationsOnDimension, 0.000001);         // MS
    	registerTask(new Branin(2),stopCriteria, evaluationsOnDimension, 0.000001);           // MS
    	registerTask(new Bohachevsky1(2),stopCriteria, evaluationsOnDimension, 0.000001);     // MS
    	registerTask(new Booth(2),stopCriteria, evaluationsOnDimension, 0.000001);            // MS
    	registerTask(new Rastrigin(30),stopCriteria, evaluationsOnDimension, 0.000001);       // MS
    	registerTask(new Schwefel(30),stopCriteria, evaluationsOnDimension, 0.000001);        // MS
    	registerTask(new Michalewicz2(2),stopCriteria, evaluationsOnDimension, 0.000001);     // MS
    	registerTask(new Michalewicz5(5),stopCriteria, evaluationsOnDimension, 0.000001);     // MS
    	registerTask(new Michalewicz10(10),stopCriteria, evaluationsOnDimension, 0.000001);   // MS
    	/* Second wave */
    	registerTask(new Schaffer(2),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new SixHumpCamelBack(2),stopCriteria, evaluationsOnDimension, 0.000001); // MN
    	registerTask(new Bohachevsky2(2),stopCriteria, evaluationsOnDimension, 0.000001);     // MN
    	registerTask(new Bohachevsky3(2),stopCriteria, evaluationsOnDimension, 0.000001);     // MN
    	registerTask(new Shubert(2),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new GoldStein_Price(2),stopCriteria, evaluationsOnDimension, 0.000001);  // MN
    	registerTask(new Kowalik(4),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new Shekel5(4),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new Shekel7(4),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new Shekel10(4),stopCriteria, evaluationsOnDimension, 0.000001);         // MN
    	registerTask(new Perm(4),stopCriteria, evaluationsOnDimension, 0.000001);             // MN
    	registerTask(new PowerSum(4),stopCriteria, evaluationsOnDimension, 0.000001);         // MN
    	registerTask(new Hartman3(3),stopCriteria, evaluationsOnDimension, 0.000001);         // MN
    	registerTask(new Hartman6(6),stopCriteria, evaluationsOnDimension, 0.000001);         // MN
    	registerTask(new Griewank(30),stopCriteria, evaluationsOnDimension, 0.000001);        // MN
    	registerTask(new Ackley(30),stopCriteria, evaluationsOnDimension, 0.000001);          // MN
    	registerTask(new Penalized(30),stopCriteria, evaluationsOnDimension, 0.000001);       // MN
    	registerTask(new Penalized2(30),stopCriteria, evaluationsOnDimension, 0.000001);      // MN
    	/* Third wave */
    	registerTask(new Langerman2(2),stopCriteria, evaluationsOnDimension, 0.000001);       // MN
    	registerTask(new Langerman5(5),stopCriteria, evaluationsOnDimension, 0.000001);       // MN
    	//registerTask(new Langerman10(10),stopCriteria, evaluationsOnDimension, 0.000001);   // MN
    	registerTask(new FletcherPowell2(2),stopCriteria, evaluationsOnDimension, 0.000001);  // MN
    	registerTask(new FletcherPowell5(5),stopCriteria, evaluationsOnDimension, 0.000001);  // MN
    	registerTask(new FletcherPowell10(10),stopCriteria, evaluationsOnDimension, 0.000001);// MN
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
        return "KarabogaBenchmark";
    }
    /* (non-Javadoc)
     * @see org.um.feri.ears.benchmark.RatingBenchmark#getInfo()
     */
    @Override
    public String getInfo() {
        return "";
    }
    
}
