package repository.impl;

import model.Bill;
import repository.BillRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>Repository class that stores bills in memory.</h1>
 * <p>*NOTE*: Use a HashMap to simulate an in-memory database.</p>
 */
public class BillRepositoryImpl implements BillRepository {

    // Map used as in-memory database
    private final Map<String, Bill> billStorage = new HashMap<>();

    /**
     * Save a bill.
     *
     * @param bill Bill
     */
    @Override
    public void save(Bill bill) {

        // Store bill by id
        this.billStorage.put(bill.getBillId(), bill);
    }

    /**
     * Find bill by billId.
     *
     * @param billId String
     */
    @Override
    public Bill findById(String billId) {

        // Return bill from map
        return billStorage.get(billId);
    }

    /**
     * Return all bills.
     */
    @Override
    public List<Bill> findAll() {

        // Convert map values to list
        return new ArrayList<>(billStorage.values());
    }
}
