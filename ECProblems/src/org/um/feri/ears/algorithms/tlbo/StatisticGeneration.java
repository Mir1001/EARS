package org.um.feri.ears.algorithms.tlbo;

import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.util.Util;

public class StatisticGeneration {
	public int getGenerationID() {
		return generationID;
	}
	public void setGenerationID(int generationID) {
		this.generationID = generationID;

	}
	int generationID;
	int dup_cound;
	int eval;
	public Individual best;
	public Individual getBest() {
		return best;
	}
	long startEval;
	int updatedByTeacher;
	int updatedByLearner;
	private boolean isBestSet;

	

	public void setBest(Individual best) {
		this.best = best;
		isBestSet = true;
	}

	public StatisticGeneration(int generationID, long startEval) {
		super();
		this.generationID = generationID;
		dup_cound = 0;
		eval = 0;
		updatedByTeacher=0;
		updatedByLearner=0;
		this.startEval=startEval;
		isBestSet = false;
	}
	public void incUpdateByTeacher() {
		updatedByTeacher++;
	}
	public void incUpdateByLearner() {
		updatedByLearner++;
	}
	void addDup() {
		dup_cound++;
	}
	void incEval() {
		eval++;
	}

	public String toString() {
		return generationID+"\t"+best.getEval()+"\t"+(getTotalEval())+"\t"+dup_cound;
	}
	public String toStringFull() {
		return generationID+"\t"+best.getEval()+"\t"+Util.arrayToString(best.getX())+"\t"+getTotalEval();
	}
	public void incDouple() {
		dup_cound++;
		
	}
	public long getTotalEval() {
		return eval+startEval;
	}

}
