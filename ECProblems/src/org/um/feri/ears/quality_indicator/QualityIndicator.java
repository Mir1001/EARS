package org.um.feri.ears.quality_indicator;

public abstract class QualityIndicator {

	/** Returns the calculated quality indicator
	   *  @param front The front.
	   *  @param trueParetoFront The true pareto front.
	   *  @param numberOfObjectives The number of objectives.
	   */
	public abstract double get_indicator(double [][] front, double [][] trueParetoFront, int numberOfObjectives);
	
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	abstract public boolean isMin();
	
}
