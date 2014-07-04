package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP8_F6_2 extends Problem {
	
	int dim;
	
	public UP8_F6_2 (int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count2;
		double sum2, yj;
		sum2 = 0.0;
		count2 = 0;

		for (int j = 3; j <= dim; j++) {
			yj = ds[j - 1] - 2.0 * ds[1] * Math.sin(2.0 * Math.PI * ds[0] + j * Math.PI / dim);
			if (j % 3 == 2) {
				sum2 += yj * yj;
				count2++;
			}
		}
		return Math.cos(0.5 * Math.PI * ds[0]) * Math.sin(0.5 * Math.PI * ds[1]) + 2.0 * sum2 / (double) count2;
	}
}