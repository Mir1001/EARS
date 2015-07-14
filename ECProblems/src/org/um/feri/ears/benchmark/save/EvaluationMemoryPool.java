package org.um.feri.ears.benchmark.save;

import java.util.Hashtable;

import org.um.feri.ears.problems.Individual;

public class EvaluationMemoryPool {
	MemoryPool data;
	Hashtable<String, Integer> keysTaskProblem; //
	
	public EvaluationMemoryPool(MemoryPool data) {
		this.data = data;
		keysTaskProblem = new Hashtable<String, Integer>();
		for (String s:data.evaluations.keySet()) {
			keysTaskProblem.put(s, 0);
		}
	}
	
	public void add(String algID, String taskID, Individual r) {
		data.add(algID, taskID, r);
	}
	
	public String getKey(String algID, String taskID) {
		return data.getKey(algID, taskID);
	}
	
	public Individual getNext(String algTaskID) {
		Integer n = keysTaskProblem.get(algTaskID);
		if (n==null) return null; //it does not exists in memory
		return data.get(algTaskID,n); //if null out or range
	}
	
}
