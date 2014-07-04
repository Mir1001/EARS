package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP10_1 extends Problem {
	
	int dim;
	
	public UP10_1 (int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count1;
		double sum1, yj, hj;
		sum1 = 0.0;
		count1 = 0;

		for (int j = 3; j <= dim; j++) {
			yj = ds[j - 1] - 2.0 * ds[1] * Math.sin(2.0 * Math.PI * ds[0] + j * Math.PI / dim);
			hj = 4.0 * yj * yj - Math.cos(8.0 * Math.PI * yj) + 1.0;
			if (j % 3 == 1) {
				sum1 += hj;
				count1++;
			}
		}
		return Math.cos(0.5 * Math.PI * ds[0]) * Math.cos(0.5 * Math.PI * ds[1]) + 2.0 * sum1 / (double) count1;
	}
}
