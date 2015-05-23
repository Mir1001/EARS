//  GDE3.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.um.feri.ears.algorithms.moo.gde3;

import java.util.Comparator;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.EnumAlgorithmParameters;
import org.um.feri.ears.operators.DifferentialEvolutionCrossover;
import org.um.feri.ears.operators.DifferentialEvolutionSelection;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.util.CrowdingComparator;
import org.um.feri.ears.util.Distance;
import org.um.feri.ears.util.DominanceComparator;
import org.um.feri.ears.util.Ranking;

/**
 * This class implements the GDE3 algorithm. 
 */
public class GDE3 extends Algorithm {
    
	MOParetoIndividual population          ;
	MOParetoIndividual offspringPopulation ;
	MOParetoIndividual union               ;
	
	Task task;
	int num_var;
	int num_obj;
	
	int populationSize;
	
  /**
  * Constructor
  * @param problem Problem to solve
  */
  public GDE3(){
    this (100) ;
  }
  
  public GDE3(int populationSize) {
		this.populationSize = populationSize;

		au = new Author("miha", "miha.ravber at gamil.com");
		ai = new AlgorithmInfo(
				"GDE3",
				"\\bibitem{Kukkonen2009}\nS.~Kukkonen, J.~Lampinen\n\\newblock Performance Assessment of Generalized Differential Evolution 3 with a Given Set of Constrained Multi-Objective Test Problems.\n\\newblock \\emph{2009 IEEE Congress on Evolutionary Computation}, 1943--1950, 2009.\n",
				"GDE3", "Generalized Differential Evolution 3");
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

		// Return the first non-dominated front
		Ranking ranking = new Ranking(population);
		MOParetoIndividual best = ranking.getSubfront(0);
		best.setFileName(task.getProblemFileName());

		if(display_data)
		{
			best.displayAllQulaityIndicators();
			best.displayData(this.getAlgorithmInfo().getPublishedAcronym(),task.getProblemShortName());
		}
		if(save_data)
		{
			best.saveData(this.getAlgorithmInfo().getPublishedAcronym(),task.getProblemShortName());
			best.printFeasibleFUN("FUN_NSGAII");
			best.printVariablesToFile("VAR");
			best.printObjectivesToFile("FUN");
		}

		return best;
	}

	private void init() {
		population = new MOParetoIndividual(populationSize);
	}

	@Override
	public void resetDefaultsBeforNewRun() {
	}
  
	/**
	 * Runs of the GDE3 algorithm.
	 * @return a <code>SolutionSet</code> that is a set of non dominated solutions as a result of the algorithm execution
	 * @throws StopCriteriaException
	 */
	public void start() throws StopCriteriaException {

		Distance distance;
		Comparator dominance;

		distance = new Distance();
		dominance = new DominanceComparator();

		MOIndividual parent[] = null;

		DifferentialEvolutionCrossover dec = new DifferentialEvolutionCrossover();
		DifferentialEvolutionSelection des = new DifferentialEvolutionSelection();

		// Create the initial solutionSet
		MOIndividual newSolution;
		for (int i = 0; i < populationSize; i++) {
			if (task.isStopCriteria())
				return;
			newSolution = new MOIndividual(task.getRandomMOIndividual());
			// problem.evaluateConstraints(newSolution);
			population.add(newSolution);
		}

		// Generations ...
		while (!task.isStopCriteria()) {
			// Create the offSpring solutionSet
			offspringPopulation = new MOParetoIndividual(populationSize * 2);

			for (int i = 0; i < populationSize; i++) {
				// Obtain parents. Two parameters are required: the population and the index of the current individual
				try {
					parent = (MOIndividual[]) des.execute(new Object[] {population, i });
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("The population has less than four solutions");
					break;
				}

				MOIndividual child;
				// Crossover. Two parameters are required: the current
				// individual and the array of parents
				child = (MOIndividual) dec.execute(new Object[] { population.get(i), parent }, task);

				if (task.isStopCriteria())
					break;
				task.eval(child);

				// Dominance test
				int result;
				result = dominance.compare(population.get(i), child);
				if (result == -1) { // Solution i dominates child
					offspringPopulation.add(population.get(i));
				} else if (result == 1) { // child dominates
					offspringPopulation.add(child);
				} else { // the two solutions are non-dominated
					offspringPopulation.add(child);
					offspringPopulation.add(population.get(i));
				}
			}

			// Ranking the offspring population
			Ranking ranking = new Ranking(offspringPopulation);

			int remain = populationSize;
			int index = 0;
			MOParetoIndividual front = null;
			population.clear();

			// Obtain the next front
			front = ranking.getSubfront(index);

			while ((remain > 0) && (remain >= front.size())) {
				// Assign crowding distance to individuals
				distance.crowdingDistanceAssignment(front, num_obj);
				// Add the individuals of this front
				for (int k = 0; k < front.size(); k++) {
					population.add(front.get(k));
				}

				// Decrement remain
				remain = remain - front.size();

				// Obtain the next front
				index++;
				if (remain > 0) {
					front = ranking.getSubfront(index);
				}
			}

			// remain is less than front(index).size, insert only the best one
			if (remain > 0) { // front contains individuals to insert
				while (front.size() > remain) {
					distance.crowdingDistanceAssignment(front, num_obj);
					front.remove(front.indexWorst(new CrowdingComparator()));
				}
				for (int k = 0; k < front.size(); k++) {
					population.add(front.get(k));
				}
				remain = 0;
			}
		}
	}
}
