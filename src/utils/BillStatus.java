package utils;

/**
 * <h1>Enum representing the status of a bill.</h1>
 */
public enum BillStatus {

    // Bill is created but has not been paid yet
    NOT_PAID,

    // Bill has been paid successfully
    PAID,

    // Bill has been cancelled or removed
    CANCELLED
}
