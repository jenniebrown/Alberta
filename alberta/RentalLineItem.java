package alberta;

public class RentalLineItem extends AbstractLineItem
{
    public RentalLineItem(RentalItem item, int quantity) {
        super(item,quantity);
    }

    public RentalLineItem(RentalItem item) {
        this(item,1);
    }
}
