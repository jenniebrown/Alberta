package alberta;

public class Payment {

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

    public boolean verifyCredit (String cardNum) { 
    	if (cardNum.length() == 16) {
    		for (int i = 0; i < cardNum.length(); i++) {
    			char c = cardNum.charAt(i);
    			if (!Character.isDigit(c)) {
    				return false;
    			}
    		}
    		return true; //16 digit.
    	}
    	else {
    		return false;
    	}
    }

//    public updatePayment(String paymentMethod, double amount) {
//
//    }
//
//    public completePayment() {
//
//    }

}
