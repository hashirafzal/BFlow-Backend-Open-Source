package bflow.subscription.enums;

public enum PaymentStatus {

    /** Payment has been created but not yet completed. */
    PENDING,

    /** Payment completed successfully. */
    PAID,

    /** Payment failed during processing. */
    FAILED,

    /** Payment has been refunded. */
    REFUNDED
}
