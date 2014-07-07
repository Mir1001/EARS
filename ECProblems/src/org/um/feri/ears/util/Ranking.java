//  Ranking.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.um.feri.ears.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.um.feri.ears.problems.moo.MOParetoIndividual;

/**
 * This class implements some facilities for ranking solutions.
 * Given a <code>SolutionSet</code> object, their solutions are ranked 
 * according to scheme proposed in NSGA-II; as a result, a set of subsets 
 * are obtained. The subsets are numbered starting from 0 (in NSGA-II, the 
 * numbering starts from 1); thus, subset 0 contains the non-dominated 
 * solutions, subset 1 contains the non-dominated solutions after removing those
 * belonging to subset 0, and so on.
 */
public class Ranking {
  
  /**
   * The <code>SolutionSet</code> to rank
   */
  private MOParetoIndividual solutions ;
  
  /**
   * An array containing all the fronts found during the search
   */
  private MOParetoIndividual[]ranking;
  
  /**
   * stores a <code>Comparator</code> for dominance checking
   */
  private static final Comparator dominance_ = new DominanceComparator();
  
  /**
   * stores a <code>Comparator</code> for Overal Constraint Violation Comparator
   * checking
   */
  //private static final Comparator constraint_ = new OverallConstraintViolationComparator();
    
  /** 
   * Constructor.
   * @param solutionSet The <code>SolutionSet</code> to be ranked.
   */       
  public Ranking(MOParetoIndividual solutionSet) {        
    solutions = solutionSet ;

    // dominateMe[i] contains the number of solutions dominating i        
    int [] dominateMe = new int[solutions.size()];

    // iDominate[k] contains the list of solutions dominated by k
    List<Integer> [] iDominate = new List[solutions.size()];

    // front[i] contains the list of individuals belonging to the front i
    List<Integer> [] front = new List[solutions.size()+1];
        
    // flagDominate is an auxiliar encodings.variable
    int flagDominate;    

    // Initialize the fronts 
    for (int i = 0; i < front.length; i++)
      front[i] = new LinkedList<Integer>();
        
    //-> Fast non dominated sorting algorithm
    // Contribution of Guillaume Jacquenot
    for (int p = 0; p < solutions.size(); p++) {
    // Initialize the list of individuals that i dominate and the number
    // of individuals that dominate me
      iDominate[p] = new LinkedList<Integer>();
      dominateMe[p] = 0;
    }
    for (int p = 0; p < (solutions.size()-1); p++) {
      // For all q individuals , calculate if p dominates q or vice versa
      for (int q = p+1; q < solutions.size(); q++) {
        //flagDominate =constraint_.compare(solutionSet.get(p),solutionSet.get(q));
    	flagDominate = 0;  
        if (flagDominate == 0) {
          flagDominate = dominance_.compare(solutionSet.get(p),solutionSet.get(q));
        }
        if (flagDominate == -1)
        {
          iDominate[p].add(q);
          dominateMe[q]++;
        }
        else if (flagDominate == 1)
        {
          iDominate[q].add(p);
          dominateMe[p]++;
        }
      }
      // If nobody dominates p, p belongs to the first front
    }
    for (int p = 0; p < solutions.size(); p++) {
      if (dominateMe[p] == 0) {
        front[0].add(p);
        solutionSet.get(p).setRank(0);
      }
    }    
    
    //Obtain the rest of fronts
    int i = 0;
    Iterator<Integer> it1, it2 ; // Iterators
    while (front[i].size()!= 0) {
      i++;
      it1 = front[i-1].iterator();
      while (it1.hasNext()) {
        it2 = iDominate[it1.next()].iterator();
        while (it2.hasNext()) {
          int index = it2.next();
          dominateMe[index]--;
          if (dominateMe[index]==0) {
            front[i].add(index);
            solutions.get(index).setRank(i);
          }
        }
      }
    }
    //<-
        
    ranking = new MOParetoIndividual[i];
    //0,1,2,....,i-1 are front, then i fronts
    for (int j = 0; j < i; j++) {
      ranking[j]= new MOParetoIndividual(front[j].size());
      it1 = front[j].iterator();
      while (it1.hasNext()) {
                ranking[j].add(solutionSet.get(it1.next()));
      }
    }
    
  } // Ranking

  /**
   * Returns a <code>SolutionSet</code> containing the solutions of a given rank. 
   * @param rank The rank
   * @return Object representing the <code>SolutionSet</code>.
   */
  public MOParetoIndividual getSubfront(int rank) {
    return ranking[rank];
  } // getSubFront

  /** 
  * Returns the total number of subFronts founds.
  */
  public int getNumberOfSubfronts() {
    return ranking.length;
  } // getNumberOfSubfronts
} // Ranking
