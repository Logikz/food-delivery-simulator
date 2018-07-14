package edu.bu.met.cs665.Map;

import java.awt.Point;

public class ObjectMap {
  private Location[][] map;

  public ObjectMap(int size) {
    this.map = new Location[size][size];
    for(int i = 0; i < size; ++i){
      for(int j = 0; j < size; ++j){
        map[i][j] = new Location(new Point(i, j));
      }
    }
  }

  public Location[][] getMap() {
    return map;
  }
}
