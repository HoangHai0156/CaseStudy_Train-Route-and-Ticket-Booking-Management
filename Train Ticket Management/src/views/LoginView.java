package views;

import model.Customer;
import service.CustomerService;
import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Map;
import java.util.Scanner;

public class LoginView {
    private Scanner scanner = new Scanner(System.in);
    private ManageCustomerView manageCustomerView;
    private CustomerService customerService;
    public LoginView(){}
    public Customer login(){
        boolean isIncorrectLogin;
        String username;
        String passWord;
        Customer customer = null;

        do {
            manageCustomerView = new ManageCustomerView();
            customerService = new CustomerService(manageCustomerView.getCustomerList());
            Map<String,String> listAccount = customerService.getAccountList();

            isIncorrectLogin = false;
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║-------------------"+ PaintUtils.setRed("Đăng nhập") +"-------------------------║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║Vui lòng nhập tên người dùng và mật khẩu để tiếp tục ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.print("  "+PaintUtils.setOrange("Tên người dùng:")+" ");
            username = scanner.nextLine();
            System.out.print("  "+PaintUtils.setOrange("Mật khẩu:")+" ");
            passWord = scanner.nextLine();
            System.out.println("╚═════════════════════════════════════════════════════╝");

            if (listAccount.containsKey(username)){
                if (passWord.equals(listAccount.get(username))){
                    customer = customerService.getCustomerByUserName(username);
                }else {
                    System.out.println("Mật khẩu không đúng. Vui lòng nhập lại");
                    isIncorrectLogin = true;
                }
            }else {
                System.out.println("Người dùng không tồn tại.");
                boolean isConfirm = ActionUtils.getConfirm("tạo mới tài khoản");
                if (isConfirm){
                    manageCustomerView.addCustomerView();
                    System.out.println(PaintUtils.setGreen("Tạo thành công. Vui lòng đăng nhập lại"));
                    isIncorrectLogin = true;
                }else {
                    System.out.println("Vui lòng nhập lại tài khoản và mật khẩu");
                    isIncorrectLogin = true;
                }
            }
        }while (isIncorrectLogin);

        return customer;
    }
}
