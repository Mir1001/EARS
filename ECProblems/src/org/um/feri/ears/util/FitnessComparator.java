package org.um.feri.ears.util;

import java.util.Comparator;

import org.um.feri.ears.problems.moo.MOIndividual;

public class FitnessComparator implements Comparator{
   
    /**
	* Compares two solutions.
	* @param o1 Object representing the first <code>Solution</code>.
	* @param o2 Object representing the second <code>Solution</code>.
	* @return -1, or 0, or 1 if o1 is less than, equal, or greater than o2,
	* respectively.
	*/
	public int compare(Object o1, Object o2) {
		if (o1 == null)
			return 1;
		else if (o2 == null)
			return -1;

		double fitness1 = ((MOIndividual) o1).getFitness();
		double fitness2 = ((MOIndividual) o2).getFitness();
		if (fitness1 < fitness2) {
			return -1;
		}

		if (fitness1 > fitness2) {
			return 1;
		}

		return 0;
	}
}
