package org.um.feri.ears.util;
/**
 * Simple util methods.
 * 
 * <p>
 * 
 * @author Matej Crepinsek
 * @version 1
 * 
 *          <h3>License</h3>
 * 
 *          Copyright (c) 2011 by Matej Crepinsek. <br>
 *          All rights reserved. <br>
 * 
 *          <p>
 *          Redistribution and use in source and binary forms, with or without
 *          modification, are permitted provided that the following conditions
 *          are met:
 *          <ul>
 *          <li>Redistributions of source code must retain the above copyright
 *          notice, this list of conditions and the following disclaimer.
 *          <li>Redistributions in binary form must reproduce the above
 *          copyright notice, this list of conditions and the following
 *          disclaimer in the documentation and/or other materials provided with
 *          the distribution.
 *          <li>Neither the name of the copyright owners, their employers, nor
 *          the names of its contributors may be used to endorse or promote
 *          products derived from this software without specific prior written
 *          permission.
 *          </ul>
 *          <p>
 *          THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *          "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *          LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *          FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *          COPYRIGHT OWNERS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *          INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *          BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *          LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *          CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *          LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *          ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *          POSSIBILITY OF SUCH DAMAGE.
 * 
 */

import java.text.DecimalFormat;
import java.util.Random;

import org.um.feri.ears.problems.Problem;

public class Util {
	public static DecimalFormat df1 = new DecimalFormat("#,###.#");
	public static DecimalFormat df = new DecimalFormat("#,###.##");
	public static DecimalFormat df3 = new DecimalFormat("#,###.###");
	public static DecimalFormat df6 = new DecimalFormat("#,###.######");
	public static DecimalFormat dfc1 = new DecimalFormat("#,##0.#######E0");
	public static DecimalFormat dfc2 = new DecimalFormat("#,##0.##E0");
	public static DecimalFormat dfcshort = new DecimalFormat("0.##E0");
	public static DecimalFormat intf = new DecimalFormat("###,###,###");
	public static long randomseed = 316227711; //to be able too repeat experiment
	public static Random rnd = new MersenneTwister(randomseed);
    public static double roundDouble3(double r) {
        return roundDouble(r, 3); 
    }
	public static double roundDouble(double r, int dec) {
	    r = Math.round(r*Math.pow(10, dec));
	    return r/Math.pow(10, dec);
	}
	public static String arrayToString(double d[]) {
		String s = "";
		for (int i = 0; i < d.length; i++) {
			s = s + df.format(d[i]);
			if (i < d.length - 1)
				s = s + ",";
		}
		return s;
	}

	public static void printPopulationCompare(double d[][], double e[],
			Problem p, int gen) {
		System.out.println("Generation:" + gen);
		for (int i = 0; i < d.length; i++) {
			System.out.println(arrayToString(d[i]) + " eval:" + e[i]
					+ " real: " + p.eval(d[i]) + " constrain: "
					+ p.constrainsEvaluations(d[i]));
			if (e[i] != p.eval(d[i]))
				System.out.println("ERROR!!!");
		}
	}

	public static void printPopulation(double d[][], Problem p, int gen) {
		System.out.println("Generation:" + gen);
		for (int i = 0; i < d.length; i++) {
			System.out.println(arrayToString(d[i]) + " eval:" + p.eval(d[i])
					+ " constrain: " + p.constrainsEvaluations(d[i]));
		}
	}

	public static double divide(double a, double b) {
		if (b == 0)
			return 0;
		return a / b;
	}
}
