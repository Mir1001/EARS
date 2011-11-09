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
package org.um.feri.ears.export.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class EDBenchmarkRunArena implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3778172015162418493L;
    public String ID; 
    public String parentID; //ratings parant 
    public String arenaOwner; 
    public String arenaName;
    public EDEnumBenchmarkRunType runType; 
    public EDPlayer players[];
    public EDBenchmark benchmark;
    public Date runDate;
    public long duration;
    public String info;
   // public StatPlayer2Player statDuels[];
   // public StatPlayer2Task statTask[];
    public EDBenchmarkRunArena() {
        ID = "";
        parentID="";
        runDate = new Date();
    }
    @Override
    public String toString() {
        return "EDBenchmarkRunArena [ID=" + ID + ", arenaOwner=" + arenaOwner + ", arenaName=" + arenaName + ", arenaType=" + runType.toString() + ", players="
                + Arrays.toString(players) + ", benchmark=" + benchmark + ", runDate=" + runDate + ", duration=" + duration + ", info=" + info + "]";
    }
}
