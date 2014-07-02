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
import org.um.feri.ears.util.Util;

public class MOParetoIndividual extends Individual {

	public MOParetoIndividual(Individual i) {
		super(i);
	}
	
public List<MOIndividual> solutions;
	
	private int capacity = 0;
	
	public MOParetoIndividual() {
		solutions = new ArrayList<MOIndividual>();
	}

	public MOParetoIndividual(int maximumSize) {

		solutions = new ArrayList<MOIndividual>();
		capacity = maximumSize;
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
		// TODO calcualte and return GDI
		return super.getEval();
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
	
	public MOParetoIndividual union(MOParetoIndividual solutionSet) {
		// Check the correct size. In development
		int newSize = this.size() + solutionSet.size();
		if (newSize < capacity)
			newSize = capacity;

		// Create a new population
		MOParetoIndividual union = new MOParetoIndividual(newSize);
		for (int i = 0; i < this.size(); i++) {
			union.add(this.get(i));
		} // for

		for (int i = this.size(); i < (this.size() + solutionSet.size()); i++) {
			union.add(solutionSet.get(i - this.size()));
		} // for

		return union;
	}
	
	public void sort(Comparator comparator){
	    if (comparator == null)
	      return ;

	    Collections.sort(solutions,comparator);
	  }
	
	/**
   * Copies the objectives of the solution set to a matrix
   * @return A matrix containing the objectives
   */
  public double [][] writeObjectivesToMatrix() {
    if (this.size() == 0) {
      return null;
    }
    double [][] objectives;
    objectives = new double[size()][get(0).numberOfObjectives()];
    for (int i = 0; i < size(); i++) {
      for (int j = 0; j < get(0).numberOfObjectives(); j++) {
        objectives[i][j] = get(i).getObjective(j);
      }
    }
    return objectives;
  }

	
	public void printObjectivesToFile(String path) {
		try {
			/* Open the file */
			FileOutputStream fos = new FileOutputStream(path+".csv");
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

	public void printVariablesToFile(String path) {

		try {
			FileOutputStream fos = new FileOutputStream(path);
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

	public void printFeasibleFUN(String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
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
	
	public void replace(int position, MOIndividual solution) {
	    if (position > solutions.size()) {
	    	solutions.add(solution);
	    } 
	    solutions.remove(position);
	    solutions.add(position,solution);
	  }

	@Override
	public String toString() {
		String s="";
		for (MOIndividual i : solutions) {
			
			s+= "["+Util.arrayToString(i.getObjectives())+"] \n";
		}
		return s;
	}

}
