package org.um.feri.ears.util;

import java.util.Comparator;

import org.um.feri.ears.algorithms.moo.spea2.DistanceNode;

public class DistanceNodeComparator implements Comparator{
    
	  /**
	   * Compares two <code>DistanceNode</code>.
	   * @param o1 Object representing a DistanceNode
	   * @param o2 Object representing a DistanceNode
	   * @return -1 if the distance of o1 is smaller than the distance of o2,
	   *          0 if the distance of both are equals, and
	   *          1 if the distance of o1 is bigger than the distance of o2
	   */
	public int compare(Object o1, Object o2) {
		DistanceNode node1 = (DistanceNode) o1;
		DistanceNode node2 = (DistanceNode) o2;

		double distance1, distance2;
		distance1 = node1.getDistance();
		distance2 = node2.getDistance();

		if (distance1 < distance2)
			return -1;
		else if (distance1 > distance2)
			return 1;
		else
			return 0;
	}
}
