package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP2_F5_1 extends Problem {
	
	int dim;

	public UP2_F5_1(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count1;
		double sum1, yj;
		sum1 = 0.0;
		count1 = 0;

		for (int j = 2; j <= dim; j++) {
			if (j % 2 == 0) {
				continue;
			} else {
				yj = ds[j - 1] - (0.3 * ds[0] * ds[0] * Math.cos(24 * Math.PI * ds[0] + 4 * j * Math.PI / dim) + 0.6 * ds[0]) * Math.cos(6.0 * Math.PI * ds[0] + j * Math.PI / dim);
				sum1 += yj * yj;
				count1++;
			}
		}
		return ds[0] + 2.0 * sum1 / (double) count1;
	}

}
