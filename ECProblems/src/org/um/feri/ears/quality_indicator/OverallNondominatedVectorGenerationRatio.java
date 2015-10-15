package org.um.feri.ears.quality_indicator;

import java.util.Comparator;

import org.um.feri.ears.util.DominanceArrayComparator;

/**
 * This class implements the ONVGR (Overall Nondominated Vector Generation and Ratio) indicator.
 * The indicator measures the ratio of the total number of nondominated vectors 
 * found in PF_known during MOEA execution to the number of vectors found in PF_true.
 * 
 * Reference: D. A. Van Veldhuizen and G. B. Lamont,
 * "On measuring multiobjective evolutionary algorithm performance", 
 * Proc. Congr. Evol. Comput., 2000, pp. 204-211.
 */
public class OverallNondominatedVectorGenerationRatio extends QualityIndicator {

	public OverallNondominatedVectorGenerationRatio() {
		name = "Overall Nondominated Vector Generation Ratio";
	}
	
	/**
	 * stores a <code>Comparator</code> for dominance checking
	 */
	private static final Comparator dominance_ = new DominanceArrayComparator();
	
	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {

		int flagDominate;
		int[] dominateMe = new int[front.length];
		
		// Set of nondominated individuals
		double PF_false = 0;
		double PF_true = 0;
		
		for (int p = 0; p < (front.length - 1); p++) {
			// For all q individuals , calculate if p dominates q or vice versa
			for (int q = p + 1; q < front.length; q++) {
				// flagDominate
				// =constraint_.compare(solutionSet.get(p),solutionSet.get(q));
				flagDominate = 0;
				if (flagDominate == 0) {
					flagDominate = dominance_.compare(front[p],front[q]);
				}
				if (flagDominate == -1) {
					dominateMe[q]++;
				} else if (flagDominate == 1) {
					dominateMe[p]++;
				}
			}
		}
		for (int p = 0; p < front.length; p++) {
			if (dominateMe[p] == 0) {
				PF_false++;
			}
		}
		
		dominateMe = new int[trueParetoFront.length];
		for (int p = 0; p < (trueParetoFront.length - 1); p++) {
			// For all q individuals , calculate if p dominates q or vice versa
			for (int q = p + 1; q < trueParetoFront.length; q++) {
				// flagDominate
				// =constraint_.compare(solutionSet.get(p),solutionSet.get(q));
				flagDominate = 0;
				if (flagDominate == 0) {
					flagDominate = dominance_.compare(trueParetoFront[p],trueParetoFront[q]);
				}
				if (flagDominate == -1) {
					dominateMe[q]++;
				} else if (flagDominate == 1) {
					dominateMe[p]++;
				}
			}
		}
		for (int p = 0; p < trueParetoFront.length; p++) {
			if (dominateMe[p] == 0) {
				PF_true++;
			}
		}
		
		return PF_false / PF_true;
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
