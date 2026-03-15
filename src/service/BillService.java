package service;

import model.Bill;

import java.util.List;

public interface BillService {

    /**
     * Create a new bill.
     */
    void createBill(Bill bill);

    /**
     * Delete a bill by id.
     */
    void cancelBill(String billId);

    /**
     * Update a bill.
     */
    void updateBill(Bill bill);

    /**
     * Get all bills.
     */
    List<Bill> getAllBills();

    Bill getBillById(String billId);

    /**
     * Search bills by provider.
     */
    List<Bill> searchByProvider(String provider);

    /**
     * Get all unpaid bills.
     */
    List<Bill> getUnpaidBills();
}
