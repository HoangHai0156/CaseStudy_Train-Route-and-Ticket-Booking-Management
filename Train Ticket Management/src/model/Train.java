package model;

public class Train implements Imodel<Train>{
    private int trainId;
    private String trainNumber;
    private ETrainProvider provider;
    private int carQuanity;

    public Train() {
    }

    public Train(int trainId, String trainNumber, ETrainProvider provider, int carQuanity) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.provider = provider;
        this.carQuanity = carQuanity;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public ETrainProvider getProvider() {
        return provider;
    }

    public void setProvider(ETrainProvider provider) {
        this.provider = provider;
    }

    public int getCarQuanity() {
        return carQuanity;
    }

    public void setCarQuanity(int carQuanity) {
        this.carQuanity = carQuanity;
    }

    @Override
    public String toString() {
//        ID,	Tên tàu,	Hãng tàu,	số toa
        return String.format("%s,%s,%s,%s",this.trainId,this.trainNumber,this.provider.name(),this.carQuanity);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.trainId = Integer.parseInt(strings[0]);
        this.trainNumber = strings[1];
        this.provider = ETrainProvider.getETrainProviderByName(strings[2]);
        this.carQuanity = Integer.parseInt(strings[3]);
    }
}
