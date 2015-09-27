import java.util.Date;
import java.util.Calender;
import java.util.ArrayList;
import java.util.List;

public class Order{

    private Date date;
    private Calender time;
    private double balance;
    private double total;
    private double payment;
    private double tax;
    private boolean complete;

    private List <SalesLineItem> saleslineitem = new ArrayList()<SalesLineItem>;


    public Order(){
        date = new Date();
        //date.toString()
        time = Calender.getinstance();
        //time.getTime()
        complete=false;
        balance = 0;
        
    }

    public double getBalance(){

        return balance;

    }

    public void completeOrder(){
        complete =true;
        total = balance + balance*getTax();

    }

    public boolean isComplete(){
        return complete;
    }

    public void addLineItem(SalesLineItem item, int price){

        saleslineitem.add(item);
        balance+=price;

    }

    public double getTotal(){

        return total;

    }

    public void makePayment(){
    
    }

    public double getTax(){
        tax=.06;
        return tax;
    
    }

}