package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP5_2 extends Problem{
	
	int dim;
	int N;
	double epsilon;
	
	public UP5_2 (int dim, int N, double epsilon) {
		this.dim = dim;
		this.N = N;
		this.epsilon = epsilon;
	}

	@Override
	public double eval(double[] ds) {

		int count2;
		double sum2, yj, hj;
		sum2 = 0.0;
		count2 = 0;

		for (int j = 2; j <= dim; j++) {
			yj = ds[j - 1] - Math.sin(6.0 * Math.PI * ds[0] + j * Math.PI / dim);
			hj = 2.0 * yj * yj - Math.cos(4.0 * Math.PI * yj) + 1.0;
			if (j % 2 == 0) {
				sum2 += hj;
				count2++;
			}
		}
		hj = (0.5 / N + epsilon) * Math.abs(Math.sin(2.0 * N * Math.PI * ds[0]));

		return 1.0 - ds[0] + hj + 2.0 * sum2 / (double) count2;
	}
}
