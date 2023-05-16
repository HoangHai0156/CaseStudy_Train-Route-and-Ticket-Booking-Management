package views;

import model.Customer;
import utils.ActionUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.List;
import java.util.Scanner;

public class ManageCustomerView {
    public static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customerList;
    private final static String fileCustomerPath = "./Data/customer.csv";
    public ManageCustomerView(){
        customerList = FileUtils.readDataFromFile(Customer.class,fileCustomerPath);
    }
    public List<Customer> getCustomerList(){
        customerList = FileUtils.readDataFromFile(Customer.class,fileCustomerPath);
        return customerList;
    }
    public String getFileCustomerPath(){
        return fileCustomerPath;
    }
    public void launcher(){
        boolean continueCheck = true;

        do {
            int manageCustomerAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý khách hàng        ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách KH                         ║");
                System.out.println("║ ▶ 02. Thêm khách hàng                               ║");
                System.out.println("║ ▶ 03. Sửa thông tin khách hàng theo ID              ║");
                System.out.println("║ ▶ 04. Xóa khách hàng                                ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageCustomerAction = ActionUtils.intHandleInput("option");
            }while (manageCustomerAction < 0 || manageCustomerAction > 4);
            switch (manageCustomerAction){
                case 1:
                    showCustomerListView();
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    private void showCustomerListView() {

    }
}
