package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP4_1 extends Problem {

	int dim;

	public UP4_1(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count1;
		double sum1, yj, hj;
		sum1 = 0.0;
		count1 = 0;

		for (int j = 2; j <= dim; j++) {
			yj = ds[j - 1] - Math.sin(6.0 * Math.PI * ds[0] + j * Math.PI / dim);
			hj = Math.abs(yj) / (1.0 + Math.exp(2.0 * Math.abs(yj)));
			if (j % 2 == 0) {
				continue;
			} else {
				sum1 += hj;
				count1++;
			}
		}
		return ds[0] + 2.0 * sum1 / (double) count1;
	}
}
