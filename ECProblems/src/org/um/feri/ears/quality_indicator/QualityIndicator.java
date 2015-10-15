package org.um.feri.ears.quality_indicator;

public abstract class QualityIndicator {

	/** Returns the calculated quality indicator
	   *  @param front The front.
	   *  @param trueParetoFront The true pareto front.
	   *  @param numberOfObjectives The number of objectives.
	   */
	public abstract double get_indicator(double [][] front, double [][] trueParetoFront, int numberOfObjectives);
	
	protected String name;
	
	public enum IndicatorType {
		Unary,
		Binary,
		Arbitrary
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * The method returns an enum which tells the number of approximations the quality indicator requires to operate.
	 * Unary - one approximation
	 * Binary - two approximation
	 * Arbitrary - arbitrary number of approximation
	 * @return the enum for the indicator type.
	 */
	abstract public IndicatorType getNumberOfSets();
	
	/**
	 * The method must return true if smaller values are better else return false.
	 * @return true if smaller values are better else return false.
	 */
	abstract public boolean isMin();
	
	/**
	 * The method returns true if reference set is required else returns false.
	 * 
	 * @return true if reference set is required else returns false.
	 */
	abstract public boolean requiresReferenceSet();

	/**
	 * Compares two approximations.
	 * @param front1 Object representing the first front.
	 * @param front2 Object representing the second front.
	 * @param numberOfObjectives
	 * @return -1, or 0, or 1 if front1 is better than front2, both are 
     * equal, or front2 is better than front1, respectively.
	 */
	public abstract int compare(double[][] front1, double[][] front2, int numberOfObjectives);
	
}
