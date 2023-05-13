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
                System.out.println("Chọn "+actionName+" thích hợp: ");
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
                System.out.println("Chọn "+inputName+" thích hợp: ");
                value = Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException numberFormatException){
                System.out.println("Giá trị nhập vào không hợp lệ. Vui lòng nhập lại");
                checkInvalid = true;
            }
        }while (checkInvalid);
        return value;
    }
}
