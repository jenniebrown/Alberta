package alberta;
public class Customer extends Person {
	double moneyAvailable;
	String paymentForm;

	public Customer(String fullName, String id, double moneyAvailable, String paymentForm) {
		super(fullName, id);
		this.moneyAvailable = moneyAvailable;
		this.paymentForm = paymentForm;
	}

	public double getAmountAvailable() {
		return moneyAvailable;
	}

	public String getPaymentForm() {
		return paymentForm;
	}
}