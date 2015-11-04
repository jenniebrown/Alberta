package alberta;
import java.util.Date;

public class RentalItem extends AbstractItem
{
    private int rentalType;
    private boolean canRent;
    private Date dueDate;

    public RentalItem(String desc, int itemID, double price, int rentalType) {
        super(desc,itemID,price);
        this.rentalType = rentalType;
        this.updateDescription(desc+" ("+getTypeDesc()+")");
        canRent = checkCanRent();
        dueDate = null;
    }

    public RentalItem() {
        super(null,0,0);
    }

    public int getRentalType() {return rentalType;}

    public boolean getCanRent() {return canRent;}

    public Date getDueDate() {return dueDate;}

    public String getRentalDescription() {
        String rentalDescription = this.getDescription()+ ", "+this.getTypeDesc()+" rental";
        return rentalDescription;
    }

    public String getTypeDesc() {
        switch(rentalType) {
            case 0:
                return "Non-rental";
            case 1:
                return "Hourly";
            case 2:
                return "Daily";
            case 3:
                return "Weekly";
            case 4:
                return "Monthly";
            default:
                System.err.println("rentalType value " + rentalType +" invalid.");
                return null;
        }
    }

    public void setDueDate(Date d) {
        this.dueDate = d;
    }

    public void updateRentType(int type) {
        switch(type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                rentalType = type;
                System.out.println("Item "+ this.getItemID()+ " rental type set to " + this.getTypeDesc());
                canRent = checkCanRent();
            default:
                System.out.println("Invalid rental type. Item " + this.getItemID() + " rental type set to default.");
                rentalType = 0;
                canRent = checkCanRent();
        }
    }

    private boolean checkCanRent() {
        switch(rentalType) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }
}
