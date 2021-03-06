package alberta;

/*
 * Written by: Bruke Mammo, modified by Giancarlo Sanguinetti
 * Keeps track of all the potentially
 * available items within the store and
 * also acts like a backup system for
 * tracking items.
 */

import java.util.HashMap;


public class ProductCatalog{

  private HashMap<Integer, Item> wholeList;
  private static ProductCatalog only = null;// new ProductCatalog();
  private DatabaseHandler connection;

  private ProductCatalog()
  {
    this.connection = DatabaseHandler.connect();
    String [] currentlyAvailable = connection.getCatalog();
    String [] currentItem;
    wholeList = new HashMap<Integer,Item>();//(currentlyAvailable.length - 1);
    for(String c : currentlyAvailable)
    {
      if (c != null){
      	currentItem = c.split(",");
      	int id = Integer.parseInt(currentItem[0]);
      	String desc = currentItem[1] + ": " + currentItem[3];
      	double price = Double.parseDouble(currentItem[2]);
      	Item created = new Item (desc, id, price);
      	wholeList.put(id, created);
      }
    }
    this.connection.disconnect();
  }

  public static synchronized ProductCatalog getInstance(){
       if(only == null) {
       	only = new ProductCatalog();
       }
       return only;
  }

  public HashMap<Integer, Item> getWholeList(){
      return wholeList;
  }

  public AbstractItem getItem(int id){
    Item item = wholeList.get(id);
    return item;
  }


}
