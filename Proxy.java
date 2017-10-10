/*
  Local representation, less resource intensive

  it protects the real subject class by checking the client's request and controlling access to the real subject class.

  it acts as a wrapper class for the real subject class. Client classes cna then interact with the proxy class, which transfers requests to the real subject class.

  it implements some form of intelligent verification of requests from client code in order to determine if, how, and to whom the request should be forwarded to.
*/

// Step 1: Design the subject interface
public interface IOrder {
  public void fulfillOrder(Order);
}

// Step 2: Implement the real subject class
public class Warehouse implements IOrder {
  // <item, the number of item>
  private HashTable<String, Integer> stock;
  private String address;


  /* constructors and other attributes would go here */
  // ...
  public void fulfillOrder(Order order){
    for (Item item : order.itemList) {
      this.stock.replace(item.sku, stock.get(item.sku) - 1)
    }
  }

  public int currentInventory(Item item){
    if(stock.containKey(item.sku)) {
      return stock.get(item.sku).intValue();
    }
    return 0;
  }
}

// Step 3: Implement the proxy class
public class OrderFulfillment implments IOrder {
  private List<Warehouse> warehouses;
  /* constructors and other attributes would go here */
  // ...

  public void fulfillOrder(Order order){
    /* For each item in a custormer order, chcck each warehouse to see if it is in stock.

    if it is then create a new order for that warehouse. Else check the next warehouse.

    Send the all the orders to the warehouse(s) after you finish iterating over all the items in the original order. */

    for(Item item: order.itemList) {
      for(Warehouse warehouse: warehouses){
        // ...
      }
    }

    return;
  }
}
