//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.problems.moo;

import java.util.ArrayList;
import java.util.List;

import org.um.feri.ears.problems.Problem;

public abstract class MOProblem extends Problem {
	  
	protected int numberOfObjectives;

	protected int numberOfConstraints;
	
	protected String file_name;
	
	protected List<Problem> functions = new ArrayList<Problem>();
	
	public void addProblem(Problem p) {
		functions.add(p);
    }
	
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

	public String getFileName() {
		return file_name;
	}

}
