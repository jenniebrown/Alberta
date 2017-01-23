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
    protected int orderID;

    public AbstractSale() {
        transactionComplete = false;
        runningTotal = 0;
        cal = Calendar.getInstance();
        date = cal.getTime();
        tc = new GenericTaxCalculator(0.06);
        finalTax = 0;
        finalTotal = 0;
        items = new ArrayList<AbstractLineItem>();
        orderID = 0;
    }

//---------------------------Getters&Setters----------------------------------//

    public boolean isComplete() {return transactionComplete;}

    public double getBalance() {return runningTotal;}

    public double getFinalTax() {return finalTax;}

    public double getFinalTotal() {return finalTotal;}

    public ArrayList<AbstractLineItem> getItems() {return items;}

    public int getOrderID() {return orderID;}

    public void setOrderID(int oID) {this.orderID = oID;}

    public void setRunningTotal(double newTotal) {this.runningTotal = newTotal;}

    public void setFinalTax(double tax) {this.finalTax = tax;}

    public void setTransactionComplete(boolean t) {this.transactionComplete = true;}

    public void setFinalTotal(double tot) {this.finalTotal = tot;}

    public boolean verifyPayment(Payment p) {
        if(!p.isCredit()) {
            return p.getAmount() - this.getFinalTotal() >= 0;
        } else {
            return p.verifyCredit(p.getCardNumber());
        }
    }

    public void updatePayment(Payment p) {
        this.payMe = p;
    }

    public void printReceipt(){
        //can add print statement for company name address and phone number
        System.out.println();
        System.out.println("Order ID: " + orderID);
        System.out.println("--------------------------------------------------------------");
        System.out.println("\t\tCompany Name");
        System.out.println("\t\tCompany Address");
        System.out.println("\t\tCompany Number");
        System.out.println("--------------------------------------------------------------");
        System.out.println();
        System.out.println(date.toString());
        System.out.println();
        System.out.println("Item Description\t\t\tQuantity\tPrice");
        for(AbstractLineItem i : items){
            System.out.println(i.getItem().getDescription()+ "\t\t("+ i.getQuantity() +"x)"+"\t\t $"+i.getSubtotal());
        }
        System.out.println();
        System.out.println("Subtotal: \t\t"
            + ""
            + ""
            + "\t\t$"+ getBalance());
        System.out.println("--------------------------------------------------------------");
        System.out.printf("Tax: \t\t\t\t\t$%.2f\n",getFinalTax());
        System.out.printf("Total: \t\t\t\t\t$%.2f\n",getFinalTotal());
        System.out.println("--------------------------------------------------------------");
        System.out.printf("Amount Paid: \t\t\t\t$%.2f\n",payMe.getAmount());

        System.out.printf("Cash Back: \t\t\t\t$%.2f\n",(payMe.getAmount()-getFinalTotal()));
        System.out.println();
        System.out.println();

    }



//---------------------------AbstractMethods----------------------------------//
    //public abstract void addItem(AbstractItem i, int quantity);

    //public abstract void addItem(AbstractItem i);

    public abstract void completeTransaction();


//---------------------------ConcreteMethods----------------------------------//





}
