import java.util.List;
public class ProductCatalog 
{
  private List<Item> storeCatalog;
  private static ProductCatalog onlyCatalog = new ProductCatalog();
  
  private ProductCatalog()
  {
    this.storeCatalog = new List<Item>(); //Needs List that is not abstract. Should create it from database
  }
  
  public double getPrice() //Needs to know how items will be stored in list.
  {
    
  }
  
  public int getId() //Same 
  {
  }
  
  public String getDescription() //Same
  {
    
  }
  
  public static synchronized ProductCatalog getInstance()
  {
    return onlyCatalog;
  }
}