package org.um.feri.ears.examples;

import org.um.feri.ears.algorithms.IAlgorithm;
import org.um.feri.ears.algorithms.random.RandomAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.unconstrained.ProblemSphere;

public class RandomAlgorithmTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Task sphere=new Task(EnumStopCriteria.EVALUATIONS,10000,0.001,new ProblemSphere(4));
		IAlgorithm test = new RandomAlgorithm(true);
		Individual best = test.run(sphere);
		System.out.println("Best is:"+best);
	}

}
