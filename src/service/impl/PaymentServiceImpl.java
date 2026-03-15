package service.impl;

import model.Bill;
import model.Customer;
import model.Payment;
import service.PaymentService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import utils.BillStatus;
import repository.PaymentRepository;
import utils.PaymentState;
import utils.PaymentUtil;
import java.util.Comparator;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {

        this.paymentRepository = paymentRepository;
    }

    /**
     * <h1>Method: Pay a single bill.</h1>
     *
     * @param customer customer who pays
     * @param bill     bill to pay
     */
    @Override
    public void payBill(Customer customer, Bill bill) {

        // Check if bill is already paid
        if (bill.getStatus() == BillStatus.PAID) {
            System.out.println("Bill already paid.");
            return;
        }

        // Get bill amount
        BigDecimal amount = bill.getAmount();

        // Attempt to deduct money from customer's account
        boolean success = customer.deductFund(amount);

        if (!success) {

            // Not enough balance
            System.out.println("Insufficient balance to pay bill: " + bill.getBillId());
        }

        // Mark bill as paid
        bill.markAsPaid();

        // Create a new payment record
        Payment payment = new Payment(
                PaymentUtil.generatePaymentId(),
                bill.getBillId(),
                amount,
                null);

        payment.setPaymentState(PaymentState.PROCESSED);
        // Save payment
        this.paymentRepository.save(payment);

        // Print success message
        System.out.println("Payment successful for bill: " + bill.getBillId());
    }

    /**
     * <h1>Method: Pay multiple bills at once.</h1>
     * <p>Bills should be processed by earliest due date.</p>
     *
     * @param customer customer who pays
     * @param bills    list of bills
     */
    @Override
    public void payMultipleBills(Customer customer, List<Bill> bills) {

        // Sort bills by due date (earliest first)
        bills.sort(Comparator.comparing(Bill::getDueDate));

        // Calculate total amount
        BigDecimal total = BigDecimal.ZERO;

        for (Bill bill : bills) {

            if (bill.getStatus() == BillStatus.PAID) {
                continue;
            }

            total = total.add(bill.getAmount()) ;
        }

        // Check if customer has enough balance
        if (customer.getCusBalance().compareTo(total) < 0) {

            System.out.println("Sorry! Not enough fund to proceed with payment.");
            return;
        }

        // Process payment
        for (Bill bill : bills) {

            payBill(customer, bill);
        }
    }

    @Override
    public List<Payment> getPayments() {
        return this.paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentById(String billId) {
        // todo
        return new ArrayList<>();
    }
}
