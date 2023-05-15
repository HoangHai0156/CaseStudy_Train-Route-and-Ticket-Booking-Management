package model;

public class Car implements Imodel{
    private int carId;
    private int trainId;
    private final int seatNum = 4;

    public Car() {
    }

    public Car(int carId, int trainId) {
        this.carId = carId;
        this.trainId = trainId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getSeatNum() {
        return seatNum;
    }

    @Override
    public String toString() {
//        ID,	ID tàu,	số ghế
        return String.format("%s,%s,%s",this.carId,this.trainId,this.seatNum);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.carId = Integer.parseInt(strings[0]);
        this.trainId = Integer.parseInt(strings[1]);
    }
}
