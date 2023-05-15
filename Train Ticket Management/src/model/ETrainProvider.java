package model;

public enum ETrainProvider {
    VNR(1,"Tổng công ty Đường sắt Việt Nam"),REE(2,"Công ty Cổ phần Điện đường sắt");
    private int id;
    private String fullName;

    ETrainProvider(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
    public static void showETrainProviders(){
        System.out.println("Danh sách nhà cung cấp tàu ở Việt Nam");
        System.out.printf("%-20s %-25s %-35s\n","Id nhà cung cấp","Ký hiệu nhà cung cấp","Tên đầy đủ nh cung cấp");
        for (ETrainProvider eTrainProvider: ETrainProvider.values()){
            System.out.printf("%-20s %-25s %-35s\n",eTrainProvider.getId(),eTrainProvider.name(),eTrainProvider.getFullName());
        }
    }
    public static ETrainProvider getETrainProviderById(int id){
        for (ETrainProvider eTrainProvider: ETrainProvider.values()){
            if (eTrainProvider.getId() == id){
                return eTrainProvider;
            }
        }
        return null;
    }
    public static ETrainProvider getETrainProviderByName(String name){
        for (ETrainProvider eTrainProvider: ETrainProvider.values()){
            if (eTrainProvider.name().equals(name)){
                return eTrainProvider;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
