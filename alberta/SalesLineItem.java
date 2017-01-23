package alberta;

/*
 * Written by: Bruke Mammo
 * Class description: Contains the information
 * for a sale of an item, with the quantity
 * and the subtotal.
 */
public class SalesLineItem extends AbstractLineItem{

  public SalesLineItem(Item item, int quantity){
    super(item, quantity);
  }

  public SalesLineItem(Item item) {
      this(item,1);
  }
//
//  public double getSubtotal(){
//    return this.subtotal;
//  }
//
//  public AbstractItem getItem() {
//    return this.item;
//  }
//
//  public int getQuantity () {
//    return this.quantity;
//  }
//
//  public static void main(String[] args){
//    Item i = new Item("asdfasd", 12, 12.34);
//    SalesLineItem s = new SalesLineItem(i, 4);
//    System.out.println(s.getSubtotal());
//  }
}
