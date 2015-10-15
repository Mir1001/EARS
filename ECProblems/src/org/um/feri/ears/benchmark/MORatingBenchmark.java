package org.um.feri.ears.benchmark;

import java.util.Collections;
import java.util.Comparator;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.benchmark.RatingBenchmark.FitnessComparator;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.moo.MOParetoIndividual;
import org.um.feri.ears.problems.moo.MOProblem;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.quality_indicator.Hypervolume;
import org.um.feri.ears.quality_indicator.QualityIndicator;
import org.um.feri.ears.rating.Game;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

public abstract class MORatingBenchmark extends RatingBenchmarkBase {
	
	protected QualityIndicator qi;
	
	public MORatingBenchmark(QualityIndicator qi){
		super();
		this.qi = qi;
	}
	
	public MORatingBenchmark(){
		this(new Hypervolume());
	}
	
	public abstract boolean resultEqual(MOParetoIndividual a, MOParetoIndividual b);
    protected abstract void registerTask(MOProblem p, EnumStopCriteria sc, int eval, double epsilon, QualityIndicator qi);
	

	protected void runOneProblem(TaskWithReset task, BankOfResults allSingleProblemRunResults) {
		long start=0;
    	long duration=0;
        for (Algorithm al: listOfAlgorithmsPlayers) {
            task.resetCounter(); //number of evaluations  
            try {
                start = System.currentTimeMillis();
                if (printSingleRunDuration) {
            	  System.out.print(al.getID()+": ");
                }
                
                MOParetoIndividual bestByALg = (MOParetoIndividual) al.run(task); //check if result is fake!
                
                duration = System.currentTimeMillis()-start;
                al.addRunDuration(duration);
                if (printSingleRunDuration) System.out.println(duration/1000);
                task.resetCounter(); //for one eval!
                if (task.areDimensionsInFeasableInterval(bestByALg.getX())) {
                	//TODO repair for multiobjective intruduce a new task method check if true
                	MOParetoIndividual best = bestByALg;
                  if (best.getEval()!=bestByALg.getEval()) 
                     System.err.println(al.getAlgorithmInfo().getVersionAcronym()+" result real"+best.getEval()+" is different than "+bestByALg.getEval());
                  results.add(new AlgorithmEvalResult(best, al)); 
                  allSingleProblemRunResults.add(task.getProblem(), best, al);
                }
                else {
                    System.err.println(al.getAlgorithmInfo().getVersionAcronym()+" result "+bestByALg+" is out of intervals! For task:"+task.getProblemShortName());
                    results.add(new AlgorithmEvalResult(null, al)); // this can be done parallel - asynchrony                    
                }
            } catch (StopCriteriaException e) {
                System.err.println(al.getAlgorithmInfo().getVersionAcronym()+" StopCriteriaException for:"+task+"\n"+e);
                results.add(new AlgorithmEvalResult(null, al));
            }   
        }
	}
	
	class FitnessComparator implements Comparator<AlgorithmEvalResult> {
        TaskWithReset t;
        public FitnessComparator(TaskWithReset t) {
            this.t = t;
        }
        @Override
        public int compare(AlgorithmEvalResult arg0, AlgorithmEvalResult arg1) {
            if (arg0.getBest()!=null) {
                if (arg1.getBest()!=null){
                   // if (resultEqual(arg0.getBest(), arg1.getBest())) return 0; Normal sor later!
                    if (t.isFirstBetter((MOParetoIndividual)arg0.getBest(),(MOParetoIndividual) arg1.getBest())) return -1;
                    else return 1;
                } else return -1; //second is null
            } else
                if (arg1.getBest()!= null) return 1; //first null
            return 0; //both equal
        }
    }
    
    protected void setWinLoseFromResultList(ResultArena arena, TaskWithReset t) {
        AlgorithmEvalResult win;
        AlgorithmEvalResult lose;        
        FitnessComparator fc;
        fc = new FitnessComparator(t);
        Collections.sort(results, fc); //best first
        for (int i=0; i<results.size()-1; i++) {
            win = results.get(i);
            for (int j=i+1; j<results.size(); j++) {
                lose = results.get(j);
                if (resultEqual((MOParetoIndividual)win.best, (MOParetoIndividual)lose.best)) { //Special for this benchmark
                    if (debugPrint) System.out.println("draw of "+win.getAl().getID()+" ("+Util.df3.format(win.getBest().getEval())+", feasable="+win.getBest().isFeasible()+") against "+lose.getAl().getID()+" ("+Util.df3.format(lose.getBest().getEval())+", feasable="+lose.getBest().isFeasible()+") for "+t.getProblemShortName());
                    arena.addGameResult(Game.DRAW, win.getAl().getAlgorithmInfo().getVersionAcronym(), lose.getAl().getAlgorithmInfo().getVersionAcronym(), t.getProblemShortName());
                } else {
                    if (win.getAl()==null) {
                        System.out.println("NULL ID "+win.getClass().getName());
                    }
                    if (win.getBest()==null) {
                        System.out.println(win.getAl().getID()+" NULL");
                    }                    
                    if (lose.getAl()==null) {
                        System.out.println("NULL ID "+lose.getClass().getName());
                    }
                    if (lose.getBest()==null) {
                        System.out.println(lose.getAl().getID()+" NULL");
                    }                     
                    if (debugPrint) System.out.println("win of "+win.getAl().getID()+" ("+Util.df3.format(win.getBest().getEval())+", feasable="+win.getBest().isFeasible()+") against "+lose.getAl().getID()+" ("+Util.df3.format(lose.getBest().getEval())+", feasable="+lose.getBest().isFeasible()+") for "+t.getProblemShortName());
                    arena.addGameResult(Game.WIN, win.getAl().getAlgorithmInfo().getVersionAcronym(), lose.getAl().getAlgorithmInfo().getVersionAcronym(), t.getProblemShortName());
                }
                    
            }
        }
    }
    
    /**
     * Fill all data!
     *  
     * @param arena needs to be filed with players and their ratings
     * @param allSingleProblemRunResults 
     * @param repetition
     */
    public void run(ResultArena arena, BankOfResults allSingleProblemRunResults, int repetition) {
        duelNumber = repetition;
        parameters.put(EnumBenchmarkInfoParameters.NUMBER_OF_DEULS, ""+repetition);
        for (TaskWithReset t:listOfProblems) {
            for (int i=0; i<repetition; i++) {
                runOneProblem(t,allSingleProblemRunResults);
                setWinLoseFromResultList(arena,t);
                results.clear();
            }
        }
        
    }
	
	

}
