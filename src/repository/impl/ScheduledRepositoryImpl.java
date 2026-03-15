package repository.impl;

import model.Payment;
import model.ScheduledPayment;
import repository.ScheduledRepository;

import java.util.List;
import java.util.ArrayList;

public class ScheduledRepositoryImpl implements ScheduledRepository {

    private final List<Payment> schedules = new ArrayList<>();

    @Override
    public void save(Payment payment) {
        this.schedules.add(payment);
    }

    @Override
    public List<Payment> findAll() {
        return this.schedules;
    }
}
