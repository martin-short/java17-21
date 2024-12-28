package streams;

public class Trader {

    private final String name;
    private String city;

    public Trader(final String name, final String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}