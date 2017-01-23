package alberta;
public class Manager extends Cashier {
	String mPassword;

	public Manager(String fullName, String employeeID, String cPassword, String mPassword) {
		super(fullName, employeeID, cPassword);
		this.mPassword = cPassword;
	}

	public String getOverridePassword() {
		return mPassword;
	}
}