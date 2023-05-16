package service;

import model.Seat;
import model.Ticket;
import utils.ActionUtils;
import utils.PaintUtils;
import views.ManageCustomerView;
import views.ManageRouteView;
import views.ManageSeatView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Ticket> ticketList;
    private ManageSeatView manageSeatView;
    private SeatService seatService;
    private ManageCustomerView manageCustomerView;
    private CustomerService customerService;
    private ManageRouteView manageRouteView;
    private RouteService routeService;
    public TicketService(List<Ticket> ticketList){
        this.ticketList = ticketList;
        manageSeatView = new ManageSeatView();
        manageCustomerView = new ManageCustomerView();
        manageRouteView = new ManageRouteView();
        seatService = new SeatService(manageSeatView.getSeatList());
        customerService = new CustomerService(manageCustomerView.getCustomerList());
        routeService = new RouteService(manageRouteView.getRouteList());
    }
    public void showTicketList(List<Ticket> ticketList){
//        ID vé,	ID ghế,	ID Khách hàng,	Giá (giá vé chuyến đi + giá giảm loại KH),	trạng thái thanh toán
        System.out.printf("%-10s %-15s %-15s %-20s %-25s\n","ID vé","ID ghế","ID Khách hàng","Giá vé","Trạng thái thanh toán");
        for (Ticket ticket: ticketList){
            System.out.printf("%-10s %-15s %-15s %-20s %-25s\n",
                    ticket.getTicketId(),ticket.getSeatId(),ticket.getCustomerId(),ticket.getPrice(),
                    ticket.isPaid()? PaintUtils.setOrange("Đã thanh toán"):"Chưa thanh toán");
        }
    }
    public List<Ticket> getTicketListByCustomerId(int customerID){
        List<Ticket> ticketListByCustomerId = new ArrayList<>();
        for (Ticket ticket: ticketList){
            if (ticket.getCustomerId() == customerID){
                ticketListByCustomerId.add(ticket);
            }
        }
        return ticketListByCustomerId;
    }
    public int getTicketIndexByTicketId(int ticketID, List<Ticket> list){
        for (int i = 0; i < list.size();i++){
            if (list.get(i).getTicketId() == ticketID){
                return i;
            }
        }
        return -1;
    }
    public Ticket getTicketByTicketId(int ticketId){
        for (Ticket ticket: ticketList){
            if (ticket.getTicketId() == ticketId){
                return ticket;
            }
        }
        return null;
    }

    public int getInputTicketID(List<Ticket> list){
        boolean invalidTicketID;
        int ticketID;

        do {
            invalidTicketID = false;
            ticketID = ActionUtils.intHandleInput("id vé");
            if (ticketID<0 || getTicketIndexByTicketId(ticketID,list) < 0){
                System.out.println("ID nhập vào không tồn tại. Xin nhập lại");
                invalidTicketID = true;
            }
        }while (invalidTicketID);

        return ticketID;
    }
    public int getInputCustomerId(){
        boolean isInvalidCustomerId;
        int customerId;

        customerService.showCustomerList(manageCustomerView.getCustomerList());

        do {
            isInvalidCustomerId = false;
            customerId = ActionUtils.intHandleInput("ID khách hàng");
            if (!customerService.isCustomerIdExist(customerId)){
                System.out.println("ID khách hàng không tồn tại. Xin nhập lại");
                isInvalidCustomerId = true;
            }
        }while (isInvalidCustomerId);

        return customerId;
    }
    public int getNewTicketId(){
        int maxId = 0;
        for (Ticket ticket: ticketList){
            if (ticket.getTicketId() > maxId){
                maxId = ticket.getTicketId();
            }
        }
        return maxId+1;
    }
    public Ticket createTicket(int customerID){
        int newId = getNewTicketId();
        String seatId = seatService.getInputSeatId();
        double price = routeService.getRouteById(seatService.getSeatBySeatId(seatId).getRouteId()).getPrice()
                +customerService.getCustomerByCustomerID(customerID).geteCustomerType().getDiscountPrice();
        return new Ticket(getNewTicketId(),seatId,customerID,price,false);
    }
}
