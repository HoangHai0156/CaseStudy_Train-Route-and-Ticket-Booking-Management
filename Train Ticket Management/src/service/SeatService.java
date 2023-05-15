package service;

import model.Seat;

import java.util.List;
import java.util.Scanner;

public class SeatService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Seat> seatList;
    public SeatService(List<Seat> seatList){
        this.seatList = seatList;
    }
    public int getNewSeatId(){
        int maxId = 0;
        for (Seat seat: seatList){
            if (seat.getSeatId() > maxId){
                maxId = seat.getSeatId();
            }
        }
        return maxId+1;
    }
}
