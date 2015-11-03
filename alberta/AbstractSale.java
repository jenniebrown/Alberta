package alberta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class AbstractSale
{
    protected Calendar cal;
    private double runningTotal;
    private boolean transactionComplete;
    protected GenericTaxCalculator tc;
    protected ArrayList<AbstractLineItem> items;
    protected Date date;
    private double finalTax;
    private double finalTotal;
    protected Payment payMe;

    public AbstractSale() {
        transactionComplete = false;
        runningTotal = 0;
        cal = Calendar.getInstance();
        date = cal.getTime();
        tc = new GenericTaxCalculator(0.06);
        finalTax = 0;
        finalTotal = 0;
        items = new ArrayList<AbstractLineItem>();
    }

//---------------------------Getters&Setters----------------------------------//

    public boolean isComplete() {return transactionComplete;}

    public double getBalance() {return runningTotal;}

    public double getFinalTax() {return finalTax;}

    public double getFinalTotal() {return finalTotal;}

    public ArrayList<AbstractLineItem> getItems() {return items;}

    public void setRunningTotal(double newTotal) {this.runningTotal = newTotal;}

    public void setFinalTax(double tax) {this.finalTax = tax;}

    public void setTransactionComplete(boolean t) {this.transactionComplete = true;}

    public void setFinalTotal(double tot) {this.finalTotal = tot;}

    public boolean verifyPayment(Payment p) {
        if(!p.isCredit()) {
            return p.getAmount() - this.getFinalTotal() >= 0;
        } else {
            //TO-DO: fix card verification
            return true;
        }
    }

    public void updatePayment(Payment p) {
        this.payMe = p;
    }

    public void printReceipt(){
        //can add print statement for company name address and phone number
        System.out.println("Company Name");
        System.out.println("Company Address");
        System.out.println("Company Number");
        System.out.println(date.toString());

        for(AbstractLineItem i : items){
            System.out.println(i.getItem().getDescription()+ "("+ i.getQuantity() +"x)"+"\t $"+i.getSubtotal());
        }

        System.out.println("Subtotal: $"+ getBalance());
        System.out.println("Tax: $"+ getFinalTax());
        System.out.println("Total: $"+ getFinalTotal());

        System.out.println("Amount Paid: $"+ payMe.getAmount());

        System.out.println("Cash Back: $"+ (payMe.getAmount()-getFinalTotal()));

    }

//---------------------------AbstractMethods----------------------------------//
    //public abstract void addItem(AbstractItem i, int quantity);

    //public abstract void addItem(AbstractItem i);

    public abstract void completeTransaction();

//---------------------------ConcreteMethods----------------------------------//





}
