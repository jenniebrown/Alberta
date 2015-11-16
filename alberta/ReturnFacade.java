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
    private String originalOrderDate;
    public ReturnFacade (Register reg)
    {
        this.reg = reg;
    }
    public void setROrderID (int rOID) {this.originalReturnOrderID = rOID;}
    
    public void setDate (String date) {this.originalOrderDate = date;}
    
    public int getReturnOrderID(){return originalReturnOrderID;}
    
    public String getOrderDate() {return originalOrderDate;}
    
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
    
    public boolean checkUPC(int upc) {
        return reg.checkUPC(upc);
    }
     
    public void completeTransaction() {
         returning.completeTransaction();
         reg.setCurrentSale(returning);
     }
    
    public void displayReceipt() {
        returning.printReceipt();
    }
     
    public void setReturnType (int choice){returning.setRentalOrSale(choice);}
    
    public void createReturn() //Note, cannot process a return that contains both
                                //a rental and an order/regular sale
    {     
        System.out.println("Original purchase verified");
        Return test = reg.createNewReturn(originalReturnOrderID, originalOrderDate);
        this.enterReturn(test);   
    }
    
    public void processReturn()
    {
        if (returning.getRentalOrSale() != 1)
        {
            reg.updateInventory(returning);
        }
    }
}
