package mckingbell;

/**
 *
 * @author Dimitri Liska
 */
public class Chef {

    LinkedDeque<Burger> burgerQueue;
    LinkedDeque<Fries> friesStack;

    public Chef(LinkedDeque<Burger> burgerQueue, LinkedDeque<Fries> friesStack) {
        this.burgerQueue = burgerQueue;
        this.friesStack = friesStack;
    }

    public void cookBurger(int currentTic) {
        while (burgerQueue.size() < 5) {
            Burger oneBurger = new Burger(currentTic);
            burgerQueue.addToBack(oneBurger);
//            System.out.println(oneBurger);
        }
    }

    public void cookFries() {
        while (friesStack.size() < 5) {
            Fries oneFries = new Fries();
            friesStack.addToFront(oneFries);
//            System.out.println(oneFries);
        }
    }

//    public void tossBurger(int currentTic) {
//        
//        //copy deck and pop
//        
//        burgerQueue.removeFront();
//        BurgerTossed++;
//    }
}
