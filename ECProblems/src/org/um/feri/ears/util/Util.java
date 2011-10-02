package org.um.feri.ears.util;

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
	public static DecimalFormat intf = new DecimalFormat("###,###,###");
	public static long randomseed = 316227711; //to be able too repeat experiment
	public static Random rnd = new MersenneTwister(randomseed);
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
