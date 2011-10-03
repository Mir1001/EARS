/**
 * Generic data about rating Player. In case of rating algorithms player is specific algorithm, with id = versionAcronym.  
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
package org.um.feri.ears.rating;

import java.util.ArrayList;

public class Player {
    private String playerId; //name
    private Rating r; //current ration
    private ArrayList<Game> listOfGamePlayed; //in last period (not evaluated yet)

    public Player(String playerId, Rating r) {
        super();
        this.playerId = playerId;
        this.r = r;
        listOfGamePlayed = new ArrayList<Game>();
    }
    
    public Player(String playerId) {
        super();
        this.playerId = playerId;
        this.r = new Rating(1500, 350, 0.06); //default from org. paper
        listOfGamePlayed = new ArrayList<Game>();
    }
    /**
     * Adds new game
     * @param newone
     */
    public void add(Game newone) {
    	listOfGamePlayed.add(newone);
    	
    }
    
    public ArrayList<Game> getUnEvaluatedGames() {
    	return listOfGamePlayed;
    }
   

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Rating getR() {
        return r;
    }
    /**
     * New rating is calculated. listOfGamePlayed is deleted!
     * 
     * @param r
     */
    public void setR(Rating r) {
    	listOfGamePlayed.clear();
        this.r = r;
    }
    
}
