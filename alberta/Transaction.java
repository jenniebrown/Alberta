package alberta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Transaction
{
    private Rental rental;
    private Order order;
    private Payment payment;
    private double transTot;
    private Calendar cal;
    private Date date;
    protected GenericTaxCalculator tc;
    private double transTax;
    private int transID;

    public Transaction() {
        rental = new Rental();
        order = new Order();
        transTot = 0.0;
        cal = Calendar.getInstance();
        date = cal.getTime();
        tc = new GenericTaxCalculator(0.06);
        payment = null;
        transTax = 0.0;
    }

//-----------------------------Getters&Setters--------------------------------//
    public Date getDate() {return date;}

    public double getTransTot() {return transTot;}

    public double getTransTax() {return transTax;}

    public int getTransID() {return transID;}

    public Payment getPayment() {return payment;}

    public Rental getRental() {return rental;}

    public Order getOrder() {return order;}

//-------------------------------Methods--------------------------------------//
    public void addRentalItem(RentalItem i, int qty) {
        rental.addItem(i, qty);
    }

    public void addSaleItem(Item i, int qty) {
        order.addItem(i, qty);
    }



}
