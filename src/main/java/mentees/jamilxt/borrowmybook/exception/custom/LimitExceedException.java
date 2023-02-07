package mentees.jamilxt.borrowmybook.exception.custom;

public class LimitExceedException extends RuntimeException {
    public LimitExceedException(String message) {
        super(message);
    }
}
