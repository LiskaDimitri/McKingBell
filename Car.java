package mckingbell;

/**
 *
 * @author Dimitri Liska
 */
public class Car {

    private int numberOfBurgersForOrder;
    private int numberOfFriesForOrder;
    private final int TIME_ARRIVED_IN_QUEUE;
    private int NumberOfTheCar;

    public void Car() {
    }

    public Car(int Number, int orderBurger, int orderFries, int ticArrived) {
        NumberOfTheCar = Number;
        numberOfBurgersForOrder = orderBurger;
        numberOfFriesForOrder = orderFries;
        TIME_ARRIVED_IN_QUEUE = ticArrived;

    }

    public int carWaitTime(int currentTic) {

        return currentTic - TIME_ARRIVED_IN_QUEUE;
    }

    public int getNumberOfBurgersForOrder() {
        return numberOfBurgersForOrder;
    }

    public void setNumberOfBurgersForOrder(int numberOfBurgersForOrder) {
        this.numberOfBurgersForOrder = numberOfBurgersForOrder;
    }

    public int getNumberOfFriesForOrder() {
        return numberOfFriesForOrder;
    }

    public void setNumberOfFriesForOrder(int numberOfFriesForOrder) {
        this.numberOfFriesForOrder = numberOfFriesForOrder;
    }

    public int getCarTimeQueue() {
        return TIME_ARRIVED_IN_QUEUE;
    }

    @Override
    public String toString() {
        return "Car#" + NumberOfTheCar + " { Order: " + "Burgers:" + numberOfBurgersForOrder + ", Fries:" + numberOfFriesForOrder + ", Tic Arrived:" + TIME_ARRIVED_IN_QUEUE + '}';
    }
}
