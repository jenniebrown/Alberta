/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alberta;

/**
 *
 * @author giancarlo
 */
public class ReturnFacade {
    private Return returning;
    private Register reg;
    private int originalReturnOrderID;
    private int returnType;
    private AbstractLineItem check;
    public ReturnFacade (Register reg)
    {
        this.reg = reg;
    }
    public void setROrderID (int rOID) {this.originalReturnOrderID = rOID;}

    public void setReturnType (int choice){returning.setRentalOrSale(choice);}

    public int getReturnOrderID(){return originalReturnOrderID;}

    public Return getReturn() {return returning;}

    public void enterReturn (Return r)
    {
        this.returning = r;
        System.out.println ("Attempting Return");
    }

    public void enterOrderItem(int upc, int quantity) {
        AbstractItem i = reg.getSaleItemFromCatalog(upc);
        Item r = (Item) i;
        returning.addItem(r,quantity);
    }

    public boolean checkUPCAgainstHistory(int upc) {
        check = reg.checkItemHistory(upc,returning);
        return (check == null);
    }
    
    public boolean checkQuantity(int compare) {
        int originalQuantity = check.getQuantity();
        return (compare <= originalQuantity);
    }

    public void completeTransaction() {
         returning.completeTransaction();
         reg.setCurrentSale(returning);
     }

    public void displayReceipt() {
        returning.printReceipt();
    }

    public void createReturn() //Note, cannot process a return that contains both
                                //a rental and an order/regular sale
    {
        System.out.println("Original purchase verified");
        Return test = reg.createNewReturn(originalReturnOrderID);
        this.enterReturn(test);
    }

    public void processReturn()
    {
        if (returning.getRentalOrSale() != 1) //increase inventory if a rental return or non-defective
        {
            reg.updateInventory(returning);
        }
        reg.addReturnToHistory(returning);
        if (returning.getRentalOrSale() != 0) //if non-rental return, give refund
            reg.refund(returning);
        else
            reg.collectLateFee(returning); //Might be 0
    }
}
