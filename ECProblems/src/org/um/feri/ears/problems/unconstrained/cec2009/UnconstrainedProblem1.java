package org.um.feri.ears.problems.unconstrained.cec2009;


public class UnconstrainedProblem1 extends Problem {
    

	public UnconstrainedProblem1() {
		this(30); // 30 variables by default
	} // CEC2009_UF1
	 
	  public UnconstrainedProblem1(Integer numberOfVariables) {
		this.numberOfVariables  = numberOfVariables;
		numberOfObjectives =  2;
		numberOfConstraints =  0;

		upperLimit = new double[numberOfVariables];
		lowerLimit = new double[numberOfVariables];

		lowerLimit[0] = 0.0 ;
		upperLimit[0] = 1.0 ;
	     for (int var = 1; var < numberOfVariables; var++){
	    	 lowerLimit[var] = -1.0;
	    	 upperLimit[var] = 1.0;
	     }
	  }
	    
	  /** 
	   * Evaluates a solution.
	   * @param solution The solution to evaluate.
	   * @throws JMException 
	   */
	  public void evaluate(Solution solution) {
	    double[] decisionVariables  = solution.getVariables();
	    
	    double [] x = new double[numberOfVariables];
	    for (int i = 0; i < numberOfVariables; i++)
	      x[i] = decisionVariables[i];

	  	int count1, count2;
			double sum1, sum2, yj;
			sum1   = sum2   = 0.0;
			count1 = count2 = 0;
	    
	    for (int j = 2 ; j <= numberOfVariables; j++) {
				yj = x[j-1] - Math.sin(6.0*Math.PI*x[0] + j*Math.PI/numberOfVariables);
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
	    solution.setObjective(1, 1.0 - Math.sqrt(x[0]) + 2.0 * sum2 / (double)count2);
	  } // evaluate
	  
	@Override
	public double[] getVariables() {

		double[] variables = new double[numberOfVariables];

		for (int var = 0; var < numberOfVariables; var++)
			variables[var] = Real.getValue(lowerLimit[var], upperLimit[var]);

		return variables;
	}
	
}