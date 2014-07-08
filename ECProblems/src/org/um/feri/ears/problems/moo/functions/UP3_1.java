package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP3_1 extends Problem {
	
	int dim;

	public UP3_1(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count1;
		double sum1, prod1, yj, pj;
		sum1 = 0.0;
		count1 = 0;
		prod1 = 1.0;

		for (int j = 2; j <= dim; j++) {
			yj = ds[j - 1] - Math.pow(ds[0], 0.5 * (1.0 + 3.0 * (j - 2.0) / (dim - 2.0)));
			pj = Math.cos(20.0 * yj * Math.PI / Math.sqrt(j));
			if (j % 2 == 0) {
				continue;
			} else {
				sum1 += yj * yj;
				prod1 *= pj;
				count1++;
			}
		}

		return ds[0] + 2.0 * (4.0 * sum1 - 2.0 * prod1 + 2.0) / (double) count1;
	}

}
