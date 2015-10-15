package org.um.feri.ears.quality_indicator;

import java.util.Comparator;

import org.um.feri.ears.util.DominanceArrayComparator;

/**
 * This class implements the Coverage of two sets indicator.
 * CS (Coverage of two Sets) is used to compare
 * two sets of non-dominated solutions. The function CS
 * maps the ordered pair (A,B) into the interval[0,1].
 * The value CS(A,B) = 1 means that all solutions in
 * B are dominated by A and the value
 * CS(A,B) = 0 indicates that none of the solutions in
 * B are dominated by A . Note that both  CS(A,B) and  CS(B,A) have
 * to be considered since CS(B,A) is not necessary
 * equal to 1 - CS(A,B).
 * 
 * Reference: E. Zitzler, K. Deb, and L. Thiele. Comparison of
 * multiobjective evolutionary algorithms: Empirical
 * results. Journal of Evolutionary Computation, 8(2),
 * 2000,pp.173-195.
 */
public class CoverageOfTwoSets extends QualityIndicator {
	
	public CoverageOfTwoSets() {
		name = "Coverage of two sets";
	}

	/**
	 * stores a <code>Comparator</code> for dominance checking
	 */
	private static final Comparator dominance_ = new DominanceArrayComparator();
	
	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {
		// TODO calculate throw error if second front is null
		double CS = 0.0;
		
		return CS;
	}

	@Override
	public IndicatorType getNumberOfSets() {
		return IndicatorType.Binary;
	}

	@Override
	public boolean isMin() {
		return false;
	}

	@Override
	public boolean requiresReferenceSet() {
		return true;
	}

	@Override
	public int compare(double[][] front1, double[][] front2, int numberOfObjectives) {
		double CS1 = 0.0, CS2 = 0.0;
		int flagDominate;
		
		for (int p = 0; p < (front1.length - 1); p++) {
			// For all q individuals , calculate if p dominates q or vice versa
			for (int q = p; q < front2.length; q++) {
				// flagDominate
				flagDominate = 0;
				if (flagDominate == 0) {
					flagDominate = dominance_.compare(front1[p],front2[q]);
				}
				if (flagDominate == -1) {
					CS1++;
				} else if (flagDominate == 1) {
					CS2++;
				}
			}
		}
		
		CS1 = CS1 / front2.length;
		CS2 = CS2 / front1.length;
		
		//TODO draw limit
		
		if(CS1 > CS2)
			return -1;
		if(CS1 < CS2)
			return 1;
		return 0;
	}

}
