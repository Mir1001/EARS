package org.um.feri.ears.problems.unconstrained.cec2009;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOProblem;
import org.um.feri.ears.problems.moo.functions.UP2_F5_1;
import org.um.feri.ears.problems.moo.functions.UP2_F5_2;


public class UnconstrainedProblem2 extends MOProblem {

	/**
	 * Constructor. Creates a default instance of problem CEC2009_UnconstrainedProblem2 (30 decision variables)
	 */
	public UnconstrainedProblem2() {
		this(30); // 30 variables by default
	}

	/**
	 * Creates a new instance of problem CEC2009_UnconstrainedProblem2.
	 * @param numberOfVariables Number of variables.
	 */
	public UnconstrainedProblem2(Integer numberOfVariables) {
		dim = numberOfVariables;
		numberOfObjectives = 2;
		numberOfConstraints = 0;

		name = "CEC2009 Unconstrained Problem 02";
		file_name = "UF2";

		interval = new double[numberOfVariables];
		intervalL = new double[numberOfVariables];

		intervalL[0] = 0.0;
		interval[0] = 1.0;
		for (int var = 1; var < numberOfVariables; var++) {
			intervalL[var] = -1.0;
			interval[var] = 1.0;
		}

		this.addProblem(new UP2_F5_1(dim));
		this.addProblem(new UP2_F5_2(dim));
	}

	/**
	 * Evaluates a solution.
	 * @param solution The solution to evaluate.
	 */
	public void evaluate(MOIndividual solution) {
		double[] decisionVariables = solution.getX();

		double[] x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] = decisionVariables[i];

		double obj[] = new double[functions.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = functions.get(i).eval(x);
		}
		solution.setEval(obj);

	}

	@Override
	public double[] evaluate(double[] ds) {

		double[] x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] = ds[i];

		double obj[] = new double[functions.size()];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = functions.get(i).eval(x);
		}
		return obj;
	}

	@Override
	public double eval(double[] ds) {
		return 0;
	}
}
