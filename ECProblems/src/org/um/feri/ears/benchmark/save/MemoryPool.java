package org.um.feri.ears.benchmark.save;

import java.util.ArrayList;
import java.util.Hashtable;

import org.um.feri.ears.problems.Individual;

public class MemoryPool {
	Hashtable<String, Integer> keysAlgorithm; //a id; //for compression
	Hashtable<String, Integer> keysTaskProblem; //task id; 
	Hashtable<String, ArrayList<Individual>> evaluations; //task/problem key,result
	public MemoryPool() {
		evaluations = new Hashtable<>();
		keysTaskProblem = new Hashtable<>();
		keysAlgorithm = new Hashtable<>();
	}
	public String getKey(String algID, String taskID) {
		Integer al = keysAlgorithm.get(algID);
		if (al==null) {
			al = keysAlgorithm.size();
			keysAlgorithm.put(algID, al);
		}
		Integer ta = keysTaskProblem.get(taskID);
		if (ta==null) {
			ta = keysTaskProblem.size();
			keysTaskProblem.put(taskID, ta);
		}
		return al+"-"+ta;
	}
	public void add(String algID, String taskID, Individual r) {
		String skey=getKey(algID,taskID);
		add(skey,r);
	}
	
	public void add(String algtaskID, Individual r) {	
		ArrayList<Individual> tmp = evaluations.get(algtaskID);
		if (tmp==null) {
			tmp = new ArrayList<>();
			evaluations.put(algtaskID, tmp);
		}
		tmp.add(r);
	}

	
	@Override
	public String toString() {
		return "EvaluationMemoryPool [keysAlgorithm=" + keysAlgorithm
				+ ", keysTaskProblem=" + keysTaskProblem + ", evaluations="
				+ evaluations + "]";
	}
	public Individual get(String algTaskID, Integer n) {
		ArrayList<Individual> all = evaluations.get(algTaskID);
		if (n>=all.size()) return null; //all evaluations are used;
		return all.get(n);
	}
	
	
}
