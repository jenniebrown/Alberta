
public class Register
{
  private ProductCatalog catalog;
  private Order currentSale;
  private ArrayList<Order> salesOfTheDay;
  private double profitMade;
  private Payment localPayment;
  
  public Register()
  {
    this.catalog = ProductCatalog.getInstance();
    this.currentSale = null;
    this.salesOfTheDay = new ArrayList <Order>();
    this.profitMade = 0.0;
    this.localPayment = null;
  }
  
  public void createNewSale()
  {
    currentSale = new Order();
  }
  
  public void endSale() //What should it do? Show the total? There is no way to close an order
  {
    currentSale.completeOrder();
  }
  
  public void enterItem(String id, int quantity) //Order should have a method that allows me to call on it's List<SalesLineItem>'s add()
  {
    currentSale.addLineItem(catalog.getItem(id), quantity);
  }
  
  public void makePayment(String form, Double amount)
  {
    localPayment = new Payment(form, amount);
    if (currentSale.verifyPayment(localPayment))
    {
      profitMade = currentSale.getTotal();
      updateInventory(currentSale);
      salesOfTheDay.add(currentSale);
    }
    else
    {
      localPayment = null;
      currentSale = null;
    }
  }
  
  public void updateInventory(Order currentSale)
  {
    
  }
}