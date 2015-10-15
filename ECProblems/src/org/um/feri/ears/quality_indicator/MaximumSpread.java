package org.um.feri.ears.quality_indicator;
/**
 * This class implements the maximum spread metric. 
 * The metric addresses the range of objective function values and takes into account the proximity to the
 * true Pareto front. This metric is applied to measure how well the PF_true is covered by the PF_known.
 * A higher value of MS reflects that a larger area of the PF_true is covered by the PF_known.
 * 
 * Reference:
 * E. Zitzler, L. Thiele, and K. Deb, "Comparison of multiobjective
 * evolutionary algorithms: Empirical results,"" IEEE Trans. Evol. Comput.,
 * vol. 8, no. 2, pp. 173–195, Jun. 2000.
 */
public class MaximumSpread extends QualityIndicator {

	static final double pow_ = 2.0;
	
	public MaximumSpread() {
		name = "Maximum Spread";
	}
	
	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {
		double MS = 0.0;
		double sum = 0.0;
		double PF_true_max, PF_true_min, PF_known_max, PF_known_min;
		
		for (int i = 0; i < numberOfObjectives; i++) {
			PF_true_max = PF_known_max = Double.MIN_VALUE;
			PF_true_min = PF_known_min = Double.MAX_VALUE;
			
			for (double[] ds : trueParetoFront) {
				if(ds[i] > PF_true_max)
					PF_true_max = ds[i];
				
				if(ds[i] < PF_true_min)
					PF_true_min = ds[i];
			}
			
			for (double[] ds : front) {
				if(ds[i] > PF_known_max)
					PF_known_max = ds[i];
				
				if(ds[i] < PF_known_min)
					PF_known_min = ds[i];
			}
			
			sum += Math.pow((Math.min(PF_known_max, PF_true_max) - Math.max(PF_known_min, PF_true_min)) / (PF_true_max - PF_true_min), pow_);
		}
		
		MS = Math.sqrt((1.0 / numberOfObjectives) * sum);
		return MS;
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
		return true;
	}

	@Override
	public int compare(double[][] front1, double[][] front2, int numberOfObjectives) {
		return 0;
	}

}
