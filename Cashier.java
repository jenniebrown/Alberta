public class Cashier extends Person {
	String cPassword;

	public Cashier(String fullName, String employeeID, String cPassword) {
		super(fullName, employeeID);
		this.cPassword = cPassword;
	}

	public String getPassword() {
		return cPassword;
	}
}