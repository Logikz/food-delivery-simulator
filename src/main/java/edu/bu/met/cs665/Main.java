package edu.bu.met.cs665;

import edu.bu.met.cs665.Goods.ChocolateBox;
import edu.bu.met.cs665.Goods.Good;
import edu.bu.met.cs665.Goods.HappyMeal;
import edu.bu.met.cs665.Goods.Pizza;
import edu.bu.met.cs665.Goods.RoseBouquet;
import edu.bu.met.cs665.Goods.TVDinner;
import edu.bu.met.cs665.Payment.Order;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
  private static final Random RANDOM = new Random();
  private static final List<Good> GOODS_LIST = createGoods();
  private static final int MAP_WIDTH = 10;
  private static final int MAP_HEIGHT = 10;

  private static List<Good> createGoods() {
    List<Good> goods = new LinkedList<>();
    goods.add(new HappyMeal());
    goods.add(new Pizza());
    goods.add(new TVDinner());
    goods.add(new RoseBouquet());
    goods.add(new ChocolateBox());

    return goods;
  }

  /**
   * A main method to run examples. 
   * @param args not used 
   */
  public static void main(String[] args) {

    List<Order> orders = generateRandomOrders(RANDOM.nextInt(30) + 20);

    System.out.println(orders);
  }

  private static List<Order> generateRandomOrders(int numOrders) {
    List<Order> orders = new ArrayList<>();

    for(int i = 0; i < numOrders; ++i){
      LinkedList<Good> goods = new LinkedList<>(GOODS_LIST);
      Collections.shuffle(goods);
      List<Good> orderedGoods = new ArrayList<>();
      // Calculate a RANDOM number of items for the order
      for(int j = 0; j < RANDOM.nextInt(goods.size()); ++j){
        orderedGoods.add(goods.pop());
      }

      Boolean birthday = RANDOM.nextBoolean();
      int x = RANDOM.nextInt(MAP_WIDTH);
      int y = RANDOM.nextInt(MAP_HEIGHT);

      orders.add(new Order(goods, new Point(x, y), birthday));
    }

    return orders;
  }

}
