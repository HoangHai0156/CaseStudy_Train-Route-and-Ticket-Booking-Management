package views;

import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class ManageCustomerView {
    public static Scanner scanner = new Scanner(System.in);
    public ManageCustomerView(){}
    public void launcher(){
        boolean continueCheck = true;

        do {
            int manageCustomerAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý                   ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách KH                         ║");
                System.out.println("║ ▶ 02. Thêm khách hàng                               ║");
                System.out.println("║ ▶ 03. Sửa thông tin khách hàng theo ID              ║");
                System.out.println("║ ▶ 04. Xóa khách hàng                                ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageCustomerAction = ActionUtils.intHandleInput("option");
            }while (manageCustomerAction < 0 || manageCustomerAction > 4);
            switch (manageCustomerAction){
                case 1:

                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }
}
