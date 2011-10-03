package org.um.feri.ears.algorithms.random;

import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.algorithms.IAlgorithm;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;

public class RandomAlgorithm implements IAlgorithm {
	Individual i;
	
	boolean debug=false;
	public RandomAlgorithm() {
		this.debug = false; 
	}
	public RandomAlgorithm(boolean d) {
		setDebug(d); 
	}
	
	@Override
	public Individual run(Task taskProblem) {
		Individual ii;
		try {
			i = taskProblem.getRandomIndividual();
			if (debug) System.out.println(taskProblem.getNumberOfEvaluations()+" "+i);
			while (!taskProblem.isStopCriteria()) {
				
				ii = taskProblem.getRandomIndividual();
				if (taskProblem.isFirstBetter(ii, i)) {
					i = ii;
					if (debug) System.out.println(taskProblem.getNumberOfEvaluations()+" "+i);
				}
			}
		} catch (StopCriteriaException e) {
			System.out.println(e.getMessage());
		}
		return i;

	}
	@Override
	public void setDebug(boolean d) {
		debug = d;
	}
	@Override
	public Author getImplementationAuthor() {
		return new Author("matej", "matej.crepinsek at uni-mb.si");
	}
	@Override
	public AlgorithmInfo getAlgorithmInfo() {
		return new AlgorithmInfo("","","RWS","Random Walk Simple");
	}

}
