package repository;

import model.Payment;
import model.ScheduledPayment;
import java.util.List;

public interface ScheduledRepository {

    public void save(Payment payment);

    public List<Payment> findAll();
}
