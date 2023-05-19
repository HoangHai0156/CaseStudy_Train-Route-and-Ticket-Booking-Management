package service;

import model.*;
import utils.*;
import views.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicketService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Ticket> ticketList;
    private ManageSeatView manageSeatView;
    private SeatService seatService;
    private ManageCustomerView manageCustomerView;
    private CustomerService customerService;
    private ManageRouteView manageRouteView;
    private RouteService routeService;
    private ManageTrainView manageTrainView;
    private TrainService trainService;
    private ManageTicketView manageTicketView;
    private Date currentDateIncludeHour;
    public TicketService(List<Ticket> ticketList){
        this.ticketList = ticketList;
        manageSeatView = new ManageSeatView();
        manageCustomerView = new ManageCustomerView();
        manageRouteView = new ManageRouteView();
        manageTrainView = new ManageTrainView();
        manageTicketView = new ManageTicketView();
        seatService = new SeatService(manageSeatView.getSeatList());
        customerService = new CustomerService(manageCustomerView.getCustomerList());
        routeService = new RouteService(manageRouteView.getRouteList());
        trainService = new TrainService(manageTrainView.getTrainList());
        currentDateIncludeHour = DateUtils.getCurrentDateIncludeHour();
    }
    public void showTicketList(List<Ticket> ticketList){
//        ID vé,	ID ghế,	ID Khách hàng,	Giá (giá vé chuyến đi + giá giảm loại KH),	trạng thái thanh toán
        System.out.printf("%-6s %-10s %-10s %-15s %-15s %-25s %-20s %-10s %-15s %-10s %-20s %-25s\n","ID vé",
                "Tàu hiệu","Toa số","Điểm xuất phát",
                "Điểm đến","Ngày khởi hành dự kiến","Ngày đến dự kiến"
                ,"ID ghế","Khách hàng","Giá vé","Ngày đặt vé","Trạng thái thanh toán");
        for (Ticket ticket: ticketList){
            if (ticket.isPaid()){
                Customer customer = customerService.getCustomerByCustomerID(ticket.getCustomerId());
                Seat seat = seatService.getSeatBySeatId(ticket.getSeatId());
                Train train = trainService.getTrainByTrainId(seat.getTrainId());
                Route route = routeService.getRouteById(seat.getRouteId());

                System.out.printf("%-6s %-10s %-10s %-15s %-15s %-25s %-20s %-10s %-15s %-10s %-20s %-25s\n",
                        ticket.getTicketId(),
                        train.getTrainNumber(),seat.getCarId(),route.getFrom().getName(),route.getDestination().getName(),
                        DateUtils.format(route.getDepartTime()),DateUtils.format(route.getRouteArriveDate()),
                        ticket.getSeatId(),
                        customer.getName(),
                        CurrencyUtils.getViCurrency(ticket.getPrice()),
                        DateUtils.format(ticket.getBookingDate()),
                        ticket.isPaid()? PaintUtils.setGreen("Đã thanh toán"):PaintUtils.setOrange("Chưa thanh toán"));
            }
        }

        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        for (Ticket ticket: ticketList){
            if (!ticket.isPaid()){
                Customer customer = customerService.getCustomerByCustomerID(ticket.getCustomerId());
                Seat seat = seatService.getSeatBySeatId(ticket.getSeatId());
                Train train = trainService.getTrainByTrainId(seat.getTrainId());
                Route route = routeService.getRouteById(seat.getRouteId());

                System.out.printf("%-6s %-10s %-10s %-15s %-15s %-25s %-20s %-10s %-15s %-10s %-20s %-25s\n",
                        ticket.getTicketId(),
                        train.getTrainNumber(),seat.getCarId(),route.getFrom().getName(),route.getDestination().getName(),
                        DateUtils.format(route.getDepartTime()),DateUtils.format(route.getRouteArriveDate()),
                        ticket.getSeatId(),
                        customer.getName(),
                        CurrencyUtils.getViCurrency(ticket.getPrice()),
                        DateUtils.format(ticket.getBookingDate()),
                        ticket.isPaid()? PaintUtils.setGreen("Đã thanh toán"):PaintUtils.setOrange("Chưa thanh toán"));
            }
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
    public List<Ticket> getTicketListByStatus(boolean isPaid, List<Ticket> list){
        List<Ticket> ticketListByStatus = new ArrayList<>();
        for (Ticket ticket:list){
            if (ticket.isPaid() == isPaid){
                ticketListByStatus.add(ticket);
            }
        }

        return ticketListByStatus;
    }
    public List<Ticket> getTicketListByDateBetween(List<Ticket> list){
        List<Ticket> ticketListByDateBetween = new ArrayList<>();
        String from;
        String to;
        boolean isInvalidDate;

        do {
            isInvalidDate = false;
            System.out.println("Nhập vào ngày bắt đầu theo định dạng dd/MM/yyyy");
            from = scanner.nextLine();
            System.out.println("Nhập vào ngày kết thúc theo định dạng dd/MM/yyyy");
            to = scanner.nextLine();
            if (!ValidateUtils.dateValidate(from) || !ValidateUtils.dateValidate(to)){
                System.out.println("Ngày nhập vào không đúng định dạng. Xin nhập lại");
                isInvalidDate = true;
            }
        }while (isInvalidDate);

        for (Ticket ticket: list){
            String dateBooking = DateUtils.formatDateWithoutHour(ticket.getBookingDate());
            Date formattedDateBooking = DateUtils.parseDate(dateBooking);
            if ((formattedDateBooking.getTime() >= DateUtils.parseDate(from).getTime()) &&
                    formattedDateBooking.getTime() <= DateUtils.parseDate(to).getTime()){
                ticketListByDateBetween.add(ticket);
            }
        }

        return ticketListByDateBetween;
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

        customerService.showCustomerList();

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
        String seatId;

        boolean isInvalidSeat;
        do {
            isInvalidSeat = false;
            seatId = seatService.getInputSeatId();

            Seat seat = seatService.getSeatBySeatId(seatId);
            Route route = routeService.getRouteById(seat.getRouteId());

            if (!seat.isEmpty()){
                System.out.println("Ghế đã được đặt. Xin chọn ghế khác");
                isInvalidSeat = true;
            }else {
                if (route.getDepartTime().before(currentDateIncludeHour)){
                    System.out.println("Chuyến đi đã khởi hành. Xin vui lòng chọn chuyến khác");
                    isInvalidSeat = true;
                }
            }
        }while (isInvalidSeat);

        double price = routeService.getRouteById(seatService.getSeatBySeatId(seatId).getRouteId()).getPrice()
                +customerService.getCustomerByCustomerID(customerID).geteCustomerType().getDiscountPrice();
        return new Ticket(newId,seatId,customerID,price,false,currentDateIncludeHour);
    }
//    public void removeTicketsByCustomerId(int customerID){
////        List<Ticket> newList = ticketList.stream().filter(ticket -> ticket.getCustomerId() != customerID && ticket.isPaid()).collect(Collectors.toList());
//        List<Ticket> newListTicket = ticketList;
//        for (Ticket ticket: ticketList){
//            if (ticket.getCustomerId() != customerID && ticket.isPaid())
//        }
//
//        FileUtils.writeDataToFile(newList, manageTicketView.getTicketFilePath());
//        manageSeatView.updateSeatList();
//    }
}
