import java.sql.*;

public class DatabaseHandler{
    private static DatabaseHandler uniqueInstance;
    private Connection c;
    private Statement stmt;
    private ResultSet rs;

    private DatabaseHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
            c.setAutoCommit(false);
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
    }

    /**
     * Singleton design pattern for DatabaseHandler. DatabaseHandler ensures one
     * connection open at a time.
     * @return uniqueInstance
     */
    public static synchronized DatabaseHandler getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new DatabaseHandler();
        }
        return uniqueInstance;
    }

    /**
     * Connect to database
     * @return DatabaseHandler
     */
    public static DatabaseHandler connect() {
        return getInstance();
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        try {
            c.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * Return all items in catalog as array of strings
     * @return result
     */
    public String[] getInventory() {
        String[] result = new String[15];
        int loopCount = 0;
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM inventory";
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
                int id = rs.getInt("ITEM_ID");
                int quantity = rs.getInt("QUANTITY");
                result[loopCount++] = ""+id+","+quantity;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * Return all items in catalog as array of strings
     * @return result
     */
    public String[] getCatalog() {
        String[] result = new String[15];
        int loopCount = 0;
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM product_catalog";
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
                int id = rs.getInt("ITEM_ID");
                String  name = rs.getString("NAME");
                double price  = rs.getDouble("PRICE");
                String  desc = rs.getString("DESCRIPTION");
                result[loopCount++] = ""+id+","+name+","+price+","+desc;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * Get quantity of item in inventory that matches parameter itemID
     * @return quantity
     */
    public int getInventoryByID(int itemID) {
        int quantity = 0;
        try {
            stmt = c.createStatement();
            String sql = "SELECT quantity FROM inventory WHERE ITEM_ID = "+itemID;
            rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                quantity = rs.getInt("QUANTITY");
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return quantity;
    }

    /**
     * get item info as comma delimited list of properties for items in table
     * product_catalog that match item ID. result is returned as array of entries
     * that match parameter itemID.
     * @return String[] result
     */
    public String[] getItemInfoByID(int itemID) {
        String result[] = new String[15];
        int loopCount = 0;
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM product_catalog WHERE ITEM_ID = "+itemID;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
                int id = rs.getInt("ITEM_ID");
                String  name = rs.getString("NAME");
                double price  = rs.getDouble("PRICE");
                String  desc = rs.getString("DESCRIPTION");
                result[loopCount] = ""+id+","+","+price+","+desc;
            }
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * update quantity of item in inventory based on parameters id and newQuantity
     * @param newQuantity
     * @param id
     */
    public void updateInventory(int id, int newQuantity) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE inventory set QUANTITY = "+newQuantity+" where ITEM_ID= "+id+";";
            stmt.executeUpdate(sql);
            c.commit();

            String request = "SELECT * FROM inventory WHERE ITEM_ID = "+id+";" ;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
               int testId = rs.getInt("ITEM_ID");
               int testQuantity = rs.getInt("QUANTITY");
               System.out.println( "ID = " + testId );
               System.out.println( "QUANTITY = " + testQuantity);
               System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }
}
