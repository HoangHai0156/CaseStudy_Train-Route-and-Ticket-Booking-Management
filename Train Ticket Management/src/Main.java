import model.Customer;
import utils.DateUtils;
import views.AdminView;
import views.CustomerView;
import views.LoginView;

import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        Customer customer = loginView.login();

        if (customer.geteRole().name().equals("ADMIN")) {
            AdminView adminView = new AdminView();
            adminView.launcher();
        } else {
            CustomerView customerView = new CustomerView(customer);
            customerView.launcher();
        }
    }
}