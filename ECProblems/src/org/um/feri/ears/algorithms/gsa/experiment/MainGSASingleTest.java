package org.um.feri.ears.algorithms.gsa.experiment;


import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.gsa.GSA;
import org.um.feri.ears.algorithms.pso.PSO;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.Individual;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.unconstrained.ProblemSchwefel;
import org.um.feri.ears.problems.unconstrained.ProblemSphere;

public class MainGSASingleTest {

	public static void main(String[] args) {
		//Task prob=new Task(EnumStopCriteria.EVALUATIONS, 50000, 0.001, new ProblemSchwefel(30));
		Algorithm test = new GSA(20);
		Algorithm test1 = new PSO();
		
		test.setDebug(true);
		Individual best;
        try {
            best = test.run(new Task(EnumStopCriteria.EVALUATIONS, 50000, 0.001, new ProblemSchwefel(30)));
            System.out.println("Best is:"+best);
            best = test1.run(new Task(EnumStopCriteria.EVALUATIONS, 50000, 0.001, new ProblemSchwefel(30)));
            System.out.println("PSO Best is:"+best);
        } catch (StopCriteriaException e) {
            e.printStackTrace();
        }

	}

}
