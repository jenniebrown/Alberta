package alberta;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RentalItemTest
{
    private RentalItem item;
    @Before
    public void setUp()
        throws Exception
    {
        item = new RentalItem("Test item", 1112, 34.99, 1);
    }


    @After
    public void tearDown()
        throws Exception
    {
    }


//    @Test
//    public void testRentalItem()
//    {
//        fail("Not yet implemented");
//    }


    @Test
    public void testGetRentalType()
    {
        assertEquals(1, item.getRentalType());
    }


    @Test
    public void testGetCanRent()
    {
        assertTrue(item.getCanRent());
    }


    @Test
    public void testGetTypeDesc()
    {
        assertEquals("Hourly",item.getTypeDesc());
    }


    @Test
    public void testUpdateRentType()
    {
        assertEquals(1, item.getRentalType());
        item.updateRentType(2);
        assertEquals(0, item.getRentalType());
        System.out.println("Rental Type updated successfully");
        assertFalse(item.getCanRent());
        System.out.println("Can Rent successfully updated.");
    }

}
