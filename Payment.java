/*
 */
package cse216project;

public class Payment {
    
    //9/27 make payment class handle the different methods of payments ( credit, debit, money )
    
    private double amount;
    
    public Payment(Money cashTendered) {
        amount = cashTendered;
    }
    public Money getAmount () {
        return amount;
    }
    
    public verifyCredit () {
        //Need to have concrete validation rules in order to further write
    }
    
    public completePayment() {
        
    }
    
}
