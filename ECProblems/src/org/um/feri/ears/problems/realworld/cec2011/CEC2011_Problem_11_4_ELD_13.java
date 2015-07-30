package org.um.feri.ears.problems.realworld.cec2011;


import org.um.feri.ears.problems.Problem;
import org.um.feri.ears.util.Util;

/**
 * Problem function!
 * 
 * @author Matej Črepinšek
 * @version 1
 * 
 **/	
public class CEC2011_Problem_11_4_ELD_13 extends Problem {
	private double g_constrains[]; //internal
	/*
*/
	

	//        % % % ============= 6 unit system data ==========
	//        % Data1=  [Pmin   Pmax     a       b           c]; 
	private static double[][] Data1={{ 0,  680, 0.00028, 8.1, 550, 300, 0.035},
		{ 0,  360, 0.00056, 8.1, 309, 200, 0.042},
		{0,  360, 0.00056, 8.1, 307, 200, 0.042},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{60, 180, 0.00324, 7.74,240, 150, 0.063},
		{40, 120, 0.00284, 8.6, 126, 100, 0.084},
		{40, 120, 0.00284, 8.6, 126, 100, 0.084},
		{55, 120, 0.00284, 8.6, 126, 100, 0.084},
		{55, 120, 0.00284, 8.6, 126, 100, 0.084}};
	       // % Data2=[Po     UR      DR      Zone1min    Zone1max     Zone2min   Zone2max];
	private static double[][] Data2={};
	
    //% Loss Co-efficients
	private static double[][] B1={ };	
	private static double[] B2={};
	private static double B3=0;
	private static double Power_Demand = 1800;; // in MW
	
	private static int Pmin_data1_col=0;// = Data1(:,1)'; 
	private static int Pmax_data1_col=1;// = Data1(:,2)';
	private static int a_data1_col=2;// = Data1(:,3)';
	private static int b_data1_col=3;// = Data1(:,4)';
	private static int c_data1_col=4;// = Data1(:,5)';
	private static int e_data1_col=5;// = Data1(:,6)';
	private static int f_data1_col=6;// = Data1(:,7)';



	public CEC2011_Problem_11_4_ELD_13() {
		dim = 13;
		constrains = 2;//
		interval = new double[dim];
		intervalL = new double[dim];
		//0,680;0,360;0,360;60,180;60,180;60,180;60,180; 60,180;60,180;40,120;40,120;55,120;55,120;];
		int z = 0;
		intervalL[z] = 0;
		interval[z] = 680 - intervalL[z];
		z++;intervalL[z] = 0;
		interval[z] = 360 - intervalL[z];
		z++;
		intervalL[z] = 0;
		interval[z] = 360 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 60;
		interval[z] = 180 - intervalL[z];
		z++;
		intervalL[z] = 40;
		interval[z] = 120 - intervalL[z];
		z++;
		intervalL[z] = 40;
		interval[z] = 120 - intervalL[z];
		z++;
		intervalL[z] = 55;
		interval[z] = 120 - intervalL[z];
		z++;
		intervalL[z] = 55;
		interval[z] = 120 - intervalL[z];

		name = "RWP_11_4_ELD_13";
		desc = "RWP_11_4_ELD_13 Static Economic Load Dispatch (ELD) Problem ";
	}
	
	private double produkt(double x[], double y[]) {
		double t=0;
		for (int i=0;i<x.length; i++) {
			t+=x[i]*y[i];
		}
		return t;
	}
	private double[] produkt2(double x[],double y[][]) {
		double t[]=new double[dim]; //dim??
		for (int i=0;i<x.length; i++) {
			double tt=0;
			for (int ii=0;ii<x.length; ii++) {
				tt+=x[i]*y[ii][i];
			}
			t[i]=tt;
		}
		return t;
	}
	protected double sum(double x[]) {
		double tt=0;
		for (int ii=0;ii<x.length; ii++) {
			tt+=x[ii];
		}
		return tt;
	}
	
	public double[] calc_constrains(double x[]) {
		g_constrains = new double[constrains];
		double Power_Loss = 0;
		double  Power_Balance_Penalty = Math.abs(Power_Demand + Power_Loss - sum(x));
        double Capacity_Limits_Penalty=0;
        for (int i=0; i<dim; i++) { //Capacity_Limits_Penalty = sum(abs(x-Pmin)-(x-Pmin)) + sum(abs(Pmax-x)-(Pmax-x));
       	 Capacity_Limits_Penalty+=Math.abs(x[i]-Data1[i][Pmin_data1_col])-(x[i]-Data1[i][Pmin_data1_col]); //sum(abs(x-Pmin)-(x-Pmin))
       	 Capacity_Limits_Penalty+=Math.abs(Data1[i][Pmax_data1_col]-x[i])-(Data1[i][Pmax_data1_col]-x[i]); //sum(abs(Pmax-x)-(Pmax-x))
        }
		  g_constrains[0] = 1e5*Power_Balance_Penalty;
	        g_constrains[1] = 1e3*Capacity_Limits_Penalty;


		return g_constrains; //do I need deep copy?
	}
	
	public double eval(double x[]) {

	        //  Cost(i,1) = sum( a.*(x.^2) + b.*x + c + abs(e.*sin(f.*(Pmin-x))) );
	        double f = 0;
	        for (int i=0; i<dim; i++) {
	        	f+=Data1[i][a_data1_col]*x[i]*x[i]+Data1[i][b_data1_col]*x[i]+Data1[i][c_data1_col]+Math.abs(Data1[i][e_data1_col]*Math.sin(Data1[i][e_data1_col]*(Data1[i][Pmin_data1_col]-x[i])));
	        }
	       // System.out.println("f:"+f+" Total_Penalty:"+Total_Penalty);
		
	  

		return f;
	}

	public double getOptimumEval() {
		return 0; //OK
	}

	

}