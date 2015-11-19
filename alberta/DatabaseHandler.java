package alberta;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler{
    private static DatabaseHandler uniqueInstance;
    private Connection c;
    private Statement stmt;
    private ResultSet rs;

    private DatabaseHandler() {
        try {
	    Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:PoS.db");
            //System.out.println("Connection Successful");
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
    private static synchronized DatabaseHandler getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new DatabaseHandler();
        }
        return uniqueInstance;
    }

    /**
     * Connect to database by calling getInstance()
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
            uniqueInstance = null;
	    //System.out.println("Database connection closed successfully");
        } catch (Exception e){
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }



//----------------------------------------------------------------------------//
//----------------------Retrieve Information----------------------------------//
//----------------------------------------------------------------------------//
    /**
     * Returns password for employee with given empID
     * @param empID
     * @return employee password
     */
    public String getEmpPass(String empID) {
        String result = null;
        try {
            stmt = c.createStatement();
            String req = "SELECT EMP_PW FROM employees WHERE EMP_ID = "+ empID;
            rs = stmt.executeQuery(req);
            if(rs.next()) {
                result = rs.getString("EMP_PW");
            }
            rs.close();
            stmt.close();
        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * Returns all customer information for customers whose email matches the given input
     * as an array of strings
     * @param email
     * @return array of customer info
     */
    public String[] getCustInfo(String cardNumber) {
        String[] result = new String[8];
        try {
            stmt = c.createStatement();
            String req = "SELECT * FROM customers WHERE CARD_NBR = " + cardNumber;
            rs = stmt.executeQuery(req);
            while(rs.next()) {
                result[0] = ""+rs.getInt("CUST_ID");
                result[1] = rs.getString("FIRST_NM");
                result[2] = rs.getString("LAST_NM");
                result[3] = rs.getString("ADDRESS");
                result[4] = rs.getString("CITY");
                result[5] = rs.getString("STATE");
                result[6] = rs.getString("ZIP");
                result[7] = rs.getString("EMAIL");
            }
            rs.close();
            stmt.close();
        } catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * Return employee data employee type, employee name, and email in array of strings
     * @param empID
     * @return employee info
     */
    public String[] getEmpData(String empID) {
        String[] result = new String[5];
        try {
            stmt = c.createStatement();
            String req = "SELECT * FROM employees WHERE EMP_ID = "+empID;
            rs = stmt.executeQuery(req);
            if(rs.next()) {
                result[0] = rs.getString("EMP_TYP_ID");
                result[1] = rs.getString("FIRST_NM");
                result[2] = rs.getString("LAST_NM");
                result[3] = rs.getString("EMAIL");
                result[4] = empID;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }

    /**
     * Return all items in catalog where quantity != 0 as array of strings
     * @return result
     */
    public String[] getCatalog() {
	String[] result = new String[15];
        int loopCount = 0;
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM product_catalog WHERE QUANTITY != 0";
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
                int id = rs.getInt("ITEM_ID");
                String  name = rs.getString("NAME");
                double price  = rs.getDouble("PURCHASE_PRICE");
                String  desc = rs.getString("DESCRIPTION");
                int quant = rs.getInt("QUANTITY");
                result[loopCount++] = ""+id+","+name+","+price+","+desc+","+quant;
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
    public String[] getFullCatalog() {
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
                int quant = rs.getInt("QUANTITY");
                result[loopCount++] = ""+id+","+name+","+price+","+desc+","+quant;
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
            String sql = "SELECT quantity FROM product_catalog WHERE ITEM_ID = "+itemID;
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
    public String getItemInfoByID(int itemID) {
        String result = "";
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM product_catalog WHERE ITEM_ID = "+itemID;
            rs = stmt.executeQuery(request);

            int id = rs.getInt("ITEM_ID");
            String  name = rs.getString("NAME");
            double price  = rs.getDouble("PRICE");
            String  desc = rs.getString("DESCRIPTION");
            result = ""+id+","+name+","+price+","+desc;

            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }
    //This is horrible design, but I can't think of another way to retrieve everything at once.
    /*public ArrayList getItemInfoByIDList(int itemID) {
        ArrayList result = new ArrayList<>();
        try {
            stmt = c.createStatement();
            String request = "SELECT * FROM product_catalog WHERE ITEM_ID = "+itemID;
            rs = stmt.executeQuery(request);

            int id = rs.getInt("ITEM_ID");
            String  name = rs.getString("NAME");
            double price  = rs.getDouble("PRICE");
            String  desc = rs.getString("DESCRIPTION");
            result.add(id); result.add(name); result.add(price); result.add(desc);
            //0 has id, 1 has name, 2 has price, 3 has description.
            rs.close();
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return result;
    }*/
    public boolean checkAgainstReceipt(int orderID,String tableName){
        try {
            stmt = c.createStatement();
            String request = "SELECT ORDER_ID,DATE FROM "+tableName+" WHERE ORDER_ID = "
                    +orderID+";";
            if (tableName.equals("rental_history"))
            {
                request = "SELECT RENTAL_ID,DATE FROM "+tableName+" WHERE RENTAL_ID = "
                    +orderID+";";
            }
            rs = stmt.executeQuery(request);
            while (rs.next()) {
                
                int testID = 0;
                if(tableName.equals("rental_history"))
                    testID = rs.getInt("RENTAL_ID");
                else
                    testID = rs.getInt("ORDER_ID");
                String testDate = rs.getString("DATE");
                //String custID = rs.getString("CUST_ID");
                System.out.println("Order number " +testID+", made on "+testDate);
                System.out.println();
                return true;
            }
        }catch (Exception e) {
            System.err.println(e.getClass() + ": " + e.getMessage());
            System.exit(0);
        }
        return false;
    }
    //Item history only has order_id, item_id and quantity for Order, while Rental
    //has all of that (order_id is rental_id) along with due date and return date. Since
    //this isn't enough to create AbstractLineItem (Or items for that matter), I will retrieve
    //Quantity and then use a modified getItemInfoByID(List) method to fill in the rest.
    //0 has id, 1 has name, 2 has price, 3 has description. 
    //Originally returning abstractLineItem, it will be built through the register instead. This
    //confirms if the item exists, and what it's quantity was. If quantity remains 0, that means 
    //the item doesn't exist in history. Will check for it in register.
    public int retrieveItemHistoryQuantity(int upc, String tableName, Return ret)
    {
        int quantity = 0;
        try{
            stmt = c.createStatement();
            switch (tableName) {
                case "order":
                    //SalesLineItem ali = null;
                    String request = "SELECT QUANTITY FROM "+tableName+"_item_history WHERE ORDER_ID = "
                            +ret.getOriginalOrderID()+ " AND ITEM_ID = "+upc+";";
                    rs = stmt.executeQuery(request);
                    while(rs.next())
                    {
                        quantity = rs.getInt("QUANTITY");
                        /*ArrayList itemInfo = this.getItemInfoByIDList(upc);
                        //This is really gross. Only way
                        Item ai = new Item((String)itemInfo.get(1),
                                (int)itemInfo.get(0),(double)itemInfo.get(2));
                        ali = new SalesLineItem(ai,quantity);*/
                    }
                break;
                case "rental":
                    RentalLineItem rli = null;
                    request = "SELECT QUANTITY FROM "+tableName+"_item_history WHERE RENTAL_ID = "
                            +ret.getOriginalOrderID()+ " AND ITEM_ID = "+upc+";";
                    rs = stmt.executeQuery(request);
                    while(rs.next())
                    {
                        quantity = rs.getInt("QUANTITY");
                       /* ArrayList itemInfo = this.getItemInfoByIDList(upc);
                        //This is really gross. Only way
                        Item ai = new Item((String)itemInfo.get(1),
                                (int)itemInfo.get(0),(double)itemInfo.get(2));
                        rli = new RentalLineItem(ai,quantity);*/
                    }
                default: 
                    break;
            }
        }catch (Exception e) {
            System.err.println(e.getClass() + ": " + e.getMessage());
            System.exit(0);
        }
        return quantity;
    }

//----------------------------------------------------------------------------//
//---------------------------Update Information-------------------------------//
//----------------------------------------------------------------------------//
    /**
     * update quantity of item in inventory based on parameters id and newQuantity
     * @param newQuantity
     * @param id
     */
    public void updateQuantity(int id, int newQuantity) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE product_catalog set QUANTITY = "+newQuantity+" where ITEM_ID= "+id+";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();

            String request = "SELECT * FROM product_catalog WHERE ITEM_ID = "+id+";" ;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
               int testId = rs.getInt("ITEM_ID");
               int testQuantity = rs.getInt("QUANTITY");
               //System.out.println( "Product ID " + testId + " quantity updated to " + testQuantity);
               //System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    /**
     * update price of item in inventory based on parameters id and newPrice
     * @param newPrice
     * @param id
     */
    public void updatePrice(int id, int newPrice) {
        try {
            stmt = c.createStatement();
            String sql = "UPDATE product_catalog set PRICE = "+newPrice+" where ITEM_ID= "+id+";";
            stmt.executeUpdate(sql);
            c.commit();

            String request = "SELECT ITEM_ID,PRICE FROM product_catalog WHERE ITEM_ID = "+id+";" ;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
               int testId = rs.getInt("ITEM_ID");
               double testPrice = rs.getDouble("PRICE");
               System.out.println( "Item ID " + testId +" price updated to " + testPrice);
               System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

//    /**
//     * Overloaded method to add order to store_order_history with no cardNumber
//     * @param date
//     * @param orderTotal
//     * @param paymentType
//     * @param custID
//     * @param id
//     */
//    public void addOrderToHistory(int id, String date, double orderTotal, String paymentType, String custID) {
//        addOrderToHistory(id,date,orderTotal,paymentType,"",custID);
//    }


    public boolean addRentalToHistory(Rental r) {
        try {
            stmt = c.createStatement();
            int rentID = r.getOrderID();
            String card = r.payMe.getCardNumber();
            String date = r.date.toString();
            String pay = Integer.toString(r.payMe.getPaymentMethod());
            double tot = r.getFinalTotal();
            String vals = rentID+",'"+card+"','"+date+"',"+tot+",'"+pay+"'";
            String sql = "INSERT INTO rental_history (RENTAL_ID,CARD_NBR,DATE,ORDER_TOTAL,PAYMENT_TYPE)"+
                            "VALUES ("+vals+");";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();

            stmt = c.createStatement();
            ArrayList<RentalLineItem> itemList= r.getRentalItems();
            for(RentalLineItem i : itemList) {
                int itemID = i.getItem().getItemID();
                int q = i.getQuantity();
                String due = i.getItem().getDueDate().toString();
                vals = rentID+","+itemID+","+q+",'"+due+"'";
                String ins = "INSERT INTO rental_item_history (RENTAL_ID,ITEM_ID,QUANTITY,DUE_DATE)"+
                    "VALUES ("+vals+");";
                stmt.executeUpdate(ins);
                c.commit();
            }
            stmt.close();
        } catch(Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }finally {
            return true;
        }
    }

    public boolean addOrderToHistory(Order o) {
        try {
            stmt = c.createStatement();
            int orderID = o.getOrderID();
            String card = o.payMe.getCardNumber();
            String date = o.date.toString();
            String pay = Integer.toString(o.payMe.getPaymentMethod());
            double tot = o.getFinalTotal();
            String vals = orderID+",'"+card+"','"+date+"',"+tot+",'"+pay+"'";
            String sql = "INSERT INTO order_history (ORDER_ID,CARD_NBR,DATE,ORDER_TOTAL,PAYMENT_TYPE)"+
                            "VALUES ("+vals+");";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();

            stmt = c.createStatement();
            ArrayList<AbstractLineItem> itemList= o.getItems();
            for(int i=0; i < itemList.size(); i++) {
                int itemID = itemList.get(i).getItem().getItemID();
                int q = itemList.get(i).getQuantity();
                vals = orderID+","+itemID+","+q;
                String ins = "INSERT INTO order_item_history (ORDER_ID,ITEM_ID,QUANTITY)"+
                    "VALUES ("+vals+");";
                stmt.executeUpdate(ins);
                c.commit();
            }
            stmt.close();
        } catch(Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        } finally {
            return true;
        }
    }

    /**
     * Add customer to database
     * @param first
     * @param last
     * @param email
     * @param cardNumber
     */
    public void addCustomer(String first, String last, String email, String cardNumber) {
        try {
            stmt = c.createStatement();
            String req = "SELECT max(CUST_ID) FROM customers";
            rs = stmt.executeQuery(req);
            int custID = 0;
            if(rs.next()) {
                custID = rs.getInt("CUST_ID");
            }
            rs.close();
            stmt.close();

            stmt = c.createStatement();
            String vals = (++custID)+",'"+cardNumber+"',"+first+",'"+last+"','"+email+"'";
            String sql = "INSERT INTO customers (CUST_ID,CARD_NBR,FIRST_NM,LAST_NM,EMAIL) "+
                            "VALUES ("+vals+");";
            stmt.executeUpdate(sql);
            c.commit();

            String request = "SELECT FIRST_NM, LAST_NM FROM customers WHERE CUST_ID = "+custID+";" ;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
               String testFirst = rs.getString("FIRST_NM");
               String testLast = rs.getString("LAST_NM");
               System.out.print( "Customer " + testFirst +" "+ testLast+" added to database");
               System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }


    /**
     * Remove employee from database from specified employee id.
     * @param empID
     */
    public void removeEmployee(int empID) {
        try {

            stmt = c.createStatement();
            String sql = "DELETE FROM employees WHERE EMP_ID = "+empID;
            stmt.executeUpdate(sql);
            c.commit();
            System.out.print( "Employee " + empID+" removed from database");

            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Get all Employees as ArrayList of String
     */
    public ArrayList<String[]> getEmployees() {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String[]> emps = new ArrayList<>();
        try {
            stmt = c.createStatement();
            String req = "SELECT EMP_ID FROM employees;";
            rs = stmt.executeQuery(req);
            while (rs.next()) {
                ids.add(rs.getString("EMP_ID"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        for (String id : ids) emps.add(getEmpData(id));
        return emps;
    }

    /**
     * Add a new employee to the database
     * @param first
     * @param last
     * @param email
     * @param id
     * @param pw
     * @param type - 1 for manager or 2 for cashier
     */
    public void addEmployee(String first, String last, String email, int id, String pw, int type) {
        try {

            stmt = c.createStatement();
            String vals = id+","+type+",'"+pw+"','"+first+"','"+last+"','"+email+"'";
            String sql = "INSERT INTO employees (EMP_ID,EMP_TYP_ID,EMP_PW,FIRST_NM,LAST_NM,EMAIL) "+
                            "VALUES ("+vals+");";
            stmt.executeUpdate(sql);
            c.commit();

            String request = "SELECT FIRST_NM, LAST_NM FROM employees WHERE EMP_ID = "+id+";" ;
            rs = stmt.executeQuery(request);
            while ( rs.next() ) {
               String testFirst = rs.getString("FIRST_NM");
               String testLast = rs.getString("LAST_NM");
               System.out.print( "Employee " + testFirst +" "+ testLast+" added to database");
               System.out.println();
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }


}
