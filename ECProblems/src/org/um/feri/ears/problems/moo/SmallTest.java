package org.um.feri.ears.problems.moo;

import java.util.ArrayList;

import org.um.feri.ears.problems.unconstrained.ProblemGriewank;
import org.um.feri.ears.problems.unconstrained.ProblemRastrigin;
import org.um.feri.ears.problems.unconstrained.ProblemRosenbrock;
import org.um.feri.ears.problems.unconstrained.ProblemSphere;

public class SmallTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
       MOProblem problem = new MOProblem();
       problem.addProblem(new ProblemSphere(2,20));
       problem.addProblem(new ProblemGriewank(2,20));
       // problem.addProblem(new ProblemRastrigin(2,10));
       ArrayList<MOIndividual> pop = new ArrayList<MOIndividual>();
       for (int i=0; i<100; i++) {
           pop.add(problem.eval(problem.getRandomVectorX()));
       }
       System.out.println(problem.toExcel(pop));
       System.out.println();
       ArrayList<MOIndividual> nondom = problem.getNonDominated(pop); 
       System.out.println(problem.toExcel(nondom));
    }

}
