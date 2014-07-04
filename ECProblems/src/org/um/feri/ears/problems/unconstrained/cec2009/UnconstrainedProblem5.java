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
import org.um.feri.ears.problems.moo.functions.UP5_1;
import org.um.feri.ears.problems.moo.functions.UP5_2;


public class UnconstrainedProblem5 extends MOProblem {
	
	int N;
	double epsilon;

	 /**
	  * Constructor.
	  * Creates a default instance of problem CEC2009_UF2 (30 decision variables)
	  * @param solutionType The solution type must "Real" or "BinaryReal".
	  */
	  public UnconstrainedProblem5() {
		  this(30, 10, 0.1); // 30 variables, N =10, epsilon = 0.1
	  } // CEC2009_UF2

	 /**
	  * Creates a new instance of problem CEC2009_UF2.
	  * @param numberOfVariables Number of variables.
	  * @param solutionType The solution type must "Real" or "BinaryReal".
	  */
	  public UnconstrainedProblem5(Integer numberOfVariables, int N, double epsilon) {
	    dim  = numberOfVariables;
	    numberOfObjectives =  2;
	    numberOfConstraints=  0;
	    
	    name = "CEC2009 Unconstrained Problem 05";
	    file_name = "UF5";
	    
	    this.N = N;
		this.epsilon = epsilon;

		interval = new double[numberOfVariables];
		intervalL = new double[numberOfVariables];

		intervalL[0] = 0.0;
		interval[0] = 1.0;
		for (int var = 1; var < numberOfVariables; var++) {
			intervalL[var] = -1.0;
			interval[var] = 1.0;
		}

		this.addProblem(new UP5_1(dim,N,epsilon));
		this.addProblem(new UP5_2(dim,N,epsilon));

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
