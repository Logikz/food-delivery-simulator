package edu.bu.met.cs665.Vehicles;

import edu.bu.met.cs665.Goods.Good;
import java.util.List;

public class Taxi extends Vehicle {

  @Override
  List<Class<? extends Good>> canStore() {
    return null;
  }
}
