package edu.bu.met.cs665.Map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to cover a location on a map.  A location can contain many objects, such as cars, shops,
 * and such.
 */
public class Location {
  private Point point;
  private List<Mappable> objects;

  /**
   * Constructor
   * @param point Point location
   */
  public Location(Point point) {
    this.point = point;
    objects = new ArrayList<>();
  }

  /**
   * Get the point for this location
   * @return Point
   */
  public Point getPoint() {
    return point;
  }

  /**
   * Add an object to this location
   * @param object A shop, or vehicle to add to this location
   */
  public void addObject(Mappable object) {
    objects.add(object);
  }

  /**
   * Remove an object from this location, such as a car
   * @param object An object to remove from this location
   */
  public void removeObject(Mappable object){
    objects.remove(object);
  }

  /**
   * A list of objects at this location.
   * @return List of mappable objects.
   */
  public List<Mappable> getObjects(){
    return this.objects;
  }
}
