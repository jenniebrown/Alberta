package alberta;

public class dbtest
{
    public static void main(String[] args) {
        DatabaseHandler c = DatabaseHandler.connect();
        update(c);
    }



    public static void update(DatabaseHandler c) {
        int dq = c.getInventoryByID(1111)-1;
        c.updateQuantity(1111, dq);
        c.disconnect();
    }
}
