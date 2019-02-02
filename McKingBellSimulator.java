package mckingbell;

import java.util.Random;

/**
 *
 * @author Dimitri Liska
 */
public class McKingBellSimulator {

    // One Tic = 1 second.
    public static int currentTic = 1;
    public static final int SIMULATION_RUN_TIME = 120;
    public static final int CHANCES_CAR_DRINVING_IN_POURCENT = 33;
    private static int NumberOfTheCar = 1;
    private static int driveOffCounter;
    private static int driveThroughCounter;
    private static int totalCarWaitingTime;
    private static int maxCarWaitingTime = 0;
//    private static int numberOfCarInLine;

    private static Random random = new Random();
    private static LinkedDeque<Car> carQueue = new LinkedDeque<>();
    private static LinkedDeque<Burger> burgerQueue = new LinkedDeque<>();
    private static LinkedDeque<Fries> friesStack = new LinkedDeque<>();
    private static Chef chef = new Chef(burgerQueue, friesStack);
    private static Clerck clerck = new Clerck(burgerQueue, friesStack, carQueue);

    public static void main(String[] args) {

        while (currentTic < SIMULATION_RUN_TIME) {

            //If there are more than 5 cars in the queue, 
            //and the last car has been waiting for more than a minute, it drives off.
            if (isCarDrivingOff()) {
                System.out.println("Car Driving oooofffffff: " + carQueue.getBack());
                carQueue.removeBack();
                driveOffCounter++;
//                numberOfCarInLine--;
            }

            //1 in 3 chance of a new car getting in the queue ( mark the time the car gets in line )
            if (isCarComming()) {

                int orderBurger = random.nextInt(11);
                int orderFries = random.nextInt(11);

                Car carComing = new Car(NumberOfTheCar, orderBurger, orderFries, currentTic);
                carQueue.addToBack(carComing);
                System.out.println("Car Driving In: " + carQueue.getBack());
                NumberOfTheCar++;
//                numberOfCarInLine++;
            }

            //The chef places burgers in queue and fries in stack.
            chef.cookBurger(currentTic);
            chef.cookFries();

            if (!carQueue.isEmpty() && !clerck.isBothOrderFulfilled()) {

                //The cleck take the order
                clerck.serveBurgerCarOrder(currentTic);

                clerck.serveFriesCarOrder();

            }
            if (!carQueue.isEmpty() && clerck.isBothOrderFulfilled()) {
                maxWaitTimeinLine(carQueue.getFront().getCarTimeQueue(), currentTic);
                System.out.println("Car Driving Out: " + carQueue.getFront());
                carQueue.removeFront();
                driveThroughCounter++;
            }

            currentTic++;
        }
        // Prints Car left in line at the end of the simulation
        while (!carQueue.isEmpty()) {
            System.out.println("List of cars left in line " + carQueue.removeFront());
        }
        System.out.println(driveThroughCounter + " # of Complete drive-though vehicules during the " + currentTic + " minutes simulation");
        System.out.println(avgCarWaitTime() + " is the average wait time of a car");
        System.out.println(maxCarWaitingTime + " is the max wait time of a car.");
        System.out.println(clerck.getNumberOfBurgerTossed() + " # of burgers thrown out.");
        System.out.println(driveOffCounter + " # of drives off.");

    }

    public static boolean isCarComming() {

        int IsCarComming = random.nextInt(100) + 1;

        if (IsCarComming >= (100 - CHANCES_CAR_DRINVING_IN_POURCENT)) {
//            System.out.println("IsCarComming: " + IsCarComming + " True");
            return true;
        }
//        System.out.println("IsCarComming: " + IsCarComming + " False");
        return false;
    }

    public static boolean isCarDrivingOff() {

        Car lastCarInLine = carQueue.getBack();

        if (carQueue.size() > 5 && lastCarInLine.carWaitTime(currentTic) > 1) {
            return true;
        }
        if (carQueue.size() > 10) {
            return true;
        }
        return false;
    }

    private static void maxWaitTimeinLine(int carArrivalTime, int currentTic) {
        int carWaitingTime = currentTic - carArrivalTime;
        totalCarWaitingTime += carWaitingTime;

        if (carWaitingTime > maxCarWaitingTime) {
            maxCarWaitingTime = carWaitingTime;
        }
    }

    public static int avgCarWaitTime() {
        return (totalCarWaitingTime / driveThroughCounter);
    }
}
