package flight.system.data;

public class Flight {

    private static final int LENGTH_OF_CITY = 3;
    private static final int MINIMUM_PRICE = 0;

    private final String from;
    private final String to;
    private final double price;

    public Flight(String from, String to, double price) {
        if (from.length() != LENGTH_OF_CITY || to.length() != LENGTH_OF_CITY) {
            throw new IllegalArgumentException("Length of city is different from 3");
        }
        if (price <= MINIMUM_PRICE) {
            throw new IllegalArgumentException("Price cannot be less than zero");
        }

        this.from = from;
        this.to = to;
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public double getPrice() {
        return price;
    }

    public String getTo() {
        return to;
    }

}
