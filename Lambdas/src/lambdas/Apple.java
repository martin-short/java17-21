package lambdas;

public class Apple {

    private Integer weight = -1;
    private String  color = "";

    public Apple(final Integer weight, final String color) {
        this.weight = weight;
        this.color  = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(final Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String toString() {
        return "Apple{" + "color='" + color + '\'' + ", weight=" + weight + '}';
    }
}
