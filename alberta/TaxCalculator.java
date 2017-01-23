package alberta;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class TaxCalculator implements Iterator<Item>
{
    public TaxCalculator() {

    }
    public abstract double calculateTax(ArrayList<Item> items);

}
