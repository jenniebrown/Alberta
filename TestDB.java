public class TestDB {
	public static void main(String args[]) {
		DatabaseHandler handler = DatabaseHandler.connect();
		String[] items = handler.getCatalog();
		for(String i: items) {
			if(i != null)
				System.out.println(i);

		}
		System.out.println();
		System.out.println(handler.getInventoryByID(1111));
		handler.disconnect();
	}
}
