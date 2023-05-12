package model;

public enum EStation {
    HUE(1,"Ga Huế"),SG(2,"Ga Sài Gòn"),HN(3,"Ga Hà Nội");
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

    EStation(int id, String name){
        this.id = id;
        this.name = name;
    }
    public static EStation getEStationByName(String name){
        for (EStation eStation : EStation.values()){
            if (eStation.toString().equals(name)){
                return eStation;
            }
        }
        return null;
    }

}
