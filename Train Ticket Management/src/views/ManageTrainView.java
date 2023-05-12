package views;

import utils.ActionUtils;
import utils.PaintUtils;

import java.util.Scanner;

public class ManageTrainView {
    public static Scanner scanner = new Scanner(System.in);
    public ManageTrainView(){}
    public void launcher(){
        boolean continueCheck = true;

        do {
            int manageTrainAction;
            do {
                System.out.println("╔═════════════════════════════════════════════════════╗");
                System.out.println("║              THICK MENU - Quản lý                   ║");
                System.out.println("╠═════════════════════════════════════════════════════╣");
                System.out.println("║ Options:                                            ║");
                System.out.println("║ ▶ 01. Hiển thị danh sách tàu                        ║");
                System.out.println("║ ▶ 02. Thêm tàu                                      ║");
                System.out.println("║ ▶ 03. Sửa thông tin tàu                             ║");
                System.out.println("║ ▶ 04. Xóa tàu                                       ║");
                System.out.println("║"+ PaintUtils.setRed(" ▶ 0. Quit")+"                                           ║");
                System.out.println("╚═════════════════════════════════════════════════════╝");

                manageTrainAction = ActionUtils.intHandleInput("option");
            }while (manageTrainAction < 0 || manageTrainAction > 4);
            switch (manageTrainAction){
                case 1:

                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }
}
