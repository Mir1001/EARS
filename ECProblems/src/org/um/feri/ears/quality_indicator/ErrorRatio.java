package org.um.feri.ears.quality_indicator;

/**
 * This class implements the Error ratio indicator.
 * It is defined as the proportion of nontrue Pareto points.
 * Lower values of ER refer to smaller proportions of nontrue
 * Pareto points in the approximation and represent better nondominated sets.
 * 
 * Reference: D. A. Van Veldhuizen, "Multiobjective evolutionary algorithms: 
 * Classifications, analyses, and new innovations," Ph.D. dissertation, 
 * Air Force Inst. Technol., Wright-Patterson AFB, OH, 1999.
 */
public class ErrorRatio extends QualityIndicator{
	
	public MetricsUtil utils_; // utils_ is used to access to the MetricsUtil funcionalities

	static final double pow_ = 2.0; // pow. This is the pow used for the distances

	public ErrorRatio() {
		utils_ = new MetricsUtil();
		name = "Error Ratio";
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
		maximumValue = utils_.getMaximumValues(trueParetoFront, numberOfObjectives);
		minimumValue = utils_.getMinimumValues(trueParetoFront, numberOfObjectives);

		// STEP 2. Get the normalized front and true Pareto fronts
		normalizedFront = utils_.getNormalizedFront(front, maximumValue, minimumValue);
		normalizedParetoFront = utils_.getNormalizedFront(trueParetoFront, maximumValue, minimumValue);
		
		int nonTruePoint = 0;
		boolean isOnFront;
		double distance;
		for (int i = 0; i < front.length; i++)
		{
			distance = Math.pow(utils_.distanceToClosedPoint(normalizedFront[i], normalizedParetoFront), pow_);
			if(distance > 0.0001)
				nonTruePoint++;
		}
		
		/*
		for (double[] ind : front) {
			isOnFront = true;
			for (double[] trueind : trueParetoFront) {

				for (int i = 0; i < numberOfObjectives; i++) {
					if(Math.abs(ind[i] - trueind[i]) > 0.1)
						isOnFront = false;
				}
			}
			if(!isOnFront)
				nonTruePoint++;
		}*/
		
		double ER = (double)nonTruePoint / (double)front.length;
		return ER;
	}

	@Override
	public boolean isMin() {
		return true;
	}

	@Override
	public IndicatorType getNumberOfSets() {
		return QualityIndicator.IndicatorType.Unary;
	}

	@Override
	public boolean requiresReferenceSet() {
		return true;
	}

	@Override
	public int compare(double[][] front1, double[][] front2, int numberOfObjectives) {
		return 0;
	}

}
