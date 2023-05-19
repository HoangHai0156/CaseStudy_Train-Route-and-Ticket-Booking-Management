package service;

import model.Customer;
import model.ECustomerType;
import model.ERole;
import utils.ActionUtils;
import utils.DateUtils;
import utils.ValidateUtils;

import java.util.*;

public class CustomerService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Customer> customerList;
    private Map<String, String> accountList = new HashMap<>();

    public CustomerService(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void showCustomerList() {
//        ID KH,	Tên KH,	Loại khách hàng
        System.out.printf("%-15s %-30s %-30s\n", "ID khách hàng", "Tên khách hàng", "Loại khách hàng");
        for (Customer customer : customerList) {
            System.out.printf("%-15s %-30s %-30s\n", customer.getCustomerId(), customer.getName(), customer.geteCustomerType().getName());
        }
    }

    public boolean isCustomerIdExist(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return true;
            }
        }
        return false;
    }

    public Customer getCustomerByCustomerID(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    public int getIndexByCustomerId(int customerId){
        for (int i = 0; i < customerList.size();i++){
            if (customerList.get(i).getCustomerId() == customerId){
                return i;
            }
        }
        return -1;
    }

    public Map<String, String> getAccountList() {
        for (Customer customer : customerList) {
            accountList.put(customer.getUserName(), customer.getPassWord());
        }
        return accountList;
    }

    public Customer getCustomerByUserName(String userName) {
        for (Customer customer : customerList) {
            if (customer.getUserName().equals(userName)) {
                return customer;
            }
        }
        return null;
    }

    public int getNewCustomerId() {
        int maxId = 0;
        for (Customer customer : customerList) {
            if (customer.getCustomerId() > maxId) {
                maxId = customer.getCustomerId();
            }
        }
        return maxId + 1;
    }
    public String getInputCustomerName(){
        boolean isInvalidName;
        String customerName;

        do {
            isInvalidName = false;
            System.out.println("Nhập vào tên khách hàng: ");
            customerName = scanner.nextLine();
            if (!ValidateUtils.customerNameValidate(customerName)){
                System.out.println("Tên nhập vào không hợp lệ");
                isInvalidName = true;
            }
        }while (isInvalidName);

        return customerName;
    }
    public String getInputUsername(){
        boolean isInvalidUsername;
        String username;

        do {
            isInvalidUsername = false;
            System.out.println("Nhập username: ");
            username = scanner.nextLine();
            if (getAccountList().containsKey(username)){
                System.out.println("Username đã tồn tại. Xin nhập lại");
                isInvalidUsername = true;
            }else {
                if (!ValidateUtils.userameValidate(username)){
                    System.out.println("Username nhập vào không hợp lệ. Xin nhập lại");
                    isInvalidUsername = true;
                }
            }
        }while (isInvalidUsername);

        return username;
    }
    public String getInputPassword(){
        boolean isInvalidPassword;
        String password;

        do {
            isInvalidPassword = false;
            System.out.println("Nhập password: ");
            password = scanner.nextLine();
            if (!ValidateUtils.passwordValidate(password)){
                System.out.println("Password phải chứa it nhất 1 chữ viết hoa, 1 chữ thường, 1 ký tự số, 1 Ký tự đặc biệt. Xin nhập lại");
                isInvalidPassword = true;
            }
        }while (isInvalidPassword);

        return password;
    }
    public Date getInputDOB(){
        boolean isInvalidDate;
        Date DOB = null;
        Date currentDate = DateUtils.getCurrentDate();

        do {
            isInvalidDate = false;
            System.out.println("Nhập vào ngày sinh theo định dạng dd/MM/yyyy: ");
            String strDOB = scanner.nextLine();
            if (!ValidateUtils.dateValidate(strDOB)){
                System.out.println("Ngày nhập vào không đúng định dạng. Xin nhập lại");
                isInvalidDate = true;
            }else {
                DOB = DateUtils.parseDate(strDOB);
                if (DOB.after(currentDate)){
                    System.out.println("Ngày sinh không thể lớn hơn ngày hiện tại. Xin nhập lại");
                    isInvalidDate = true;
                }
            }
        }while (isInvalidDate);

        return DOB;
    }
    public int getInputCustomerId(){
        int customerId;
        boolean isInvalidCustomerID;

        do {
            isInvalidCustomerID = false;
            customerId = ActionUtils.intHandleInput("id khách hàng");
            if (!isCustomerIdExist(customerId)){
                System.out.println("ID khách hàng không tồn tại. Xin nhập lại");
                isInvalidCustomerID = true;
            }
        }while (isInvalidCustomerID);

        return customerId;
    }
    public Customer createCustomer(){
        int customerId = getNewCustomerId();
        String customerName = getInputCustomerName();
        String username = getInputUsername();
        String password = getInputPassword();
        Date DOB = getInputDOB();
        int age = DateUtils.getAgeByDOB(DOB);
        ECustomerType eCustomerType = ECustomerType.getECustomerTypeByAge(age);

        return new Customer(customerId,customerName,username,password,DOB,age,eCustomerType,ERole.CLIENT);
    }

}
