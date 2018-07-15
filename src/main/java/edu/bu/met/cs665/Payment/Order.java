package edu.bu.met.cs665.Payment;

import edu.bu.met.cs665.Goods.Good;
import java.awt.Point;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

/**
 * Class to encapsulate an order.
 */
public class Order extends Observable {
  private List<Good> goods;
  private Point deliveryAddress;
  private OrderStatus status;
  private UUID orderId;

  /**
   * Constructor
   * @param goods List of goods in this order
   * @param deliveryAddress The delivery address of the order
   */
  public Order(List<Good> goods, Point deliveryAddress) {
    this.goods = goods;
    this.deliveryAddress = deliveryAddress;
    this.status = OrderStatus.ORDERED;
    this.orderId = UUID.randomUUID();
  }


  @Override
  public String toString() {
    return "Order{" +
        "goods=" + goods +
        ", deliveryAddress=" + deliveryAddress +
        ", status=" + status +
        '}';
  }

  /**
   * Get order ID
   * @return UUID
   */
  public UUID getOrderId() {
    return orderId;
  }

  /**
   * List of the goods in this order
   * @return List of ordered goods
   */
  public List<Good> getGoods() {
    return goods;
  }

  /**
   * Get the address to deliver the order to
   * @return Delivery address location
   */
  public Point getDeliveryAddress() {
    return deliveryAddress;
  }

  /**
   * Status of the order
   * @return The order status
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * Set the status of the order, notify observers the order status has changed.
   * @param status status of the order to set
   */
  public void setStatus(OrderStatus status) {
    this.status = status;
    setChanged();
    notifyObservers(this.status);
  }

}
