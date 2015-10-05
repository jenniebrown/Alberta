import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Order{

    private Date date;
    private double runningTotal;
    private double finalCost;
    private Payment payMe;
    private double taxTotal;
    private double taxRate;
    private boolean completed;

    private List <SalesLineItem> saleslineitems;


    public Order(){
        date = new Date();
        //date.toString()
        completed = false;
        runningTotal = 0;
        saleslineitems = new ArrayList()<SalesLineItem>;
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
        if (qty <= 0) {
            System.err.println("Did not enter quantity");
            return
        }

        SalesLineItem newItem = new SalesLineItem(current, qty);
        
        for (int i; qty > i; i++){
            saleslineitems.add(newItem);
            runningTotal+=newItem.getSubtotal();
        }

    }

    public double getTotal(){
        finalCost = runningTotal + taxTotal;
        return total;

    }

    public boolean verifyPayment(Payment payUp){
        if(iscomplete() && getTotal()== payUp.getAmount()){ //add machine implementation later
            payMe = payUp;
            return true;
        }
        else{
            double unpaidBalance = getTotal()-payUp.getAmount();
            System.out.println("Need to pay $"+unpaidBalance);
            
            finalCost-=payUp.getAmount();
            
        }
    }

    public void getTax(){
        if(isComplete()){
            taxTotal = runningTotal*taxRate;
        }
        //if called to early not sure where to shoot program back to
    
    }
    
    public ArrayList<SalesLineItem> getListFromOrder(){
        return saleslineitems;
    }
    
    

}