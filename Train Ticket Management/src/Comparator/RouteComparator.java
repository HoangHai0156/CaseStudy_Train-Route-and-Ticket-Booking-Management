package Comparator;

import model.Route;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    private String key;
    private boolean isAscending;

    public RouteComparator(String key, boolean isAscending) {
        this.key = key;
        this.isAscending = isAscending;
    }

    @Override
    public int compare(Route o1, Route o2) {
        switch (this.key) {
            case "routeId" -> {
                if (isAscending)
                    return Integer.compare(o1.getRouteId(), o2.getRouteId());
                else return Integer.compare(o2.getRouteId(), o1.getRouteId());
            }
            case "price" -> {
                if (isAscending)
                    return Double.compare(o1.getPrice(),o2.getPrice());
                else return Double.compare(o2.getPrice(),o1.getPrice());
            }
            case "arriveDate" -> {
                if (isAscending)
                    return o1.getRouteArriveDate().compareTo(o2.getRouteArriveDate());
                else return o2.getRouteArriveDate().compareTo(o1.getRouteArriveDate());
            }
            case "fromStation" -> {
                if (isAscending)
                    return o1.getFrom().name().compareTo(o2.getFrom().name());
                else return o2.getFrom().name().compareTo(o1.getFrom().name());
            }
            case "arriveStation" -> {
                if (isAscending)
                    return o1.getDestination().name().compareTo(o2.getDestination().name());
                else return o2.getDestination().name().compareTo(o1.getDestination().name());
            }
            case "runTime" -> {
                if (isAscending)
                    return Double.compare(o1.getRunTime(),o2.getRunTime());
                else return Double.compare(o2.getRunTime(),o1.getRunTime());
            }
            default -> {
                if (isAscending)
                    return o1.getDepartTime().compareTo(o2.getDepartTime());
                else return o2.getDepartTime().compareTo(o1.getDepartTime());
            }
        }
    }
}
