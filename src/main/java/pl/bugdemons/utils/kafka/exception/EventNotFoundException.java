package pl.bugdemons.utils.kafka.exception;

public class EventNotFoundException extends Exception {

    public EventNotFoundException() {
        super("Event matching given predicate not found");
    }
}
