package edu.bu.met.cs665.Map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Location {
  private Point point;
  private List<Mappable> objects;

  public Location(Point point) {
    this.point = point;
    objects = new ArrayList<>();
  }

  public Point getPoint() {
    return point;
  }

  public void addObject(Mappable object) {
    objects.add(object);
  }

  public void removeObject(Mappable object){
    objects.remove(object);
  }

  public List<Mappable> getObjects(){
    return this.objects;
  }
}
