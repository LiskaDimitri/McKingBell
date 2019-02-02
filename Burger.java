package mckingbell;

/**
 *
 * @author Dimitri Liska
 */
public class Burger {

    private final int TIME_WHEN_MADE;

    public Burger(int currentTic) {
        TIME_WHEN_MADE = currentTic;
    }

    public int getTimeBurgerMade() {
        return TIME_WHEN_MADE;
    }

    @Override
    public String toString() {
        return "Burger{" + "TIME_WHEN_MADE=" + TIME_WHEN_MADE + '}';
    }
}
