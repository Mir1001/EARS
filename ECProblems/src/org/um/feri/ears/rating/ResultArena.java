/**
 * Main class where all results are collected!
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.um.feri.ears.rating.*;

public class ResultArena {
	private HashMap<String,Player> players;
	int id_period;
	public ResultArena(int id_period) {
		players = new HashMap<String, Player>();
		this.id_period = id_period;
	}
	public void addPlayer(String id, double rating, double RD, double ratingVolatility){
		players.put(id, new Player(id,new Rating(rating, RD, ratingVolatility)));
	}
	/**
	 * Players need to be in arena!
	 * 
	 * @param gameResult
	 * @param a
	 * @param b
	 * @param info
	 */
	public void addGameResult(double gameResult, String a, String b, String info) {
		new Game(gameResult, players.get(a),players.get(b), info);
	}
	/**
	 * Recalculates ranks and returns list. All ranks need to be updated. 
	 * @return
	 */
	public Collection<Player> recalcRangs() {
		id_period++;
		RatingCalculations.computePlayerRatings(players); //changes ratins
		return players.values();
		
	}
}
