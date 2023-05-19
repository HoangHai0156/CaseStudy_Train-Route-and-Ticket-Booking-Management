package model;

import utils.DateUtils;

import java.util.Date;

public class Customer implements Imodel<Customer>{
    private int customerId;
    private String name;
    private String userName;
    private String passWord;
    private Date DOB;
    private int age;
    private ECustomerType eCustomerType;
    private ERole eRole;

    public Customer() {
    }

    public Customer(int customerId, String name, String userName, String passWord, Date DOB,int age,ECustomerType eCustomerType, ERole eRole) {
        this.customerId = customerId;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.DOB = DOB;
        this.age = age;
        this.eCustomerType = eCustomerType;
        this.eRole = eRole;
    }

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ECustomerType geteCustomerType() {
        return eCustomerType;
    }

    public void seteCustomerType(ECustomerType eCustomerType) {
        this.eCustomerType = eCustomerType;
    }

    @Override
    public String toString() {
//        ID KH,	Tên KH,	username, password, DOB, age, Loại khách hàng, Role
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",this.customerId,this.name,this.userName,this.passWord,
                DateUtils.formatDateWithoutHour(this.DOB),this.age,this.eCustomerType.name(),this.eRole.name());
    }

    @Override
    public void parseData(String line) {
//        String userName, String passWord, Date DOB, int age,
        String[] strings = line.split(",");
        this.customerId = Integer.parseInt(strings[0]);
        this.name = strings[1];
        this.userName = strings[2];
        this.passWord = strings[3];
        this.DOB = DateUtils.parseDate(strings[4]);
        this.age = Integer.parseInt(strings[5]);
        this.eCustomerType = ECustomerType.getECustomerTypeByName(strings[6]);
        this.eRole = ERole.getRoleByEName(strings[7]);
    }
}
