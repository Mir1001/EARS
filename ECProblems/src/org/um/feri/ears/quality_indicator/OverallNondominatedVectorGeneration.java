package org.um.feri.ears.quality_indicator;

import java.util.Comparator;

import org.um.feri.ears.util.DominanceArrayComparator;

/**
 * This class implements the ONVG (Overall Nondominated Vector Generation) indicator.
 * The metric measures the number of nondominated individuals found in an
 * approximation front during MOEA evolution. Too few individuals in PF_known make the front’s
 * representation poor and too many vectors may overwhelm
 * the decision maker. It should be noted that if algorithm A
 * outperforms B on this metric does not necessarily imply
 * that algorithm A is clearly better than B.
 * 
 * Reference: D. A. Van Veldhuizen, "Multiobjective evolutionary algorithms: 
 * Classifications, analyses, and new innovations," Ph.D. dissertation, 
 * Air Force Inst. Technol., Wright-Patterson AFB, OH, 1999.
 */
public class OverallNondominatedVectorGeneration extends QualityIndicator {

	
	public OverallNondominatedVectorGeneration() {
		name = "Overall Nondominated Vector Generation";
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
		
		return PF_false;
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
