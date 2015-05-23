/**
 * Insert data
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
package org.um.feri.ears.benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.export.data.EDBenchmark;
import org.um.feri.ears.export.data.EDTask;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.rating.Game;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

//TODO calculate CD for rating
public abstract class RatingBenchmark {
    public static boolean debugPrint=false;
    protected ArrayList<TaskWithReset> listOfProblems;
    protected ArrayList<Algorithm> listOfAlgorithmsPlayers;
    
    protected EnumStopCriteria stopCriteria = EnumStopCriteria.EVALUATIONS; //default
    private ArrayList<AlgorithmEvalResult> results;
    private EnumMap<EnumBenchmarkInfoParameters,String> parameters; //add all specific parameters
    public static boolean printSingleRunDuration=false;
    protected int duelNumber;
    public void addParameter(EnumBenchmarkInfoParameters id, String value){
        parameters.put(id, value);
    }
    
    public EnumMap<EnumBenchmarkInfoParameters, String> getParameters() {
        return parameters;
    }
    public void clearPlayers() {
        listOfAlgorithmsPlayers.clear();
        results.clear();
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> a = new  ArrayList<Task>();
        for (TaskWithReset tw:listOfProblems) {
            a.add(tw);
        }
        return a;
    }
    public String getParams() {
        StringBuffer sb = new StringBuffer();
        sb.append("Parameters:\n");
        for (EnumBenchmarkInfoParameters a:parameters.keySet()) {
            sb.append(a.getShortName()).append(" = ").append(parameters.get(a)).append("\t").append("(").append(a.getDescription()).append(")\n");
        }
        return sb.toString();
    }
    public void updateParameters() {
        parameters.put(EnumBenchmarkInfoParameters.NUMBER_OF_TASKS, ""+listOfProblems.size());  
    }
    public EDBenchmark export() {
        updateParameters();
        EDBenchmark ed=new EDBenchmark();
        ed.acronym = getAcronym();
        ed.name = getName();
        ed.info = getParams();
        if (getInfo().length()>3) ed.info=ed.info+"\n"+getInfo();
        EDTask tmp;
        for (TaskWithReset ta:listOfProblems) {
            tmp = new EDTask();
            tmp.name = ta.getProblemShortName();
            tmp.info = ta.getStopCriteriaDescription(); //tu!!!
            ed.tasks.add(tmp);
        }
        return ed;
    }
    public RatingBenchmark() {
        listOfProblems = new ArrayList<TaskWithReset>();
        listOfAlgorithmsPlayers = new ArrayList<Algorithm>();
        results = new ArrayList<AlgorithmEvalResult>();
        parameters = new  EnumMap<EnumBenchmarkInfoParameters, String>(EnumBenchmarkInfoParameters.class);
        //initFullProblemList();
    }
    
    protected abstract void registerTask(Problem p, EnumStopCriteria sc, int eval, double epsilon);
    protected abstract void initFullProblemList(); 
    
    public void registerAlgorithm(Algorithm al) {
        listOfAlgorithmsPlayers.add(al);
    }
    
    public abstract boolean resultEqual(Individual a, Individual b);
    public abstract String getName(); //long name 
    public abstract String getAcronym(); //short name for tables etc    
    public abstract String getInfo(); //some explanation
    
    /**
     * TODO  this function can be done parallel - asynchrony
     * 
     * @param task
     * @param allSingleProblemRunResults 
     */
    private void runOneProblem(TaskWithReset task, BankOfResults allSingleProblemRunResults) {
    	long start=0;
    	long duration=0;
        for (Algorithm al: listOfAlgorithmsPlayers) {
            task.resetCounter(); //number of evaluations  
            try {
                start = System.currentTimeMillis();
                if (printSingleRunDuration) {
            	  System.out.print(al.getID()+": ");
                }
                
                Individual bestByALg = al.run(task); //check if result is fake!
                
                duration = System.currentTimeMillis()-start;
                al.addRunDuration(duration);
                if (printSingleRunDuration) System.out.println(duration/1000);
                task.resetCounter(); //for one eval!
                if (task.areDimensionsInFeasableInterval(bestByALg.getX())) {
                	//TODO popravi za multiobjective intruduce a new task method check if true
                  //Individual best = task.eval(bestByALg.getX());
                  Individual best = bestByALg;
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
                    if (t.isFirstBetter(arg0.getBest(), arg1.getBest())) return -1;
                    else return 1;
                } else return -1; //second is null
            } else
                if (arg1.getBest()!= null) return 1; //first null
            return 0; //both equal
        }
    }
    
    private void setWinLoseFromResultList(ResultArena arena, TaskWithReset t) {
        AlgorithmEvalResult win;
        AlgorithmEvalResult lose;        
        FitnessComparator fc;
        fc = new FitnessComparator(t);
        Collections.sort(results, fc); //best first
        for (int i=0; i<results.size()-1; i++) {
            win = results.get(i);
            for (int j=i+1; j<results.size(); j++) {
                lose = results.get(j);
                if (resultEqual(win.best, lose.best)) { //Special for this benchmark
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
