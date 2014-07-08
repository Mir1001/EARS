//  Distance.java
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

package org.um.feri.ears.util;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOParetoIndividual;

/**
 * This class implements some utilities for calculating distances
 */
public class Distance {

	/**
	 * Constructor.
	 */
	public Distance() {
		// do nothing.
	}
    
    /** 
    * Returns a matrix with distances between solutions in a 
    * <code>SolutionSet</code>.
    * @param solutionSet The <code>SolutionSet</code>.
    * @return a matrix with distances.
    */
	public double[][] distanceMatrix(MOParetoIndividual solutionSet) {
		MOIndividual solutionI, solutionJ;

		// The matrix of distances
		double[][] distance = new double[solutionSet.size()][solutionSet.size()];
		// -> Calculate the distances
		for (int i = 0; i < solutionSet.size(); i++) {
			distance[i][i] = 0.0;
			solutionI = solutionSet.get(i);
			for (int j = i + 1; j < solutionSet.size(); j++) {
				solutionJ = solutionSet.get(j);
				distance[i][j] = this.distanceBetweenObjectives(solutionI, solutionJ);
				distance[j][i] = distance[i][j];
			}
		}
		// ->Return the matrix of distances
		return distance;
	}
    
    /** Returns the minimum distance from a <code>Solution</code> to a 
    * <code>SolutionSet according to the objective values</code>.
    * @param solution The <code>Solution</code>.
    * @param solutionSet The <code>SolutionSet</code>.
    * @return The minimum distance between solution and the set.
    */  
	public double distanceToSolutionSetInObjectiveSpace(MOIndividual solution, MOParetoIndividual solutionSet) {
		// At start point the distance is the max
		double distance = Double.MAX_VALUE;

		// found the min distance respect to population
		for (int i = 0; i < solutionSet.size(); i++) {
			double aux = this.distanceBetweenObjectives(solution, solutionSet.get(i));
			if (aux < distance)
				distance = aux;
		}

		// ->Return the best distance
		return distance;
	}
    
    /** Returns the minimum distance from a <code>Solution</code> to a 
    * <code>SolutionSet according to the encodings.variable values</code>.
    * @param solution The <code>Solution</code>.
    * @param solutionSet The <code>SolutionSet</code>.
    * @return The minimum distance between solution and the set.
    */  
	public double distanceToSolutionSetInSolutionSpace(MOIndividual solution, MOParetoIndividual solutionSet) {
		// At start point the distance is the max
		double distance = Double.MAX_VALUE;

		// found the min distance respect to population
		for (int i = 0; i < solutionSet.size(); i++) {
			double aux = this.distanceBetweenSolutions(solution, solutionSet.get(i));
			if (aux < distance)
				distance = aux;
		}
		// ->Return the best distance
		return distance;
	}
    
    /** Returns the distance between two solutions in the search space.
    *  @param solutionI The first <code>Solution</code>. 
    *  @param solutionJ The second <code>Solution</code>.
    *  @return the distance between solutions. 
    */
	public double distanceBetweenSolutions(MOIndividual solutionI, MOIndividual solutionJ) {
		double distance = 0.0;
		if ((solutionI.getDecisionVariables() != null)
				&& (solutionJ.getDecisionVariables() != null)) {
			double[] decisionVariableI = solutionI.getDecisionVariables();
			double[] decisionVariableJ = solutionJ.getDecisionVariables();

			double diff; // Auxiliar var
			// -> Calculate the Euclidean distance
			for (int i = 0; i < decisionVariableI.length; i++) {
				diff = decisionVariableI[i] - decisionVariableJ[i];
				distance += Math.pow(diff, 2.0);
			}
		}
		// -> Return the euclidean distance
		return Math.sqrt(distance);
	}
    
    /** Returns the distance between two solutions in objective space.
    *  @param solutionI The first <code>Solution</code>.
    *  @param solutionJ The second <code>Solution</code>.
    *  @return the distance between solutions in objective space.
    */
	public double distanceBetweenObjectives(MOIndividual solutionI, MOIndividual solutionJ) {
		double diff; // Auxiliar var
		double distance = 0.0;
		// -> Calculate the euclidean distance
		for (int nObj = 0; nObj < solutionI.numberOfObjectives(); nObj++) {
			diff = solutionI.getObjective(nObj) - solutionJ.getObjective(nObj);
			distance += Math.pow(diff, 2.0);
		}

		// Return the euclidean distance
		return Math.sqrt(distance);
	}
           
    /** Assigns crowding distances to all solutions in a <code>SolutionSet</code>.
    * @param solutionSet The <code>SolutionSet</code>.
    * @param nObjs Number of objectives.
    */
	public void crowdingDistanceAssignment(MOParetoIndividual solutionSet, int nObjs) {
		int size = solutionSet.size();

		if (size == 0)
			return;

		if (size == 1) {
			solutionSet.get(0).setCrowdingDistance(Double.POSITIVE_INFINITY);
			return;
		}

		if (size == 2) {
			solutionSet.get(0).setCrowdingDistance(Double.POSITIVE_INFINITY);
			solutionSet.get(1).setCrowdingDistance(Double.POSITIVE_INFINITY);
			return;
		}

		// Use a new SolutionSet to evite alter original solutionSet
		MOParetoIndividual front = new MOParetoIndividual(size);
		for (int i = 0; i < size; i++) {
			front.add(solutionSet.get(i));
		}

		for (int i = 0; i < size; i++)
			front.get(i).setCrowdingDistance(0.0);

		double objetiveMaxn;
		double objetiveMinn;
		double distance;

		for (int i = 0; i < nObjs; i++) {
			// Sort the population by Obj n
			front.sort(new ObjectiveComparator(i));
			objetiveMinn = front.get(0).getObjective(i);
			objetiveMaxn = front.get(front.size() - 1).getObjective(i);

			// Set de crowding distance
			front.get(0).setCrowdingDistance(Double.POSITIVE_INFINITY);
			front.get(size - 1).setCrowdingDistance(Double.POSITIVE_INFINITY);

			for (int j = 1; j < size - 1; j++) {
				distance = front.get(j + 1).getObjective(i) - front.get(j - 1).getObjective(i);
				distance = distance / (objetiveMaxn - objetiveMinn);
				distance += front.get(j).getCrowdingDistance();
				front.get(j).setCrowdingDistance(distance);
			}
		}
	}
}

