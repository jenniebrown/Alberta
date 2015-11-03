package alberta;

/*
 */
//package cse216project;

public class Payment {

    //9/27 make payment class handle the different methods of payments ( credit, debit, money )

    private double amountTendered;
    private int paymentMethod;
    private boolean credit;
    private String cardNumber;

    public Payment(String cardNumber, double amount) {
        this.credit = true;
        this.amountTendered = amount;
        setCardNumber(cardNumber);
        this.paymentMethod = 2;
    }

    public Payment(double amount) {
        this.credit = false;
        this.amountTendered = amount;
        this.cardNumber = null;
        this.paymentMethod = 1;
    }
    public double getAmount() {
        return amountTendered;
    }

    public boolean isCredit() {return credit;}

    public void setCardNumber(String num) {
        if(verifyCredit(num)) {
            this.cardNumber = num;
        }
    }

    public String getCardNumber() {return this.cardNumber;}

    //no verifyCredit yet.
    public boolean verifyCredit (String cardNum) {
        //TO-DO: implement credit number verification
        return true;
    }

//    public updatePayment(String paymentMethod, double amount) {
//
//    }
//
//    public completePayment() {
//
//    }

}
