package alberta;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Register class is designed to hold the ProductCatalog instance from startup
 *  to shutdown and to record transactions made during that time.
 *
 *  @author Jennie
 *  @version Nov 1, 2015
 */
public class Register
{
  private ProductCatalog catalog;
  private AbstractSale currentSale;
  private ArrayList<AbstractSale> salesOfTheDay;
  private double profitMade;
  private Payment localPayment;
  public DatabaseHandler constantConnection;

  public Register()
  {
    this.catalog = ProductCatalog.getInstance();
    //this.currentSale = null;
    this.salesOfTheDay = new ArrayList <AbstractSale>();
    this.profitMade = 0.0;
    //this.localPayment = null;
    this.constantConnection = DatabaseHandler.connect();
  }


//----------------------Getters&Setters---------------------------------------//
  public void setCurrentSale(AbstractSale current) {
      this.currentSale = current;
  }

  public AbstractSale getCurrentSale() {return currentSale;}

//--------------------------------Methods-------------------------------------//
  public Order createNewOrder()
  {
    currentSale = new Order();
    return (Order)currentSale;
  }

  public Rental createNewRental() {
      currentSale = new Rental();
      return (Rental)currentSale;
  }

  public void addSale() {
      salesOfTheDay.add(currentSale);
  }

  public void endSale()
  {
    currentSale.completeTransaction();
  }

//  public boolean enterItem(int id, int quantity)
//  {
//    AbstractItem item = catalog.getItem(id);
//    if (item == null) {
//        return false;
//    } else {
//        currentSale.addItem(item, quantity);
//        //System.out.println("Entered Item. Current Sale = "+currentSale);
//        return true;
//    }
//  }

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
    	System.out.println("Change due: "+change);
        profitMade += currentSale.getFinalTotal();
        addSale();
        updateInventory(currentSale);
        currentSale.updatePayment(p);
        localPayment = p;
        //TO-DO: Add customer info to database
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
    	  quantityChange = constantConnection.getInventoryByID(x.getItem().getItemID()) - x.getQuantity();
    	  System.out.println("quantity change calcd");
    	  constantConnection.updateQuantity(x.getItem().getItemID(),quantityChange);
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

  public void cutConnection ()
  {
    this.constantConnection.disconnect();
  }

}
