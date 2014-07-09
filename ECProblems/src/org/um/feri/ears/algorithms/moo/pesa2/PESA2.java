//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.algorithms.moo.pesa2;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.EnumAlgorithmParameters;
import org.um.feri.ears.operators.PESA2Selection;
import org.um.feri.ears.operators.PolynomialMutation;
import org.um.feri.ears.operators.SBXCrossover;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOParetoIndividual;

public class PESA2 extends Algorithm{

	int populationSize = 100;
	int archiveSize = 100;
	int bisections = 5;
	MOParetoIndividual population;
	AdaptiveGridArchive archive;
	int num_var;
	int num_obj;
	Task task;

	public PESA2() {
		this(100);
	}

	public PESA2(int populationSize) {
		this.populationSize = populationSize;

		au = new Author("miha", "miha.ravber at gamil.com");
		ai = new AlgorithmInfo(
				"PESA2",
				"\\bibitem{corne2001}\nD.W.~Corne,N.R.~Jerram,J.D.~Knowles,M.J.~Oates\n\\newblock PESA-II: Region-based Selection in Evolutionary Multiobjective Optimization.\n\\newblock \\emph{Proceedings of the Genetic and Evolutionary Computation Conference (GECCO-2001)}, 283--290, 2001.\n",
				"PESA2", "Pareto Envelope-Based Selection Algorithm");
		ai.addParameter(EnumAlgorithmParameters.POP_SIZE, populationSize + "");
	}

	@Override
	public Individual run(Task taskProblem) throws StopCriteriaException {
		task = taskProblem;
		num_var = task.getDimensions();
		num_obj = task.getNumberOfObjectives();

		init();
		start();

		archive.setFileName(task.getProblemFileName());
		double IGD_value = archive.getEval();
		System.out.println("IGD value: " + IGD_value);

		archive.printFeasibleFUN("FUN_SPEA2");
		archive.printVariablesToFile("VAR");
		archive.printObjectivesToFile("FUN");

		return archive;
	}

	@Override
	public void resetDefaultsBeforNewRun() {
	}

	private void init() {
		archive = new AdaptiveGridArchive(archiveSize, bisections, num_obj);
		population = new MOParetoIndividual(populationSize);
	}

	public void start() throws StopCriteriaException {

		SBXCrossover sbx = new SBXCrossover(0.9, 20.0);
		PolynomialMutation plm = new PolynomialMutation(1.0 / num_var, 20.0);
		PESA2Selection selection = new PESA2Selection();

		// Create the initial individual and evaluate it and his constraints
		for (int i = 0; i < populationSize; i++) {
			if (task.isStopCriteria())
				return;
			MOIndividual solution = new MOIndividual(task.getRandomMOIndividual());
			// problem.evaluateConstraints(solution);
			population.add(solution);
		}

		// Incorporate non-dominated solution to the archive
		for (int i = 0; i < population.size(); i++) {
			archive.add(population.get(i)); // Only non dominated are accepted by the archive
		}

		// Clear the init solutionSet
		population.clear();

		// Iterations....
		MOIndividual[] parents = new MOIndividual[2];

		do {
			// -> Create the offSpring solutionSet
			while (population.size() < populationSize) {
				parents[0] = (MOIndividual) selection.execute(archive);
				parents[1] = (MOIndividual) selection.execute(archive);

				MOIndividual[] offSpring = (MOIndividual[]) sbx.execute(parents, task);
				plm.execute(offSpring[0], task);
				if (task.isStopCriteria())
					break;
				task.eval(offSpring[0]);
				// problem.evaluateConstraints(offSpring[0]);
				population.add(offSpring[0]);
			}

			for (int i = 0; i < population.size(); i++)
				archive.add(population.get(i));

			// Clear the solutionSet
			population.clear();

		} while (!task.isStopCriteria());
	}
}
