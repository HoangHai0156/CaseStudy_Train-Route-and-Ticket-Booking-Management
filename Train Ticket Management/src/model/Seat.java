package model;

public class Seat implements Imodel<Seat> {
    private int seatId;
    private int routeId;
    private int trainId;
    private int carId;
    private boolean isEmpty;

    public Seat() {
    }

    public Seat(int seatId, int routeId, int trainId, int carId, boolean isEmpty) {
        this.seatId = seatId;
        this.routeId = routeId;
        this.trainId = trainId;
        this.carId = carId;
        this.isEmpty = isEmpty;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    @Override
    public String toString() {
//        id ghế,	id chuyen di,	id tàu,	id toa,	trạng thái trống
        return String.format("%s,%s,%s,%s,%s",this.seatId,this.routeId,this.trainId,this.carId,this.isEmpty);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.seatId = Integer.parseInt(strings[0]);
        this.routeId = Integer.parseInt(strings[1]);
        this.trainId = Integer.parseInt(strings[2]);
        this.carId = Integer.parseInt(strings[3]);
        this.isEmpty = Boolean.parseBoolean(strings[4]);
    }
}
