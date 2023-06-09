package utils;

import java.util.Scanner;

public class ActionUtils {
    private static Scanner scanner = new Scanner(System.in);
    public static int intHandleInput(String actionName){
        int action = -1;
        boolean checkInvalid;

        do {
            checkInvalid = false;
            try {
                System.out.println("Chọn "+PaintUtils.setYellow(actionName)+" thích hợp: ");
                action = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Giá trị nhập vào không hợp lệ. Vui lòng nhập lại");
                checkInvalid = true;
            }
        }while (checkInvalid);
        return action;
    }
    public static double doubleHandleInput(String inputName){
        double value = -1;
        boolean checkInvalid;

        do {
            checkInvalid = false;
            try {
                System.out.println("Chọn "+PaintUtils.setYellow(inputName)+" thích hợp: ");
                value = Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Giá trị nhập vào không hợp lệ. Vui lòng nhập lại");
                checkInvalid = true;
            }
        }while (checkInvalid);
        return value;
    }
    public static boolean getConfirm(String inputName){
        boolean checkInvalid;
        boolean isConfirm = false;

        do {
            checkInvalid = false;
            System.out.println(PaintUtils.setRed("Bạn có chắc muốn "+inputName+" không ?(Y/N)"));
            char confirm = scanner.nextLine().charAt(0);

            if (confirm == 'y' || confirm == 'Y'){
                isConfirm = true;
            }else if (confirm == 'n' || confirm == 'N'){
                isConfirm = false;
            }else {
                System.out.println("Vui lòng chỉ nhập ký tự Y hoặc N. Xin nhập lại");
                checkInvalid = true;
            }
        }while (checkInvalid);

        return isConfirm;
    }
}
