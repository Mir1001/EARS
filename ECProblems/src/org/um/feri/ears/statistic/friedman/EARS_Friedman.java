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
package org.um.feri.ears.statistic.friedman;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;

import net.sourceforge.jswarm_pso.SwarmAlgorithm;

import org.um.feri.ears.algorithms.Algorithm;
import org.um.feri.ears.algorithms.PlayerAlgorithmExport;
import org.um.feri.ears.algorithms.cma.CMA_ESAlgorithm;
import org.um.feri.ears.algorithms.es.ES1p1sAlgorithm;
import org.um.feri.ears.algorithms.random.RandomWalkAMAlgorithm;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.algorithms.tlbo.TLBOAlgorithm;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.benchmark.RatingCEC2010;
import org.um.feri.ears.benchmark.RatingRPUOed2;
import org.um.feri.ears.export.data.EDBenchmark;
import org.um.feri.ears.export.data.EDBenchmarkRunArena;
import org.um.feri.ears.export.data.EDEnumBenchmarkRunType;
import org.um.feri.ears.export.data.EDPlayerMoreInfo;
import org.um.feri.ears.export.data.EDStatP2PList;
import org.um.feri.ears.export.data.EDStatP2TaskList;
import org.um.feri.ears.export.data.EDStatPlayer2Player;
import org.um.feri.ears.export.data.EDStatPlayer2Task;
import org.um.feri.ears.export.data.EDStatPlayerMoreInfoList;
import org.um.feri.ears.export.data.EDWinnLossDraw;
import org.um.feri.ears.problems.results.BankOfResults;
import org.um.feri.ears.problems.results.FriedmanTransport;
import org.um.feri.ears.rating.Player;
import org.um.feri.ears.rating.Rating;
import org.um.feri.ears.rating.ResultArena;
import org.um.feri.ears.run.RunMain;
import org.um.feri.ears.util.Util;

import com.erciyes.karaboga.bee.BeeColonyAlgorithm;
import com.google.gson.Gson;
import com.um.feri.brest.de.DEAlgorithm;

/**
 * @author Administrator
 * 
 */
