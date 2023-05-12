package model;

public enum ECustomerType {
    ADULT(1,0,"Người lớn"),KID(2,-1000,"Trẻ nhỏ"),OLD(3,-2000,"Người già và phụ nữ mang thai");
    private int id;
    private double discountPrice;
    private String name;
    ECustomerType(int id,double discountPrice, String name){
        this.id = id;
        this.name = name;
        this.discountPrice = discountPrice;
    }
    public static ECustomerType getECustomerTypeByName(String name){
        for (ECustomerType eCustomerType : ECustomerType.values()){
            if (eCustomerType.toString().equals(name)){
                return eCustomerType;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
