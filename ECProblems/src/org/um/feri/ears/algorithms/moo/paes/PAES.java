//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.algorithms.moo.paes;

import java.util.Comparator;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.EnumAlgorithmParameters;
import org.um.feri.ears.algorithms.moo.pesa2.AdaptiveGridArchive;
import org.um.feri.ears.operators.PolynomialMutation;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.util.DominanceComparator;

public class PAES extends Algorithm {

	AdaptiveGridArchive archive;
	int archiveSize = 100;
	int bisections = 5;
	int num_var;
	int num_obj;
	Task task;

	public PAES() {
		this(100);
	}

	public PAES(int populationSize) {
		this.archiveSize = populationSize;

		au = new Author("miha", "miha.ravber at gamil.com");
		ai = new AlgorithmInfo(
				"PAES",
				"\\bibitem{knowles1999}\nJ.~Knowles,D.W.~Corne\n\\newblock The Pareto Archived Evolution Strategy: A New Baseline Algorithm for Pareto Multiobjective Optimisation.\n\\newblock \\emph{Proceedings of the Congress of Evolutionary Computation}, 98--105, 1999.\n",
				"PAES", "Pareto Archived Evolution Strategy");
		ai.addParameter(EnumAlgorithmParameters.POP_SIZE, populationSize + "");
	}

	@Override
	public Individual run(Task taskProblem) throws StopCriteriaException {
		task = taskProblem;
		num_var = task.getDimensions();
		num_obj = task.getNumberOfObjectives();

		long initTime = System.currentTimeMillis();
		init();
		start();
		long estimatedTime = System.currentTimeMillis() - initTime;
		System.out.println("Total execution time: "+estimatedTime + "ms");

		archive.setFileName(task.getProblemFileName());
		
		if(display_data)
		{
			archive.displayAllQulaityIndicators();
			archive.displayData(this.getAlgorithmInfo().getPublishedAcronym(),task.getProblemShortName());
		}
		if(save_data)
		{
			archive.saveData(this.getAlgorithmInfo().getPublishedAcronym(),task.getProblemShortName());
			archive.printFeasibleFUN("FUN_NSGAII");
			archive.printVariablesToFile("VAR");
			archive.printObjectivesToFile("FUN");
		}

		return archive;
	}

	@Override
	public void resetDefaultsBeforNewRun() {

	}

	private void init() {
		archive = new AdaptiveGridArchive(archiveSize, bisections, num_obj);
	}

	public void start() throws StopCriteriaException {

		Comparator dominance;

		PolynomialMutation plm = new PolynomialMutation(1.0 / num_var, 20.0);
		dominance = new DominanceComparator();

		if (task.isStopCriteria())
			return;
		MOIndividual solution = new MOIndividual(task.getRandomMOIndividual());
		// problem.evaluateConstraints(solution);

		archive.add(new MOIndividual(solution));

		do {
			// Create the mutate one
			MOIndividual mutatedIndividual = new MOIndividual(solution);
			plm.execute(mutatedIndividual, task);

			if (task.isStopCriteria())
				break;
			task.eval(mutatedIndividual);
			// problem.evaluateConstraints(mutatedIndividual);

			// Check dominance
			int flag = dominance.compare(solution, mutatedIndividual);

			if (flag == 1) { // If mutate solution dominate
				solution = new MOIndividual(mutatedIndividual);
				archive.add(mutatedIndividual);
			} else if (flag == 0) { // If none dominate the other
				if (archive.add(mutatedIndividual)) {
					solution = test(solution, mutatedIndividual, archive);
				}
			}
			/*
			 * if ((evaluations % 100) == 0) {
			 * archive.printObjectivesToFile("FUN"+evaluations) ;
			 * archive.printVariablesToFile("VAR"+evaluations) ;
			 * archive.printObjectivesOfValidSolutionsToFile("FUNV"+evaluations)
			 * ; }
			 */
		} while (!task.isStopCriteria());

	}

	public static MOIndividual test(MOIndividual solution,
			MOIndividual mutatedSolution, AdaptiveGridArchive archive) {

		int originalLocation = archive.getGrid().location(solution);
		int mutatedLocation = archive.getGrid().location(mutatedSolution);

		if (originalLocation == -1) {
			return new MOIndividual(mutatedSolution);
		}

		if (mutatedLocation == -1) {
			return new MOIndividual(solution);
		}

		if (archive.getGrid().getLocationDensity(mutatedLocation) < archive
				.getGrid().getLocationDensity(originalLocation)) {
			return new MOIndividual(mutatedSolution);
		}

		return new MOIndividual(solution);
	}

}
