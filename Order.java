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

    private List <SalesLineItem> saleslineitem;


    public Order(){
        date = new Date();
        //date.toString()
        complete=false;
        runningTotal = 0;
        saleslineitem = new ArrayList()<SalesLineItem>;
        taxRate = .06;
        
    }

    public double getBalance(){

        return runningTotal;

    }

    public void completeOrder(){
        complete =true;
        getTax();
        getTotal();
    }

    public boolean isComplete(){
        return completed;
    }

    public void addLineItem(Item current, int qty){
        int counter =0;
        
        SalesLineItem newItem = new SalesLineItem(current, qty);
        
        while(qty>counter){
            saleslineitem.add(newItem);
            runningTotal+=newItem.getSubtotal();
            counter++;
        }
        if(counter == 0){
            System.out.println("Did not enter quantity");
        }

    }

    public double getTotal(){
        finalCost = runningTotal + taxTotal;
        return total;

    }

    public boolean verifyPayment(Payment payUp){
        if(iscomplete() && getTotal()== payUp.getAmount() && !!verify credit!!){
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
    
    public ArrayList<SalesLineItems> getListFromOrder(){
    
        return saleslineitem;
        
    }
    
    

}