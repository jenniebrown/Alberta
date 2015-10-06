/*
 * Written by: Bruke Mammo
 * Class description: Contains the information
 * for an item object. Includes the ID and price as well.
 */
public class Item{
  
  private String description;
  private int itemID;
  private double price;
  
  public Item(String description, int itemID, double price){
    this.description = description;
    this.itemID = itemID;
    this.price = price;
    
  }
  
  public String getDescription(){
    return this.description;
  }
  
  public int getItemID(){
    return this.itemID;
  }
  
  public double getPrice(){
    return this.price;
  }
  
}
  
  
