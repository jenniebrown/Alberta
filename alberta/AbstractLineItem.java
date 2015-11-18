package alberta;

public abstract class AbstractLineItem
{
    protected AbstractItem item;
    private int quantity;
    private double subtotal;

    public AbstractLineItem(AbstractItem item, int quantity){

      this.item = item;
      this.quantity = quantity;
      this.subtotal = quantity*item.getPrice();

    }

    public double getSubtotal(){
      return this.subtotal;
    }

    public double getItemCost() {
        return this.item.getPrice();
    }

    public AbstractItem getItem() {
      return this.item;
    }

    public int getQuantity () {
      return this.quantity;
    }
}
