package service.impl;

import model.Bill;
import repository.BillRepository;
import service.BillService;
import utils.BillStatus;

import java.util.ArrayList;
import java.util.List;

public class BillServiceImpl implements BillService {
    // Repository storing bill data
    private final BillRepository billRepository;

    /**
     * Constructor injection.
     */
    public BillServiceImpl(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    /**
     * Create a new bill.
     */
    @Override
    public void createBill(Bill bill) {

        // Save bill to repository
        billRepository.save(bill);
    }

    /**
     * Delete bill by id.
     */
    @Override
    public void cancelBill(String billId) {

        // Find bill
        Bill bill = billRepository.findById(billId);

        if (bill == null) {

            System.out.println("Bill not found");
            return;
        }

        // Cancel bill
        bill.markAsCancelled();
        billRepository.save(bill);
    }

    /**
     * Update bill information.
     */
    @Override
    public void updateBill(Bill bill) {

        // Replace existing bill
        billRepository.save(bill);
    }

    /**
     * Get all bills.
     */
    @Override
    public List<Bill> getAllBills() {

        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(String billId) {
        Bill bill = billRepository.findById(billId);

        if (bill == null) {

            System.out.println("Bill not found");
        }
        return bill;
    }

    /**
     * Search bills by provider name.
     */
    @Override
    public List<Bill> searchByProvider(String provider) {

        List<Bill> result = new ArrayList<>();

        for (Bill bill : billRepository.findAll()) {

            // Compare provider name ignoring case
            if (bill.getServiceProvider()
                    .equalsIgnoreCase(provider)) {

                result.add(bill);
            }
        }

        return result;
    }

    /**
     * Return all unpaid bills.
     */
    @Override
    public List<Bill> getUnpaidBills() {

        List<Bill> result = new ArrayList<>();

        // Loop all bills
        for (Bill bill : billRepository.findAll()) {

            // Only select bills that are not paid
            if (bill.getStatus() == BillStatus.NOT_PAID) {

                result.add(bill);
            }
        }

        return result;
    }
}
