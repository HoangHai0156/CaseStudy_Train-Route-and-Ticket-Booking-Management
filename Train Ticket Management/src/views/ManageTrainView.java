package views;

import model.Train;
import utils.ActionUtils;
import utils.FileUtils;
import utils.PaintUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageTrainView {
    public static Scanner scanner = new Scanner(System.in);
    private static String trainFilePath = "./Data/train.csv";
    private static List<Train> trainList = new ArrayList<>();
    public List<Train> getTrainList(){
        return trainList;
    }
    public ManageTrainView(){
        trainList = FileUtils.readDataFromFile(Train.class,trainFilePath);
    }
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
                    showListTrain(trainList);
                case 0:
                    continueCheck = false;
                    break;
            }
        }while (continueCheck);
    }

    public void showListTrain(List<Train> trainList) {
//        ID,	Tên tàu,	Hãng tàu,	số toa
        System.out.printf("%-15s %-15s %-20s %-10s\n","ID Tàu","Tên tàu","Hãng tàu","Số toa");
        for (Train train: trainList){
            System.out.printf("%-15s %-15s %-20s %-10s\n",train.getTrainId(),
                    train.getName(),train.getProvider(),train.getCarNum());
        }
    }
}
