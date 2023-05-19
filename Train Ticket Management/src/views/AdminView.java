package views;

import utils.ActionUtils;
import utils.DateUtils;
import utils.PaintUtils;

import java.util.Date;
import java.util.Scanner;

public class AdminView {
    private static ManageRouteView manageRouteView;
    private static ManageTrainView manageTrainView;
    private static ManageCustomerView manageCustomerView;
    private static ManageSeatView manageSeatView;
    private static ManageTicketView manageTicketView;
    private static ManageIncomeView manageIncomeView;
    private static Scanner scanner = new Scanner(System.in);
    private Date currentDateIncludeHour;
    public AdminView(){
        this.currentDateIncludeHour = DateUtils.getCurrentDateIncludeHour();
    }
    public void launcher(){

        boolean continueCheck = true;

        do {
            int adminAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý                   ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Quản lý chuyến đi                             ║");
                System.out.println("║ ▶ 02. Quản lý tàu                                   ║");
                System.out.println("║ ▶ 03. Quản lý khách hàng                            ║");
                System.out.println("║ ▶ 04. Quản lý danh sách ghế ngồi                    ║");
                System.out.println("║ ▶ 05. Quản lý danh sách vé                          ║");
                System.out.println("║ ▶ 06. Quản lý doanh thu                             ║");
                System.out.println("║"+PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                adminAction = ActionUtils.intHandleInput("option");
            }while (adminAction < 0 || adminAction > 6);

            manageRouteView = new ManageRouteView();
            manageTrainView = new ManageTrainView();
            manageCustomerView = new ManageCustomerView();
            manageSeatView = new ManageSeatView();
            manageTicketView = new ManageTicketView();
            manageIncomeView = new ManageIncomeView();

            switch (adminAction) {
                case 1 -> manageRouteView.launcher();
                case 2 -> manageTrainView.launcher();
                case 3 -> manageCustomerView.launcher();
                case 4 -> manageSeatView.launcher();
                case 5 -> manageTicketView.launcher();
                case 6 -> manageIncomeView.launcher();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
        System.out.println(PaintUtils.setRed("x͙i͙n͙ c͙h͙à͙o͙ v͙à͙ h͙ẹ͙n͙ g͙ặ͙p͙ l͙ạ͙i͙"));
    }
}
