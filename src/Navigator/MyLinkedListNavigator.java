package Navigator;

import LinkedList.MyLinkedList;
import LinkedList.MyArrayList;
import LinkedList.MyList;

import java.util.*;

public class MyLinkedListNavigator implements MyNavigator {
    MyList<Route> routeLinkedList = new MyLinkedList<>();
    @Override
    public void addRoute(Route route) {
        if (!contains(route))
            routeLinkedList.add(route);
        else
            System.out.println("Такой маршрут уже есть");
    }

    @Override
    public void removeRoute(String routeId) {
        Route current = getRoute(routeId);
        if (current!=null)
            routeLinkedList.remove(current);
    }

    @Override
    public boolean contains(Route route) {
        for (Route r:routeLinkedList){
            if (r.equals(route))
                return true;
        }
        return false;
    }

    @Override
    public int size() {
        return routeLinkedList.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (Route route:routeLinkedList) {
            if (route.getId().compareTo(routeId)==0){
                route.addPopularity();
                return route;
            }
        }
        return null;
    }

    @Override
    public void chooseRoute(String routeId) {
        getRoute(routeId).addPopularity();
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        MyArrayList<Route> sortedRouteList = new MyArrayList<>();
        // Добавьте все маршруты, удовлетворяющие условию startPoint и endPoint, в sortedRouteList
        for (Route route : routeLinkedList) {
            if (route.getStartPoint().equals(startPoint) && route.getEndPoint().equals(endPoint)) {
                sortedRouteList.add(route);
            }
        }
        // Отсортируйте список sortedRouteList по определенному критерию (например, по времени или расстоянию)
        sortedRouteList.sort(new RouteDistanceComparator());
        sortedRouteList.sort(new RoutePopularityComparator());
        sortedRouteList.sort(new RouteFavoriteComparator());
        return sortedRouteList;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        MyArrayList<Route> sortedRouteList = new MyArrayList<>();
        for (Route route : routeLinkedList) {
            if (route.getIsFavorite() && route.getEndPoint().equals(destinationPoint)) {
                sortedRouteList.add(route);
            }
        }
        sortedRouteList.sort(Comparator.comparing(Route::getDistance));
        sortedRouteList.sort(new RoutePopularityComparator());
        return sortedRouteList;
    }

    @Override
    public Iterable<Route> getTopFiveRoutes() {
        MyArrayList<Route> sortedRouteList = new MyArrayList<>();
        for (Route route : routeLinkedList) {
            sortedRouteList.add(route);
        }
        sortedRouteList.sort(new RoutePopularityComparator());
        MyArrayList<Route> resultSortedRouteList = new MyArrayList<>();
        for (int i = 0; i<5; i++){
            resultSortedRouteList.add(sortedRouteList.get(i));
        }
        resultSortedRouteList.sort(Comparator.comparing(Route::getDistance));
        resultSortedRouteList.sort(Comparator.comparing(Route::getCountOFPoints));
        return resultSortedRouteList;
    }

    @Override
    public void printAllRouts(){
        for (Route r:routeLinkedList){
            System.out.println(r);
        }
    }

    public void changeFavoriteFLag(Route route){
        if(route.getIsFavorite())
            route.setFavorite(false);
        else
            route.setFavorite(true);
    }
}
