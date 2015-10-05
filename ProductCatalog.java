<<<<<<< HEAD
import java.util.List;
public class ProductCatalog 
{
  private List<Item> storeCatalog;
  private static ProductCatalog onlyCatalog = new ProductCatalog();
  
  private ProductCatalog()
  {
    this.storeCatalog = new List<Item>(); //Needs List that is not abstract. Should create it from database
  }
  
  public double getPrice() //Needs to know how items will be stored in list.
  {
    
  }
  
  public int getId() //Same 
  {
  }
  
  public String getDescription() //Same
  {
    
  }
  
  public static synchronized ProductCatalog getInstance()
  {
    return onlyCatalog;
=======
/*
 * Written by: Bruke Mammo
 * Keeps track of all the potentially
 * available items within the store and
 * also acts like a backup system for
 * tracking items.
 */

import java.util.HashMap;

public class ProductCatalog{
  
  private HashMap<String, Item> wholeList;
  private static ProductCatalog only;
  
  private ProductCatalog(){};
  
  public static synchronized ProductCatalog getInstance(){
    if(only == null){
      only = new ProductCatalog();
    }
    return only;
  }
  
  public Item getItem(String id){   
    Item item = wholeList.get(id);
    return item;     
>>>>>>> 9be8db51b91f119f32e55c2d5154204635ff4988
  }
}