package alberta;

import java.util.Scanner;

public class UserInterface
{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Do login:
        //get username
        System.out.print("Enter username: ");
        String username = scan.next();
        //TO-DO: verify username
        //get password
        System.out.print("Enter password: ");
        String password = scan.next();
        //TO-DO: verify password
        //return user

        //for now, let user be manager (type 1); DELETE THIS LATER
        int userType = 1;
        Register reg = new Register();
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
                    //return
                    break;
                case "4":
                    //Commented out until working. just so i can run the prog
//                    if(userType != 1) {
//                        System.out.println("Permission denied. Enter credentials to continue or q to quit.");
//                        System.out.print("username: ");
//                        String un = scan.next();
//                        if(un == "q" || un == "Q") {
//                            break;
//                        } else {
//                            //verify username
//                            System.out.print("password: ");
//                            String pw = scan.next();
//                            //verify password
//                            //if unverified, break;
//                        }
//
//                    }
//                    //user manage

                    break;
                default:
                    finish = true;
            }


        } while (!finish);

        //TO-DO: logout procedure
        reg.cutConnection();

    }
}
