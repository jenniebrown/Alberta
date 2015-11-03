package alberta;

import java.util.Date;
import java.util.ArrayList;

public class Order extends AbstractSale{


    //private Payment payMe;
    //private ArrayList <AbstractLineItem> saleslineitems;


    public Order(){
        //saleslineitems = new ArrayList<AbstractLineItem>();
    }


//----------------------------Getters&Setters---------------------------------//
    public ArrayList<AbstractLineItem> getListFromOrder(){return items;}

//----------------------------Methods-----------------------------------------//
    public void addItem(Item current, int qty){
        if (qty <= 0) {
                System.err.println("Did not enter quantity");
                return;
        }
        SalesLineItem newItem = new SalesLineItem(current, qty);
        /*
            for (int i; qty > i; i++){
                saleslineitems.add(newItem);
                runningTotal+=newItem.getSubtotal();
            }
            */
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
//This is the equivalent method to calcTransacTot
//    public double getTotal(){
//        finalCost = runningTotal + taxTotal;
//	//System.out.println("Final Cost (tax+total) = "+finalCost);
//        return finalCost;
//
//    }

//    public boolean verifyPayment(Payment payUp){
//        completeTransaction();
//        if(this.isComplete()){// && this.getTotal()== payUp.getAmount()){ //add machine implementation later
//            payMe = payUp;
//            System.out.println("Order Total = "+ getFinalTotal());
//            return true;
//        }
//        else{
//            double unpaidBalance = getFinalTotal()-payUp.getAmount();
//            System.out.println("Need to pay $"+unpaidBalance);
//
//            finalCost-=payUp.getAmount();
//            return false;
//        }
//    }


    @Override
    public void completeTransaction()
    {
        setTransactionComplete(true);
        setFinalTotal(calcTransacTot());
        System.out.print(this.toString());
        System.out.println("Total tax: \t"+getFinalTax());
        System.out.println("Balance due: \t"+getFinalTotal());

    }

    public String toString() {
        StringBuffer r = new StringBuffer();
        AbstractLineItem it;
        for(int i = 0; i < items.size(); i++) {
            it = items.get(i);
            r.append(it.getItem().getDescription());
            r.append("\t "+it.getQuantity());
            r.append("\t"+it.getItem().getPrice());
            r.append("\n\t\t\t"+it.getSubtotal());
            r.append("\n");
        }
        String result = r.toString();
        return result;
    }
}
