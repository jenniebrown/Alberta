package alberta;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInterface {
    static Register reg = new Register();
    static Scanner scan = new Scanner(System.in);

    private static int verifyUser() {
        int tries = 0;
        int userType = -1;

        while (tries != 2)
        {
            System.out.print("Enter Employee ID: ");
            String employeeID = scan.next();
            System.out.print("Enter password: ");
            String password = scan.next();
            String passwordLookup = reg.constantConnection.getEmpPass(employeeID);

            if ((passwordLookup == null) || (!passwordLookup.equals(password)))
            {
                tries++;
                System.out.println ("Incorrect username or password. Please try again!");
                if (tries == 2)
                {
                    System.out.println ("Too many incorrect attempts! Goodbye!");
                    System.exit(0);
                }
            }
            else
            {
                String [] infoLookup = reg.constantConnection.getEmpData(employeeID);
                System.out.println("Welcome " + infoLookup[1] + " " + infoLookup[2]);
                userType = Integer.parseInt(infoLookup[0]);
                break;
            }
        }
        return userType;
    }

    protected static int getInt() { //method so we can get an int when needed
        while (!scan.hasNextInt()) {
            System.out.println("Enter a whole number");

            scan.next();
        }
        return scan.nextInt();
    }

    public static void main(String[] args) {
        int userType = verifyUser();
        boolean finish = false;
        do {
            if(userType == 1) {
                System.out.print("Select desired operation.\nEnter '1' for Process Sale, '2' for Process Rental, '3' for Return, '4' to Add/Manage Users, any key to logout: ");
            } else if(userType == 2) {
                System.out.print("Select desired operation.\nEnter '1' for Process Sale, '2' for Process Rental, '3' for Return, any key to logout: ");
            }

            String op = scan.next();
      //      System.out.println ("Testing Case and exit from cases: " + op);
            switch(op) {
//------------------------------ProcessSale-----------------------------------//
                case "1":
                    //process sale
                    System.out.println("Begin new Sale.");
                    OrderFacade of = new OrderFacade(reg);
                    boolean repeat = true;
                    //add items to rental
                    do {
                       System.out.print("Enter product upc, or any key to complete transaction: ");
                       if(!scan.hasNextInt()) {
                           scan.next();
                           repeat = false;
                       } else {
                           int upc = getInt();
                           //check if valid upc
                           while(!of.checkUPC(upc)) {
                               System.out.print("Invalid UPC. Try again: ");
                               upc = getInt();
                           }
                           System.out.print("Enter quantity: ");
                           int q = getInt();
                           while(q < 1) { // could change this to 1 because logically the sale shouldn't go through if they did not buy it
                               System.out.println("Invalid quantity: Try again: ");
                               q = getInt();
                           }
                           //add item to rental
                           of.enterOrderItem(upc, q);
                       }
                    } while(repeat);
                    //complete transaction and display order status
                    of.completeTransaction();


                    //take care of payment by choosing payment method
                    int paymentMethod;
                    do {
                        System.out.print("Enter payment method: Press 1 for cash or 2 for credit");
                        paymentMethod = scan.nextInt();
                        boolean complete;
                        switch(paymentMethod) {
                            case 1:
                               System.out.print("Enter amount tendered: ");
                                double amt;
                                //finish cash payment. create change yada yada
                                do {
                                	while (!scan.hasNextDouble()) {
                                		System.out.print("INVALID. Enter amount tendered: ");
                                		scan.next();
                                	}
                                    amt = scan.nextDouble();
                                    complete = of.createCashPayment(amt);
                                    if(!complete) {
                                        System.out.println("Insufficient funds. Try again: ");
                                    }
                                }while(!complete);
                                break;
                            case 2:
                                System.out.print("Enter credit card number: ");
                                String cardNumber;
                                //finish credit payment. verify card number, add customer
                                do {
                                    cardNumber = scan.next();
                                    complete = of.createPayment(cardNumber);
                                    if(!complete) {
                                        System.out.println("Payment not verified. Enter another card: ");
                                    }
                                }while(!complete);
                                break;
                            default:
                                System.out.println("Invalid payment method. Try again: ");
                                break;
                        }
                    } while (paymentMethod != 1 && paymentMethod != 2);

                    //once payment is complete, display receipt.
                    System.out.println("Transaction complete: ");
                    //update database
                    of.updateOrderHistory();
                    of.displayReceipt();

                    break;
//---------------------------------ProcessRental------------------------------//
                case "2":
                    System.out.println("Begin new rental.");
                    RentalFacade rf = new RentalFacade(reg);
                    repeat = true;
                    //add items to rental
                    do {
                       System.out.print("Enter product upc, or any key to complete transaction: ");
                       if(!scan.hasNextInt()) {
                           scan.next();
                           repeat = false;
                       } else {
                           int upc = scan.nextInt();
                           //check if valid upc
                           while(!rf.checkUPC(upc)) {
                               System.out.print("Invalid UPC. Try again: ");
                               upc = scan.nextInt();
                           }
                           System.out.print("Enter quantity: ");
                           int q = scan.nextInt();
                           while(q < 0) {
                               System.out.println("Invalid quantity: Try again: ");
                               q = scan.nextInt();
                           }
                           //add item to rental
                           rf.enterRentalItem(upc, q);
                       }
                    } while(repeat);
                    //complete transaction and display order status
                    rf.completeTransaction();


                    //take care of payment by choosing payment method
                    do {
                        System.out.print("Enter payment method: Press 2 for credit");
                        paymentMethod = scan.nextInt();
                        boolean complete;
                        switch(paymentMethod) {
                            case 2:
                                System.out.print("Enter credit card number: ");
                                String cardNumber = scan.next();
                                //finish credit payment. verify card number, add customer
                                do {
                                    complete = rf.createPayment(cardNumber);
                                }while(!complete);
                                break;
                            default:
                                System.out.println("Invalid payment method. Rentals must use credit.");
                                break;
                        }
                    } while (paymentMethod != 2);

                    //once payment is complete, display receipt.
                    System.out.println("Transaction complete: ");
                    rf.updateRentalHistory();
                    rf.displayReceipt();

                    break;
//-----------------------------------Return-----------------------------------//
                case "3":
//                    try {
                        int findOrderAttempts = 0;
                        System.out.println ("Begin Return Process. Please have receipt ready");
                        ReturnFacade returnFac = new ReturnFacade(reg);
                        //Process Return
                        System.out.println ("If returning a rental, press 0. If "
                            + "returning a defective order, press 1. If returning "
                            + "an unwanted order, press 2.");
                        int retType = getInt();
                        System.out.println("Please enter the orderID number from your receipt:");
                        int oID = getInt();

                        returnFac.setROrderID(oID);
                        returnFac.createReturn();
                        returnFac.setReturnType(retType);
                        //This loop will not allow a person to continue return unless the order existed
                        while (!(repeat = reg.verifyPreviousPurchase(oID,returnFac.getReturn())))
                        {
                            System.out.println("Could not verify purchase. Please try again");
                            if (findOrderAttempts == 2)
                            {
                                System.out.println("Too many attempts to find order in history. Ending transaction");
                                break; ///Need to find a way out properly. This crashes it if too many fails
                            }
                            oID = getInt();
                            returnFac.setROrderID(oID);
                            returnFac.createReturn(); //The previous return type and new returnOrderID is use.
                            findOrderAttempts++;
                        }

                        while(repeat) {
                            System.out.print("Enter product upc of item on receipt"
                                + ", or any non-number key to complete transaction: ");
                            if(!scan.hasNextInt()) {
                                scan.next();
                                repeat = false;
                            } else {
                                int upc = getInt();
                                //check if valid upc
                                while(returnFac.checkUPCAgainstHistory(upc)) { 
                                    System.out.print("Invalid UPC. Try again: "); 
                                    upc = getInt();
                                }
                                System.out.print("Enter quantity: "); 
                                int q = getInt();
                                while(!returnFac.checkQuantity(q) || q < 1) {
                                    System.out.println("Invalid quantity: Try again: "); 
                                    q = getInt();
                                }
                                //add item to return
                                returnFac.enterOrderItem(upc, q);
                            }
                        }
//                        if (reg.verifyPreviousPurchase(oID))
//                        {
//                            //returnFac.setDate(dateOrder);
//                            returnFac.setROrderID(oID);
//                            returnFac.createReturn();
//                            System.out.println ("If returning a rental, press 0. If "
//                                    + "returning a defective order, press 1. If returning"
//                                    + "an unwanted order, press 2.");
//                            returnFac.setReturnType(scan.nextInt());
//                            repeat = true;
                    //add items to return
//                    do {
//                       System.out.print("Enter product upc of item on receipt"
//                               + ", or any non-number key to complete transaction: ");
//                       if(!scan.hasNextInt()) {
//                           scan.next();
//                           repeat = false;
//                       } else {
//                           int upc = scan.nextInt();
//                           //check if valid upc
//                           while(!returnFac.checkUPC(upc)) {
//                               System.out.print("Invalid UPC. Try again: ");
//                               upc = scan.nextInt();
//                           }
//                           System.out.print("Enter quantity: ");
//                           int q = scan.nextInt();
//                           while(q < 0) {
//                               System.out.println("Invalid quantity: Try again: ");
//                               q = scan.nextInt();
//                           }
//                           //add item to rental
//                           returnFac.enterOrderItem(upc, q);
//                       }
//                    } while(repeat);
                    returnFac.completeTransaction();
                    returnFac.processReturn();
                    returnFac.displayReceipt();
                        //}
//                        else
//                            System.out.println("Incorrect order ID. Ending Return Transaction");
                   /* } catch (InputMismatchException i)
                    {
                        System.out.println("Input not allowed, ending return transaction");
                        break;
                    }*/
                    break;
//------------------------------------UserManagement--------------------------//
                case "4":
                    if (userType != 1) if ((userType = verifyUser()) != 1) break;
                    //user manage
                    System.out.print("Add, Delete, or View: ");
                    String val = scan.next().toLowerCase();
                    if (val.equals("delete")) {
                        System.out.print("ID: ");
                        int id = scan.nextInt();
                        System.out.println(); //prints new line
                        reg.constantConnection.removeEmployee(id);
                    } else if (val.equals("view")) {
                        for (String[] data : reg.constantConnection.getEmployees()) {
                            System.out.println("-------------------------------");
                            for (String i : data) System.out.println(i);
                        } System.out.println("-------------------------------");
                    } else {
                        System.out.print("First name: ");
                        String fname = scan.next();
                        System.out.print("Last name: ");
                        String lname = scan.next();
                        System.out.print("Email: ");
                        String email = scan.next();
                        System.out.print("ID: ");
                        int id = scan.nextInt();
                        System.out.print("Password: ");
                        String pass = scan.next();
                        System.out.print("User type (int): ");
                        int utype = scan.nextInt();

                        reg.constantConnection.addEmployee(fname, lname, email, id, pass, utype);
                    }

                    break;
                default:
                    finish = true;
            }
        } while (!finish);
        //TO-DO: logout procedure
        scan.close();
        reg.cutConnection();
        System.out.println("Logging out...");
        System.exit(0);
    }
}
