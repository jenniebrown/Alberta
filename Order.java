import java.util.Date;
import java.util.Calender;
import java.util.ArrayList;
import java.util.List;

public class Order{

    private Date date;
    private Calender time;
    private int balance;
    private int total;
    private int payment;
    private int tax=.06;
    private boolean complete;

    private List <SalesLineItem> saleslineitem = new ArrayList()<SalesLineItem>;


    public Order(){
        date = new Date();
        //date.toString()
        time = Calender.getinstance();
        //cal.getTime()
        complete=false;
        
    }

    public int getBalance(){

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

    public int getTotal(){

        return total;

    }

    public void makePayment(){
    
    }

    public int getTax(){
    
        return tax;
    
    }

}