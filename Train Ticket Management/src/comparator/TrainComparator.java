package comparator;

import model.Train;

import java.util.Comparator;

public class TrainComparator implements Comparator<Train> {
    private boolean isAscending;
    private String key;
    public TrainComparator(String key,boolean isAscending){
        this.key = key;
        this.isAscending = isAscending;
    }
    @Override
    public int compare(Train o1, Train o2) {
        switch (this.key) {
            case "trainId" -> {
                if (isAscending)
                    return Integer.compare(o1.getTrainId(), o2.getTrainId());
                else return Integer.compare(o2.getTrainId(), o1.getTrainId());
            }
            case "trainNumber" -> {
                if (isAscending)
                    return o1.getTrainNumber().compareTo(o2.getTrainNumber());
                else return o2.getTrainNumber().compareTo(o1.getTrainNumber());
            }
            case "provider" -> {
                if (isAscending)
                    return o1.getProvider().name().compareTo(o2.getProvider().name());
                else return o2.getProvider().name().compareTo(o1.getProvider().name());
            }
            default -> {
                if (isAscending)
                    return Integer.compare(o1.getCarQuanity(), o2.getCarQuanity());
                else return Integer.compare(o2.getCarQuanity(), o1.getCarQuanity());
            }
        }
    }
}
