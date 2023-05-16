package service;

import model.Seat;
import utils.PaintUtils;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Seat> seatList;

    public SeatService(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public void showSeatList(List<Seat> seatList) {
//        id ghế,	id chuyen di,	id tàu,	id toa,	trạng thái trống
        System.out.printf("%-15s %-20s %-15s %-15s %-25s\n", "Id ghế", "Id chuyến đi", "Id tàu", "Id toa", "Trạng thái trống");
        for (Seat seat : seatList) {
            System.out.printf("%-15s %-30s %-15s %-15s %-25s\n", seat.getSeatId(),
                    seat.getRouteId() % 2 == 0 ? PaintUtils.setBlue(String.valueOf(seat.getRouteId())) : PaintUtils.setOrange(String.valueOf(seat.getRouteId())),
                    seat.getTrainId(), seat.getCarId(),
                    seat.isEmpty() ? PaintUtils.setGreen("Còn trống") : PaintUtils.setRed("Đã được đặt"));
        }
    }
    public List<Seat> getSeatListByRouteId(int routeId){
        List<Seat> seatListByRouteId = new ArrayList<>();
        for (Seat seat: seatList){
            if (seat.getRouteId() == routeId){
                seatListByRouteId.add(seat);
            }
        }
        return seatListByRouteId;
    }
    public boolean isSeatIdExist(String seatId){
        for (Seat seat: seatList){
            if (seat.getSeatId().equals(seatId)){
                return true;
            }
        }
        return false;
    }

    public String getInputSeatId(){
        String seatId;
        boolean isInvalidSeatId;

        do {
            isInvalidSeatId = false;
            System.out.println("Nhập vào ID của ghế theo định dạng R0T0C0S0");
            seatId = scanner.nextLine();
            if (!ValidateUtils.seatIdValidate(seatId)){
                System.out.println("ID ghế không đug định dạng. Xin nhập lại");
                isInvalidSeatId = true;
            }else if (!isSeatIdExist(seatId)){
                System.out.println("ID ghế không tồn tại. Xin nhập lại");
                isInvalidSeatId = true;
            }
        }while (isInvalidSeatId);

        return seatId;
    }
    public Seat getSeatBySeatId(String seatId){
        for (Seat seat: seatList){
            if (seat.getSeatId().equals(seatId)){
                return seat;
            }
        }
        return null;
    }
}