public class EARS_Friedman {
    public static void main(String[] args) {
    	
    	int dim = 30;
    	int eval = 100000;
    	int runs = 25;
    	double draw = 0.000001;
    	
    	RatingCEC2010 b2 = new RatingCEC2010(draw);
    	
    	
        RunMain m = new RunMain(false, false, b2) ;
        m.addAlgorithm(new RandomWalkAlgorithm(),new Rating(1500, 350, 0.06)); // RWSi
        m.addAlgorithm(new BeeColonyAlgorithm(),new Rating(1500, 350, 0.06));  // ABC
        m.addAlgorithm(new TLBOAlgorithm(),new Rating(1500, 350, 0.06));       // TLBO
        m.addAlgorithm(new DEAlgorithm(DEAlgorithm.JDE_rand_1_bin, 20),new Rating(1500, 350, 0.06));  //jDE
        m.addAlgorithm(new ES1p1sAlgorithm(),new Rating(1500, 350, 0.06)); // ES
        m.addAlgorithm(new CMA_ESAlgorithm(true),new Rating(1500, 350, 0.06)); //CMA-ES
        for (int k=1;k<11;k++)
            m.addAlgorithm(new DEAlgorithm(k,20),new Rating(1500, 350, 0.06));
        m.run(runs);
        BankOfResults br = m.getBankOfResults();
        FriedmanTransport fr = br.calc4Friedman();
        //fr.print();
        //System.out.println("");
        
        //System.out.println(br);
        //System.out.println(m);
        
        ArrayList<PlayerAlgorithmExport> vsi = m.getListAll();  
        
        Friedman_2.setStatistics(fr.getDatasets(), fr.getAlgoritms(), fr.getMean());
        Results[] statistics_results = Friedman_2.getResults();
        
		Vector<String> al = fr.getAlgoritms();
		Vector<String> pr = fr.getDatasets();
		
		/* OUTPUT */
		
		StringBuffer sb_new = new StringBuffer();
		
		/*
		sb_new.append("Algorithm").append('\t');
		sb_new.append("Average rank").append('\t');
		sb_new.append("Rating").append('\t');
		sb_new.append("Rating dev.").append('\t');
		sb_new.append("Rating interval").append('\t');
		sb_new.append("Sig. Diff. Nemenyi's test").append('\t');
		sb_new.append("Sig. Diff. Holm's test").append('\t');
		sb_new.append("Sig. Diff. Shaffer's test").append('\t');
		sb_new.append('\n');
		
       
        for (int k=0; k<vsi.size();k++) {
        	for (int l=0;l<statistics_results.length;l++){
				if (statistics_results[l].Name == (String)vsi.get(k).getPlayerId()){
					statistics_results[l].Rating = vsi.get(k).getR().getRating();
					statistics_results[l].RatingDev = vsi.get(k).getR().getRD();
					statistics_results[l].RatingVol = vsi.get(k).getR().getRatingVolatility();
					statistics_results[l].DiffersNeme = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Nemenyi");
					statistics_results[l].DiffersHolm = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Holm");
					statistics_results[l].DiffersShaf = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Shaffer");
					break;
				}
        	}
        }
        
		long rating_L = 0;
		long rating_R = 0;
		for (int k=0; k<al.size();k++) {
			sb_new.append(al.get(k)).append('\t');
			for (int l=0;l<statistics_results.length;l++){
				if (statistics_results[l].Name == (String)al.get(k)){
					sb_new.append(statistics_results[l].AverageRank).append('\t');
				    sb_new.append(Math.round(statistics_results[l].Rating)).append('\t');
					sb_new.append(Math.round(statistics_results[l].RatingDev)).append('\t');
					rating_L = Math.round(statistics_results[l].Rating) - 2*Math.round(statistics_results[l].RatingDev);
					rating_R = Math.round(statistics_results[l].Rating) + 2*Math.round(statistics_results[l].RatingDev);
					sb_new.append("[" + rating_L + ", " + rating_R + "]").append('\t');
					sb_new.append(statistics_results[l].DiffersNeme).append('\t');
					sb_new.append(statistics_results[l].DiffersHolm).append('\t');
					sb_new.append(statistics_results[l].DiffersShaf).append('\t');
					break;
				}
			}
			sb_new.append('\n');
		}
		*/
		
		/*
		 * Calculate data for output
		 */
		
		for (int k=0; k<vsi.size();k++) {
        	for (int l=0;l<statistics_results.length;l++){
				if (statistics_results[l].Name == (String)vsi.get(k).getPlayerId()){
					statistics_results[l].Rating = vsi.get(k).getR().getRating();
					statistics_results[l].RatingDev = vsi.get(k).getR().getRD();
					statistics_results[l].RatingVol = vsi.get(k).getR().getRatingVolatility();
					statistics_results[l].DiffersNeme = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Nemenyi");
					statistics_results[l].DiffersHolm = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Holm");
					statistics_results[l].DiffersShaf = Friedman_2.getDiffers((String)vsi.get(k).getPlayerId(), 0.05, "Shaffer");
					break;
				}
        	}
        }
		
		/*
		 * Experiment setup
		 */
		
		sb_new.append("Number of algorithms: " + al.size() + "\n\\\\\n");
		sb_new.append("Number of problems: " + pr.size() + "\n\\\\\n");
		sb_new.append("Problems dimension: " + dim + "\n\\\\\n");
		sb_new.append("Maximum number of evaluations: " + eval + "\n\\\\\n");
		sb_new.append("Draw limit: " + draw + "\n\\\\\n");
		sb_new.append("Number of runs: " + runs + "\n\\\\\n");
		
		/*
		 * Average ranking of the algorithms
		 */
		
		sb_new.append("\\begin{table}[!htp]\n\\centering\n" +
           		"\\begin{tabular}{c | c}\n" +
           		"Algorithm & Average ranking\\\\\n\\hline\n");
		
		
		// Order by average rank ASC
		Results stat_temp;
		for (int n=0;n<statistics_results.length;n++){
			for (int o=n;o<statistics_results.length;o++){
				if(statistics_results[o].AverageRank < statistics_results[n].AverageRank){
					stat_temp = statistics_results[n];
					statistics_results[n] = statistics_results[o];
					statistics_results[o] = stat_temp;
				}
			}
		}
		
		
		for (int l=0;l<statistics_results.length;l++){
			sb_new.append(statistics_results[l].Name + " & ");
			sb_new.append(statistics_results[l].AverageRank + "\\\\\n");
		}
		
	
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Average ranking of the algorithms}\n" + "\\end{table}");
		
