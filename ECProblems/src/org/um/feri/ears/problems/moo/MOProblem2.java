package org.um.feri.ears.problems.moo;

import org.um.feri.ears.problems.Problem;

public abstract class MOProblem2 extends Problem {
	  
	protected int numberOfObjectives;

	protected int numberOfConstraints;
	
	public abstract void evaluate(MOIndividual solution);
	

	public int getNumberOfObjectives() {
		return numberOfObjectives;
	}

	public void setNumberOfObjectives(int numberOfObjectives) {
		this.numberOfObjectives = numberOfObjectives;
	}
	
	public int getNumberOfVariables() {
		return dim;
	}

	public void setNumberOfVariables(int numberOfVariables) {
		setDim(numberOfVariables);
	}

	public int getNumberOfConstraints() {
		return numberOfConstraints;
	}

	public void setNumberOfConstraints(int numberOfConstraints) {
		this.numberOfConstraints = numberOfConstraints;
	}

	public double getLowerLimit(int i) {
		return intervalL[i];
	}

	public double getUpperLimit(int i) {
		return interval[i];
	}

	public double[] getVariables() {
		return getRandomVectorX();
	}
	
	public abstract double[] evaluate(double x[]);

}
