package hr.java.flightreservationsystem.exception;

public class IllegalAirlineIndexSelectedException extends Exception {
    public IllegalAirlineIndexSelectedException() {
    }

    public IllegalAirlineIndexSelectedException(String message) {
        super(message);
    }

    public IllegalAirlineIndexSelectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAirlineIndexSelectedException(Throwable cause) {
        super(cause);
    }

    public IllegalAirlineIndexSelectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
