package org.um.feri.ears.problems;

public class Task {
	protected EnumStopCriteria stopCriteria;
	protected int maxEvaluations; // for Stop criteria
	protected int numberOfEvaluations = 0; // for Stop criteria
	protected double epsilon; // for Stop criteria
	private boolean isStop;
	private boolean isGlobal;
	private Problem p;

	public Task(EnumStopCriteria stop, int eval, double epsilon, Problem p) {
		stopCriteria = stop;
		maxEvaluations = eval;
		numberOfEvaluations = 0;
		this.epsilon = epsilon;
		isStop = false;
		isGlobal = false;
		this.p = p;
	}
	
	public double[] getIntervalLength(){
		return p.interval;
	}
	
	public double[] getIntervalLeft(){
		return p.intervalL;
	}
	
	public double[] getIntervalRight(){
		double intervalR[] = new double[p.interval.length];
		for (int i=0; i<intervalR.length;i++) {
			intervalR[i] = p.intervalL[i]+p.interval[i];
		}
		return intervalR;
	}
	
	public int getDimensions() {
		return p.getDim();
	}
	
	public Individual getRandomIndividual() throws StopCriteriaException {
		return eval(p.getRandomVectorX()); 
	}
	
	public boolean isFirstBetter(Individual x, Individual y) {
		return p.isFirstBetter(x.getX(), x.getEval(), y.getX(), y.getEval());
	}
	
	private void incEvaluate() throws StopCriteriaException {
		if (numberOfEvaluations >= maxEvaluations)
			throw new StopCriteriaException("Max evaluations");
		numberOfEvaluations++;
		if (numberOfEvaluations >= maxEvaluations)
			isStop = true;
	}
	
	public int getNumberOfEvaluations(){
		return numberOfEvaluations;
	}
	
	public boolean isStopCriteria() {
		return isStop||isGlobal;
	}


	public Individual eval(double[] ds) throws StopCriteriaException {
		if (stopCriteria == EnumStopCriteria.EVALUATIONS) {
			incEvaluate();
			return new Individual(ds,p.eval(ds));
		}
		if (stopCriteria == EnumStopCriteria.GLOBAL_OPTIMUM_OR_EVALUATIONS) {
			if (isGlobal)
				throw new StopCriteriaException("Global optimum already found");
			incEvaluate();
			double d = p.eval(ds);
			if (Math.abs(d - p.getOptimumEval()) <= epsilon) {
				isGlobal = true;
			}
			return new Individual(ds,d);
		}
		assert false; // Execution should never reach this point!
		return null; //error
	}
}
