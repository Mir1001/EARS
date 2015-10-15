package org.um.feri.ears.benchmark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.export.data.EDBenchmark;
import org.um.feri.ears.export.data.EDTask;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.TaskWithReset;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.rating.ResultArena;

public abstract class RatingBenchmarkBase {
    public static boolean debugPrint=false;
    protected ArrayList<TaskWithReset> listOfProblems;
    protected ArrayList<Algorithm> listOfAlgorithmsPlayers;
    
    protected EnumStopCriteria stopCriteria = EnumStopCriteria.EVALUATIONS; //default
    protected ArrayList<AlgorithmEvalResult> results;
    protected EnumMap<EnumBenchmarkInfoParameters,String> parameters; //add all specific parameters
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
    public RatingBenchmarkBase() {
        listOfProblems = new ArrayList<TaskWithReset>();
        listOfAlgorithmsPlayers = new ArrayList<Algorithm>();
        results = new ArrayList<AlgorithmEvalResult>();
        parameters = new  EnumMap<EnumBenchmarkInfoParameters, String>(EnumBenchmarkInfoParameters.class);
        //initFullProblemList();
    }
    
    protected abstract void initFullProblemList(); 
    
    public void registerAlgorithm(Algorithm al) {
        listOfAlgorithmsPlayers.add(al);
    }
    
    public abstract String getName(); //long name 
    public abstract String getAcronym(); //short name for tables etc    
    public abstract String getInfo(); //some explanation
    
    /**
     * TODO  this function can be done parallel - asynchrony
     * 
     * @param task
     * @param allSingleProblemRunResults 
     */
    protected abstract void runOneProblem(TaskWithReset task, BankOfResults allSingleProblemRunResults);
    
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
    
    protected abstract void setWinLoseFromResultList(ResultArena arena, TaskWithReset t);
    
    /**
     * Fill all data!
     *  
     * @param arena needs to be filed with players and their ratings
     * @param allSingleProblemRunResults 
     * @param repetition
     */
    public abstract void run(ResultArena arena, BankOfResults allSingleProblemRunResults, int repetition);
}
