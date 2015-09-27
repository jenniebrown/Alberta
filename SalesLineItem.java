public class SalesLineItem{
  
  private Item item;
  private int quantity;
  
  public SalesLineItem(Item item, int quantity){
    
    this.item = item;
    this.quantity = quantity;
    
  }
  
  public double getSubtotal(){
    
    double price = item.getPrice();
    double subtotal = price*quantity;
    return subtotal;
    
  }
  
  public static void main(String[] args){
    Item i = new Item("asdfasd", 12, 12.34);
    SalesLineItem s = new SalesLineItem(i, 4);
    System.out.println(s.getSubtotal());
  }
}