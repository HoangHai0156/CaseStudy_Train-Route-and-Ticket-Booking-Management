package model;

public enum ERole {
    ADMIN(1,"Quản lý"),CLIENT(2,"Khách hàng");
    private int id;
    private String name;
    ERole(int id, String name){
        this.id = id;
        this.name = name;
    }
    public static ERole getRoleByEName(String eName){
        for (ERole eRole: ERole.values()){
            if (eRole.name().equals(eName)){
                return eRole;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
