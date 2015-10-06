import java.util.Date;
import java.util.ArrayList;

public class Order{

    public Date date;
    private double runningTotal;
    private double finalCost;
    private Payment payMe;
    private double taxTotal;
    private double taxRate;
    private boolean completed;

    private ArrayList <SalesLineItem> saleslineitems;


    public Order(){
        date = new Date();
        System.out.println(date);
	//date.toString()
        completed = false;
        runningTotal = 0;
        saleslineitems = new ArrayList<SalesLineItem>();
        taxRate = 0.06;
        
    }

    public double getBalance(){
        return runningTotal;
    }

    public void completeOrder(){
        completed =true;
        getTax();
        getTotal();
    }

    public boolean isComplete(){
        return completed;
    }

    public void addLineItem(Item current, int qty){
        System.out.println("Entered addLineItem in SalesLineItem");
	if (qty <= 0) {
            System.err.println("Did not enter quantity");
            return;
        }
	System.out.println("Making newItem");
        SalesLineItem newItem = new SalesLineItem(current, qty);
        System.out.println("Made newItem");
	/*
        for (int i; qty > i; i++){
            saleslineitems.add(newItem); 
            runningTotal+=newItem.getSubtotal();
        }
        */
        runningTotal += newItem.getSubtotal();
	System.out.println("Running Total = "+runningTotal);
        System.out.println("Adding newItem");
	saleslineitems.add(newItem);
    	System.out.println("Added newItem: "+saleslineitems);
    }

    public double getTotal(){
        finalCost = runningTotal + taxTotal;
	//System.out.println("Final Cost (tax+total) = "+finalCost);
        return finalCost;

    }

    public boolean verifyPayment(Payment payUp){
	completeOrder();       
	if(this.isComplete()){// && this.getTotal()== payUp.getAmount()){ //add machine implementation later
            payMe = payUp;
	    System.out.println("Order Total = "+ getTotal());
            return true;
        }
        else{
            double unpaidBalance = getTotal()-payUp.getAmount();
            System.out.println("Need to pay $"+unpaidBalance);
            
            finalCost-=payUp.getAmount();
            return false;
        }
    }

    public void getTax(){
        if(isComplete()){
            taxTotal = runningTotal*taxRate;
        }
        //if called to early not sure where to shoot program back to
    
    }
    
    public ArrayList<SalesLineItem> getListFromOrder(){
        System.out.println("in getListFromOrder()");
	for(SalesLineItem i : saleslineitems){
		System.out.println(i);
    	}
	return saleslineitems;
    } 

}
