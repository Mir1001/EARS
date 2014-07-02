//  DifferentialEvolutionCrossover.java
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

package org.um.feri.ears.operators;

import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.util.Util;


/**
 * Differential evolution crossover operators
 * Comments:
 * - The operator receives two parameters: the current individual and an array
 *   of three parent individuals
 * - The best and rand variants depends on the third parent, according whether
 *   it represents the current of the "best" individual or a randon one. 
 *   The implementation of both variants are the same, due to that the parent 
 *   selection is external to the crossover operator. 
 * - Implemented variants:
 *   - rand/1/bin (best/1/bin)
 *   - rand/1/exp (best/1/exp)
 *   - current-to-rand/1 (current-to-best/1)
 *   - current-to-rand/1/bin (current-to-best/1/bin)
 *   - current-to-rand/1/exp (current-to-best/1/exp)
 */
public class DifferentialEvolutionCrossover {
	/**
	 * DEFAULT_CR defines a default CR (crossover operation control) value
	 */
	private static final double DEFAULT_CR = 0.5;

	/**
	 * DEFAULT_F defines the default F (Scaling factor for mutation) value
	 */
	private static final double DEFAULT_F = 0.5;

	/**
	 * DEFAULT_K defines a default K value used in variants current-to-rand/1
	 * and current-to-best/1
	 */
	private static final double DEFAULT_K = 0.5;

	/**
	 * DEFAULT_VARIANT defines the default DE variant
	 */

	private static final String DEFAULT_DE_VARIANT = "rand/1/bin";

	private double CR  ;
	private double F   ;
	private double K   ;
	private String DE_Variant ; // DE variant (rand/1/bin, rand/1/exp, etc.)

	/**
	 * Constructor
	 */
	public DifferentialEvolutionCrossover() {
		
		CR = DEFAULT_CR ;
		F  = DEFAULT_F  ;
		K  = DEFAULT_K   ;
		DE_Variant = DEFAULT_DE_VARIANT ;

	}
	
	public DifferentialEvolutionCrossover(double CR, double F, double K, String DE_VARIANT) {

  		this.CR = CR;  		
  		this.F = F;  		
  		this.K = K;  		
  		this.DE_Variant = DE_VARIANT;  		

	}


	/**
	 * Constructor
	 */
	//public DifferentialEvolutionCrossover(Properties properties) {
	//	this();
	//	CR_ = (new Double((String)properties.getProperty("CR_")));
	//	F_  = (new Double((String)properties.getProperty("F_")));
	//	K_  = (new Double((String)properties.getProperty("K_")));
	//	DE_Variant_ = properties.getProperty("DE_Variant_") ;
	//} // Constructor

