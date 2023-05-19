package views;

import model.Customer;
import model.ECustomerType;
import service.CustomerService;
import utils.ActionUtils;
import utils.DateUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManageCustomerView {
    public static Scanner scanner = new Scanner(System.in);
    private static List<Customer> customerList;
    private static CustomerService customerService;
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

            customerList = getCustomerList();
            customerService = new CustomerService(getCustomerList());

            switch (manageCustomerAction){
                case 1:
                    customerService.showCustomerList();
                    break;
                case 2:
                    addCustomerView();
                    break;
                case 3:
                    int customerEditId = customerService.getInputCustomerId();
                    editCustomerById(customerEditId);
                    break;
                case 4:
                    int customerRemoveId = customerService.getInputCustomerId();
                    removeCustomerById(customerRemoveId);
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    private void removeCustomerById(int customerRemoveId) {
        boolean isConfirm = ActionUtils.getConfirm("xóa khách hàng này");

        if (isConfirm){
            customerList.remove(customerService.getIndexByCustomerId(customerRemoveId));
            FileUtils.writeDataToFile(customerList,fileCustomerPath);
        }
    }

    protected void editCustomerById(int customerId) {

        boolean continueCheck = true;

        do {
            int editCustomerAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║        THICK MENU - Sửa thông tin khách hàng        ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Sửa tên                                       ║");
                System.out.println("║ ▶ 02. Sửa mật khẩu                                  ║");
                System.out.println("║ ▶ 03. Sửa ngày sinh                                 ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                editCustomerAction = ActionUtils.intHandleInput("option");
            }while (editCustomerAction < 0 || editCustomerAction > 3);

            customerList = getCustomerList();
            customerService = new CustomerService(getCustomerList());

            switch (editCustomerAction){
                case 1:
                    editCustomerNameView(customerId);
                    FileUtils.writeDataToFile(customerList,fileCustomerPath);
                    break;
                case 2:
                    editPasswordView(customerId);
                    FileUtils.writeDataToFile(customerList,fileCustomerPath);
                    break;
                case 3:
                    editCustomerDOBView(customerId);
                    FileUtils.writeDataToFile(customerList,fileCustomerPath);
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);

        System.out.println(PaintUtils.setGreen("Sửa thành công!"));
    }

    private void editCustomerDOBView(int customerId) {
        Date newDOB = customerService.getInputDOB();
        int age = DateUtils.getAgeByDOB(newDOB);
        ECustomerType eCustomerType = ECustomerType.getECustomerTypeByAge(age);

        customerList.get(customerService.getIndexByCustomerId(customerId)).setDOB(newDOB);
        customerList.get(customerService.getIndexByCustomerId(customerId)).setAge(age);
        customerList.get(customerService.getIndexByCustomerId(customerId)).seteCustomerType(eCustomerType);
    }

    private void editPasswordView(int customerId) {
        String newPassword = customerService.getInputPassword();
        customerList.get(customerService.getIndexByCustomerId(customerId)).setPassWord(newPassword);
    }

    private void editCustomerNameView(int customerId) {
        String newName = customerService.getInputCustomerName();
        customerList.get(customerService.getIndexByCustomerId(customerId)).setName(newName);
    }

    protected void addCustomerView() {
        customerList = getCustomerList();
        customerService = new CustomerService(getCustomerList());

        Customer customer = customerService.createCustomer();

        customerList.add(customer);
        FileUtils.writeDataToFile(customerList,fileCustomerPath);

        System.out.println(PaintUtils.setGreen("Tạo thành công!"));
    }

}
