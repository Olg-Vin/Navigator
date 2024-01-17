package Navigator;

import java.util.*;

public class Route implements Comparable<Route>{
    private String id; // уникальный идентификатор
    private Double distance; // видимо длинная маршрута в единицах измерения
    private Integer popularity; // кол-во запросов к этому маршруту
    private boolean isFavorite; // является ли она избранной для данного пользователя
    private List<String> locationPoints = new ArrayList<>(); // список строк (Точка, для упрощения, просто названия городов);

    public String getId() {
        return id;
    }
    public Double getDistance() {
        return distance;
    }
    public Integer getPopularity() {
        return popularity;
    }
    public List<String> getLocationPoints() {
        return locationPoints;
    }
    public boolean getIsFavorite() {
        return isFavorite;
    }

    public Route(String id, Double distance, List<String> locationPoints) {
        setId(id);
        setDistance(distance);
        setPopularity(0);
        setFavorite(false);
        setLocationPoints(locationPoints);
    }
    public Route(String id, Double distance, int popularity, List<String> locationPoints) {
        setId(id);
        setDistance(distance);
        setPopularity(popularity);
        setFavorite(false);
        setLocationPoints(locationPoints);
    }
    public Route(String id, Double distance, boolean isFavorite, List<String> locationPoints) {
        setId(id);
        setDistance(distance);
        setPopularity(0);
        setFavorite(isFavorite);
        setLocationPoints(locationPoints);
    }
    public Route(String id, Double distance,
                 Integer popularity, boolean isFavorite,
                 List<String> locationPoints) {
        setId(id);
        setDistance(distance);
        setPopularity(popularity);
        setFavorite(isFavorite);
        setLocationPoints(locationPoints);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setDistance(Double distance) {
        this.distance = distance;
    }
    private void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    private void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }
    public void addPopularity(){
        setPopularity(++popularity);
    }
    public String getStartPoint(){
        return locationPoints.get(0);
    }
    public String getEndPoint(){
        int len = locationPoints.size();
        return locationPoints.get(--len);
    }
    public int getCountOFPoints(){
        return locationPoints.size();
    }

    @Override
    public int compareTo(Route o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return (Double.compare(distance, route.distance) == 0 &&
                popularity == route.popularity && isFavorite == route.isFavorite &&
                Objects.equals(id, route.id) &&
                Objects.equals(locationPoints, route.locationPoints)) ||

                (Objects.equals(getStartPoint(), route.getStartPoint()) &&
                        getDistance() == route.getDistance() &&
                        Objects.equals(getEndPoint(), route.getEndPoint()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, popularity, isFavorite, locationPoints);
    }
//  Обязательно реализуйте equals и hashCode для Navigator.Route.

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + locationPoints +
                '}';
    }
}
class RouteFavoriteComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        if (o1.getIsFavorite() == o2.getIsFavorite())
            return 0;
        else if (o1.getIsFavorite() && !o2.getIsFavorite())
            return -1;
        return 1;
    }
}
class RouteDistanceComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return -o1.getDistance().compareTo(o2.getDistance());
    }
}
class RoutePopularityComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return -(o1.getPopularity().compareTo(o2.getPopularity()));
    }
}


