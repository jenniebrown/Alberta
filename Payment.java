/*
 */
package cse216project;

public class Payment {
    
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
