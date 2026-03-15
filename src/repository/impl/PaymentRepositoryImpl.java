package repository.impl;

import model.Bill;
import repository.PaymentRepository;
import model.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepositoryImpl implements PaymentRepository {

    // List storing transactions
    private final Map<String, Payment> paymentStorage = new HashMap<>();
    /**
     * Save a payment.
     *
     * @param payment payment
     */
    @Override
    public void save(Payment payment) {

        // save payment
        this.paymentStorage.put(payment.getPaymentId(), payment);
    }

    /**
     * Get all transactions.
     */
    @Override
    public List<Payment> findAll() {

        // Convert map values to list
        return new ArrayList<>(paymentStorage.values());
    }

    @Override
    public Payment findById(String paymentId) {
        // Return bill from map
        return paymentStorage.get(paymentId);
    }
}
