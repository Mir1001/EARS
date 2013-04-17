package org.um.feri.ears.problems.results;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.util.MeanStDev;

public class BankOfResults {
	private Hashtable<Algorithm,Hashtable<Problem,ArrayList<Double>>> all;
	
	public BankOfResults() {
		all = new Hashtable<Algorithm,Hashtable<Problem,ArrayList<Double>>>();
	}
	public void add(Problem problem, Individual individual, Algorithm al) {
		Hashtable<Problem,ArrayList<Double>> tmpA;
		MeanStDev tmpBB;
		ArrayList<Double> tmpB;
		tmpA = all.get(al);
		if (tmpA==null) {
			tmpA = new Hashtable<Problem, ArrayList<Double>>();
			all.put(al, tmpA);
		}
		tmpB = tmpA.get(problem);
		if (tmpB==null) {
			tmpB = new ArrayList<Double>();
			tmpA.put(problem, tmpB);
		}
		tmpB.add(individual.getEval());
	}

	public FriedmanTransport calc4Friedman() {
		//FriedmanTransport
		 double[][] mean;
		 Vector<String> algoritms = new Vector<String>();
		 Vector<String> datasets = new Vector<String>();
		StringBuffer sb = new StringBuffer();
		ArrayList<Algorithm> tmp = new ArrayList(all.keySet());
		ArrayList<Problem> tmpProblem; // = new ArrayList(all.keySet());
		Hashtable<Problem,ArrayList<Double>> tmpA;
		ArrayList<Double> tmpB;
		Individual tmpC;
		MeanStDev std;
		mean=null;

		int i=-1;
		int j=-1;
		for (Algorithm  a: tmp) {
			i++;//
			algoritms.add(a.getID());
			tmpA =all.get(a);
			tmpProblem = new ArrayList(tmpA.keySet());
			//mean[i] = new double[tmpProblem.size()];
			j=-1;
			for (Problem  p: tmpProblem) {
				j++;
				if (i==0) {
					if (j==0) {
						mean = new double[tmpProblem.size()][];
						for (int g=0; g<tmpProblem.size();g++) {
							mean[g] = new double[tmp.size()];
						}
					}
					
					datasets.add(p.getName());
				}
				tmpB = tmpA.get(p);
				std = new MeanStDev(tmpB);
					sb.append(a.getID()).append('\t').append(p.getName());
					sb.append('\t').append(std.getMean());
					sb.append("\n");
				mean[j][i] = std.mean;
				}
			}
		System.out.println(sb);
		FriedmanTransport result = new FriedmanTransport(mean, algoritms,datasets);
		return result;
	}		
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		ArrayList<Algorithm> tmp = new ArrayList(all.keySet());
		ArrayList<Problem> tmpProblem; // = new ArrayList(all.keySet());
		Hashtable<Problem,ArrayList<Double>> tmpA;
		ArrayList<Double> tmpB;
		Individual tmpC;
		for (Algorithm  a: tmp) {
			tmpA =all.get(a);
			tmpProblem = new ArrayList(tmpA.keySet());
			for (Problem  p: tmpProblem) {
				tmpB = tmpA.get(p);
				for (Double in:tmpB) {
					sb.append(a.getID()).append('\t').append(p.getName());
					sb.append('\t').append(in);
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

}
