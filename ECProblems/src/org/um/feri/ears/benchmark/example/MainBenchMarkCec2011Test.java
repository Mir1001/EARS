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
package org.um.feri.ears.benchmark.example;

import net.sourceforge.jswarm_pso.SwarmAlgorithm;

import org.um.feri.ears.algorithms.es.ES1p1sAlgorithm;
import org.um.feri.ears.algorithms.gsa.GSA;
import org.um.feri.ears.algorithms.pso.PSO;
import org.um.feri.ears.algorithms.pso.PSOOmega;
import org.um.feri.ears.algorithms.random.RandomWalkAMAlgorithm;
import org.um.feri.ears.algorithms.random.RandomWalkAlgorithm;
import org.um.feri.ears.algorithms.tlbo.TLBOAlgorithm;
import org.um.feri.ears.benchmark.RatingBenchmark;
import org.um.feri.ears.benchmark.RatingCEC2014;
import org.um.feri.ears.benchmark.RatingRPCOCEC2011;
import org.um.feri.ears.benchmark.RatingRPUOed2;
import org.um.feri.ears.benchmark.RatingRPUOed30;
import org.um.feri.ears.rating.Rating;
import org.um.feri.ears.run.RunMainBestAlgSettings;
import org.um.feri.ears.util.Util;

import com.erciyes.karaboga.bee.BeeColonyAlgorithm;
import com.um.feri.brest.de.DEAlgorithm;

/**
 * @author Administrator
 *
 */
public class MainBenchMarkCec2011Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	   Util.rnd.setSeed(System.currentTimeMillis());
           RatingBenchmark.debugPrint = true; //prints one on one results
           RunMainBestAlgSettings rbs = new RunMainBestAlgSettings(true,true, new RatingRPCOCEC2011());   
          rbs.addAlgorithm(new BeeColonyAlgorithm(),new Rating(1500, 350, 0.06));  
          rbs.addAlgorithm(new PSOOmega(30,0.7, 2, 2),new Rating(1500, 350, 0.06)); 
          rbs.addAlgorithm(new PSO(30,0.7, 2, 2),new Rating(1500, 350, 0.06)); 
      //    rbs.addAlgorithm(new RandomWalkAlgorithm(),new Rating(1500, 350, 0.06));  
          rbs.addAlgorithm(new DEAlgorithm(DEAlgorithm.JDE_rand_1_bin,20),new Rating(1500, 350, 0.06));  
          rbs.run(5);
          System.out.println(rbs);
 
    }

}
