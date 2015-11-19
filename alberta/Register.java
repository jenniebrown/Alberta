package alberta;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

// -------------------------------------------------------------------------
/**
 *  Register class is designed to hold the ProductCatalog instance from startup
 *  to shutdown and to record transactions made during that time.
 *
 *  @author Giancarlo
 *  @version Nov 1, 2015
 */
public class Register
{
  private ProductCatalog catalog;
  private AbstractSale currentSale;
  private Rental currentRental;
  private ArrayList<AbstractSale> salesOfTheDay;
  private double profitMade;
  private Payment localPayment;
  public DatabaseHandler constantConnection;
  private final int MAX_INT = Integer.MAX_VALUE;
  private Random oIDGen;

  public Register()
  {
    this.catalog = ProductCatalog.getInstance();
    //this.currentSale = null;
    this.salesOfTheDay = new ArrayList <AbstractSale>();
    this.profitMade = 0.0;
    //this.localPayment = null;
    Date seed = new Date();
    this.oIDGen = new Random(seed.getTime());
    this.constantConnection = DatabaseHandler.connect();
  }


//----------------------Getters&Setters---------------------------------------//
  public void setCurrentSale(AbstractSale current) {
      this.currentSale = current;
  }

  public void setCurrentRental(Rental current) {
      this.currentRental = current;
  }

  public AbstractSale getCurrentSale() {return currentSale;}

  public Rental getCurrentRental() {return currentRental;}
//--------------------------------Methods-------------------------------------//
  public Order createNewOrder()
  {
    currentSale = new Order();
    currentSale.setOrderID(oIDGen.nextInt(MAX_INT)); /* Sets orderID to random number */
    return (Order)currentSale;
  }

  public Rental createNewRental() {
      currentSale = new Rental();
      currentSale.setOrderID(oIDGen.nextInt(MAX_INT)); /* Sets orderID to random number */
      return (Rental)currentSale;
  }

  public Return createNewReturn(int originalOrderID) {
      currentSale = new Return(originalOrderID);
      currentSale.setOrderID(oIDGen.nextInt(MAX_INT)); /* Sets orderID of return to random number */
      return (Return)currentSale;
  }

  public void addSale() {
      salesOfTheDay.add(currentSale);
  }
//Is this even used?
  public void endSale()
  {
    currentSale.completeTransaction();
  }

  public boolean makePayment(String cardNumber, double amount)
  {
      Payment p;
      if(cardNumber.equals("0")) {
          p = new Payment(amount);
      } else {
          p = new Payment(cardNumber, amount);
      }
    if (currentSale.verifyPayment(p)){
    	double change = amount - currentSale.getFinalTotal();
    	//System.out.println("Change due: "+change);
        profitMade += currentSale.getFinalTotal();
        addSale();
        updateInventory(currentSale);
        currentSale.updatePayment(p);
        localPayment = p;
        //TO-DO: ADD order to database history
        //TO-DO: Add customer info to database, can only do this if cardnumber exists



        return true;
    } else {
    	System.out.println("localPayment NOT VERIFIED");
        localPayment = null;
        currentSale = null;
        return false;
    }
  }

  protected void updateInventory(AbstractSale sale) //Lots of method calls, can be a bit slow.
  {
    if(sale != null) {
        ArrayList<AbstractLineItem> local= sale.getItems();
    	int quantityChange = 0;
    	for (AbstractLineItem x : local)
    	{
            if (sale instanceof Return){ //This allows for increment of inventory
            quantityChange = constantConnection.getInventoryByID(x.getItem().getItemID()) + x.getQuantity();
            constantConnection.updateQuantity(x.getItem().getItemID(),quantityChange);
            }
            else{     //This allows for decrement of inventory
            quantityChange = constantConnection.getInventoryByID(x.getItem().getItemID()) - x.getQuantity();
            constantConnection.updateQuantity(x.getItem().getItemID(),quantityChange);
            }
        }

    }
  }

  public boolean checkUPC(int upc) {
      if(catalog.getWholeList().containsKey(upc)) {
          return true;
      } else {
          System.out.println("Item not in catalog");
          return false;
      }
  }
 
  public AbstractLineItem checkItemHistory(int upc, Return ret)
  {
      String tableName = null;
      //System.out.println(ret.getRentalOrSale());

      switch (ret.getRentalOrSale()){
          case 0:
              tableName = "rental";
              int quantity = this.constantConnection.retrieveItemHistoryQuantity(upc, tableName, ret);
              if (quantity == 0){
                  System.out.println("Item not found in rental history. ");
                  return null;
              }
              else
                  return new RentalLineItem((RentalItem)this.getRentalItemFromCatalog(upc),quantity);
          default:
              tableName = "order";
              quantity = this.constantConnection.retrieveItemHistoryQuantity(upc, tableName, ret);
              if (quantity == 0){
                  System.out.println("Item not found in order history. ");
                  return null;
              }
              else
                  return new SalesLineItem((Item)this.getSaleItemFromCatalog(upc),quantity);
      }  
  }
          
  public AbstractItem getRentalItemFromCatalog(int upc) {
      AbstractItem i = catalog.getItem(upc);
      RentalItem r = new RentalItem(i.getDescription(),i.getItemID(),i.getPrice(),i.getRentalType());
      return r;
  }

  public AbstractItem getSaleItemFromCatalog(int upc) {
      AbstractItem i = catalog.getItem(upc);
      Item l = new Item(i.getDescription(),i.getItemID(),i.getPrice());
      return l;
  }

  public boolean verifyPreviousPurchase(int prevOrderID,Return ret)
  {
      String tableName;
      switch(ret.getRentalOrSale()) {
          case 0:
              tableName = "rental_history";
              break;
          default:
              tableName = "order_history";
      }

      return this.constantConnection.checkAgainstReceipt(prevOrderID,tableName);
  }

  public void cutConnection()
  {
    this.constantConnection.disconnect();
  }

  public boolean addOrderToHistory(Order o) {
      return constantConnection.addOrderToHistory(o);
  }

  public boolean addRentalToHistory(Rental r) {
      return constantConnection.addRentalToHistory(r);
  }
}
