package org.um.feri.ears.problems.unconstrained.cec2009;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOProblem;
import org.um.feri.ears.problems.moo.functions.UP1_F2_1;
import org.um.feri.ears.problems.moo.functions.UP1_F2_2;
import org.um.feri.ears.problems.moo.functions.UP2_F5_1;
import org.um.feri.ears.problems.moo.functions.UP2_F5_2;
import org.um.feri.ears.problems.moo.functions.UP3_1;
import org.um.feri.ears.problems.moo.functions.UP3_2;
import org.um.feri.ears.problems.moo.functions.UP4_1;
import org.um.feri.ears.problems.moo.functions.UP4_2;
import org.um.feri.ears.problems.moo.functions.UP7_1;
import org.um.feri.ears.problems.moo.functions.UP7_2;
import org.um.feri.ears.problems.moo.functions.UP8_F6_1;
import org.um.feri.ears.problems.moo.functions.UP8_F6_2;
import org.um.feri.ears.problems.moo.functions.UP8_F6_3;
import org.um.feri.ears.problems.moo.functions.UP9_1;
import org.um.feri.ears.problems.moo.functions.UP9_2;
import org.um.feri.ears.problems.moo.functions.UP9_3;




public class UnconstrainedProblem9 extends MOProblem {

	 double epsilon;
	 /**
	  * Constructor.
	  * Creates a default instance of problem CEC2009_UF2 (30 decision variables)
	  * @param solutionType The solution type must "Real" or "BinaryReal".
	  */
	  public UnconstrainedProblem9() {
	    this(30, 0.1); // 30 variables by default
	  } // CEC2009_UF2

	 /**
	  * Creates a new instance of problem CEC2009_UF2.
	  * @param numberOfVariables Number of variables.
	  * @param solutionType The solution type must "Real" or "BinaryReal".
	  */
	  public UnconstrainedProblem9(Integer numberOfVariables, double epsilon) {
	    dim  = numberOfVariables;
	    numberOfObjectives =  3;
	    numberOfConstraints=  0;
	    
	    name = "CEC2009 Unconstrained Problem 09";
	    file_name = "UF9";
	    
	    this.epsilon = epsilon;

		interval = new double[numberOfVariables];
		intervalL = new double[numberOfVariables];

		intervalL[0] = 0.0;
		interval[0] = 1.0;
		intervalL[1] = 0.0;
		interval[1] = 1.0;
		for (int var = 2; var < numberOfVariables; var++) {
			intervalL[var] = -2.0;
			interval[var] = 2.0;
		}

		this.addProblem(new UP9_1(dim,epsilon));
		this.addProblem(new UP9_2(dim,epsilon));
		this.addProblem(new UP9_3(dim,epsilon));

	  }

	  /**
	   * Evaluates a solution.
	   * @param solution The solution to evaluate.
	   * @throws JMException
	   */
	public void evaluate(MOIndividual solution) {
		double[] decisionVariables = solution.getX();

		double[] x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] = decisionVariables[i];
		
		double obj[] =new double [functions.size()];
        for (int i=0; i<obj.length; i++) {
        	obj[i] = functions.get(i).eval(x);
        }
        solution.setEval(obj);

	}

	@Override
	public double[] evaluate(double[] ds) {
		
		double[] x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] = ds[i];

		double obj[] =new double [functions.size()];
        for (int i=0; i<obj.length; i++) {
        	obj[i] = functions.get(i).eval(x);
        }
        return obj;
	}

	@Override
	public double eval(double[] ds) {
		// TODO Auto-generated method stub
		return 0;
	}
}
