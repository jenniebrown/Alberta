/*
 * Written by: Bruke Mammo, modified by Giancarlo Sanguinetti
 * Keeps track of all the potentially
 * available items within the store and
 * also acts like a backup system for
 * tracking items.
 */

import java.util.HashMap;
import java.util.Scanner;


public class ProductCatalog{
  
  private HashMap<String, Item> wholeList;
  private static ProductCatalog only = new ProductCatalog();
  private DatabaseHandler connection;
  
  private ProductCatalog()
  {
    this.connection = DatabaseHandler.connect();
    String [] currentlyAvailable = connection.getCatalog();
    String [] currentItem;
    wholeList = new HashMap<String,Item>(currentlyAvailable.length);
    for(String c : currentlyAvailable)
    {
      currentItem = c.split(",");
      int id = Integer.parseInt(currentItem[0]);
      String desc = currentItem[1] + ": " + currentItem [3];
      double price = Double.parseDouble(currentItem[2]);
      Item created = new Item (desc, id, price);
      wholeList.put(currentItem[0], created);
    }
    this.connection.disconnect();
  }
  
  public static synchronized ProductCatalog getInstance(){
       return only;
  }
  
  public Item getItem(String id){   
    Item item = wholeList.get(id);
    return item;     
  }
}