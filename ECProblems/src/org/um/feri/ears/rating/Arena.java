package org.um.feri.ears.rating;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.um.feri.ears.rating.*;

public class Arena {
	private HashMap<String,Player> players;
	int id_period;
	public Arena(int id_period) {
		players = new HashMap<String, Player>();
		this.id_period = id_period;
	}
	public void addPlayer(String id, double rating, double RD, double ratingVolatility){
		players.put(id, new Player(id,new Rating(rating, RD, ratingVolatility)));
	}
	
	public void addGameResult(double gameResult, String a, String b, String info) {
		new Game(gameResult, players.get(a),players.get(a), info);
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
