package org.um.feri.ears.quality_indicator;

import java.util.Comparator;

import org.um.feri.ears.util.DominanceArrayComparator;

/**
 * This class implements the RNI (Ratio Of Nondominated Individuals) indicator.
 * 
 * The indicator calculates the ratio of nondominated individuals. The RNI value
 * is in the interval [0,1], the larger is the better.
 * The value RNI = 1 means all the individuals in the population 
 * are non-dominated while the opposite, RNI = 0 represents the 
 * situation where none of the individuals in the population are non-dominated.
 * 
 * Reference: K. C. Tan, T. H. Lee, and E. F. Khor, "Evolutionary algorithms for multiobjective
 * optimization: Performance assessments and comparisons,"
 * Artificial Intell. Rev., vol. 17, no. 4, pp. 253–290, 2002.
 */
public class RatioOfNondominatedIndividuals extends QualityIndicator{

	public RatioOfNondominatedIndividuals() {
		name = "Ratio Of Nondominated Individuals";
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
		int X = 0;
		
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
				X++;
			}
		}
		double RNI = (double)X / (double)front.length;
		return RNI;
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
