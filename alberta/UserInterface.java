package alberta;

import java.util.Scanner;

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
    
    public static void main(String[] args) {
        int userType = verifyUser();
        boolean finish = false;
        do {
            if(userType == 1) {
                System.out.print("Select desired operation.\nEnter '1' for Process Sale, '2' for Process Rental, '3' for Return, '4' to Add/Manage Users, any key to logout");
            } else if(userType == 2) {
                System.out.print("Select desired operation.\nEnter '1' for Process Sale, '2' for Process Rental, '3' for Return, any key to logout");
            }

            String op = scan.next();
            switch(op) {
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
                           int upc = scan.nextInt();
                           //check if valid upc
                           while(!of.checkUPC(upc)) {
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
                                double amt = scan.nextDouble();
                                //finish cash payment. create change yada yada
                                do {
                                    complete = of.createCashPayment(amt);
                                }while(!complete);
                                break;
                            case 2:
                                System.out.print("Enter credit card number: ");
                                String cardNumber = scan.next();
                                //finish credit payment. verify card number, add customer
                                do {
                                    complete = of.createPayment(cardNumber);
                                }while(!complete);
                                break;
                            default:
                                System.out.println("Invalid payment method. Rentals must use credit.");
                                break;
                        }
                    } while (paymentMethod != 1 && paymentMethod != 2);

                    //once payment is complete, display receipt.
                    System.out.println("Transaction complete: ");
                    of.displayReceipt();

                    break;
                case "2":
                    //process rental
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
                    rf.displayReceipt();

                    break;
                case "3":
                    System.out.println ("Begin Return Process");
                    ReturnFacade retFac = new ReturnFacade(reg);
                    repeat = true;
                    //Process Return
                    do {
                       System.out.print("Enter product upc of item being returned,"
                               + " or any key to complete transaction: ");
                       if(!scan.hasNextInt()) {
                           scan.next();
                           repeat = false;
                       } else {
                           int upc = scan.nextInt();
                           //check if valid upc
                           while(!retFac.checkUPC(upc)) {
                               System.out.print("Invalid UPC. Try again: ");
                               upc = scan.nextInt();
                           }
                       }
                    } while(repeat);
                    //complete transaction and display order status
                    retFac.completeTransaction();
                    retFac.displayReceipt();
                    //return
                    break;
                case "4":
                    if (userType != 1) if ((userType = verifyUser()) != 1) break;
                    //user manage
                    System.out.print("Add or Delete: ");
                    String val = scan.next().toLowerCase();
                    if (val.equals("delete")) {
                        System.out.print("ID: ");
                        int id = scan.nextInt();
                        reg.constantConnection.removeEmployee(id);
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
