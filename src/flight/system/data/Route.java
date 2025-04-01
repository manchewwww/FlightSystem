package flight.system.data;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private final List<String> cities;
    private final double totalPrice;

    public Route(List<String> cities, double totalPrice) {
        this.cities = new ArrayList<>(cities);
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return String.join(", ", cities) + ", " + totalPrice;
    }

    public List<String> getCities() {
        return cities;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
