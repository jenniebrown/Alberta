package alberta;

public abstract class AbstractItem
{
    private String description;
    private int itemID;
    private double price;
    private int rentalType;

    public AbstractItem(String description, int itemID, double price){
        this.description = description;
        this.itemID = itemID;
        this.price = price;
        this.rentalType = 1;

      }

      public String getDescription() {return this.description;}

      protected void updateDescription(String desc) {this.description = desc;}

      public int getItemID(){
        return this.itemID;
      }

      public int getRentalType() {return rentalType;}

      public void setRentalType(int i) {this.rentalType = i;}

      public double getPrice(){
        return this.price;
      }
}
