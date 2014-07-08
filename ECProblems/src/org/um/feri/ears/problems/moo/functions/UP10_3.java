package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP10_3 extends Problem {

	int dim;

	public UP10_3(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count3;
		double sum3, yj, hj;
		sum3 = 0.0;
		count3 = 0;

		for (int j = 3; j <= dim; j++) {
			yj = ds[j - 1] - 2.0 * ds[1] * Math.sin(2.0 * Math.PI * ds[0] + j * Math.PI / dim);
			hj = 4.0 * yj * yj - Math.cos(8.0 * Math.PI * yj) + 1.0;
			if (j % 3 == 1 || j % 3 == 2) {
				continue;
			} else {
				sum3 += hj;
				count3++;
			}
		}
		return Math.sin(0.5 * Math.PI * ds[0]) + 2.0 * sum3 / (double) count3;
	}
}
