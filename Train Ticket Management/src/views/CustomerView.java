package views;

import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class CustomerView {
    public static Scanner scanner = new Scanner(System.in);
    public CustomerView(){

    }
    public void launcher(){
        boolean continueCheck = true;

        do {
            int userAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║               THICK MENU - Khách hàng               ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách chuyến đi                  ║");
                System.out.println("║ ▶ 02. Tìm kiếm chuyến đi                            ║");
                System.out.println("║ ▶ 03. Hiển thị danh sách ghế ngồi theo id chuyến đi ║");
                System.out.println("║ ▶ 04. Đặt vé theo id ghế ngồi                       ║");
                System.out.println("║ ▶ 05. Hiển thị danh sách vé đã đặt                  ║");
                System.out.println("║ ▶ 06. Hủy đặt vé theo id vé                         ║");
                System.out.println("║ ▶ 07. Thanh toán những vé đã đặt                    ║");
                System.out.println("║ ▶ 08. Sửa thông tin cá nhân                         ║");
                System.out.println("║"+PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                userAction = ActionUtils.intHandleInput("option");
            }while (userAction < 0 || userAction > 8);
            switch (userAction){
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
        System.out.println(PaintUtils.setRed("x͙i͙n͙ c͙h͙à͙o͙ v͙à͙ h͙ẹ͙n͙ g͙ặ͙p͙ l͙ạ͙i͙"));
    }
}
