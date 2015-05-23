package org.um.feri.ears.problems.moo;

import java.util.ArrayList;
import java.util.List;

import org.um.feri.ears.algorithms.moo.gde3.GDE3;
import org.um.feri.ears.algorithms.moo.moead.MOEAD_DRA;
import org.um.feri.ears.algorithms.moo.nsga2.NSGAII;
import org.um.feri.ears.algorithms.moo.paes.PAES;
import org.um.feri.ears.algorithms.moo.pesa2.PESAII;
import org.um.feri.ears.algorithms.moo.spea2.SPEA2;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.unconstrained.ProblemBranin;
import org.um.feri.ears.problems.unconstrained.ProblemRastrigin;
import org.um.feri.ears.problems.unconstrained.ProblemSphere;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem1;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem10;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem2;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem3;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem4;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem5;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem6;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem7;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem8;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem9;
import org.um.feri.ears.quality_indicator.Epsilon;
import org.um.feri.ears.quality_indicator.GenerationalDistance;
import org.um.feri.ears.quality_indicator.Hypervolume;
import org.um.feri.ears.quality_indicator.InvertedGenerationalDistance;
import org.um.feri.ears.quality_indicator.Spread;
import org.um.feri.ears.util.Util;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;

public class Main4Run {
    public static void main(String[] args) {
    	Util.rnd.setSeed(System.currentTimeMillis());
    	
    	//Set Quality Indicator
    	MOParetoIndividual.setQualityIndicator(new InvertedGenerationalDistance());

    	Task t1 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem1());
    	Task t2 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem2());
    	Task t3 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem3());
    	Task t4 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem4());
    	Task t5 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem5());
    	Task t6 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem6());
    	Task t7 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem7());
    	Task t8 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem8());
    	Task t9 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem9());
    	Task t10 = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem10());
    	ArrayList<Task> tasks = new ArrayList<Task>();
    	tasks.add(t1);
    	tasks.add(t2);
    	tasks.add(t3);
    	tasks.add(t4);
    	tasks.add(t5);
    	tasks.add(t6);
    	tasks.add(t7);
    	tasks.add(t8);
    	tasks.add(t9);
    	tasks.add(t10);
    	String data ="";
    	for (Task task : tasks) {
    		GDE3 test = new GDE3();
            try {
            	Individual best = test.run(task);
            	data+= best.getEval()+"\n";
            } catch (StopCriteriaException e) {
                e.printStackTrace();
            }
		}
    	data = data.replace('.', ',');
    	System.out.println(data);
    	
        
    	
        //test.setDisplayData(true);
        //test.setSaveData(true);
        /*double[] results = new double[50];
        double sum = 0.0;
        double avg, sd = 0.0;
        for (int i = 0; i < results.length; i++) {
        	UnconstrainedProblem4 uc4 = new UnconstrainedProblem4();
            Task t = new Task(EnumStopCriteria.EVALUATIONS, 300000, 0.0001, new UnconstrainedProblem4());
            PESAII test = new PESAII();
            
        	try {
            	Individual best = test.run(t);
            	System.out.println(best.getEval());
            	results[i] = best.getEval();
            	sum+= best.getEval();
            } catch (StopCriteriaException e) {
                e.printStackTrace();
            }
		}
        avg = sum / results.length;
        
        for (int i=0; i<results.length;i++)
        {
            sd += Math.pow(results[i] - avg, 2);
        }
        sd = Math.sqrt(sd / results.length);
        
        System.out.println("Avg: "+avg+"\nStd: "+sd);*/
    }
}
