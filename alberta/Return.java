package alberta;

import java.util.Date;
public class Return
{
  private Rental returningRental;
  private double lateFee;
  private Payment lateFeePaid;
  private boolean paidInFull;
  
  /* Constructor. Only required the Rental being returned and the Date it is being returned */
  public Return (Rental returning)
  {
    this.returningRental = returning;
    this.lateFee = 0.0; //later should be this.surcharge()
    this.paidInFull =  (this.lateFee == 0.0); //Should rely on surcharge
  }
  
  /* Getter Methods */
  public Rental getRental() { return returningRental;}
  public boolean isPaid() { return paidInFull;}
  public double getLateFee() {return lateFee;}
  
  /* This allows for payment to be made for the return. It will only accept full payment */
  public boolean pay (Payment payLateFee)
  {
    this.lateFeePaid = payLateFee;
    return this.paidInFull = true;
  }
  
  public void printReceipt()
  {
        //can add print statement for company name address and phone number
        System.out.println("Return Receipt");
        System.out.println("---------------------------------------------------");
        System.out.println("\t\tCompany Name");
        System.out.println("\t\tCompany Address");
        System.out.println("\t\tCompany Number");
        System.out.println("---------------------------------------------------");
        System.out.println();
        System.out.println(returningRental.date.toString());
        System.out.println();
        System.out.println("Item Description\tQuantity\tPrice");
        for(AbstractLineItem i : returningRental.items){
            System.out.println(i.getItem().getDescription()+ "\t("+ i.getQuantity() +"x)"+"\t\t $"+i.getSubtotal());
            if(i.getItem() instanceof RentalItem) {System.out.println("\t Due "+((RentalItem) i.getItem()).getDueDate());}
        }
        System.out.println("Return Complete");
  
        System.out.println();
        System.out.println();

    }
  }
  
  
  /* This method checks to see if the item was returned late. If it was, it applies a surcharge
   * 15% of the original rental fee per day. Go through the items ArrayList
  and check their dates
  public double surcharge ()
  {
    if (!this.dateDue.equals(this.dateReturned))
    {
      int daysBetween = TimeUnit.Milliseconds.toDays(this.dateReturned - 
              returningRental.date);
    }
    else
      return 0.0;
  }
  */


