package mckingbell;

/**
 *
 * @author Dimitri Liska
 */
public class Clerck {

    LinkedDeque<Burger> burgerQueue;
    LinkedDeque<Fries> friesStack;
    LinkedDeque<Car> carQueue;
    private int burgerTossed;
    private int burgerNeeded;
    private int friesNeeded;
    public static final int BURGER_LIFE_TIME = 10;

    public Clerck(LinkedDeque<Burger> burgerQueue, LinkedDeque<Fries> friesStack, LinkedDeque<Car> carQueue) {
        this.burgerQueue = burgerQueue;
        this.friesStack = friesStack;
        this.carQueue = carQueue;
    }

    public void takeCarOrder() {

    }

    public void serveBurgerCarOrder(int currentTic) {
        burgerNeeded = carQueue.getFront().getNumberOfBurgersForOrder();

        while (!burgerQueue.isEmpty() && !isBurgerOrderFulfilled()) {
            if (checkBurgerLifeTime(currentTic) > BURGER_LIFE_TIME) {
                TossBurger();
            } else {
                carQueue.getFront().setNumberOfBurgersForOrder(burgerNeeded - 1);
                burgerNeeded--;
                burgerQueue.removeFront();
            }
        }
    }

    public void serveFriesCarOrder() {
        friesNeeded = carQueue.getFront().getNumberOfFriesForOrder();

        while (!friesStack.isEmpty() && !isFriesOrderFulfilled()) {
            carQueue.getFront().setNumberOfFriesForOrder(friesNeeded - 1);
            friesNeeded--;
            friesStack.removeFront();
        }
    }

    public boolean isBurgerOrderFulfilled() {
        if (carQueue.getFront().getNumberOfBurgersForOrder() == 0) {
            return true;
        }
        return false;
    }

    public boolean isFriesOrderFulfilled() {
        if (carQueue.getFront().getNumberOfFriesForOrder() == 0) {
            return true;
        }
        return false;
    }

    public boolean isBothOrderFulfilled() {
        if (isBurgerOrderFulfilled() && isFriesOrderFulfilled()) {
            return true;
        }
        return false;
    }

    private int checkBurgerLifeTime(int currentTime) {
        int totalWaitTimeBurger = currentTime - burgerQueue.getFront().getTimeBurgerMade();

        return totalWaitTimeBurger;
    }

    private void TossBurger() {

        burgerQueue.removeFront();
        burgerTossed++;
    }

    public int getNumberOfBurgerTossed() {
        return burgerTossed;
    }
}
