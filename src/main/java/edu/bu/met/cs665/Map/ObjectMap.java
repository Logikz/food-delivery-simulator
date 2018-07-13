package edu.bu.met.cs665.Map;

public class ObjectMap {
  private Location[][] map;

  public ObjectMap(int size) {
    this.map = new Location[size][size];
  }

  public Location[][] getMap() {
    return map;
  }
}
