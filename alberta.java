public class alberta{
	public static void main(String[] args) {
		Register reg = new Register();
		reg.createNewSale();
		reg.enterItem(1111, 3);
		reg.makePayment("cash", reg.getCurrentSale().getBalance());
		reg.updateInventory(reg.getCurrentSale());
		reg.cutConnection();
	}
}
