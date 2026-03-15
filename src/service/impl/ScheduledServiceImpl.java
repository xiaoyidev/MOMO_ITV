package service.impl;

import model.Bill;
import model.Customer;
import model.Payment;
import model.ScheduledPayment;
import repository.BillRepository;
import repository.ScheduledRepository;
import service.BillService;
import service.PaymentService;
import service.ScheduledService;
import utils.DateUtil;
import utils.PaymentState;
import utils.PaymentUtil;

import java.time.LocalDate;
import java.util.List;

public class ScheduledServiceImpl implements ScheduledService {

    private final ScheduledRepository scheduleRepository;
    private final BillService billService;
    private final PaymentService paymentService;
    private final Customer customer;

    public ScheduledServiceImpl(
            ScheduledRepository scheduleRepository,
            BillService billService,
            PaymentService paymentService,
            Customer customer) {

        this.scheduleRepository = scheduleRepository;
        this.billService = billService;
        this.paymentService = paymentService;
        this.customer = customer;
    }

    /**
     * Create scheduled payment.
     *
     * @param billId String
     * @param date LocalDate
     */
    @Override
    public void scheduleBill(String billId, LocalDate date) {


        // todo
        /*Payment payment = paymentService.getPaymentById(billId);

        scheduleRepository.save(payment);

        System.out.println(
                "Payment for bill id " + billId +
                        " is scheduled on " + DateUtil.format(date));*/
    }

    /**
     * Execute scheduled payments when date matches.
     *
     * @param today LocalDate
     */
    @Override
    public void processScheduledPayments(LocalDate today) {

        List<Payment> schedulePayments = scheduleRepository.findAll();

        for (Payment payment : schedulePayments) {

            if (payment.getPaymentState() == PaymentState.PENDING &&
                    payment.getScheduledDate().equals(today)) {

                Bill bill = billService.getBillById(payment.getBillId());

                if (bill != null) {

                    paymentService.payBill(customer, bill);

                    payment.markProcessed();
                }
            }
        }
    }
}
