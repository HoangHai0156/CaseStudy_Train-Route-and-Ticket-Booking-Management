package service;

import model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Customer> customerList;
    public CustomerService(List<Customer> customerList){
        this.customerList = customerList;
    }
    public void showCustomerList(List<Customer> customerList){
//        ID KH,	Tên KH,	Loại khách hàng
        System.out.printf("%-15s %-30s %-30s\n","ID khách hàng","Tên khách hàng","Loại khách hàng");
        for (Customer customer: customerList){
            System.out.printf("%-15s %-30s %-30s\n",customer.getCustomerId(),customer.getName(),customer.geteCustomerType().getName());
        }
    }
    public boolean isCustomerIdExist(int customerId){
        for (Customer customer: customerList){
            if (customer.getCustomerId() == customerId){
                return true;
            }
        }
        return false;
    }
    public Customer getCustomerByCustomerID(int customerId){
        for (Customer customer: customerList){
            if (customer.getCustomerId() == customerId){
                return customer;
            }
        }
        return null;
    }
}
