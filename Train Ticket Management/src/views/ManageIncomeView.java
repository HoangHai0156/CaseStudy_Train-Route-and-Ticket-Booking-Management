package views;

import model.Ticket;
import service.TicketService;
import utils.ActionUtils;
import utils.CurrencyUtils;
import utils.PaintUtils;

import java.util.List;

public class ManageIncomeView {
    private static ManageTicketView manageTicketView;
    private static TicketService ticketService;
    public ManageIncomeView(){};
    public void launcher(){
        boolean continueCheck = true;

        do {
            int manageIncomeAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║            THICK MENU - Quản lý doanh thu           ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Xem doanh thu từ đầu đến nay                  ║");
                System.out.println("║ ▶ 02. Xem doanh thu trong khoảng thời gian tự chọn  ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageIncomeAction = ActionUtils.intHandleInput("option");
            }while (manageIncomeAction < 0 || manageIncomeAction > 2);

            manageTicketView = new ManageTicketView();
            ticketService = new TicketService(manageTicketView.getTicketList());

            switch (manageIncomeAction) {
                case 1 -> getAllTimeRevenue(manageTicketView.getTicketList());
                case 2 -> getBetweenRevenue();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);

    }

    private void getBetweenRevenue() {
        List<Ticket> allTicketListBetween = ticketService.getTicketListByDateBetween(manageTicketView.getTicketList());

        getAllTimeRevenue(allTicketListBetween);
    }

    private void getAllTimeRevenue(List<Ticket> list) {
        double allTimeRealRevenue = 0;
        double allTimeExpectRevenue = 0;

        for (Ticket ticket: ticketService.getTicketListByStatus(true,list)){
            allTimeRealRevenue += ticket.getPrice();
        }

        for (Ticket ticket: list){
            allTimeExpectRevenue  += ticket.getPrice();
        }

        System.out.printf("%-25s %s\n","Tổng doanh thu "+PaintUtils.setBlue("thực tế")+" là: ", PaintUtils.setPurple(CurrencyUtils.getViCurrency(allTimeRealRevenue)));
        System.out.printf("%-25s %s\n","Tổng doanh thu "+PaintUtils.setBlue("dự kiến")+" là: ", PaintUtils.setPurple(CurrencyUtils.getViCurrency(allTimeExpectRevenue)));
    }
}
