package org.um.feri.ears.problems.unconstrained.cec2009;

import java.util.ArrayList;

import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.problems.moo.MOProblem2;
import org.um.feri.ears.problems.moo.functions.UP1_F2_1;
import org.um.feri.ears.problems.moo.functions.UP1_F2_2;


public class UnconstrainedProblem1 extends MOProblem2 {
    

	public UnconstrainedProblem1() {
		this(30); // 30 variables by default
	} // CEC2009_UF1
	 
	  public UnconstrainedProblem1(Integer numberOfVariables) {
		dim  = numberOfVariables;
		numberOfObjectives =  2;
		numberOfConstraints =  0;

		interval = new double[numberOfVariables];
		intervalL = new double[numberOfVariables];

		intervalL[0] = 0.0 ;
		interval[0] = 1.0 ;
	     for (int var = 1; var < numberOfVariables; var++){
	    	 intervalL[var] = -1.0;
	    	 interval[var] = 1.0;
	     }

	     this.addProblem(new UP1_F2_1(dim));
	     this.addProblem(new UP1_F2_2(dim));
	  }
	    
	  /** 
	   * Evaluates a solution.
	   * @param solution The solution to evaluate.
	   * @throws JMException 
	   */
	  public void evaluate(MOIndividual solution) {
	    double[] decisionVariables  = solution.getX();
	    
	    double [] x = new double[dim];
	    for (int i = 0; i < dim; i++)
	      x[i] = decisionVariables[i];

	    double obj[] =new double [functions.size()];
        for (int i=0; i<obj.length; i++) {
        	obj[i] = functions.get(i).eval(x);
        }
        solution.setEval(obj);
	  	/*int count1, count2;
			double sum1, sum2, yj;
			sum1   = sum2   = 0.0;
			count1 = count2 = 0;
	    
	    for (int j = 2 ; j <= dim; j++) {
				yj = x[j-1] - Math.sin(6.0*Math.PI*x[0] + j*Math.PI/dim);
				yj = yj * yj;
				if(j % 2 == 0) {
					sum2 += yj;
					count2++;
				} else {
					sum1 += yj;
					count1++;
				}      
	    }
	    
	    solution.setObjective(0, x[0] + 2.0 * sum1 / (double)count1);
	    solution.setObjective(1, 1.0 - Math.sqrt(x[0]) + 2.0 * sum2 / (double)count2);*/
	  }

	@Override
	public double eval(double[] ds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] evaluate(double[] ds) {

		double[] x = new double[dim];
		for (int i = 0; i < dim; i++)
			x[i] = ds[i];

		double obj[] =new double [functions.size()];
        for (int i=0; i<obj.length; i++) {
        	obj[i] = functions.get(i).eval(x);
        }
        
        return obj;
		/*int count1, count2;
		double sum1, sum2, yj;
		sum1 = sum2 = 0.0;
		count1 = count2 = 0;

		for (int j = 2; j <= dim; j++) {
			yj = x[j - 1] - Math.sin(6.0 * Math.PI * x[0] + j * Math.PI / dim);
			yj = yj * yj;
			if (j % 2 == 0) {
				sum2 += yj;
				count2++;
			} else {
				sum1 += yj;
				count1++;
			}
		}
		double[] res = new double[2];
		res[0] = x[0] + 2.0 * sum1 / (double) count1;
		res[1] = 1.0 - Math.sqrt(x[0]) + 2.0 * sum2 / (double) count2;

		return res;*/
	}
}