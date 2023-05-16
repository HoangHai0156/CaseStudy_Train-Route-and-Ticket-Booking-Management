package service;

import Comparator.TrainComparator;
import model.ETrainProvider;
import model.Train;
import utils.ActionUtils;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainService {
    public static Scanner scanner = new Scanner(System.in);
    private List<Train> trainList;
    public TrainService(List<Train> trainList){
        this.trainList = trainList;
    }
    public boolean isTrainIdExist(int id){
        for (Train train: this.trainList){
            if (train.getTrainId() == id){
                return true;
            }
        }
        return false;
    }
    public List<Train> getTrainsByTrainNumber(String trainNumber){
        List<Train> trainsByTrainNumber = new ArrayList<>();
        for (Train train: trainList){
            if (train.getTrainNumber().equalsIgnoreCase(trainNumber)){
                trainsByTrainNumber.add(train);
            }
        }
        return trainsByTrainNumber;
    }
    public List<Train> getTrainsByTrainProvier(String provider){
        List<Train> trainsByTrainProvier = new ArrayList<>();
        for (Train train: trainList){
            if (train.getProvider().name().equals(provider)){
                trainsByTrainProvier.add(train);
            }
        }
        return trainsByTrainProvier;
    }
    public List<Train> getTrainsByCarQuanity(int carQuanity){
        List<Train> trainsByCarQuanity = new ArrayList<>();
        for (Train train: trainList){
            if (train.getCarQuanity() == carQuanity){
                trainsByCarQuanity.add(train);
            }
        }
        return trainsByCarQuanity;
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
            if (carQuanity <= 0){
                System.out.println("Số toa không thể là không hay số âm. Xin nhập lại");
                isCarQuanityInvalid = true;
            }
        }while (isCarQuanityInvalid);

        return carQuanity;
    }
    public ETrainProvider getInputTrainProvider(){
        ETrainProvider eTrainProvider;
        boolean nullCheck;

        ETrainProvider.showETrainProviders();
        System.out.println("Chọn hãng cung cấp tàu theo ID");
        do {
            nullCheck = false;
            int providerId = ActionUtils.intHandleInput("id");
            eTrainProvider = ETrainProvider.getETrainProviderById(providerId);
            if (eTrainProvider == null){
                System.out.println("ID nhập vào không tồn tại. Xin nhập lại");
                nullCheck = true;
            }
        }while (nullCheck);
        return eTrainProvider;
    }
    public void editTrainNumber(Train train){
        String newTrainNumber = getInputTrainNumber();

        int trainIndex = getTrainIndexByID(train.getTrainId());
        train.setTrainNumber(newTrainNumber);
        trainList.set(trainIndex,train);
    }
    public void editTrainProvider(Train train){
        System.out.println("Nhập vào hãng tàu");
        ETrainProvider trainProvider = getInputTrainProvider();

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
        ETrainProvider trainProvider = getInputTrainProvider();
        int carQuanity = getInputCarQuanity();

        return new Train(getNewTrainId(),trainNumber,trainProvider,carQuanity);
    }
    public List<Train> sortTrainById(boolean isAscending){
        TrainComparator trainComparatorById = new TrainComparator("trainId",isAscending);
        trainList.sort(trainComparatorById);
        return trainList;
    }
    public List<Train> sortTrainByTrainNumber(boolean isAscending){
        TrainComparator trainComparatorByTrainNumber = new TrainComparator("trainNumber",isAscending);
        trainList.sort(trainComparatorByTrainNumber);
        return trainList;
    }
    public List<Train> sortTrainByProvider(boolean isAscending){
        TrainComparator trainComparatorByProvider = new TrainComparator("provider",isAscending);
        trainList.sort(trainComparatorByProvider);
        return trainList;
    }
    public List<Train> sortTrainByCarQuanity(boolean isAscending){
        TrainComparator trainComparatorByCarQuanity = new TrainComparator("provider",isAscending);
        trainList.sort(trainComparatorByCarQuanity);
        return trainList;
    }
}
