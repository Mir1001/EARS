package org.um.feri.ears.problems.unconstrained.cec2014;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.um.feri.ears.problems.Problem;
/**
 * Wrapper for orig. CEC2014 functions
 * 
 * @author Matej
 *
 */

public class FX extends Problem {
     
	double[] f;
	int type;
	int id; //1..30
	public static Testfunc tf = new Testfunc();
	// CEC 2014
	public FX(int id, int d) {
		type=Problem.SHIFTED;
		this.id = id;
		dim = d;
		interval = new double[d];
		intervalL = new double[d];
		Arrays.fill(interval, 200);
		Arrays.fill(intervalL, -100);
		name = "F"+id+" CEC2014";
		f= new double[1]; //one result
	}
	
	public double eval(double x[]) {
	  try {
      tf.test_func(x,f,dim,1,id);
    } catch (Exception e) {
      e.printStackTrace();
    }
	  return f[0];
	}

	public double getOptimumEval() {
		return id*100;
	}
	 public double[][] getOptimalVector() {
	    double[][] v = new double[1][dim];
	    File fpt = new File(Testfunc.map()+"input_data//shift_data_"+id+".txt");
	      Scanner input;
        try {
          input = new Scanner(fpt);
   
	      for(int k=0;k<dim;k++)
	      {
	        v[0][k] = input.nextDouble();
	      }
	      input.close();
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
	    return v;
	  }


	@Override
	public boolean isFirstBetter(double[] x, double eval_x, double[] y,
			double eval_y) {
		return eval_x < eval_y;
	}
}