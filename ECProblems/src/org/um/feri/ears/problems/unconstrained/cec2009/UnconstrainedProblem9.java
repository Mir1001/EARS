package org.um.feri.ears.problems.unconstrained.cec2009;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOProblem;
import org.um.feri.ears.problems.moo.functions.UP9_1;
import org.um.feri.ears.problems.moo.functions.UP9_2;
import org.um.feri.ears.problems.moo.functions.UP9_3;
import org.um.feri.ears.quality_indicator.InvertedGenerationalDistance;
import org.um.feri.ears.quality_indicator.QualityIndicator;

public class UnconstrainedProblem9 extends MOProblem {

	double epsilon;

	/**
	 * Constructor. Creates a default instance of problem CEC2009_UnconstrainedProblem9 (30 decision variables)
	 */
	public UnconstrainedProblem9() {
		this(30, 0.1, new InvertedGenerationalDistance()); // 30 variables by default
	}

	/**
	 * Creates a new instance of problem CEC2009_UnconstrainedProblem9.
	 * @param numberOfVariables Number of variables.
	 */
	public UnconstrainedProblem9(Integer numberOfVariables, double epsilon, QualityIndicator type) {
		super(type);
		minimum = type.isMin(); // comparison depends on metrics (hypervolume max is better; IGD min is better)
		dim = numberOfVariables;
		numberOfObjectives = 3;
		numberOfConstraints = 0;

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

		this.addObjective(new UP9_1(dim, epsilon));
		this.addObjective(new UP9_2(dim, epsilon));
		this.addObjective(new UP9_3(dim, epsilon));
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
		// TODO Auto-generated method stub
		return 0;
	}
}
