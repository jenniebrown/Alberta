public class Register
{
  private ProductCatalog catalog;
  private Order currentSale;
  private int salesOfTheDay;
  private int totalMade;
  public Register()
  {
    catalog = ProductCatalog.getInstance();
    currentSale = new Order();
  }
  
  public void createNewSale()
  {
    currentSale = new Order();
  }
  
  public void endSale() //What should it do? Show the total? There is no way to close an order
  {
    currentSale.completeOrder();
  }
  
  public void enterItem() //Order should have a method that allows me to call on it's List<SalesLineItem>'s add()
  {
    
  }
  
  public void makePayment()
  {
  }
  
  public void updateInventory()
  {
    
  }
}