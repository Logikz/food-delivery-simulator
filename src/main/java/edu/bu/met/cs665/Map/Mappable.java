package edu.bu.met.cs665.Map;

/**
 * Interface for objects that should appear on the map.  Provides a way for classes to provide
 * a mapping display string
 */
public interface Mappable {

  /**
   * A string to represent an object on the map.
   * @return A string to represent the object.
   */
  String getMapCode();
}
