package repository;

import model.Payment;

import java.util.List;

public interface PaymentRepository {

    /**
     * <h1>Method: Save a transaction.</h1>
     */
    public void save(Payment transaction);

    /**
     * <h1>Method: Get all transactions.</h1>
     */
    public List<Payment> findAll();

    public Payment findById(String paymentId);
}
