package org.um.feri.ears.algorithms.cma;
/**
 * This is just wrapper for inria-s CMA implementation
 * Tukaj daj link na originalno kodo!
 * 
 */
import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.Author;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;

import fr.inria.optimization.cmaes.CMAEvolutionStrategy;

public class CMA_ESAlgorithm extends Algorithm {
	Individual i;
	
	boolean debug=false;
	public CMA_ESAlgorithm() {
		this.debug = false;
		ai = new AlgorithmInfo("","","CMA-ES","CMA Evolution Strategy");
		au =  new Author("based on Nikolaus Hansen", "");
	}
	public CMA_ESAlgorithm(boolean d) {
	    this();
		setDebug(d); 
	}
	
	@Override
	public Individual run(Task taskProblem) throws StopCriteriaException{
		CMAEvolutionStrategy cma = new CMAEvolutionStrategy();
		cma.setDimension(taskProblem.getDimensions()); // overwrite some loaded properties
		cma.setInitialX(0.05); // in each dimension, also setTypicalX can be used
		cma.setInitialStandardDeviation(0.2);
		if (taskProblem.isMaximize()) System.out.println("Maximize problem "+taskProblem.getProblemShortName());
		double[] fitness = cma.init();
		double[][] pop;// = cma.samplePopulation();
		System.out.println(taskProblem.getProblemShortName()+" "+taskProblem.getNumberOfEvaluations()+" ");
			if (debug) System.out.println(taskProblem.getProblemShortName()+" "+taskProblem.getNumberOfEvaluations()+" ");
			while (!taskProblem.isStopCriteria()) {
				pop = cma.samplePopulation();
				for(int i = 0; i < pop.length; ++i) {    // for each candidate solution i
	            	// a simple way to handle constraints that define a convex feasible domain  
	            	// (like box constraints, i.e. variable boundaries) via "blind re-sampling" 
	            	                                       // assumes that the feasible domain is convex, the optimum is  
					while (!taskProblem.areDimensionsInFeasableInterval(pop[i]))     //   not located on (or very close to) the domain boundary,  
						pop[i] = cma.resampleSingle(i);    //   initialX is feasible and initialStandardDeviations are  
	                                                       //   sufficiently small to prevent quasi-infinite looping here
	                // compute fitness/objective value	
					fitness[i] = taskProblem.justEval(pop[i]); // fitfun.valueOf() is to be minimized
					if (taskProblem.isStopCriteria()) break;
				}
				cma.updateDistribution(fitness);   
			}
		i = new Individual(cma.getBestX(), cma.getBestFunctionValue());	
		return i;

	}

    @Override
    public void resetDefaultsBeforNewRun() {
        i=null;
        
    }

}