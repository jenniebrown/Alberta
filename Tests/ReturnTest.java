/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alberta;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giancarlo
 */
public class ReturnTest {
 
    public ReturnTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRental method, of class Return.
     */
    @Test
    public void testGetRental() {
        System.out.println("getRental");
        Rental local = new Rental();
        Return instance = new Return (local);
        Rental expResult = local;
        Rental result = instance.getRental();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isPaid method, of class Return.
     */
    @Test
    public void testIsPaid() {
        System.out.println("isPaid");
        Rental local = new Rental();
        Return instance = new Return (local);
        boolean expResult = true;
        boolean result = instance.isPaid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getLateFee method, of class Return.
     */
    @Test
    public void testGetLateFee() {
        System.out.println("getLateFee");
        Rental local = new Rental();
        Return instance = new Return (local);
        double expResult = 0.0;
        double result = instance.getLateFee();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of pay method, of class Return.
     */
    @Test
    public void testPay() {
        System.out.println("pay");
        Payment payLateFee = new Payment("cash",0.0);
        Rental local = new Rental();
        Return instance = new Return (local);
        boolean expResult = true;
        boolean result = instance.pay(payLateFee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of printReceipt method, of class Return.
     */
    @Test
    public void testPrintReceipt() {
        System.out.println("printReceipt");
        Rental local = new Rental();
        Return instance = new Return (local);
        instance.printReceipt();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
