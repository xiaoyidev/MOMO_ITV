package service;

import model.Bill;
import model.Customer;
import model.Payment;

import java.util.List;

/**
 * <h1>Service responsible for handling bill payments.</h1>
 */
public interface PaymentService{

    /**
     * <h1>Method: Pay a single bill.</h1>
     *
     * @param customer customer who pays
     * @param bill bill to pay
     */
    void payBill(Customer customer, Bill bill);

    /**
     * <h1>Method: Pay multiple bills at once.</h1>
     * <p>Bills should be processed by earliest due date.</p>
     *
     * @param customer customer who pays
     * @param bills list of bills
     */
    void payMultipleBills(Customer customer, List<Bill> bills);

    List<Payment> getPayments();

    List<Payment> getPaymentById(String paymentId);
}
