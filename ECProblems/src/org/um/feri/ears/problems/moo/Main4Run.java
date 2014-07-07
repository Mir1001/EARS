package org.um.feri.ears.problems.moo;

import org.um.feri.ears.algorithms.moo.moead.MOEAD_DRA;
import org.um.feri.ears.algorithms.moo.nsga2.NSGAII;
import org.um.feri.ears.algorithms.moo.spea2.SPEA2;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.problems.EnumStopCriteria;
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
import org.um.feri.ears.util.Util;

public class Main4Run {
    public static void main(String[] args) {
    	Util.rnd.setSeed(8706);
        Task t = new Task(EnumStopCriteria.EVALUATIONS, 150000, 0.0001, new UnconstrainedProblem6());
        MOEAD_DRA test = new MOEAD_DRA(300);
        try {
            System.out.println(test.run(t));
        } catch (StopCriteriaException e) {
            e.printStackTrace();
        }
    }
}
