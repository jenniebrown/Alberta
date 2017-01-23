package alberta;

public class RentalFacade
{
    private Rental rental;
    private Register reg;
    public RentalFacade(Register reg) {
        this.reg = reg;
        rental = reg.createNewRental();
    }

    public void enterRentalItem(int upc, int quantity) {
        AbstractItem i = reg.getRentalItemFromCatalog(upc);
        RentalItem r = (RentalItem)i;
        rental.addItem(r,quantity);
    }

    public boolean checkUPC(int upc) {
        return reg.checkUPC(upc);
    }

    public void completeTransaction() {
        rental.completeTransaction();
        reg.setCurrentSale(rental);
    }
 //-------------this is buggy-----------------------//
    public boolean createCashPayment(double amt) {
        if(amt >= rental.getFinalTotal()) {
            reg.makePayment("0", amt);
            return true;
        } else {
            return false;
        }
    }
    public boolean createPayment(String num) {
        return reg.makePayment(num, rental.getFinalTotal());
    }

    public void updateRentalHistory() {
        reg.addRentalToHistory(rental);
    }

    public void displayReceipt() {
        rental.printReceipt();
    }

    public boolean checkInventory(int q, int id) {
        if(q <= 0) {
            return false;
        } else {
            return reg.checkInventory(q, id);
        }
    }
}
