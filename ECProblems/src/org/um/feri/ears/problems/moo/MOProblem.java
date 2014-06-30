package org.um.feri.ears.problems.moo;

import java.util.ArrayList;
import java.util.Arrays;

import org.um.feri.ears.problems.Problem;

public class MOProblem {
    ArrayList<Problem> list;
    public double[] interval;
    public double[] intervalL;
    
    public MOProblem() {
        list = new ArrayList<Problem>();
    }
    
    public void addProblem(Problem p) {
        list.add(p);
        interval = p.interval; //TODO check that all intervals p are same
        intervalL = p.intervalL; 
    }
    
    public int noOfObjectives() {
        return list.size();
    }
    
    
    public MOIndividual eval(double x[]) {
        double eval[] =new double [list.size()];
        for (int i=0; i<eval.length; i++) {
            eval[i] = list.get(i).eval(x);
        }
        return new MOIndividual(x, eval); //not calculating constrains 
    }

    public int getDim() {
        // TODO Auto-generated method stub
        return 0;
    }

    public double[] getRandomVectorX() {
        return list.get(0).getRandomVectorX();    //TODO check that all intervals p are same

    }

    public boolean isFirstBetter(MOIndividual t1, MOIndividual t2) {
        
        return false;
    }

    public boolean areDimensionsInFeasableInterval(double[] ds) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    public double feasible(double d, int i) {
        // TODO Auto-generated method stub
        return 0;
    }
    //http://en.wikipedia.org/wiki/Pareto_efficient 
    /*public boolean isDominated(MOIndividual prvi, MOIndividual drugi) {
        int temp = 0;
        int tempE = 0;
        for (int i = 0; i < prvi.getObjectives(); i++) {
            if (list.get(i).isFirstBetter(prvi.getEval()[i], drugi.getEval()[i])) {
                tempE++;
                temp++;
            }
            if (prvi.getEval()[i] == drugi.getEval()[i]) {
                temp++;
            }       
                 if (prvi.getEval()[i] >= drugi.getEval()[i])
                temp++;
            if (prvi.getEval()[i] > drugi.getEval()[i])
                tempE++;
                
        }
        if (temp == prvi.getObjectives() && tempE > 0)
            return true;
        return false;
    }*/

    /*public ArrayList<MOIndividual> getNonDominated(ArrayList<MOIndividual> populacija) {
        ArrayList<MOIndividual> tmp = new ArrayList<MOIndividual>();
        ArrayList<MOIndividual> tmp2 = new ArrayList<MOIndividual>();
        tmp.addAll(populacija);
    
        for (int i = 0; i < tmp.size(); i++) { 
            for (int ii = 0; ii < tmp.size(); ii++) {
                if (tmp.get(i)!= tmp.get(ii)) {
                    if (isDominated(tmp.get(i), tmp.get(ii))) {
                        if (!tmp2.contains(tmp.get(ii))) {
                          tmp2.add(tmp.get(ii));
                        }
                    }
                }
            }
        }
        tmp.removeAll(tmp2);
        for (int i=0; i<tmp.size();i++) { //remove duplicate
            for (int ii=tmp.size()-1;ii>=i+1;ii--) {
                if (Arrays.toString(tmp.get(i).getEval()).equals(Arrays.toString(tmp.get(ii).getEval()))) tmp.remove(ii);
            }
        }
        return tmp;
    }*/
    public String toExcel(ArrayList<MOIndividual> populacija) {
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<populacija.size();i++) {
            sb.append(populacija.get(i).toStringFitness()).append("\n");
        }
        return sb.toString();
        
    }
    
    
}
