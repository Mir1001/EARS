package org.um.feri.ears.algorithms.cma;
/**
 * This is just wrapper for inria-s CMA implementation
 * 
 * The CMA-ES (Covariance Matrix Adaptation Evolution Strategy) is an evolutionary algorithm
 * for difficult non-linear non-convex optimization problems in continuous domain. The CMA-ES
 * is typically applied to unconstrained or bounded constraint optimization problems, and
 * search space dimensions between three and a hundred. 
 * 
 * Original source code: https://www.lri.fr/~hansen/cmaes_inmatlab.html
 * 
 * @author Matej Crepinsek
 * @version 1
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
		au =  new Author("based on Nikolaus Hansen", "nikolaus.hansen@inf.ethz.ch");
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
		cma.options.verbosity = -1;
		double[] fitness = cma.init();
		double[][] pop;
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