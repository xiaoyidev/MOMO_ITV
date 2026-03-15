package model;

import utils.BillStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <h1>Represents a bill issued by a service provider.</h1>
 */
public class Bill {
    // Unique bill id
    private String billId;

    // Name of service provider
    private String serviceProvider;

    private String billType;

    // Bill amount that customer needs to pay
    private BigDecimal amount;

    // Due date for payment
    private LocalDate dueDate;

    // Current bill status
    private BillStatus status;

    /**
     * <h1>Constructor: Bill.</h1>
     * <p>Constructor for creating a bill.</p>
     *
     * @param billId bill identifier
     * @param type type of Bill
     * @param serviceProvider provider name
     * @param amount bill amount
     * @param dueDate payment due date
     */
    public Bill(String billId, String type, String serviceProvider, BigDecimal amount, LocalDate dueDate) {

        // Assign bill id
        this.billId = billId;

        this.billType  = type ;
        // Assign service provider
        this.serviceProvider = serviceProvider;

        // Assign bill amount
        this.amount = amount;

        // Assign due date
        this.dueDate = dueDate;

        // Default status when bill created
        this.status = BillStatus.NOT_PAID;
    }

    /**
     * <h1>Method: Mark bill as paid after successful payment.</h1>
     */
    public void markAsPaid() {

        // Change bill status to PAID
        this.status = BillStatus.PAID;
    }

    /**
     * <h1>Method: Mark bill as cancelled.</h1>
     */
    public void markAsCancelled() {

        // Change bill status to CANCELLED
        this.status = BillStatus.CANCELLED;
    }

    /**
     * <h1>Method: Mark bill as pending.</h1>
     */
    public void markAsPending() {

        // Change bill status to NOT_PAID
        this.status = BillStatus.NOT_PAID;
    }

    /**
     * <h1>Getter: Get bill identifier.</h1>
     */
    public String getBillId() {

        // Return bill id
        return this.billId;
    }

    /**
     * <h1>Getter: Get service provider name.</h1>
     */
    public String getServiceProvider() {

        // Return provider name
        return this.serviceProvider;
    }

    /**
     * <h1>Getter: Get bill amount.</h1>
     */
    public BigDecimal getAmount() {

        // Return amount
        return this.amount;
    }

    /**
     * <h1>Getter: Get bill due date.</h1>
     */
    public LocalDate getDueDate() {

        // Return due date
        return this.dueDate;
    }

    /**
     * <h1>Getter: Get bill status.</h1>
     */
    public BillStatus getStatus() {

        // Return bill status
        return this.status;
    }

    public String getBillType() {
        return billType;
    }
}
