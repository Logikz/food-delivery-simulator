package edu.bu.met.cs665.Map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Location {
  private Point point;
  private List<Object> objects;

  public Location(Point point) {
    this.point = point;
    objects = new ArrayList<>();
  }

  public Point getPoint() {
    return point;
  }

  public void addObject(Object object) {
    objects.add(object);
  }

  public void removeObject(Object object){
    objects.remove(object);
  }

  public List<Object> getObjects(){
    return this.objects;
  }
}
