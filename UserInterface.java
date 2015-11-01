package alberta;

import java.util.Scanner;

public class UserInterface
{
    public void main(String[] args) {
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

        boolean finish = false;
        do {
            if(userType == 1) {
                System.out.print("Select desired operation. Enter '1' for Process Sale, '2' for Process Rental, '3' for Return, '4' to Add/Manage Users, any key to logout");
            } else if(userType == 2) {
                System.out.print("Select desired operation. Enter '1' for Process Sale, '2' for Process Rental, '3' for Return, any key to logout");
            }

            String op = scan.next();
            switch(op) {
                case "1":
                    //process sale
                    break;
                case "2":
                    //process rental

                    break;
                case "3":
                    //return
                    break;
                case "4":
                    if(userType != 1) {
                        System.out.println("Permission denied. Enter credentials to continue or q to quit.");
                        System.out.print("username: ");
                        String un = scan.next();
                        if(un == "q" || un == "Q") {
                            break;
                        } else {
                            //verify username
                            System.out.print("password: ");
                            String pw = scan.next();
                            //verify password
                        }
                        break;
                    } else {
                    //user manage
                    }
                    break;
                default:
                    finish = true;
            }


        } while (!finish);

        //TO-DO: logout procedure

    }
}
