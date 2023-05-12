package model;

public class Train implements Imodel<Train>{
    private int trainId;
    private String name;
    private String provider;
    private int carNum;

    public Train() {
    }

    public Train(int trainId, String name, String provider, int carNum) {
        this.trainId = trainId;
        this.name = name;
        this.provider = provider;
        this.carNum = carNum;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    @Override
    public String toString() {
//        ID,	Tên tàu,	Hãng tàu,	số toa
        return String.format("%s,%s,%s,%s",this.trainId,this.name,this.provider,this.carNum);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.trainId = Integer.parseInt(strings[0]);
        this.name = strings[1];
        this.provider = strings[2];
        this.carNum = Integer.parseInt(strings[3]);
    }
}
