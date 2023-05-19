package model;

import utils.DateUtils;

import java.util.Date;

public class Route implements Imodel<Route>{
    private int routeId;
    private int trainId;
    private Date departTime;
    private double runTime;
    private ERouteType routeType;
    private EStation from;
    private EStation destination;
    private double price;

    public Route() {
    }

    public Route(int idRoute, int idTrain, Date departTime, double runTime
            , ERouteType routeType, EStation from, EStation destination, double price) {
        this.routeId = idRoute;
        this.trainId = idTrain;
        this.departTime = departTime;
        this.runTime = runTime;
        this.routeType = routeType;
        this.from = from;
        this.destination = destination;
        this.price = price;
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

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public double getRunTime() {
        return runTime;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime;
    }

    public ERouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(ERouteType routeType) {
        this.routeType = routeType;
    }

    public EStation getFrom() {
        return from;
    }

    public void setFrom(EStation from) {
        this.from = from;
    }

    public EStation getDestination() {
        return destination;
    }

    public void setDestination(EStation destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
//        id,	id tàu,	khởi hành time,	thời gian chạy(H),	loại chuyến đi,	Đ xuất phát,	Đ kết thúc,	Giá
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",this.routeId,this.trainId, DateUtils.format(this.departTime),
                this.runTime,this.routeType.toString(),this.from.name(),this.destination.name(),this.price);
    }
    public Date getRouteArriveDate(){
        return DateUtils.plusHour(this.departTime,this.runTime);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.routeId = Integer.parseInt(strings[0]);
        this.trainId = Integer.parseInt(strings[1]);
        this.departTime = DateUtils.parseDateWithHour(strings[2]);
        this.runTime = Double.parseDouble(strings[3]);
        this.routeType = ERouteType.getERouTypeByName(strings[4]);
        this.from = EStation.getEStationByName(strings[5]);
        this.destination = EStation.getEStationByName(strings[6]);
        this.price = Double.parseDouble(strings[7]);
    }
}
