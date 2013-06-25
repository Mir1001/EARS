package org.um.feri.ears.benchmark.example;

import org.um.feri.ears.algorithms.cma.CMA_ESAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.unconstrained.ProblemRosenbrock;

public class RunOneFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskWithReset my =new TaskWithReset(EnumStopCriteria.EVALUATIONS, 30000, 0.001, new ProblemRosenbrock(22));
		CMA_ESAlgorithm mya = new CMA_ESAlgorithm();
		try {
			System.out.println("Result is "+mya.run(my));
		} catch (StopCriteriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
