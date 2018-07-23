package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.FrozenMeal;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Map.ObjectMap;
import edu.bu.met.cs665.Mission.Idle;
import edu.bu.met.cs665.Structures.Structure;
import java.awt.Point;
import java.util.List;

/**
 * Class to capture a taxi transportation vehicle.
 */
public class Taxi extends Vehicle {

  /**
   * Constructor
   * @param goods Goods currently onboard
   * @param location Location of the vehicle
   * @param objectMap Map of the world(GPS)
   * @param vehicleId ID for this vehicle
   */
  public Taxi(List<Good> goods, Point location, ObjectMap objectMap, int vehicleId) {
    this.goods = goods;
    this.location = location;
    this.objectMap = objectMap;
    this.deliveryMission = new Idle();
    this.vehicleId = vehicleId;
    this.odometer = 0;
  }

  /**
   * Prints the status of the taxi
   */
  @Override
  protected void printStatus() {
    Structure destination = this.deliveryMission.getNextDestination();
    String destStr = "IDLE";
    if(destination != null){
      destStr = String.format("%s(%d, %d)", destination.toString(), destination.getLocation().x, destination.getLocation().y);
    }
    System.out.printf("Taxi(%d), Odometer(%d): (%d, %d) => %s Time Left: %d\n",
        this.vehicleId, this.odometer, this.location.x, this.location.y,
        destStr, this.deliveryMission.getTimeRemaining());
    this.printOrderStatus();
  }

  /**
   * Taxis can hold any kind of goods except frozen meals but can be overrode if the delivery location
   * is close to the store containing the frozen good.
   * @param good A good to check if it can store
   * @return True if it can store the good under normal conditions
   */
  @Override
  public Boolean canStore(Good good) {
    return !(good instanceof FrozenMeal);
  }

  @Override
  public String toString() {
    return "Taxi{}";
  }

  @Override
  public String getMapCode() {
    return "T" + vehicleId;
  }


}