	/**
	 * Executes the operation
	 * @param object An object containing an array of three parents
	 * @return An object containing the offSprings
	 */
	public Object execute(Object object, Task task) {
		Object[] parameters = (Object[])object ;
		MOIndividual current   = (MOIndividual) parameters[0];
		MOIndividual [] parent = (MOIndividual [])parameters[1];

		MOIndividual child;

		int jrand ;

		child = new MOIndividual(current) ;
		
		
		MOIndividual xParent0 = parent[0];
		MOIndividual xParent1 = parent[1];
		MOIndividual xParent2 = parent[2];
		MOIndividual xCurrent = current;
		MOIndividual xChild   = child;

		int numberOfVariables = xParent0.getDecisionVariables().length;
		jrand = Util.rnd.nextInt(numberOfVariables - 1);

		// STEP 4. Checking the DE variant
		if ((DE_Variant.compareTo("rand/1/bin") == 0) || 
				(DE_Variant.compareTo("best/1/bin") == 0)) { 
			for (int j=0; j < numberOfVariables; j++) {
				if (Util.rnd.nextDouble() < CR || j == jrand) {
					double value ;
					value = xParent2.getValue(j)  + F * (xParent0.getValue(j) -
							                                  xParent1.getValue(j)) ;
					
					if (value < task.getIntervalLeft()[j])
						value =  task.getIntervalLeft()[j];
					if (value > task.getIntervalLength()[j])
						value = task.getIntervalLength()[j];
          /*
					if (value < xChild.getLowerBound(j)) {
            double rnd = PseudoRandom.randDouble(0, 1) ;
            value = xChild.getLowerBound(j) + rnd *(xParent2.getValue(j) - xChild.getLowerBound(j)) ;
					}
          if (value > xChild.getUpperBound(j)) {
            double rnd = PseudoRandom.randDouble(0, 1) ;
            value = xChild.getUpperBound(j) - rnd*(xChild.getUpperBound(j)-xParent2.getValue(j)) ;
          }
          */
					xChild.setValue(j, value) ;
				}
				else {
					double value ;
					value = xCurrent.getValue(j);
					xChild.setValue(j, value) ;
				} // else
			} // for
		} // if
		else if ((DE_Variant.compareTo("rand/1/exp") == 0) || 
				     (DE_Variant.compareTo("best/1/exp") == 0)) {
			for (int j=0; j < numberOfVariables; j++) {
				if (Util.rnd.nextDouble() < CR || j == jrand) {
					double value ;
					value = xParent2.getValue(j)  + F * (xParent0.getValue(j) -
							xParent1.getValue(j)) ;

					if (value < task.getIntervalLeft()[j])
						value =  task.getIntervalLeft()[j];
					if (value > task.getIntervalLength()[j])
						value = task.getIntervalLength()[j];

					xChild.setValue(j, value) ;
				}
				else {
					CR = 0.0 ;
					double value ;
					value = xCurrent.getValue(j);
					xChild.setValue(j, value) ;
			  } // else
			} // for		
		} // if
		else if ((DE_Variant.compareTo("current-to-rand/1") == 0) || 
             (DE_Variant.compareTo("current-to-best/1") == 0)) { 
			for (int j=0; j < numberOfVariables; j++) {
				double value ;
				value = xCurrent.getValue(j) + K * (xParent2.getValue(j) - 
					    xCurrent.getValue(j)) +					
						  F * (xParent0.getValue(j) - xParent1.getValue(j)) ;

				if (value < task.getIntervalLeft()[j])
					value =  task.getIntervalLeft()[j];
				if (value > task.getIntervalLength()[j])
					value = task.getIntervalLength()[j];

				xChild.setValue(j, value) ;
			} // for		
		} // if
		else if ((DE_Variant.compareTo("current-to-rand/1/bin") == 0) ||
				     (DE_Variant.compareTo("current-to-best/1/bin") == 0)) { 
			for (int j=0; j < numberOfVariables; j++) {
				if (Util.rnd.nextDouble() < CR || j == jrand) {
					double value ;
					value = xCurrent.getValue(j) + K * (xParent2.getValue(j) - 
							xCurrent.getValue(j)) +					
							F * (xParent0.getValue(j) - xParent1.getValue(j)) ;

					if (value < task.getIntervalLeft()[j])
						value =  task.getIntervalLeft()[j];
					if (value > task.getIntervalLength()[j])
						value = task.getIntervalLength()[j];

					xChild.setValue(j, value) ;
				}
				else {
					double value ;
					value = xCurrent.getValue(j);
					xChild.setValue(j, value) ;
				} // else
			} // for
		} // if
		else if ((DE_Variant.compareTo("current-to-rand/1/exp") == 0) || 
				(DE_Variant.compareTo("current-to-best/1/exp") == 0)) {
			for (int j=0; j < numberOfVariables; j++) {
				if (Util.rnd.nextDouble() < CR || j == jrand) {
					double value ;
					value = xCurrent.getValue(j) + K * (xParent2.getValue(j) - 
							xCurrent.getValue(j)) +					
							F * (xParent0.getValue(j) - xParent1.getValue(j)) ;

					if (value < task.getIntervalLeft()[j])
						value =  task.getIntervalLeft()[j];
					if (value > task.getIntervalLength()[j])
						value = task.getIntervalLength()[j];

					xChild.setValue(j, value) ;
				}
				else {
					CR = 0.0 ;
					double value ;
					value = xCurrent.getValue(j);
					xChild.setValue(j, value) ;
				} // else
			} // for		
		} // if		
		else {
			System.err.println("Exception");
		} // else
		return child ;
	}
} // DifferentialEvolutionCrossover
