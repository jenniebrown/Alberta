public class TestDatabase {
    public static void main(String args[]) {
        DatabaseHandler handler = DatabaseHandler.connect();
        String[] items = handler.getCatalog();
        for(String i: items) {
            if(i != null)
                System.out.println(i);
        }
        System.out.println();
        String[] inv = handler.getInventory();
        for(String i: inv) {
            if(i != null)
                System.out.println(i);
        }
        System.out.println();
        System.out.println(handler.getInventoryByID(1111));
        handler.updateInventory(1111, 59);
        System.out.println(handler.getInventoryByID(1111));



        handler.disconnect();

    }


}
