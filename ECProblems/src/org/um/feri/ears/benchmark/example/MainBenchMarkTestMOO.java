package org.um.feri.ears.benchmark.example;

import java.util.ArrayList;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.moo.moead.MOEAD_DRA;
import org.um.feri.ears.algorithms.moo.nsga2.NSGAII;
import org.um.feri.ears.algorithms.moo.paes.PAES;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.benchmark.RatingCEC2009;
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.quality_indicator.CoverageOfTwoSets;
import org.um.feri.ears.rating.Player;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

public class MainBenchMarkTestMOO {

	public static void main(String[] args) {
		
        Util.rnd.setSeed(System.currentTimeMillis());
        RatingBenchmark.debugPrint = true; //prints one on one results
        ArrayList<Algorithm> players = new ArrayList<Algorithm>();
        players.add(new MOEAD_DRA());
        players.add(new NSGAII());
        //players.add(new SPEA2());
        //players.add(new PESAII());
        players.add(new PAES());
        //players.add(new GDE3());
        
        ResultArena ra = new ResultArena(100);
 

        RatingCEC2009 cec = new RatingCEC2009(); //Create banchmark
        for (Algorithm al:players) {
          ra.addPlayer(al.getID(), 1500, 350, 0.06,0,0,0); //init rating 1500
          cec.registerAlgorithm(al);
        }
        BankOfResults ba = new BankOfResults();
        cec.run(ra, ba, 50); //repeat competition 50X
        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(ra.recalcRangs()); //new rangs
        for (Player p: list) System.out.println(p); //print rangs

	}

}
