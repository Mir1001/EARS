//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.

package org.um.feri.ears.problems.moo.functions;

import org.um.feri.ears.problems.Problem;

public class UP7_1 extends Problem {

	int dim;

	public UP7_1(int dim) {
		this.dim = dim;
	}

	@Override
	public double eval(double[] ds) {

		int count1;
		double sum1, yj;
		sum1 = 0.0;
		count1 = 0;

		for (int j = 2; j <= dim; j++) {
			yj = ds[j - 1] - Math.sin(6.0 * Math.PI * ds[0] + j * Math.PI / dim);
			if (j % 2 == 0) {
				continue;
			} else {
				sum1 += yj * yj;
				count1++;
			}
		}
		yj = Math.pow(ds[0], 0.2);

		return yj + 2.0 * sum1 / (double) count1;
	}
}
