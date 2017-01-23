package alberta;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseHandlerTest
{
    static DatabaseHandler db;
    @BeforeClass
    public static void setUpBeforeClass()
        throws Exception
    {
        db = DatabaseHandler.connect();
    }


    @AfterClass
    public static void tearDownAfterClass()
        throws Exception
    {
        db.disconnect();
    }


    @Before
    public void setUp()
        throws Exception
    {
    }


    @After
    public void tearDown()
        throws Exception
    {
    }


    @Test
    public void testGetEmpInfo()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testGetCustInfo()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testGetCatalog()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testGetFullCatalog()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testGetInventoryByID()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testUpdateQuantity()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testUpdatePrice()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testAddOrderToHistoryIntStringDoubleStringStringString()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testAddCustomer()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testAddEmployee()
    {
        fail("Not yet implemented");
    }

}
