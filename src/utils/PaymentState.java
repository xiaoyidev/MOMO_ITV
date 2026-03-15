package utils;

/**
 * Payment state for scheduled payment.
 */
public enum PaymentState {
    // Payment waiting for scheduled date
    PENDING,

    // Payment has been executed
    PROCESSED
}
