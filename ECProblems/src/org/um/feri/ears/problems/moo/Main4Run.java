package org.um.feri.ears.problems.moo;

import org.um.feri.ears.algorithms.moo.moead.MOEAD_DRA;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
import org.um.feri.ears.problems.StopCriteriaException;
import org.um.feri.ears.problems.Task;
import org.um.feri.ears.problems.unconstrained.ProblemBranin;
import org.um.feri.ears.problems.unconstrained.ProblemRastrigin;
import org.um.feri.ears.problems.unconstrained.ProblemSphere;
import org.um.feri.ears.problems.unconstrained.cec2009.UnconstrainedProblem1;

public class Main4Run {
    public static void main(String[] args) {
        Task t = new Task(EnumStopCriteria.EVALUATIONS, 200000, 0.0001, new UnconstrainedProblem1()); //run problem Sphere Dimension 5, 3000 evaluations
        MOEAD_DRA test = new MOEAD_DRA();
        try {
            //System.out.println(test.run(t)); //prints best result afrer 3000 runs
        	test.run(t);
        } catch (StopCriteriaException e) {
            e.printStackTrace();
        }
    }
}
