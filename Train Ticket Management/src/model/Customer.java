package model;

public class Customer implements Imodel<Customer>{
    private int customerId;
    private String name;
    private ECustomerType eCustomerType;

    public Customer() {
    }

    public Customer(int customerId, String name, ECustomerType eCustomerType) {
        this.customerId = customerId;
        this.name = name;
        this.eCustomerType = eCustomerType;
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
//        ID KH,	Tên KH,	Loại khách hàng
        return String.format("%s,%s,%s",this.customerId,this.name,this.eCustomerType.name());
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.customerId = Integer.parseInt(strings[0]);
        this.name = strings[1];
        this.eCustomerType = ECustomerType.getECustomerTypeByName(strings[2]);
    }
}
