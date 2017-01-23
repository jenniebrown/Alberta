package alberta;

import java.util.ArrayList;

public class GenericTaxCalculator
{
    private double taxRate;
    public GenericTaxCalculator(double taxRate) {
        this.taxRate = taxRate;

    }

    public double calculateTax(ArrayList<AbstractLineItem> items) {
        double totalTax = 0;
        for(int i = 0; i < items.size(); i++) {
            AbstractLineItem current = items.get(i);
            totalTax += current.getSubtotal()*taxRate;
        }
        return totalTax;
    }


}
