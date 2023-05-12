package views;

import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class AdminView {
    public static ManageRouteView manageRouteView = new ManageRouteView();
    public static ManageTrainView manageTrainView = new ManageTrainView();
    public static ManageCustomerView manageCustomerView = new ManageCustomerView();
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
                System.out.println("║ ▶ 04. Hiển thị danh sách ghế ngồi theo chuyến đi    ║");
                System.out.println("║ ▶ 05. Cập nhật trạng thái ghế ngồi                  ║");
                System.out.println("║ ▶ 06. Hiển thị danh sách vé đã đặt của tất cả KH    ║");
                System.out.println("║ ▶ 07. Hủy đặt vé theo id vé                         ║");
                System.out.println("║"+PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                adminAction = ActionUtils.intHandleInput("option");
            }while (adminAction < 0 || adminAction > 7);
            switch (adminAction){
                case 1:
                    manageRouteView.launcher();
                    break;
                case 2:
                    manageTrainView.launcher();
                    break;
                case 3:
                    manageCustomerView.launcher();
                    break;
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
        System.out.println(PaintUtils.setRed("x͙i͙n͙ c͙h͙à͙o͙ v͙à͙ h͙ẹ͙n͙ g͙ặ͙p͙ l͙ạ͙i͙"));
    }
}
