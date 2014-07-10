//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.problems.moo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.quality_indicator.Hypervolume;
import org.um.feri.ears.quality_indicator.InvertedGenerationalDistance;
import org.um.feri.ears.quality_indicator.MetricsUtil;
import org.um.feri.ears.util.Util;

public class MOParetoIndividual extends Individual {

	public MOParetoIndividual(Individual i) {
		super(i);
	}
	
public List<MOIndividual> solutions;
	
	/**
	 * Maximum size of the solution set
	 */
	private int capacity = 0;
	private String file_name;
	
	public MOParetoIndividual() {
		solutions = new ArrayList<MOIndividual>();
		constrains = new double[1000];
	}

	public MOParetoIndividual(int maximumSize) {

		solutions = new ArrayList<MOIndividual>();
		capacity = maximumSize;
		constrains = new double[1000];
	}

	public boolean add(MOIndividual solution) {
		if (solutions.size() == capacity) {
			return false;
		}

		solutions.add(solution);
		return true;
	}

	public MOIndividual get(int i) {
		if (i >= solutions.size()) {
			throw new IndexOutOfBoundsException("Index out of Bound " + i);
		}
		return solutions.get(i);
	}

	@Override
	public double getEval() {
		
		InvertedGenerationalDistance IGD = new InvertedGenerationalDistance();
	    
	    double[][] front = this.writeObjectivesToMatrix();
	    MetricsUtil metrics_util = new MetricsUtil();
	    MOParetoIndividual true_front = metrics_util.readNonDominatedSolutionSet("pf_data/"+ getFileName() +".dat");
	    double[][] trueParetoFront = true_front.writeObjectivesToMatrix();
	    
	    double IGD_value = IGD.invertedGenerationalDistance(front, trueParetoFront, solutions.get(0).numberOfObjectives());
	    
	    Hypervolume hp = new Hypervolume();
	    double hyper_volume = hp.hypervolume(front, trueParetoFront, solutions.get(0).numberOfObjectives());
		
		return IGD_value;
	}
	
	@Override
	public double[] getX() {
		double[] x = new double[1000];
		return x;
	}

	public int getMaxSize() {
		return capacity;
	}

	public void clear() {
		solutions.clear();
	}

	public int size() {
		return solutions.size();
	}

	public void remove(int i) {
		solutions.remove(i);
	}
	
	 /** 
	  * Returns the index of the worst Solution using a <code>Comparator</code>.
	  * If there are more than one occurrences, only the index of the first one is returned
	  * @param comparator <code>Comparator</code> used to compare solutions.
	  * @return The index of the worst Solution attending to the comparator or 
	  * <code>-1<code> if the SolutionSet is empty
	  */
	public int indexWorst(Comparator comparator) {

		if ((solutions == null) || (this.solutions.isEmpty())) {
			return -1;
		}

		int index = 0;
		MOIndividual worstKnown = solutions.get(0), candidateSolution;
		int flag;
		for (int i = 1; i < solutions.size(); i++) {
			candidateSolution = solutions.get(i);
			flag = comparator.compare(worstKnown, candidateSolution);
			if (flag == -1) {
				index = i;
				worstKnown = candidateSolution;
			}
		}

		return index;

	}
	
	/** 
	 * Returns a new <code>MOParetoIndividual</code> which is the result of the union
	 * between the current solution set and the one passed as a parameter.
	 * @param MOParetoIndividual MOParetoIndividual to join with the current MOParetoIndividual.
	 * @return The result of the union operation.
	 */
	public MOParetoIndividual union(MOParetoIndividual solutionSet) {
		// Check the correct size. In development
		int newSize = this.size() + solutionSet.size();
		if (newSize < capacity)
			newSize = capacity;

		// Create a new population
		MOParetoIndividual union = new MOParetoIndividual(newSize);
		for (int i = 0; i < this.size(); i++) {
			union.add(this.get(i));
		}

		for (int i = this.size(); i < (this.size() + solutionSet.size()); i++) {
			union.add(solutionSet.get(i - this.size()));
		}

		return union;
	}

	public void sort(Comparator comparator) {
		if (comparator == null)
			return;

		Collections.sort(solutions, comparator);
	}
	
	/**
   * Copies the objectives of the solution set to a matrix
   * @return A matrix containing the objectives
   */
	public double[][] writeObjectivesToMatrix() {
		if (this.size() == 0) {
			return null;
		}
		double[][] objectives;
		objectives = new double[size()][get(0).numberOfObjectives()];
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < get(0).numberOfObjectives(); j++) {
				objectives[i][j] = get(i).getObjective(j);
			}
		}
		return objectives;
	}
	
    /**
     * Prints the objectives to a file in CSV format.
     * @param file_name The name of the file.
     */
	public void printObjectivesToFile(String file_name) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(file_name + ".csv");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (MOIndividual aSolutionsList_ : solutions) {
				// if (this.vector[i].getFitness()<1.0) {
				bw.write(aSolutionsList_.toStringCSV());
				bw.newLine();
				// }
			}
			/* Close the file */
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Prints the variables to a file.
     * @param file_name The name of the file.
     */
	public void printVariablesToFile(String file_name) {

		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			if (solutions.size() > 0) {
				int numberOfVariables = solutions.get(0).getDecisionVariables().length;
				for (MOIndividual aSolutionsList : solutions) {
					for (int j = 0; j < numberOfVariables; j++)
						bw.write(aSolutionsList.getDecisionVariables()[j] + " ");
					bw.newLine();
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Prints the objectives to a file.
     * @param file_name The name of the file.
     */
	public void printFeasibleFUN(String file_name) {
		try {
			FileOutputStream fos = new FileOutputStream(file_name);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			for (MOIndividual aSolutionsList : solutions) {
				bw.write(aSolutionsList.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Replaces a solution by a new one 
	 * @param position The position of the solution to replace
	 * @param MOIndividual The new solution
	 */
	public void replace(int position, MOIndividual solution) {
		if (position > solutions.size()) {
			solutions.add(solution);
		}
		solutions.remove(position);
		solutions.add(position, solution);
	}

	@Override
	public String toString() {
		String s = "";
		for (MOIndividual i : solutions) {
			s += "[" + Util.arrayToString(i.getObjectives()) + "] \n";
		}
		return s;
	}

	public String getFileName() {
		return file_name;
	}

	public void setFileName(String file_name) {
		this.file_name = file_name;
	}

}
