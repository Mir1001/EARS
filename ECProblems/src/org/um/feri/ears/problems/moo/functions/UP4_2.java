//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP4_2 extends Problem {

	int dim;

	public UP4_2(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count2;
		double sum2, yj, hj;
		sum2 = 0.0;
		count2 = 0;

		for (int j = 2; j <= dim; j++) {
			yj = ds[j - 1] - Math.sin(6.0 * Math.PI * ds[0] + j * Math.PI / dim);
			hj = Math.abs(yj) / (1.0 + Math.exp(2.0 * Math.abs(yj)));
			if (j % 2 == 0) {
				sum2 += hj;
				count2++;
			}
		}

		return 1.0 - ds[0] * ds[0] + 2.0 * sum2 / (double) count2;
	}
}
