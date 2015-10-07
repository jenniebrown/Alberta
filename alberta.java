public class alberta{
	public static void main(String[] args) {
		Register reg = new Register();
		int inum = reg.constantConnection.getInventoryByID(1111);
//		System.out.println(inum);

		reg.createNewSale();
		reg.enterItem(1111, 3);
		reg.makePayment("cash", reg.getCurrentSale().getBalance());
		//reg.updateInventory(reg.getCurrentSale());
		
		int inum2 = reg.constantConnection.getInventoryByID(1111);
//		System.out.print(inum2);
//		System.out.println(inum2 == inum - 3);

		reg.cutConnection();
	}
}
