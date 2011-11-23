package org.um.feri.ears.run;

import java.util.ArrayList;
import java.util.List;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.AlgorithmInfo;
import org.um.feri.ears.algorithms.AlgorithmRunTime;
import org.um.feri.ears.algorithms.PlayerAlgorithmExport;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.rating.Rating;

public class RunMainBestAlgSettings extends RunMain {
    public static final int NUMBER_OF_EVALUATIONS = 30;
    private ArrayList<Algorithm> allAlgorithmWithBestSettings;
    private ArrayList<Rating> allAlgorithmWithBestSettingsRating;

    public RunMainBestAlgSettings(boolean printDebug, boolean printSingleRunDuration, RatingBenchmark banchmark) {
        super(printDebug, printSingleRunDuration, banchmark);
        allAlgorithmWithBestSettings = new ArrayList<Algorithm>();
        allAlgorithmWithBestSettingsRating = new ArrayList<Rating>();
    }
    @Override
    public void run(int repeat) {
        benchMark.clearPlayers();
        for(int i=0; i<allAlgorithmWithBestSettings.size(); i++) {
            super.addAlgorithm(allAlgorithmWithBestSettings.get(i), allAlgorithmWithBestSettingsRating.get(i));
        }
        super.run(repeat);
    }
    
    @Override
    public void addAlgorithm(Algorithm al, Rating startRating) {
        allAlgorithmWithBestSettingsRating.add(startRating);
        RunMain findBestSettings = new RunMain(false, false, benchMark);
        //System.out.println("Add:"+al.getID());
        List<Algorithm> allSettings = al.getAlgorithmParameterTest(benchMark.getParameters(), 8);
        //allSettings.add(al);
        if (allSettings.size() == 0) {
            allAlgorithmWithBestSettings.add(al);
            return;
        }
        if (allSettings.size() == 1) {
            allAlgorithmWithBestSettings.add(allSettings.get(0));
            return;
        }
        if (allSettings.size() > 1) {
            Algorithm a;
            for (int k = 0; k < allSettings.size(); k++) {
                a = allSettings.get(k);
                a.setAlgorithmTmpInfo(new AlgorithmInfo("" + k, "", "" + k, ""));
                findBestSettings.addAlgorithm(a, new Rating(startRating));
            }
            findBestSettings.run(NUMBER_OF_EVALUATIONS);
            //System.out.println(findBestSettings);
            ArrayList<PlayerAlgorithmExport> all = findBestSettings.getListAll(); //sorted
            a =  all.get(0).getAlgorithm();
            System.out.println(a.getID());
            int ver=Integer.parseInt(a.getAlgorithmInfo().getVersionAcronym());
            a.setAlgorithmInfoFromTmp();
            a.getAlgorithmInfo().setSelectedParameterCombination(ver);
            a.resetDuration();
            allAlgorithmWithBestSettings.add(a);
            benchMark.clearPlayers();
                                                                        // BEST
        } 
        //System.out.println("End:"+al.getID());
    }

}
