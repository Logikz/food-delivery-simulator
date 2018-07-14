package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.FrozenMeal;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.Mappable;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;

public class Van extends Vehicle implements Mappable {

  private Boolean hasFreezer;


  public Van(List<Good> goods, Point location, ObjectMap objectMap, Boolean hasFreezer, int vehicleID) {
    this.goods = goods;
    this.location = location;
    this.hasFreezer = hasFreezer;
    this.objectMap = objectMap;
    this.deliveryMission = new Idle();
    this.vehicleId = vehicleID;
  }


  @Override
  protected void printStatus() {
    Structure destination = this.deliveryMission.getNextDestination();
    String destStr = "IDLE";
    if(destination != null){
      destStr = String.format("(%d, %d)", destination.getLocation().x, destination.getLocation().y);
    }
    System.out.printf("Van(%d): (%d, %d) => %s Time Left: %d\n",
        this.vehicleId, this.location.x, this.location.y,
        destStr, this.deliveryMission.getTimeRemaining());
  }

  @Override
  public Boolean canStore(Good good) {
    if(good instanceof FrozenMeal){
      return this.hasFreezer;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Van{" +
        "hasFreezer=" + hasFreezer +
        '}';
  }

  @Override
  public String getMapCode() {
    return "V" + vehicleId;
  }
}
