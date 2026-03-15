package repository;

import model.Bill;

import java.util.List;

/**
 * <h1>Repository class that stores bills in memory.</h1>
 */
public interface BillRepository {

    /**
     * Save a bill.
     */
    void save(Bill bill);

    /**
     * Find bill by billId.
     */
    Bill findById(String billId);

    /**
     * Return all bills.
     */
    List<Bill> findAll();
}
