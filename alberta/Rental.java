package alberta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Rental extends AbstractSale
{
    public Rental() {
        super();
    }

//----------------------------Getters&Setters---------------------------------//
   // public ArrayList<AbstractLineItem> getItems() {return items;}
//----------------------------Methods-----------------------------------------//

    public void addItem(RentalItem i, int q) {
        if(i.getCanRent()) {
            i.setDueDate(calcDueDate(i));
            RentalLineItem item = new RentalLineItem(i, q);
            items.add(item);
            updateTotal(item);
        } else {
            System.out.println("Item not available for rent. Item not added to transaction.");
        }
        //TO-DO: display transaction info
    }

    public void addItem(RentalItem i){
        addItem(i, 1);
    }

    /**
     * Method that calculates and assigns finalTax and finalTotal. Then prints out
     * final items list and total tax and balance due.
     */
    @Override
    public void completeTransaction() {
        setTransactionComplete(true);
        setFinalTotal(calcTransacTot());
        System.out.print(this.toString());
        System.out.println("Total tax: \t"+getFinalTax());
        System.out.println("Balance due: \t"+getFinalTotal());
    }

    public String toString() {
        StringBuffer r = new StringBuffer();
        AbstractLineItem it;
        for(int i = 0; i < items.size(); i++) {
            it = items.get(i);
            r.append(it.getItem().getDescription());
            r.append("\t "+it.getQuantity());
            r.append("\t"+it.getItem().getPrice());
            r.append("\t"+((RentalItem)it.getItem()).getDueDate().toString());
            r.append("\n\t\t\t\t"+it.getSubtotal());
            r.append("\n");
        }
        String result = r.toString();
        return result;
    }

    private Date calcDueDate(RentalItem i) {
        Date due;
        switch(i.getRentalType()) {
            case 1:
                cal.add(Calendar.HOUR, 4);
                break;
            case 2:
                cal.add(Calendar.DAY_OF_YEAR, 3);
                break;
            case 3:
                cal.add(Calendar.DAY_OF_YEAR, 7);
                break;
            case 4:
                cal.add(Calendar.MONTH, 1);
                break;
            default:
                System.out.println("Item " + i.getItemID()+ " not available for rent. No due date calculated.");
                return null;
        }
        due = cal.getTime();
        cal.setTime(date);
        return due;
    }

    private void updateTotal(RentalLineItem i) {
        setRunningTotal(getBalance()+i.getSubtotal());
    }

    private double calcTransacTot() {
        setFinalTax(tc.calculateTax(items));
        return getFinalTax() + getBalance();
    }

    @Override
    public void printReceipt(){
        //can add print statement for company name address and phone number
        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println("\t\tCompany Name");
        System.out.println("\t\tCompany Address");
        System.out.println("\t\tCompany Number");
        System.out.println("---------------------------------------------------");
        System.out.println();
        System.out.println(date.toString());
        System.out.println();
        System.out.println("Item Description\tQuantity\tPrice");
        for(AbstractLineItem i : items){
            System.out.println(i.getItem().getDescription()+ "\t("+ i.getQuantity() +"x)"+"\t\t $"+i.getSubtotal());
            if(i.getItem() instanceof RentalItem) {System.out.println("\t Due "+((RentalItem) i.getItem()).getDueDate());}
        }
        System.out.println();
        System.out.println("Subtotal: \t\t"
            + ""
            + ""
            + "\t\t$"+ getBalance());
        System.out.println("---------------------------------------------------");
        System.out.println("Tax: \t\t\t\t\t$"+ getFinalTax());
        System.out.println("Total: \t\t\t\t\t$"+ getFinalTotal());
        System.out.println("---------------------------------------------------");
        System.out.println("Amount Paid: \t\t\t\t$"+ payMe.getAmount());

        System.out.println("Cash Back: \t\t\t\t$"+ (payMe.getAmount()-getFinalTotal()));
        System.out.println();
        System.out.println();

    }


}
