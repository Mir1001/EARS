package org.um.feri.ears.algorithms.moo.pesa2;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.util.Util;

public class AdaptiveGrid {

	private int bisections;

	private int objectives;

	private int[] hypercubes;

	private double[] lowerLimits;

	private double[] upperLimits;

	private double[] divisionSize;

	private int mostPopulated;

	private int[] occupied;

	public AdaptiveGrid(int bisections, int objetives) {
		this.bisections = bisections;
		this.objectives = objetives;
		lowerLimits = new double[objectives];
		upperLimits = new double[objectives];
		divisionSize = new double[objectives];
		hypercubes = new int[(int) Math.pow(2.0, bisections * objectives)];

		for (int i = 0; i < hypercubes.length; i++)
			hypercubes[i] = 0;
	}

	private void updateLimits(MOParetoIndividual solutionSet) {
		// Init the lower and upper limits
		for (int obj = 0; obj < objectives; obj++) {
			// Set the lower limits to the max real
			lowerLimits[obj] = Double.MAX_VALUE;
			// Set the upper limits to the min real
			upperLimits[obj] = Double.MIN_VALUE;
		}

		// Find the max and min limits of objetives into the population
		for (int ind = 0; ind < solutionSet.size(); ind++) {
			MOIndividual tmpIndividual = solutionSet.get(ind);
			for (int obj = 0; obj < objectives; obj++) {
				if (tmpIndividual.getObjective(obj) < lowerLimits[obj]) {
					lowerLimits[obj] = tmpIndividual.getObjective(obj);
				}
				if (tmpIndividual.getObjective(obj) > upperLimits[obj]) {
					upperLimits[obj] = tmpIndividual.getObjective(obj);
				}
			}
		}
	}

	private void addSolutionSet(MOParetoIndividual solutionSet) {
		// Calculate the location of all individuals and update the grid
		mostPopulated = 0;
		int location;

		for (int ind = 0; ind < solutionSet.size(); ind++) {
			location = location(solutionSet.get(ind));
			hypercubes[location]++;
			if (hypercubes[location] > hypercubes[mostPopulated])
				mostPopulated = location;
		}

		// The grid has been updated, so also update ocuppied's hypercubes
		calculateOccupied();
	}

	public void updateGrid(MOParetoIndividual solutionSet) {
		// Update lower and upper limits
		updateLimits(solutionSet);

		// Calculate the division size
		for (int obj = 0; obj < objectives; obj++) {
			divisionSize[obj] = upperLimits[obj] - lowerLimits[obj];
		}

		// Clean the hypercubes
		for (int i = 0; i < hypercubes.length; i++) {
			hypercubes[i] = 0;
		}

		// Add the population
		addSolutionSet(solutionSet);
	}

	public void updateGrid(MOIndividual solution, MOParetoIndividual solutionSet) {

		int location = location(solution);
		if (location == -1) {// Re-build the Adaptative-Grid Update lower and upper limits
			updateLimits(solutionSet);

			// Actualize the lower and upper limits whit the individual
			for (int obj = 0; obj < objectives; obj++) {
				if (solution.getObjective(obj) < lowerLimits[obj])
					lowerLimits[obj] = solution.getObjective(obj);
				if (solution.getObjective(obj) > upperLimits[obj])
					upperLimits[obj] = solution.getObjective(obj);
			}

			// Calculate the division size
			for (int obj = 0; obj < objectives; obj++) {
				divisionSize[obj] = upperLimits[obj] - lowerLimits[obj];
			}

			// Clean the hypercube
			for (int i = 0; i < hypercubes.length; i++) {
				hypercubes[i] = 0;
			}
			// add the population
			addSolutionSet(solutionSet);
		}
	}

	public int location(MOIndividual solution) {
		// Create a int [] to store the range of each objetive
		int[] position = new int[objectives];

		// Calculate the position for each objetive
		for (int obj = 0; obj < objectives; obj++) {

			if ((solution.getObjective(obj) > upperLimits[obj])
					|| (solution.getObjective(obj) < lowerLimits[obj]))
				return -1;
			else if (solution.getObjective(obj) == lowerLimits[obj])
				position[obj] = 0;
			else if (solution.getObjective(obj) == upperLimits[obj])
				position[obj] = ((int) Math.pow(2.0, bisections)) - 1;
			else {
				double tmpSize = divisionSize[obj];
				double value = solution.getObjective(obj);
				double account = lowerLimits[obj];
				int ranges = (int) Math.pow(2.0, bisections);
				for (int b = 0; b < bisections; b++) {
					tmpSize /= 2.0;
					ranges /= 2;
					if (value > (account + tmpSize)) {
						position[obj] += ranges;
						account += tmpSize;
					}
				}
			}
		}

		// Calcualate the location into the hypercubes
		int location = 0;
		for (int obj = 0; obj < objectives; obj++) {
			location += position[obj] * Math.pow(2.0, obj * bisections);
		}
		return location;
	}

	public int getMostPopulated() {
		return mostPopulated;
	}

	public int getLocationDensity(int location) {
		return hypercubes[location];
	}

	public void removeSolution(int location) {
		// Decrease the solutions in the location specified.
		hypercubes[location]--;

		// Update the most poblated hypercube
		if (location == mostPopulated)
			for (int i = 0; i < hypercubes.length; i++)
				if (hypercubes[i] > hypercubes[mostPopulated])
					mostPopulated = i;

		// If hypercubes[location] now becomes to zero, then update ocupped hypercubes
		if (hypercubes[location] == 0)
			this.calculateOccupied();
	}

	public void addSolution(int location) {
		// Increase the solutions in the location specified.
		hypercubes[location]++;

		// Update the most poblated hypercube
		if (hypercubes[location] > hypercubes[mostPopulated])
			mostPopulated = location;

		// if hypercubes[location] becomes to one, then recalculate
		// the occupied hypercubes
		if (hypercubes[location] == 1)
			this.calculateOccupied();
	}

	public int getBisections() {
		return bisections;
	}

	public String toString() {
		String result = "Grid\n";
		for (int obj = 0; obj < objectives; obj++) {
			result += "Objective " + obj + " " + lowerLimits[obj] + " " + upperLimits[obj] + "\n";
		}
		return result;
	}

	public int rouletteWheel() {
		// Calculate the inverse sum
		double inverseSum = 0.0;
		for (int aHypercubes_ : hypercubes) {
			if (aHypercubes_ > 0) {
				inverseSum += 1.0 / (double) aHypercubes_;
			}
		}

		// Calculate a random value between 0 and sumaInversa
		double random = Util.rnd.nextDouble() * inverseSum;
		int hypercube = 0;
		double accumulatedSum = 0.0;
		while (hypercube < hypercubes.length) {
			if (hypercubes[hypercube] > 0) {
				accumulatedSum += 1.0 / (double) hypercubes[hypercube];
			}

			if (accumulatedSum > random) {
				return hypercube;
			}
			hypercube++;
		}
		return hypercube;
	}

	public int calculateOccupied() {
		int total = 0;
		for (int aHypercubes_ : hypercubes) {
			if (aHypercubes_ > 0) {
				total++;
			}
		}

		occupied = new int[total];
		int base = 0;
		for (int i = 0; i < hypercubes.length; i++) {
			if (hypercubes[i] > 0) {
				occupied[base] = i;
				base++;
			}
		}
		return total;
	}

	public int occupiedHypercubes() {
		return occupied.length;
	}

	public int randomOccupiedHypercube() {
		int rand = Util.rnd.nextInt(occupied.length);
		return occupied[rand];
	}
}
