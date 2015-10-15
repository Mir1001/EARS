//  Epsilon.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.um.feri.ears.quality_indicator;

/**
 * This class implements the unary epsilon additive indicator as proposed in
 * E. Zitzler, E. Thiele, L. Laummanns, M., Fonseca, C., and Grunert da Fonseca.
 * V (2003): Performance Assesment of Multiobjective Optimizers: An Analysis and
 * Review. The code is the a Java version of the original metric implementation
 * by Eckart Zitzler.
 * It can be used also as a command line program just by typing
 * $java jmetal.qualityIndicator.Epsilon <solutionFrontFile> <trueFrontFile> <numberOfObjectives>
 */
public class Epsilon extends QualityIndicator{

	/* stores the number of objectives */
	int dim;
	// obj_[i]=0 means objective i is to be minimized. This code always assume the minimization of all the objectives
	int[] obj; /* obj_[i] = 0 means objective i is to be minimized */
	// method_ = 0 means apply additive epsilon and method_ = 1 means multiplicative epsilon. This code always apply additive epsilon
	int method;
	
	
	
	public Epsilon() {
		name = "Epsilon";
	}

	@Override
	public double get_indicator(double[][] front, double[][] trueParetoFront, int numberOfObjectives) {

		int i, j, k;
		double eps, eps_j = 0.0, eps_k = 0.0, eps_temp;

		dim = numberOfObjectives;
		set_params();

		if (method == 0)
			eps = Double.MIN_VALUE;
		else
			eps = 0;

		for (i = 0; i < trueParetoFront.length; i++) {
			for (j = 0; j < front.length; j++) {
				for (k = 0; k < dim; k++) {
					switch (method) {
					case 0:
						if (obj[k] == 0)
							eps_temp = front[j][k] - trueParetoFront[i][k];
						// eps_temp = b[j * dim_ + k] - a[i * dim_ + k];
						else
							eps_temp = trueParetoFront[i][k] - front[j][k];
						// eps_temp = a[i * dim_ + k] - b[j * dim_ + k];
						break;
					default:
						if ((trueParetoFront[i][k] < 0 && front[j][k] > 0)
								|| (trueParetoFront[i][k] > 0 && front[j][k] < 0)
								|| (trueParetoFront[i][k] == 0 || front[j][k] == 0)) {
							// if ( (a[i * dim_ + k] < 0 && b[j * dim_ + k] > 0)
							// ||
							// (a[i * dim_ + k] > 0 && b[j * dim_ + k] < 0) ||
							// (a[i * dim_ + k] == 0 || b[j * dim_ + k] == 0)) {
							System.err.println("error in data file");
							System.exit(0);
						}
						if (obj[k] == 0)
							eps_temp = front[j][k] / trueParetoFront[i][k];
						// eps_temp = b[j * dim_ + k] / a[i * dim_ + k];
						else
							eps_temp = trueParetoFront[i][k] / front[j][k];
						// eps_temp = a[i * dim_ + k] / b[j * dim_ + k];
						break;
					}
					if (k == 0)
						eps_k = eps_temp;
					else if (eps_k < eps_temp)
						eps_k = eps_temp;
				}
				if (j == 0)
					eps_j = eps_k;
				else if (eps_j > eps_k)
					eps_j = eps_k;
			}
			if (i == 0)
				eps = eps_j;
			else if (eps < eps_j)
				eps = eps_j;
		}
		return eps;
	}

	/**
	 * Established the params by default
	 */
	void set_params() {
		int i;
		obj = new int[dim];
		for (i = 0; i < dim; i++) {
			obj[i] = 0;
		}
		method = 0;
	}

	@Override
	public boolean isMin() {
		
		return true;
	}

	@Override
	public IndicatorType getNumberOfSets() {
		return QualityIndicator.IndicatorType.Unary;
	}

	@Override
	public boolean requiresReferenceSet() {
		return true;
	}

	@Override
	public int compare(double[][] front1, double[][] front2, int numberOfObjectives) {
		return 0;
	}
}
