//  GenerationalDistance.java
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

package org.um.feri.ears.quality_indicator;

/**
 * This class implements the generational distance indicator. It can be used also 
 * as a command line by typing: 
 * "java jmetal.qualityIndicator.GenerationalDistance <solutionFrontFile>  
 * <trueFrontFile> <numberOfObjectives>"
 * Reference: Van Veldhuizen, D.A., Lamont, G.B.: Multiobjective Evolutionary 
 *            Algorithm Research: A History and Analysis. 
 *            Technical Report TR-98-03, Dept. Elec. Comput. Eng., Air Force 
 *            Inst. Technol. (1998)
 */
public class GenerationalDistance extends QualityIndicator{
	public MetricsUtil utils; // utils_ is used to access to the MetricsUtil funcionalities

	static final double pow_ = 2.0; // pow. This is the pow used for the distances

	/**
	 * Constructor. Creates a new instance of the generational distance metric.
	 */
	public GenerationalDistance() {
		utils = new MetricsUtil();
		name = "Generational Distance";
	}

	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {

		/**
		 * Stores the maximum values of true pareto front.
		 */
		double[] maximumValue;

		/**
		 * Stores the minimum values of the true pareto front.
		 */
		double[] minimumValue;

		/**
		 * Stores the normalized front.
		 */
		double[][] normalizedFront;

		/**
		 * Stores the normalized true Pareto front.
		 */
		double[][] normalizedParetoFront;

		// STEP 1. Obtain the maximum and minimum values of the Pareto front
		maximumValue = utils.getMaximumValues(trueParetoFront, numberOfObjectives);
		minimumValue = utils.getMinimumValues(trueParetoFront, numberOfObjectives);

		// STEP 2. Get the normalized front and true Pareto fronts
		normalizedFront = utils.getNormalizedFront(front, maximumValue, minimumValue);
		normalizedParetoFront = utils.getNormalizedFront(trueParetoFront, maximumValue, minimumValue);

		// STEP 3. Sum the distances between each point of the front and the
		// nearest point in the true Pareto front
		double sum = 0.0;
		for (int i = 0; i < front.length; i++)
			sum += Math.pow(utils.distanceToClosedPoint(normalizedFront[i], normalizedParetoFront), pow_);

		// STEP 4. Obtain the sqrt of the sum
		sum = Math.pow(sum, 1.0 / pow_);

		// STEP 5. Divide the sum by the maximum number of points of the front
		double generationalDistance = sum / normalizedFront.length;

		return generationalDistance;
	}

	@Override
	public boolean isMin() {
		return true;
	}
}
