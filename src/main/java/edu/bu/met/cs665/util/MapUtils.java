package edu.bu.met.cs665.util;

import edu.bu.met.cs665.Structures.Shop;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;

/**
 * Contains some general map utilities such as distance computation
 */
public class MapUtils {

  /**
   * Calculates the distance between two points using orthogonal movement rather
   * than as the crew flies.
   * @param a First point
   * @param b Second point
   * @return Distance as integer
   */
  public static int calculateDistance(Point a, Point b){
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  /**
   * Calculate the time remaining based on a list of destination from a given point
   * @param location Location of the consumer
   * @param destinations List of destinations to go to
   * @return Distance(time) as an integer
   */
  public static int calculateTimeRemaining(Point location, List<Structure> destinations){
    int distance = 0;
    Structure previousStructure = null;
    for(Structure destination : destinations){
      if(previousStructure == null){
        distance += MapUtils.calculateDistance(location, destination.getLocation());
      } else {
        distance += MapUtils.calculateDistance(previousStructure.getLocation(), destination.getLocation());
      }
      previousStructure = destination;
    }

    return distance;
  }

  /**
   * Given a location, finds the closest shop to go to.
   * @param location Location of the consumer
   * @param shops List of shops to pick from
   * @return The closet shop from the given list
   */
  public static Shop findClosestShop(Point location, List<Shop> shops) {
    int maxDistance = Integer.MAX_VALUE;
    Shop result = null;
    for(Shop shop: shops){
      int distance = MapUtils.calculateDistance(location.getLocation(), shop.getLocation());
      if(distance < maxDistance){
        maxDistance = distance;
        result = shop;
      }
    }

    return result;
  }
}
