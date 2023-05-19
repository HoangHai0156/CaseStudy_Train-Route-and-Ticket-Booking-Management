package model;

import utils.DateUtils;

import java.util.Date;

public class Ticket implements Imodel<Ticket>{
    private int ticketId;
    private String seatId;
    private int customerId;
    private double price;
    private boolean isPaid;
    private Date bookingDate;

    public Ticket() {
    }

    public Ticket(int ticketId, String seatId, int customerId, double price, boolean isPaid, Date bookingDate) {
        this.ticketId = ticketId;
        this.seatId = seatId;
        this.customerId = customerId;
        this.price = price;
        this.isPaid = isPaid;
        this.bookingDate = bookingDate;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
//        ID vé,	ID ghế,	ID Khách hàng,	Giá (giá vé chuyến đi + giá giảm loại KH),	trạng thái thanh toán, Ngày đặt
        return String.format("%s,%s,%s,%s,%s,%s",this.ticketId,this.seatId,this.customerId,this.price,this.isPaid,DateUtils.format(this.bookingDate));
    }

    @Override
    public void parseData(String line) {
        String[] strings = line.split(",");
        this.ticketId = Integer.parseInt(strings[0]);
        this.seatId = strings[1];
        this.customerId = Integer.parseInt(strings[2]);
        this.price = Double.parseDouble(strings[3]);
        this.isPaid = Boolean.parseBoolean(strings[4]);
        this.bookingDate = DateUtils.parseDateWithHour(strings[5]);
    }
}
