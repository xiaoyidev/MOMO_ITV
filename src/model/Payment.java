package model;

import utils.PaymentState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <h1>Represents a payment transaction.</h1>
 * <p>Used for tracking payment history.</p>
 */
public class Payment {

    // Transaction Id
    private String paymentId;

    // Bill identifier that was paid
    private String billId;

    // Amount paid
    private BigDecimal amount;

    // Payment state
    private PaymentState paymentState;

    // Payment timestamp
    private LocalDateTime paymentDate;

    // Assign schedule date
    private LocalDate scheduledDate;

    /**
     * <p>Constructor: Transaction</p>
     * <p>Constructor to create a transaction record.</p>
     *
     * @param paymentId payment identifier
     * @param billId bill identifier
     * @param amount payment amount
     */
    public Payment(String paymentId, String billId, BigDecimal amount, LocalDate scheduledDate) {

        // Assign transaction id
        this.paymentId = paymentId;

        // Assign bill id
        this.billId = billId;

        // Assign amount
        this.amount = amount;

        // Record current time as payment time
        this.paymentDate = LocalDateTime.now();

        this.paymentState = PaymentState.PENDING;

        this.scheduledDate = scheduledDate;
    }

    /**
     * <h1>Getter: Get payment time.</h1>
     */
    public LocalDateTime getPaymentDate() {

        // Return payment timestamp
        return paymentDate;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public String getBillId() {
        return billId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentState getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    public void markProcessed() {
        this.paymentState = PaymentState.PROCESSED;
    }
}
