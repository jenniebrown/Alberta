package alberta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class CreateDatabase
{
    public static void main(String args[]) {


//            dropTable("product_catalog");
//            createProductCatalog();
//            populateInventory();
//
//        dropTable("order_history");
//        createOrderHistory();
//        dropTable("rental_history");
//        createRentalHistory();
//        dropTable("order_item_history");
//        createOrderItemHistory();
//        dropTable("rental_item_history");
//        createRentalItemHistory();

//        createReturns();
//        createReturnedItems();
//        createEndOfDay();
        //populateInventory();
        DatabaseHandler c = DatabaseHandler.connect();
        c.updateQuantity(1111, 100);
        c.updateQuantity(1112, 100);
        c.updateQuantity(1113, 100);
        c.updateQuantity(1114, 100);
        c.disconnect();

    }

    public static void dropTable(String tableName) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String drop = "DROP TABLE "+tableName;
            stmt.executeUpdate(drop);
            stmt.close();
            c.commit();
            c.close();
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void createEndOfDay() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE end_of_day " +
                       "(DATE LONG PRIMARY KEY NOT NULL," +
                       "REVENUE FLOAT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createReturns() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE returns " +
                       "(RETURN_ID INT PRIMARY KEY NOT NULL," +
                       "ORDER_ID INT NOT NULL, " +
                       "RET_DATE TEXT NOT NULL, " +
                       "CARD_NBR TEXT, " +
                       "REFUND FLOAT NOT NULL, " +
                       "PAYMENT_TYPE TEXT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createReturnedItems() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE return_items " +
                       "(RETURN_ID INT NOT NULL," +
                       "ITEM_ID INT NOT NULL, " +
                       "QUANTITY INT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }



    public static void createOrderHistory() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE order_history " +
                       "(ORDER_ID INT PRIMARY KEY NOT NULL," +
                       "CUST_ID TEXT," +
                       "CARD_NBR TEXT, " +
                       "DATE TEXT NOT NULL, " +
                       "ORDER_TOTAL FLOAT NOT NULL, " +
                       "PAYMENT_TYPE TEXT NOT NULL)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createOrderItemHistory() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE order_item_history " +
                       "(ORDER_ID INT NOT NULL," +
                       "ITEM_ID INT," +
                       "QUANTITY INT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createRentalItemHistory() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE rental_item_history " +
                       "(RENTAL_ID INT NOT NULL," +
                       "ITEM_ID INT," +
                       "QUANTITY INT,"+
                       "DUE_DATE TEXT NOT NULL,"+
                       "RETURN_DATE TEXT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createRentalHistory() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE rental_history " +
                       "(RENTAL_ID INT PRIMARY KEY NOT NULL," +
                       "CUST_ID TEXT," +
                       "CARD_NBR TEXT, " +
                       "DATE TEXT NOT NULL, " +
                       "ORDER_TOTAL FLOAT NOT NULL, " +
                       "PAYMENT_TYPE TEXT NOT NULL," +
                       "LATE_FEE FLOAT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }



    public static void populateInventory() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO product_catalog (ITEM_ID,NAME,PURCHASE_PRICE,RENTAL_PRICE,QUANTITY,RENTAL_TYPE,DESCRIPTION) " +
                         "VALUES (1111, 'Mop', 14.99, 5.00, 100, 1, 'Rubbermaid cut-end mop');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO product_catalog (ITEM_ID,NAME,PURCHASE_PRICE,RENTAL_PRICE,QUANTITY,RENTAL_TYPE,DESCRIPTION)" +
                  "VALUES (1112, 'Broom', 9.99, 4.00, 75, 1, '12 in angle broom');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO product_catalog (ITEM_ID,NAME,PURCHASE_PRICE,RENTAL_PRICE,QUANTITY,RENTAL_TYPE,DESCRIPTION)" +
                "VALUES (1113, 'Bucket', 8.00, NULL, 65, 0, 'Rubbermaid 3 gal');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO product_catalog (ITEM_ID,NAME,PURCHASE_PRICE,RENTAL_PRICE,QUANTITY,RENTAL_TYPE,DESCRIPTION)" +
                "VALUES (1114, 'Hand Soap', 3.50, NULL, 50, 0, 'Softsoap 12 fl oz');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
          System.out.println("Records created successfully");
    }

    public static void createCustomers() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE customers " +
                       "(CUST_ID INT PRIMARY KEY     NOT NULL," +
                       "CARD_NBR            TEXT,    " +
                       "FIRST_NM    TEXT," +
                       "LAST_NM     TEXT," +
                       "ADDRESS     TEXT," +
                       "CITY        TEXT," +
                       "STATE       TEXT," +
                       "ZIP         TEXT," +
                       "EMAIL       TEXT)";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public static void createEmployees() {
        Connection c = null;
      Statement stmt = null;
      try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String sql = "CREATE TABLE employees " +
                     "(EMP_ID INT PRIMARY KEY     NOT NULL," +
                     "EMP_TYP_ID            INT  NOT NULL,    " +
                     "EMP_PW      TEXT    NOT NULL," +
                     "FIRST_NM    TEXT    NOT NULL," +
                     "LAST_NM     TEXT    NOT NULL," +
                     "ADDRESS     TEXT," +
                     "CITY        TEXT," +
                     "STATE       TEXT," +
                     "ZIP         TEXT," +
                     "EMAIL       TEXT    NOT NULL)";
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Table created successfully");
    }



    public static void createProductCatalog() {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE product_catalog " +
                       "(ITEM_ID INT PRIMARY KEY NOT NULL," +
                       " NAME TEXT NOT NULL, " +
                       " PURCHASE_PRICE FLOAT NOT NULL, " +
                       " RENTAL_PRICE FLOAT, " +
                       " QUANTITY INT NOT NULL, " +
                       " RENTAL_TYPE INT NOT NULL, " +
                       " DESCRIPTION CHAR(50))";
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Table created successfully");
    }

  //Create PoS.db
    public static void createPoS() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e. getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }



}
