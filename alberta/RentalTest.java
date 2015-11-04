package alberta;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RentalTest
{
    Rental rent;
    Date date;
    Calendar cal;
    RentalItem it1;
    RentalItem it2;
    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception
    {
    }


    @AfterClass
    public static void tearDownAfterClass()
        throws Exception
    {
    }


    @Before
    public void setUp()
        throws Exception
    {
        rent = new Rental();
        date = rent.date;
        cal = rent.cal;
        it1 = new RentalItem("Item 1", 1111, 15, 1);
        it2 = new RentalItem("Item 2", 1112, 3, 2);
        rent.addItem(it1);
        rent.addItem(it2, 2);
    }


    @After
    public void tearDown()
        throws Exception
    {
    }
    @Test
    public void testCompleteTransaction()
    {
        rent.completeTransaction();
        assertTrue("boolean field transactionComplete incorrect.",rent.isComplete());
        assertEquals("Final tax incorrect", 1.26, rent.getFinalTax(), 0.001 );
        assertEquals("Final total incorrect", 22.26, rent.getFinalTotal(), 0.001);
    }


    @Test
    public void testAddItemRentalItemInt()
    {
        Rental r = new Rental();
        RentalItem i = new RentalItem("Item 3", 1113, 20.00, 3);
        RentalItem j = new RentalItem("Item 4", 1114, 15.00, 1);
        r.addItem(i);
        Calendar c = r.cal;
        c.add(c.DAY_OF_YEAR,7);
        Date tdate = c.getTime();
        c.setTime(r.date);
        c.add(c.HOUR, 4);
        Date t2date = c.getTime();
        c.setTime(r.date);
        assertEquals(r.getItems().size(), 1, 0.001);
        assertEquals("Item 3 (Weekly)",r.getItems().get(0).getItem().getDescription());
        assertEquals(tdate.toString(), ((RentalItem)r.getItems().get(0).getItem()).getDueDate().toString());

    }








    @Test
    public void testGetItems()
    {
        ArrayList<AbstractLineItem> test = new ArrayList<AbstractLineItem>();
        RentalLineItem i1 = new RentalLineItem(it1,1);
        RentalLineItem i2 = new RentalLineItem(it2, 2);
        test.add(i1);
        test.add(i2);
        ArrayList<AbstractLineItem> rlist = rent.getItems();
        for(int i = 0; i < test.size(); i++) {
            assertEquals(test.get(i).getItem().getDescription(),rlist.get(i).getItem().getDescription());
            assertEquals(test.get(i).getItemCost(),rlist.get(i).getItemCost(),.001);
            assertEquals(test.get(i).getQuantity(),rlist.get(i).getQuantity());
            assertEquals(test.get(i).getSubtotal(),rlist.get(i).getSubtotal(),.001);
        }

    }

    @Test
    public void testVerifyPayment()
    {
        rent.completeTransaction();
        Payment p1 = new Payment("1234123412341234",22.26);
        Payment p2 = new Payment(22.26);
        Payment p3 = new Payment("asdjf",30.00);
        Payment p4 = new Payment(20.00);
        assertTrue(rent.verifyPayment(p1));
        assertTrue(rent.verifyPayment(p2));
        assertFalse(rent.verifyPayment(p3));
        assertFalse(rent.verifyPayment(p4));

    }


}
