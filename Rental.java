package alberta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Rental extends AbstractSale
{
    //private ArrayList<AbstractLineItem> items;

    public Rental() {
        super();
        //items = new ArrayList<AbstractLineItem>();
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


}
