package org.um.feri.ears.operators;

import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.moo.MOIndividual;
import org.um.feri.ears.util.Util;


public class PolynomialMutation {
	
	
	private static final double ETA_M_DEFAULT_ = 20.0;
	private final double eta_m = ETA_M_DEFAULT_;

	private Double mutationProbability = null;
	private Double distributionIndex = eta_m;

	public PolynomialMutation(Double mutationProbability, Double distributionIndex) {

		this.mutationProbability = mutationProbability;
		this.distributionIndex = distributionIndex;
	}

	public void doMutation(double probability, MOIndividual solution, Task task) {
		double rnd, delta1, delta2, mut_pow, deltaq;
		double y, yl, yu, val, xy;
		for (int var = 0; var < task.getDimensions(); var++) {
			if (Util.rnd.nextDouble() <= probability) {
				y = solution.getValue(var);
				yl = task.getIntervalLeft()[var];
				yu = task.getIntervalLength()[var];
				delta1 = (y - yl) / (yu - yl);
				delta2 = (yu - y) / (yu - yl);
				rnd = Util.rnd.nextDouble();
				mut_pow = 1.0 / (eta_m + 1.0);
				if (rnd <= 0.5) {
					xy = 1.0 - delta1;
					val = 2.0 * rnd + (1.0 - 2.0 * rnd) * (Math.pow(xy, (distributionIndex + 1.0)));
					deltaq = java.lang.Math.pow(val, mut_pow) - 1.0;
				} else {
					xy = 1.0 - delta2;
					val = 2.0 * (1.0 - rnd) + 2.0 * (rnd - 0.5) * (java.lang.Math.pow(xy, (distributionIndex + 1.0)));
					deltaq = 1.0 - (java.lang.Math.pow(val, mut_pow));
				}
				y = y + deltaq * (yu - yl);
				if (y < yl)
					y = yl;
				if (y > yu)
					y = yu;
				solution.setValue(var, y);
			}
		}
	}

	public Object execute(Object object, Task task) {

		MOIndividual solution = (MOIndividual) object;
		doMutation(mutationProbability, solution, task);
		return solution;
	}
}
