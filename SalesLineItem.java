/*
 * Written by: Bruke Mammo
 * Class description: Contains the information
 * for a sale of an item, with the quantity
 * and the subtotal.
 */
public class SalesLineItem{
  
  private Item item;
  private int quantity;
  private double subtotal;
  
  public SalesLineItem(Item item, int quantity){
    
    this.item = item;
    this.quantity = quantity;
    this.subtotal = quantity*item.getPrice();
    
  }
  
  public double getSubtotal(){
    return this.subtotal;
  }
  
  public static void main(String[] args){
    Item i = new Item("asdfasd", 12, 12.34);
    SalesLineItem s = new SalesLineItem(i, 4);
    System.out.println(s.getSubtotal());
  }
}