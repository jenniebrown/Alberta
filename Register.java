import java.util.ArrayList;
public class Register
{
  private ProductCatalog catalog;
  private Order currentSale;
  private ArrayList<Order> salesOfTheDay;
  private double profitMade;
  private Payment localPayment;
  private DatabaseHandler constantConnection;
  
  public Register()
  {
    this.catalog = ProductCatalog.getInstance();
    this.currentSale = null;
    this.salesOfTheDay = new ArrayList <Order>();
    this.profitMade = 0.0;
    this.localPayment = null;
    this.constantConnection = DatabaseHandler.connect();
  }
  
  public void createNewSale()
  {
    currentSale = new Order();
  }
  
  public void endSale() 
  {
    currentSale.completeOrder();
  }
  
  public void enterItem(String id, int quantity) 
  {
    currentSale.addLineItem(catalog.getItem(id), quantity);
  }
  
  public void makePayment(String form, Double amount)
  {
    localPayment = new Payment(form, amount);
    if (currentSale.verifyPayment(localPayment))
    {
      profitMade += currentSale.getTotal();
      updateInventory(currentSale);
      salesOfTheDay.add(currentSale);
    }
    else
    {
      localPayment = null;
      currentSale = null;
    }
  }
  
  public void updateInventory(Order sale) //Lots of method calls, can be a bit slow.
  {
    ArrayList <SalesLineItem> local= currentSale.getListFromOrder();
    int quantityChange = 0;
    for (SalesLineItem x : local)
    {
      quantityChange = constantConnection.getInventoryByID(x.getItem().getItemID()) - x.getQuantity();
      constantConnection.updateQuantity(x.getItem().getItemID(),quantityChange);
    }
  }
  
  public void cutConnection ()
  {
    this.constantConnection.disconnect();
  }
  
}