		/*
		 * Data about Friedman's and Iman's & Davenport's statistic
		 */
		
		sb_new.append("\n\nFriedman statistic distributed" +
				" according to chi-square with "+(al.size()-1) +
				" degrees of freedom $\\chi^2_F$ = " + Friedman_2.FriedmanStat + ".\n\\\\\n");
		sb_new.append("P-value computed by Friedman Test: " + Friedman_2.FriedmanStatPValue +".\n\\\\");
		sb_new.append("\\\\\n");
		sb_new.append("Iman and Davenport statistic distributed" +
				" according to F-distribution with "+(al.size()-1)+" and "+ (al.size()-1)*(pr.size()-1) +
				" degrees of freedom $F^2_F$ = " + Friedman_2.ImanDenverStat + ".\n\\\\\n");
		sb_new.append("P-value computed by Iman and Daveport Test: " + Friedman_2.ImanDenverPValue +".\n\\\\\n");
		
		/*
		 * Table with adjusted p-values
		 */
		
		sb_new.append("\\begin{table}[!htp]\n\\centering\\tiny\n" +
           		"\\begin{tabular}{c c c c c}\n" +
           		" & Hypothesis & $p_{Nemenyi}$ & $p_{Holm}$ & $p_{Shaffer}$\\\\\n\\hline\n");
		
