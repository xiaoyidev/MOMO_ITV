package model;
import utils.PaymentState;

import java.time.LocalDate;

/**
 * Represents a scheduled bill payment.
 */
public class ScheduledPayment {

    // Unique schedule id
    private String spId;

    // Bill id that will be paid
    private String billId;

    // Date when payment will be executed
    private LocalDate spDate;

    // Payment state
    private PaymentState paymentState;

    public ScheduledPayment(String spId, String billId, LocalDate scheduledDate) {

        // Assign id
        this.spId = spId;

        // Assign bill id
        this.billId = billId;

        // Assign schedule date
        this.spDate = scheduledDate;

        // Default state
        this.paymentState = PaymentState.PENDING;
    }

    public String getBillId() {
        return this.billId;
    }

    public LocalDate getScheduledDate() {
        return this.spDate;
    }

    public PaymentState getState() {
        return this.paymentState;
    }

    public void markProcessed() {
        this.paymentState = PaymentState.PROCESSED;
    }
}