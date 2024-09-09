package g54900.skyjo.utils;

/**
 * Interface Observable represents the observable object for the Observer design
 * pattern.
 *
 * @author timmy
 */
public interface Observable {

    /**
     * Adds an observer to the different observers of the observable object.
     *
     * @param obs the observer to add
     */
    void registerObserver(Observer obs);

    /**
     * Removes an observer from the different observers of the observable
     * object.
     *
     * @param obs the observer to delete
     */
    void removeObserver(Observer obs);

    /**
     * Notifies the different observers of the modifications.
     */
    void notifyObservers();
}
