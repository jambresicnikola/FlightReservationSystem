package hr.java.flightreservationsystem.exception;

public class IllegalSeatNumberException extends Exception {
    public IllegalSeatNumberException() {
    }

    public IllegalSeatNumberException(String message) {
        super(message);
    }

    public IllegalSeatNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalSeatNumberException(Throwable cause) {
        super(cause);
    }

    public IllegalSeatNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
