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

import org.um.feri.ears.algorithms.IAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.unconstrained.*;
import org.um.feri.ears.rating.Game;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.util.Util;

//TODO calculate CD for rating
public abstract class RatingBenchmark {
    public static boolean debugPrint=false;
    protected ArrayList<TaskWithReset> listOfProblems;
    protected ArrayList<IAlgorithm> listOfAlgorithmsPlayers;
    
    protected EnumStopCriteria stopCriteria = EnumStopCriteria.EVALUATIONS; //default
    private ArrayList<AlgorithmEvalResult> results;
    public RatingBenchmark() {
        listOfProblems = new ArrayList<TaskWithReset>();
        listOfAlgorithmsPlayers = new ArrayList<IAlgorithm>();
        results = new ArrayList<AlgorithmEvalResult>();
        //initFullProblemList();
    }
    
    protected abstract void registerTask(Problem p);
    protected abstract void initFullProblemList(); 
    
    public void registerAlgorithm(IAlgorithm al) {
        listOfAlgorithmsPlayers.add(al);
    }
    public abstract boolean resultEqual(Individual a, Individual b);
    public abstract String getName(); //long name 
    public abstract String getAcronym(); //short name for tables etc    
    public abstract String getInfo(); //some explanation
    private void runOneProblem(TaskWithReset task) {
        for (IAlgorithm al: listOfAlgorithmsPlayers) {
            task.resetCounter(); //number of evaluations
            try {
                results.add(new AlgorithmEvalResult(al.run(task), al));
            } catch (StopCriteriaException e) {
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
                if (debugPrint) System.out.println("win of "+win.getAl().getID()+" ("+Util.df3.format(win.getBest().getEval())+") against "+lose.getAl().getID()+" ("+Util.df3.format(lose.getBest().getEval())+") for "+t.getProblemShortName());
                if (resultEqual(win.best, lose.best)) { //Special for this benchmark
                    arena.addGameResult(Game.DRAW, win.getAl().getAlgorithmInfo().getVersionAcronym(), lose.getAl().getAlgorithmInfo().getVersionAcronym(), t.getProblemShortName());
                } else
                    arena.addGameResult(Game.WIN, win.getAl().getAlgorithmInfo().getVersionAcronym(), lose.getAl().getAlgorithmInfo().getVersionAcronym(), t.getProblemShortName());
                    
            }
        }
    }
    
    /**
     * Fill all data!
     *  
     * @param arena needs to be filed with players and their ratings
     * @param repetition
     */
    public void run(ResultArena arena, int repetition) {
        for (TaskWithReset t:listOfProblems) {
            for (int i=0; i<repetition; i++) {
                runOneProblem(t);
                setWinLoseFromResultList(arena,t);
                results.clear();
            }
        }
        
    }
    
}
