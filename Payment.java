/*
 */
//package cse216project;

public class Payment {
    
    //9/27 make payment class handle the different methods of payments ( credit, debit, money )
    
    private double amount;
    private String paymentMethod;
    private boolean isCredit;
    
    public Payment(String paymentMethod, double amount) {
        String paymentCase = paymentMethod.toLowerCase();
        if(paymentCase.matches("cash")) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.isCredit = false;
	
        }
    }
    public double getAmount () {
        return amount;
    }
    
    //no verifyCredit yet.
   // public verifyCredit () {
        //Need to have concrete validation rules in order to further write
   // }
    
//    public updatePayment(String paymentMethod, double amount) {
//
//    }
//    
//    public completePayment() {
//        
//    }
    
}
