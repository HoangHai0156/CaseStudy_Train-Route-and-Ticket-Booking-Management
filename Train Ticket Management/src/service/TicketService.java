package service;

import model.Seat;
import model.Ticket;

import java.util.List;
import java.util.Scanner;

public class TicketService {
    private static Scanner scanner = new Scanner(System.in);
    private List<Ticket> ticketList;
    public TicketService(List<Ticket> ticketList){
        this.ticketList = ticketList;
    }
}
