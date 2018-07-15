package edu.bu.met.cs665.Map;

import java.awt.Point;

/**
 * The world represented as a 2d list of locations.
 */
public class ObjectMap {
  private Location[][] map;

  /**
   * Constructor to initialize the map, assuming a square map.
   * @param size Integer size to create the map.
   */
  public ObjectMap(int size) {
    this.map = new Location[size][size];
    for(int i = 0; i < size; ++i){
      for(int j = 0; j < size; ++j){
        map[i][j] = new Location(new Point(i, j));
      }
    }
  }

  /**
   * Get the map
   * @return The map.
   */
  public Location[][] getMap() {
    return map;
  }
}
