import java.util.ArrayList;
public class Register
{
  private ProductCatalog catalog;
  private Order currentSale;
  private ArrayList<Order> salesOfTheDay;
  private double profitMade;
  private Payment localPayment;
  public DatabaseHandler constantConnection;

  public Register()
  {
    System.out.println("in Register constructor");
    this.catalog = ProductCatalog.getInstance();
    System.out.println("Got catalog");
    //this.currentSale = null;
    System.out.println("Making Order ArrayList");
    this.salesOfTheDay = new ArrayList <Order>();
    System.out.println("Made Order ArrayList");
    this.profitMade = 0.0;
    System.out.println("Profit Made = "+profitMade);
    //this.localPayment = null;
    this.constantConnection = DatabaseHandler.connect();
    System.out.println("Made constantConnection");
  }
  
  public void createNewSale()
  {
    currentSale = new Order();
  }

  public Order getCurrentSale() {return currentSale;}
  
  public void endSale() 
  {
    currentSale.completeOrder();
  }
  
  public boolean enterItem(int id, int quantity) 
  {
    System.out.println("Entering Item");
    Item item = catalog.getItem(id);
    if (item == null) return false; 
    currentSale.addLineItem(item, quantity);
    System.out.println("Entered Item. Current Sale = "+currentSale);
    return true;
  }
  
  public void makePayment(String form, Double amount)
  {
    localPayment = new Payment(form, amount);
    if (currentSale.verifyPayment(localPayment))
    {
	System.out.println("localPayment VERIFIED");
      profitMade += currentSale.getTotal();
      updateInventory(currentSale);
      salesOfTheDay.add(currentSale);
    }
    else
    {
	System.out.println("localPayment NOTVERIFIED");
      localPayment = null;
      currentSale = null;
    }
  }
  
  protected void updateInventory(Order sale) //Lots of method calls, can be a bit slow.
  {
    if(sale != null) {
    	System.out.println(sale);
    	ArrayList <SalesLineItem> local= sale.getListFromOrder();
    	int quantityChange = 0;
    	for (SalesLineItem x : local)
    	{
    	  quantityChange = constantConnection.getInventoryByID(x.getItem().getItemID()) - x.getQuantity();
    	  constantConnection.updateQuantity(x.getItem().getItemID(),quantityChange);
    	}
    	}
  }
  
  public void cutConnection ()
  {
    this.constantConnection.disconnect();
  }
  
}
