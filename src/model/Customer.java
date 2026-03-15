package model;

import java.math.BigDecimal;

/**
 * <h1></h1>
 */
public class Customer {

    // Unique identifier of the customer
    private String cusId;

    // Customer Name
    private String cusName;

    // Current balance of customer's account
    private BigDecimal cusBalance;

    /**
     * <h1>Constructor: Customer.</h1>
     *
     * @param id customer id
     * @param name customer name
     */
    public Customer(String id, String name) {

        // Initialize id
        this.cusId = id;

        // Initialize name
        this.cusName = name;

        // Default balance is 0 when account is created
        this.cusBalance = BigDecimal.ZERO;
    }

    /**
     * <h1>Getter: Get current balance.</h1>
     *
     * @return account balance
     */
    public BigDecimal getCusBalance() {

        // Return current balance
        return this.cusBalance;
    }

    /**
     * <h1>Method: Add money into customer's account.</h1>
     *
     * @param amount double
     */
    public void addFund(BigDecimal amount) {

        // Increase balance
        cusBalance = cusBalance.add(amount);
    }

    /**
     * <h1>Method: Deduct fund from customer's account if enough balance exists.</h1>
     *
     * @param amount amount to deduct
     * @return true if deduction successful, otherwise false
     */
    public boolean deductFund(BigDecimal amount) {

        // Check if customer has enough balance
        if (cusBalance.compareTo(amount) >= 0) {
            cusBalance = cusBalance.subtract(amount);

            // deduction successful
            return true;
        }

        // deduction fail
        return false;
    }
}
