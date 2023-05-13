package service;

import model.Train;

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
}
