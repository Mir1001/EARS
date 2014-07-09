//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.algorithms.moo.spea2;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.EnumAlgorithmParameters;
import org.um.feri.ears.operators.BinaryTournament2;
import org.um.feri.ears.operators.PolynomialMutation;
import org.um.feri.ears.operators.SBXCrossover;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem2;
import org.um.feri.ears.util.Ranking;


public class SPEA2 extends Algorithm{

	Task task;
	int populationSize;
	int archiveSize = 100;
	MOParetoIndividual population;
	MOParetoIndividual archive;
	int num_var;
	int num_obj;

	public static final int TOURNAMENTS_ROUNDS = 1;

	public SPEA2() {
		this(100);
	}

	public SPEA2(int populationSize) {
		this.populationSize = populationSize;

		au = new Author("miha", "miha.ravber at gamil.com");
		ai = new AlgorithmInfo(
				"SPEA2",
				"\\bibitem{Zitzler2002}\nE.~Zitzler,M.~Laumanns,L.~Thiele\n\\newblock SPEA2: Improving the Strength Pareto Evolutionary Algorithm for Multiobjective Optimization.\n\\newblock \\emph{EUROGEN 2001. Evolutionary Methods for Design, Optimization and Control with Applications to Industrial Problems}, 95--100, 2002.\n",
				"SPEA2", "Strength Pareto Evolutionary Algorithm");
		ai.addParameter(EnumAlgorithmParameters.POP_SIZE, populationSize + "");
	}

	@Override
	public Individual run(Task taskProblem) throws StopCriteriaException {
		task = taskProblem;
		num_var = task.getDimensions();
		num_obj = task.getNumberOfObjectives();

		init();
		start();

		Ranking ranking = new Ranking(archive);
		MOParetoIndividual best = ranking.getSubfront(0);
		best.setFileName(task.getProblemFileName());
		double IGD_value = best.getEval();
		System.out.println("IGD value: " + IGD_value);

		best.printFeasibleFUN("FUN_SPEA2");
		best.printVariablesToFile("VAR");
		best.printObjectivesToFile("FUN");

		return best;
	}

	@Override
	public void resetDefaultsBeforNewRun() {
	}

	private void init() {
		population = new MOParetoIndividual(populationSize);
		archive = new MOParetoIndividual(archiveSize);
	}

	public void start() throws StopCriteriaException {

		MOParetoIndividual offspringPopulation;

		BinaryTournament2 bt2 = new BinaryTournament2();
		SBXCrossover sbx = new SBXCrossover(0.9, 20.0);
		PolynomialMutation plm = new PolynomialMutation(1.0 / num_var, 20.0);

		// -> Create the initial solutionSet
		for (int i = 0; i < populationSize; i++) {
			if (task.isStopCriteria())
				return;
			MOIndividual newSolution = new MOIndividual(task.getRandomMOIndividual());
			// problem.evaluateConstraints(newSolution);;
			population.add(newSolution);
		}

		while (!task.isStopCriteria()) {
			MOParetoIndividual union = ((MOParetoIndividual) population).union(archive);
			Spea2fitness spea = new Spea2fitness(union);
			spea.fitnessAssign();
			archive = spea.environmentalSelection(archiveSize);
			// Create a new offspringPopulation
			offspringPopulation = new MOParetoIndividual(populationSize);
			MOIndividual[] parents = new MOIndividual[2];
			while (offspringPopulation.size() < populationSize) {
				int j = 0;
				do {
					j++;
					parents[0] = (MOIndividual) bt2.execute(archive);
				} while (j < SPEA2.TOURNAMENTS_ROUNDS);
				int k = 0;
				do {
					k++;
					parents[1] = (MOIndividual) bt2.execute(archive);
				} while (k < SPEA2.TOURNAMENTS_ROUNDS);

				// make the crossover
				MOIndividual[] offSpring = (MOIndividual[]) sbx.execute(
						parents, task);
				plm.execute(offSpring[0], task);
				if (task.isStopCriteria())
					break;
				task.eval(offSpring[0]);
				// problem.evaluateConstraints(offSpring[0]);
				offspringPopulation.add(offSpring[0]);
			}
			// End Create a offSpring solutionSet
			population = offspringPopulation;
		}
	}
}
