package alberta;

import java.util.Date;
public class Return
{
  private Rental returningRental;
  private double feeDue;
  private double lateFee;
  private Date dateRented;
  private Date dateDue;
  private Date dateReturned;
  private String renterName;
  private DatabaseHandler connection;
  private Payment receivedPayment;
  private boolean paidInFull;
  
  /* Constructor. Only required the Rental being returned and the Date it is being returned */
  public Return (Rental returning, Date returned)
  {
    this.returningRental = returning;
    this.feeDue = returningRental.getFinalTotal();
    this.lateFee = 0.0;
    this.dateRented = returningRental.date; //I hope this is right
    this.dateDue = Rental
    this.dateReturned = returned;
    this.renterName = returning.getRenter();
    this.receivedPayment = null;
    this.paidInFull = false;
    this.connection = DatabaseHandler.connect();
  }
  
  /* Getter Methods */
  public Rental getRental() { return returningRental;}
  public double getFeeDue() { return feeDue;}
  public Date getDateRented() { return dateRented;}
  public Date getDateDue() { return dateDue;}
  public Date getDateReturned() { return dateReturned;}
  public String getRenterName() { return renterName;}
  public boolean isPaid() { return paidInFull;}
  
  /* This allows for payment to be made for the return. It will only accept full payment */
  public boolean pay (Payment payForReturn)
  {
    if (payForReturn.getAmount() == (this.feeDue + this.lateDue))
    {
      this.paidInFull = true;
      this.receivedPayment = payForReturn;
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /* This method checks to see if the item was returned late. If it was, it applies a surcharge
   * 15% of the original rental fee per day. */
  public double surcharge ()
  {
    if (!this.dateDue.equals(this.dateReturned))
    {
      //Compare days difference, * .15
    }
    else
      return 0.0;
  }
   
  /* Generally called after rental is made. Will update database, close connection.
   * Note that a rental might not necessarily be paid off
   */
  public void updateRentalDatebase()
  {
    //Need to know how to update database, if one is available
    connection.disconnect();
  }
}

