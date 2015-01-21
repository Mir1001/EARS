package org.um.feri.ears.algorithms.pso;

import java.util.Arrays;

import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.util.Util;

public class MyPSOIndividual extends Individual{
	MyPSOIndividual p; //personal best
	double v[];
	
	
	public MyPSOIndividual getP() {
		return p;
	}

	public void setP(MyPSOIndividual p) {
		this.p = p;
	}

	public double[] getV() {
		return v;
	}

	public MyPSOIndividual(Task t) throws StopCriteriaException {
		super(t.getRandomIndividual());
		v = new double[t.getDimensions()];
		double l; double r;
		for (int i=0; i<t.getDimensions(); i++) {
			l = -Math.abs(t.getIntervalRight()[i]-t.getIntervalLeft()[i])/4; 
			r = Math.abs(t.getIntervalRight()[i]-t.getIntervalLeft()[i])/4; 
		    v[i] = Util.rnd.nextDouble()*(r-l)+l;
		}
		p = this;
	}
	
	public MyPSOIndividual(Individual eval) {
		super(eval);
		
	}

	@Override
	public String toString() {
		return super.toString()+" v:"+(Arrays.toString(v)+" p:"+p.getEval());
	}

	public MyPSOIndividual update(Task t, double v[]) throws StopCriteriaException {
		double x[] = getNewX();
		for (int i=0; i<x.length; i++) {
			x[i]=t.feasible(x[i]+v[i],i);
		}
		MyPSOIndividual tmp = new MyPSOIndividual(t.eval(x));
		if (t.isFirstBetter(tmp, p)) {
			tmp.p = tmp;
		} else
			tmp.p = p;
		tmp.v = v;
		return tmp;
		
	}


}
