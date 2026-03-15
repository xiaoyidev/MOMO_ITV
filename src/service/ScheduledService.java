package service;

import java.time.LocalDate;

/**
 * Service responsible for scheduled payments.
 */
public interface ScheduledService {

    /**
     * Create scheduled payment.
     */
    void scheduleBill(String billId, LocalDate date);

    /**
     * Execute scheduled payments when date matches.
     */
    void processScheduledPayments(LocalDate today);
}
