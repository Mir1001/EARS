package org.um.feri.ears.quality_indicator;

public class UniformDistribution extends QualityIndicator{

	public UniformDistribution() {
		name = "Uniform Distribution";
	}
	
	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {
		
		double sigma_share;
		
		return 0;
	}

	@Override
	public boolean isMin() {

		return false;
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
