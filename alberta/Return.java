package alberta;
import java.util.Date;
public class Return extends AbstractSale
{
    private int originalOrderID;
    private Date originalDate;
    private int rentalOrSale; //If 0 rental, 1 defective return, 2 regularSale return
    private double lateFee;
  
  /* Constructor */
  public Return (int originalOID)
  {
    super();
    this.originalOrderID = originalOID;
  }
  
  public int getOriginalOrderID () {return this.originalOrderID;}
  public void setOriginalOrderID (int originalOID) {this.originalOrderID = originalOID;}
  public double getLateFee() {return this.lateFee;}
  public void setLateFee(double lf) {this.lateFee = lf;}
  public Date getOriginalDate () {return this.originalDate;}
  public void setOriginalDate (Date oDate) {this.originalDate = oDate;}
  public void setRentalOrSale (int choice) {this.rentalOrSale = choice;}
  public int getRentalOrSale () {return this.rentalOrSale;}
  
  public void addItem(Item current, int qty){
        if (qty <= 0) {
                System.err.println("Did not enter quantity");
                return;
        }
        SalesLineItem newItem = new SalesLineItem(current, qty);
        setRunningTotal(newItem.getSubtotal()+getBalance());
        items.add(newItem);
    }

    public void addItem(Item i) {
        addItem(i, 1);
    }
    
    private double calcTransacTot() {
        setFinalTax(tc.calculateTax(items));
        return getFinalTax() + getBalance();
    }
    
    @Override
    public void completeTransaction()
    {
        setTransactionComplete(true);
        if (rentalOrSale != 0)
            setFinalTotal(calcTransacTot());
        else
            setFinalTotal (0.0);
    }

  @Override
  public void printReceipt()
  {
        //can add print statement for company name address and phone number
        System.out.println("Order ID For Return: " + orderID);
        System.out.println("Original purchase Order ID " + originalOrderID);
        System.out.println("Return Receipt");
        System.out.println("---------------------------------------------------");
        System.out.println("\t\tCompany Name");
        System.out.println("\t\tCompany Address");
        System.out.println("\t\tCompany Number");
        System.out.println("---------------------------------------------------");
        System.out.println("Date of Return: " + date);
        System.out.println("Original date of purchase: " + originalDate.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Item Description\tQuantity\t");
        for(AbstractLineItem i : items){
            System.out.println(i.getItem().getDescription()+ "\t("+ i.getQuantity() +"x)");
        }
        if (rentalOrSale != 0)
            System.out.println("Refund total: " + this.getFinalTotal());
        else
            System.out.println("Late fee charged to credit card: " + this.getLateFee());
        System.out.println("Return Complete");
        System.out.println();
        System.out.println();

    }
  }
  

