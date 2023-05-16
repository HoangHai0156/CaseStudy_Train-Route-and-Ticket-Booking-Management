package views;

import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class AdminView {
    public static ManageRouteView manageRouteView;
    public static ManageTrainView manageTrainView;
    public static ManageCustomerView manageCustomerView;
    public static ManageSeatView manageSeatView;
    public static ManageTicketView manageTicketView;
    public static Scanner scanner = new Scanner(System.in);
    public AdminView(){

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
                System.out.println("║"+PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                adminAction = ActionUtils.intHandleInput("option");
            }while (adminAction < 0 || adminAction > 7);

            manageRouteView = new ManageRouteView();
            manageTrainView = new ManageTrainView();
            manageCustomerView = new ManageCustomerView();
            manageSeatView = new ManageSeatView();
            manageTicketView = new ManageTicketView();

            switch (adminAction) {
                case 1 -> manageRouteView.launcher();
                case 2 -> manageTrainView.launcher();
                case 3 -> manageCustomerView.launcher();
                case 4 -> manageSeatView.launcher();
                case 5 -> manageTicketView.launcher();
                case 0 -> continueCheck = false;
            }
        }while (continueCheck);
        System.out.println(PaintUtils.setRed("x͙i͙n͙ c͙h͙à͙o͙ v͙à͙ h͙ẹ͙n͙ g͙ặ͙p͙ l͙ạ͙i͙"));
    }
}
