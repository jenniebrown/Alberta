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
        setRunningTotal(newItem.getSubtotal());
        items.add(newItem);
    }

    public void addItem(Item i) {
        addItem(i, 1);
    }

//    public void completeTransaction(int paymentMethod){
//        setTransactionComplete(true);
//        setFinalTotal(calcTransacTot());
//        payMe = new Payment(paymentMethod, getFinalTotal());
//
//    }

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


//    public void getTax(){
//        if(isComplete()){
//            taxTotal = runningTotal*taxRate;
//        }
//        //if called to early not sure where to shoot program back to
//
//    }

//    public ArrayList<AbstractLineItem> getListFromOrder(){
//	//for(SalesLineItem i : saleslineitems){
//	//	System.out.println(i);
//    	//}
//	return items;
//    }


    public ArrayList<AbstractLineItem> getListFromOrder(){
	//for(SalesLineItem i : saleslineitems){
	//	System.out.println(i);
    	//}
	return items;
    }




}
