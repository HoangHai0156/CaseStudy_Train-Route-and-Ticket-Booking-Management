package model;

import java.util.Date;

public class Ticket implements Imodel<Ticket>{
    private int ticketId;
    private int seatId;
    private int customerId;
    private double price;
    private boolean isPaid;

    public Ticket() {
    }

    public Ticket(int ticketId, int seatId, int customerId, double price, boolean isPaid) {
        this.ticketId = ticketId;
        this.seatId = seatId;
        this.customerId = customerId;
        this.price = price;
        this.isPaid = isPaid;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
//        ID vé,	ID ghế,	ID Khách hàng,	Giá (giá vé chuyến đi + giá giảm loại KH),	trạng thái thanh toán
        return String.format("%s,%s,%s,%s,%s",this.ticketId,this.seatId,this.customerId,this.price,this.isPaid);
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.ticketId = Integer.parseInt(strings[0]);
        this.seatId = Integer.parseInt(strings[1]);
        this.customerId = Integer.parseInt(strings[2]);
        this.price = Double.parseDouble(strings[3]);
        this.isPaid = Boolean.parseBoolean(strings[4]);
    }
}
