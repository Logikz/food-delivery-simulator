package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.FrozenMeal;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.Mappable;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;

/**
 * Class to capture a van transportation vehicle.  Vans can sometimes have freezers which gives them
 * the ability to transport frozen items at any distance.
 */
public class Van extends Vehicle implements Mappable {

  private Boolean hasFreezer;


  /**
   * Constructor
   * @param goods List of goods currently on board
   * @param location Location of this vehicle
   * @param objectMap Map of the world
   * @param hasFreezer Has a freezer or not
   * @param vehicleID ID of the vehicle
   */
  public Van(List<Good> goods, Point location, ObjectMap objectMap, Boolean hasFreezer, int vehicleID) {
    this.goods = goods;
    this.location = location;
    this.hasFreezer = hasFreezer;
    this.objectMap = objectMap;
    this.deliveryMission = new Idle();
    this.vehicleId = vehicleID;
    this.odometer = 0;
  }


  /**
   * Prints the status of the van
   */
  @Override
  protected void printStatus() {
    Structure destination = this.deliveryMission.getNextDestination();
    String destStr = "IDLE";
    if(destination != null){
      destStr = String.format("%s(%d, %d)", destination.toString(), destination.getLocation().x, destination.getLocation().y);
    }
    System.out.printf("Van(%d), Freezer(%b), Odometer(%d): (%d, %d) => %s Time Left: %d\n",
        this.vehicleId, this.hasFreezer, this.odometer, this.location.x, this.location.y,
        destStr, this.deliveryMission.getTimeRemaining());
    this.printOrderStatus();
  }

  /**
   * Vans can have a freezer which can hold frozen meals
   * @param good A type of good to check if we can store it
   * @return Boolean if the van can store it under normal conditions
   */
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
