package org.um.feri.ears.quality_indicator;
/**
 * This class implements the Spacing metric.
 * This metric is a value measuring how evenly the nondominated solutions are distributed along the approximation front.
 * S = 0 indicates that all members of the approximation front are equidistantly spaced.
 * 
 * Reference: 
 * J. R. Schott. Fault Tolerant Design Using Single
 * and Multicriteria Genetic Algorithm Optimization.
 * Master Thesis, Boston, MA: Department of
 * Aeronautics and Astronautics, Massachusetts Institute
 * of Technology, 1995.
 *
 */
public class Spacing extends QualityIndicator{

	public MetricsUtil utils_; // utils_ is used to access to the MetricsUtil funcionalities

	static final double pow_ = 2.0; // pow. This is the pow used for the distances

	/**
	 * Constructor. Creates a new instance of the spacing metric.
	 */
	public Spacing() {
		utils_ = new MetricsUtil();
		name = "Spacing";
	}
	
	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {
		
		double S = 0.0;
		double sum = 0.0;
		double closestpoint = 0.0;
		double averageDistance = 0.0;
		
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

		// STEP 1. Obtain the maximum and minimum values of the Pareto front
		maximumValue = utils_.getMaximumValues(front, numberOfObjectives);
		minimumValue = utils_.getMinimumValues(front, numberOfObjectives);

		// STEP 2. Get the normalized front and true Pareto fronts
		normalizedFront = utils_.getNormalizedFront(front, maximumValue, minimumValue);
		
		for (int i = 0; i < front.length; i++)
		{
			averageDistance += utils_.distanceToNearestPoint(i, normalizedFront);
		}
		averageDistance = averageDistance / front.length;
		for (int i = 0; i < front.length; i++)
		{
			closestpoint = utils_.distanceToNearestPoint(i, normalizedFront);
			sum+= Math.pow(averageDistance - closestpoint, pow_);
		}
		
		
		S = Math.sqrt((1.0/(front.length-1.0)) * sum);
		return S;
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
		return false;
	}

	@Override
	public int compare(double[][] front1, double[][] front2, int numberOfObjectives) {
		return 0;
	}

}
