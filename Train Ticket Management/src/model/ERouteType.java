package model;

public enum ERouteType {
    NORMAL(1,"Thường"),BOTH(2,"Khứ hồi");
    private int id;
    private String name;

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

    ERouteType(int id, String name){
        this.id = id;
        this.name = name;
    }
    public static ERouteType getERouTypeByName(String name){
        for (ERouteType eRouteType : ERouteType.values()){
            if (eRouteType.toString().equals(name)){
                return eRouteType;
            }
        }
        return null;
    }
}
