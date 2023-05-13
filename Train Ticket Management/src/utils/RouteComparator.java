package utils;

import model.Route;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    private String key;
    public RouteComparator(String key){
        this.key = key;
    }

    @Override
    public int compare(Route o1, Route o2) {
        switch (this.key){
            default -> {
                return o1.getDepartTime().compareTo(o2.getDepartTime());
            }
        }
    }
}