		for (int l=0;l<Friedman_2.statistics_pairs_p_values.length;l++){
			sb_new.append(l+1 + " & " + 
						  Friedman_2.statistics_pairs_p_values[l].first + " vs. " +
					      Friedman_2.statistics_pairs_p_values[l].second + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pNeme,10) + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pHolm,10) + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pShaf,10) + "\\\\\n");
			if ((l+1)%60==0 && l+1 < Friedman_2.statistics_pairs_p_values.length){
				sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Adjusted $p$-values}\n" + "\\end{table}");
				sb_new.append("\\begin{table}[!htp]\n\\centering\\tiny\n" +
		           		"\\begin{tabular}{c c c c c}\n" +
		           		" & Hypothesis & $p_{Nemenyi}$ & $p_{Holm}$ & $p_{Shaffer}$\\\\\n\\hline\n");
			}
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Adjusted $p$-values}\n" + "\\end{table}");
		
		/*
		 * Significant difference detected with Nemenyi's test
		 */
		
		sb_new.append("\n\\begin{table}[!htp]\n\\centering\\tiny\n" +
           		"\\begin{tabular}{c c c}\n" +
           		" & Hypothesis & $p_{Nemenyi}$ \\\\\n\\hline\n");
		
		for (int l=0;l<Friedman_2.statistics_pairs_p_values.length;l++){
			if (roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pNeme,10)<0.05){
					sb_new.append(l+1 + " & " +
						  Friedman_2.statistics_pairs_p_values[l].first + " vs. " +
					      Friedman_2.statistics_pairs_p_values[l].second + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pNeme,10) + "\\\\\n");
			}
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Significant difference detected with Nemenyi's test, $\\alpha = 0.05$}\n" + "\\end{table}");
		
		/*
		 * Significant difference detected with Holm's test
		 */
		
		sb_new.append("\n\\begin{table}[!htp]\n\\centering\\tiny\n" +
           		"\\begin{tabular}{c c c}\n" +
           		" & Hypothesis & $p_{Holm}$ \\\\\n\\hline\n");
		
		for (int l=0;l<Friedman_2.statistics_pairs_p_values.length;l++){
			if (roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pHolm,10)<0.05){
					sb_new.append(l+1 + " & " +
						  Friedman_2.statistics_pairs_p_values[l].first + " vs. " +
					      Friedman_2.statistics_pairs_p_values[l].second + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pHolm,10) + "\\\\\n");
			}
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Significant difference detected with Holm's test, $\\alpha = 0.05$}\n" + "\\end{table}");
		
		/*
		 * Significant difference detected with Shaffer's test
		 */
		
		sb_new.append("\n\\begin{table}[!htp]\n\\centering\\tiny\n" +
           		"\\begin{tabular}{c c c}\n" +
           		" & Hypothesis & $p_{Shaffer}$ \\\\\n\\hline\n");
		
		for (int l=0;l<Friedman_2.statistics_pairs_p_values.length;l++){
			if (roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pShaf,10)<0.05){
				  	sb_new.append(l+1 + " & " +
						  Friedman_2.statistics_pairs_p_values[l].first + " vs. " +
					      Friedman_2.statistics_pairs_p_values[l].second + " & " +
					      roundToDecimals(Friedman_2.statistics_pairs_p_values[l].pShaf,10) + "\\\\\n");
			}
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Significant difference detected with Shaffer test, $\\alpha = 0.05$}\n" + "\\end{table}");
		
		/*
		 * EARS output
		 */
		
		sb_new.append("\n\\begin{table}[!htp]\n\\centering\n" +
           		"\\begin{tabular}{l c c c c}\n" +
           		" & Algorithm & Rating & RD & RI \\\\\n\\hline\n");
		
		// Order by rating DESC
		for (int n=0;n<statistics_results.length;n++){
			for (int o=n;o<statistics_results.length;o++){
				if(statistics_results[o].Rating > statistics_results[n].Rating){
					stat_temp = statistics_results[n];
					statistics_results[n] = statistics_results[o];
					statistics_results[o] = stat_temp;
				}
			}
		}
		
		long rating_L = 0;
		long rating_R = 0;
		for (int k=0; k<al.size();k++) {
			rating_L = Math.round(statistics_results[k].Rating) - 2*Math.round(statistics_results[k].RatingDev);
			rating_R = Math.round(statistics_results[k].Rating) + 2*Math.round(statistics_results[k].RatingDev);
		    sb_new.append(k+1 + " & " + statistics_results[k].Name + " & "
		    		+ Math.round(statistics_results[k].Rating) + " & "
		    		+ Math.round(statistics_results[k].RatingDev) + " & " 
		    		+ "[" + rating_L + ", " + rating_R + "]" + "\\\\\n");
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Rating list, RD = rating deviation, RI = rating interval}\n" + "\\end{table}");
		
		/*
		 * Significant difference detected with EARS
		 */
		
		sb_new.append("\n\\begin{table}[!htp]\n\\centering\\tiny\n" +
           		"\\begin{tabular}{c c c}\n" +
           		" & Hypothesis & Rating difference \\\\\n\\hline\n");
		
		double first_lower, second_upper, rating_difference;
		int counter = 1;
		for (int l=0;l<statistics_results.length;l++){
			for (int k=l+1; k<statistics_results.length;k++){
				first_lower = statistics_results[l].Rating - 2*statistics_results[l].RatingDev;
				second_upper = statistics_results[k].Rating + 2*statistics_results[k].RatingDev;
				// Overlaping Rating Intervals
				if (second_upper < first_lower){
					rating_difference = first_lower = statistics_results[l].Rating - statistics_results[k].Rating;
					sb_new.append(counter + " & " +
							  Friedman_2.statistics_results[l].Name + " vs. " +
						      Friedman_2.statistics_results[k].Name + " & " +
						      Math.round(rating_difference) + "\\\\\n");

					if (counter%60==0 && counter < statistics_results.length*(statistics_results.length-1)/2){
						sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Significant difference detected with EARS}\n" + "\\end{table}");
						sb_new.append("\n\\begin{table}[!htp]\n\\centering\\tiny\n" +
			           		"\\begin{tabular}{c c c}\n" +
			           		" & Hypothesis & Rating difference \\\\\n\\hline\n");
					}
					counter++;
				}
			}
		}
		
		sb_new.append("\\hline\\end{tabular}\n" + "\\caption{Significant difference detected with EARS}\n" + "\\end{table}");
		
		
		System.out.println(sb_new);
    }
    
    public static double roundToDecimals(double d, int c)  
    {   
       int temp = (int)(d * Math.pow(10 , c));  
       return ((double)temp)/Math.pow(10 , c);  
    }
}
