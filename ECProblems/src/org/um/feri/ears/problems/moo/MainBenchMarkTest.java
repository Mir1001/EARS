package org.um.feri.ears.problems.moo;

import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.BeepAction;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.es.ES1p1sAlgorithm;
import org.um.feri.ears.algorithms.moo.moead.MOEAD_DRA;
import org.um.feri.ears.algorithms.moo.nsga2.NSGAII;
import org.um.feri.ears.algorithms.moo.paes.PAES;
import org.um.feri.ears.algorithms.moo.pesa2.PESA2;
import org.um.feri.ears.algorithms.moo.spea2.SPEA2;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.algorithms.tlbo.TLBOAlgorithm;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.benchmark.RatingCEC2009;
import org.um.feri.ears.benchmark.RatingRPUOed2;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.rating.Player;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

import com.erciyes.karaboga.bee.BeeColonyAlgorithm;

public class MainBenchMarkTest {
    public static void main(String[] args) {
        Util.rnd.setSeed(System.currentTimeMillis());
        RatingBenchmark.debugPrint = true; //prints one on one results
        ArrayList<Algorithm> players = new ArrayList<Algorithm>();
        players.add(new MOEAD_DRA(100)); //EARS exampels
        players.add(new NSGAII(100)); //EARS exampels
        players.add(new SPEA2(100));
        players.add(new PESA2(100));
        players.add(new PAES(100));
        
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
