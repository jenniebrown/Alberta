/*
 * Written by: Bruke Mammo, modified by Giancarlo Sanguinetti
 * Keeps track of all the potentially
 * available items within the store and
 * also acts like a backup system for
 * tracking items.
 */

import java.util.HashMap;

public class ProductCatalog{
  
  private HashMap<String, Item> wholeList;
  private static ProductCatalog only;
  private DatabaseHandler connection;
  
  private ProductCatalog()
  {
    this.connection = DatabaseHandler.connect();
  }
  
  public static synchronized ProductCatalog getInstance(){
    if(only == null){
      only = new ProductCatalog();
    }
    return only;
  }
  
  public Item getItem(String id){   
    Item item = wholeList.get(id);
    return item;     
  }
}