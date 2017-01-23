package alberta;

public class AddUsers
{
    public static void main(String[] args) {
        DatabaseHandler db = DatabaseHandler.connect();
        db.addEmployee("Giancarlo", "Sanguinetti", "gisan15@error.net", 10001, "gspass", 1);
        db.addEmployee("Jacob", "Schecter", "jasc15@error.net", 10002, "jspass", 1);
        db.addEmployee("Bruke", "Mammo", "brma15@error.net", 10003, "bmpass", 1);
        db.addEmployee("Jennie", "Brown", "jebr15@error.net", 11047, "jbpass", 1);
        db.addEmployee("Bill", "Bailey", "biba@error.net", 10004, "bbpass", 2);
        db.addEmployee("Luke", "Skywalker", "thedarkside@starwars.net", 666666, "notyourfather", 2);

        db.addCustomer("Kanye", "West", "imthebest@betterthanyou.com", "5683920581058403");

    }
}
