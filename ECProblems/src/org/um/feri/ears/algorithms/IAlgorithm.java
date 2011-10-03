package org.um.feri.ears.algorithms;

import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Task;

public interface IAlgorithm {
	/**
	 * Search for best solution
	 * 
	 * @param taskProblem
	 * @return best solution
	 */
	public Individual run(Task taskProblem);
	public void setDebug(boolean d);
	public Author getImplementationAuthor();
	public AlgorithmInfo getAlgorithmInfo();
	
}
