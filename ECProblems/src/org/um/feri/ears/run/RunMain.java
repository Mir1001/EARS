package org.um.feri.ears.run;

import java.util.ArrayList;
import java.util.Collections;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.PlayerAlgorithmExport;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.rating.Player;
import org.um.feri.ears.rating.Rating;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

public class RunMain {
    private ArrayList<PlayerAlgorithmExport> listAll;
    private boolean printDebug;
    private boolean printSingleRunDuration;
    private ArrayList<Algorithm> players;
    private ResultArena ra;
    private RatingBenchmark suopm; // suopm = new RatingRPUOed2();
    private long duration;
    private boolean internal;

    public ArrayList<PlayerAlgorithmExport> getListAll() {
        return listAll;
    }
    public boolean isPrintDebug() {
        return printDebug;
    }
    public RatingBenchmark getRatingBenchmark() {
        return suopm;
    }
    public long getDuration() {
        return duration;
    }
    public void setRa(ResultArena ra) {
        this.ra = ra;
    }
    private RunMain(RatingBenchmark banchmark) {
        internal = true;
        players = new ArrayList<Algorithm>();
        this.printDebug = printDebug;
        suopm = banchmark;
        listAll = new ArrayList<PlayerAlgorithmExport>();
        Util.rnd.setSeed(System.currentTimeMillis());
        ra = new ResultArena(100);
        this.printSingleRunDuration = printSingleRunDuration;     
    }
    /**
     * Set all data!
     * 
     * @param printDebug
     * @param banchmark
     * @param arenaName
     * @param arenaOwner
     */
    public RunMain(boolean printDebug, boolean printSingleRunDuration, RatingBenchmark banchmark) {
        Util.rnd.setSeed(System.currentTimeMillis());
        internal = false;
        players = new ArrayList<Algorithm>();
        this.printDebug = printDebug;
        suopm = banchmark;
        listAll = new ArrayList<PlayerAlgorithmExport>();
        Util.rnd.setSeed(System.currentTimeMillis());
        ra = new ResultArena(100);
        this.printSingleRunDuration = printSingleRunDuration;
    }
    /**
     * Add algorithms in arena.
     * Then run!
     * 
     * @param al
     * @param startRating
     */
    public void addAlgorithm(Algorithm al, Rating startRating) {
        if (!internal) {
            RunMain inte= new RunMain(suopm);
            
        }
        players.add(al);
        if (al==null) System.out.println("Add null algorithm");
        if (al.getAlgorithmInfo()==null) System.out.println("Add algorithm with null AlgorithmInfo "+al.getClass().getName());
        if (al.getImplementationAuthor()==null)  System.out.println("Add algorithm with null Author "+al.getClass().getName());
        PlayerAlgorithmExport tmp;
        tmp = new PlayerAlgorithmExport(al, startRating, 0, 0, 0);
        listAll.add(tmp);
        ra.addPlayer(tmp);
        suopm.registerAlgorithm(al);
    }

    public void run(int repeat) {
        long stTime = System.currentTimeMillis();
        RatingBenchmark.debugPrint = printDebug; // prints one on one results
        RatingBenchmark.printSingleRunDuration = printSingleRunDuration;
        suopm.run(ra, repeat);
        ra.recalcRangs();
        Collections.sort(listAll, new Player.RatingComparator());
        long endTime = System.currentTimeMillis();
        duration = endTime - stTime;
       // System.out.println("Benchmark DURATION: "+duration/1000+"s");
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Results for benchmark:").append(suopm.getAcronym()).append("Benchmark DURATION: ("+duration/1000+"s)").append("\n").append("\n");;
        for (PlayerAlgorithmExport a:listAll) {
            sb.append(a.getPlayerId()).append(" ").append(a.getR().toString()).append("\n");
        }
       return sb.toString();
    }

}
