package alberta;

public class OrderFacade
{
    private Order order;
    private Register reg;
    public OrderFacade(Register reg) {
        this.reg = reg;
        order = reg.createNewOrder();
    }

    public void enterOrderItem(int upc, int quantity) {
        AbstractItem i = reg.getSaleItemFromCatalog(upc);
        Item r = (Item) i;
        order.addItem(r,quantity);
    }

    public boolean checkUPC(int upc) {
        return reg.checkUPC(upc);
    }

    public void completeTransaction() {
        order.completeTransaction();
        reg.setCurrentSale(order);
    }
 //-------------this is buggy-----------------------//
    public boolean createCashPayment(double amt) {
        if(amt >= order.getFinalTotal()) {
            reg.makePayment("0", amt);
            return true;
        } else {
            return false;
        }
    }
    public boolean createPayment(String num) {
        return reg.makePayment(num, order.getFinalTotal());
    }

    public void displayReceipt() {
        order.printReceipt();
    }
}
