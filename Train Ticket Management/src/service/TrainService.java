package service;

import model.Train;
import utils.ActionUtils;
import utils.ValidateUtils;

import java.util.List;
import java.util.Scanner;

public class TrainService {
    public static Scanner scanner = new Scanner(System.in);
    private List<Train> trainList;
    public TrainService(List<Train> trainList){
        this.trainList = trainList;
    }
    public boolean isTrainIdExist(int id){
        for (Train train: trainList){
            if (train.getTrainId() == id){
                return true;
            }
        }
        return false;
    }
    public int getNewTrainId(){
        int maxTrainId = 0;
        for (Train train: trainList){
            if (train.getTrainId() > maxTrainId){
                maxTrainId = train.getTrainId();
            }
        }
        return maxTrainId+1;
    }
    public int getTrainIndexByID(int id) {
        for (int i = 0; i < trainList.size(); i++) {
            if (trainList.get(i).getTrainId() == id) {
                return i;
            }
        }
        return -1;
    }
    public Train getTrainByTrainId(){
        int trainId;
        boolean isTrainIdInvalid;

        do {
            isTrainIdInvalid = false;
            System.out.println("Nhập vào id tàu");
            trainId = ActionUtils.intHandleInput("id");
            if (!isTrainIdExist(trainId)){
                System.out.println("ID tàu không tồn tại. Xin nhập lại");
                isTrainIdInvalid = true;
            }
        }while (isTrainIdInvalid);

        return trainList.get(getTrainIndexByID(trainId));
    }
    public String getInputTrainNumber(){
        String trainNumber;
        boolean isTrainNumberInvalid;

        do {
            isTrainNumberInvalid = false;
            System.out.println("Nhập số hiệu cho tàu theo định dạng SE00");
            trainNumber = scanner.nextLine();
            if (!ValidateUtils.trainNumberValidate(trainNumber)){
                System.out.println("Số hiệu tàu nhập vào không đúng định dạng. Xin nhập lại");
                isTrainNumberInvalid = true;
            }
        }while (isTrainNumberInvalid);

        return trainNumber;
    }
    public int getInputCarQuanity(){
        int carQuanity;
        boolean isCarQuanityInvalid;

        do {
            isCarQuanityInvalid = false;
            carQuanity = ActionUtils.intHandleInput("số toa");
            if (carQuanity < 0){
                System.out.println("Số toa không thể là số âm. Xin nhập lại");
                isCarQuanityInvalid = true;
            }
        }while (isCarQuanityInvalid);

        return carQuanity;
    }
    public void editTrainNumber(Train train){
        String newTrainNumber = getInputTrainNumber();

        int trainIndex = getTrainIndexByID(train.getTrainId());
        train.setTrainNumber(newTrainNumber);
        trainList.set(trainIndex,train);
    }
    public void editTrainProvider(Train train){
        System.out.println("Nhập vào hãng tàu");
        String trainProvider = scanner.nextLine();

        int trainIndex = getTrainIndexByID(train.getTrainId());
        train.setProvider(trainProvider);
        trainList.set(trainIndex,train);
    }
    public void editCarQuanity(Train train){
        int carQuanity = getInputCarQuanity();

        int trainIndex = getTrainIndexByID(train.getTrainId());
        train.setCarQuanity(carQuanity);
        trainList.set(trainIndex,train);
    }
    public Train createTrain(){
        String trainNumber = getInputTrainNumber();
        System.out.println("Nhập vào hãng tàu");
        String trainProvider = scanner.nextLine();
        int carQuanity = getInputCarQuanity();

        return new Train(getNewTrainId(),trainNumber,trainProvider,carQuanity);
    }
}
