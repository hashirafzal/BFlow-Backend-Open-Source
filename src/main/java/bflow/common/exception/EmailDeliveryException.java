package bflow.common.exception;

public class EmailDeliveryException extends RuntimeException {

    /**
     * Create a new email delivery failure exception.
     *
     * @param message error details
     */
    public EmailDeliveryException(final String message) {
        super(message);
    }

    /**
     * Create a new email delivery failure exception with a cause.
     *
     * @param message error details
     * @param cause underlying exception
     */
    public EmailDeliveryException(
            final String message,
            final Throwable cause
    ) {
        super(message, cause);
    }
}
