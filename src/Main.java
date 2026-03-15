import model.Bill;
import model.Customer;
import model.Payment;
import repository.BillRepository;
import repository.PaymentRepository;
import repository.ScheduledRepository;
import repository.impl.BillRepositoryImpl;
import repository.impl.PaymentRepositoryImpl;
import repository.impl.ScheduledRepositoryImpl;
import service.BillService;
import service.PaymentService;
import service.ScheduledService;
import service.impl.BillServiceImpl;
import service.impl.PaymentServiceImpl;
import service.impl.ScheduledServiceImpl;
import utils.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final static Customer customer = new Customer("cus01", "MM_USER");

    private final static BillRepository billRepository = new BillRepositoryImpl();
    private final static BillService billService = new BillServiceImpl(billRepository);

    private final static PaymentRepository paymentRepository = new PaymentRepositoryImpl();

    private final static PaymentService paymentService = new PaymentServiceImpl(paymentRepository);

    private final static ScheduledRepository scheduledRepository = new ScheduledRepositoryImpl();

    private final static ScheduledService scheduledService =
            new ScheduledServiceImpl(scheduledRepository, billService,paymentService,customer);

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        seedData();

        while (true) {

            System.out.print("\n\n\n");
            System.out.print("SELECT PROGRAM:\n");
            System.out.print("1 CASH_IN\n");
            System.out.print("2 LIST_BILL\n");
            System.out.print("3 PAY\n");
            System.out.print("4 DUE_DATE\n");
            System.out.print("5 SCHEDULE\n");
            System.out.print("6 LIST_PAYMENT\n");
            System.out.print("7 SEARCH_BILL_BY_PROVIDER\n");
            System.out.print("8 CHECK_MY_MONEY\n");
            System.out.print("===================================\n");
            System.out.print("$ ");

            String input = scanner.nextLine();

            String[] command = input.split(" ");

            switch (command[0]) {

                case "CASH_IN":

                    cashIn(command);
                    break;

                case "CHECK_MY_MONEY":

                    System.out.println("Your available balance: " + customer.getCusBalance());
                    break;

                case "LIST_BILL":

                    listBills();
                    break;

                case "PAY":

                    payBills(command);
                    break;

                case "LIST_PAYMENT":

                    listPayments();
                    break;

                case "SEARCH_BILL_BY_PROVIDER":
                    listBillsByProvider(command);
                    break;

                case "DUE_DATE":
                    listUnpaidBills();
                    break;

                case "SCHEDULE":
                    payBillWithSchedule(command);
                    break;

                case "EXIT":

                    return;

                default:

                    System.out.println("Invalid command");
            }
        }

    }

    private static void seedData() {

        billRepository.save(new Bill(
                "1",
                "ELECTRIC",
                "ENV HCMC",
                new BigDecimal(200000),
                LocalDate.of(2026,3,15)
        ));

        billRepository.save(new Bill(
                "2",
                "WATER",
                "SAVAGO",
                new BigDecimal(175000),
                LocalDate.of(2026,3,28)
        ));

        billRepository.save(new Bill(
                "3",
                "INTERNET",
                "VNPT",
                new BigDecimal(800000),
                LocalDate.of(2026,3,30)
        ));
    }

    private static void listPayments() {

        int index = 1;

        List<Payment> payments = paymentService.getPayments();

        if (payments.isEmpty()) {
            System.out.println("None of Payment");
            return;
        }

        for (Payment t : payments) {

            System.out.println(
                    index++ + ". " +
                            t.getAmount() + " " +
                            t.getPaymentDate() + " " +
                            t.getPaymentState() + " " +
                            t.getBillId()
            );
        }
    }

    private static void listBills() {

        System.out.println("Bill No. Type Amount Due Date State");

        int index = 1;

        for (Bill bill : billService.getAllBills()) {

            printBill(bill, index++);
        }
    }

    private static void payBills(String[] command) {

        List<Bill> bills = new ArrayList<>();

        for (int i = 1; i < command.length; i++) {

            Bill bill = billService.getBillById(command[i]);

            if (bill == null) {

                System.out.println("Sorry! Not found a bill with such id");
                return;
            }

            bills.add(bill);
        }

        paymentService.payMultipleBills(customer, bills);

        System.out.println("Your current balance is: " + customer.getCusBalance());
    }

    private static void payBillWithSchedule(String[] command) {

        String billId = command[1];

        LocalDate date = DateUtil.parseDate(command[2]);

        scheduledService.scheduleBill(billId, date);
    }

    private static void listBillsByProvider(String[] command) {

        String provider = command[1];
        List<Bill> bills = billService.searchByProvider(provider);

        int index = 1;

        for (Bill bill : bills) {

            printBill(bill, index++);
        }
    }
    private static void listUnpaidBills() {

        List<Bill> bills = billService.getUnpaidBills();

        int index = 1;

        for (Bill bill : bills) {

            printBill(bill, index++);
        }
    }

    private static void printBill(Bill bill, Integer index) {
        System.out.println(
                index + ". " +
                        bill.getBillType() + " " +
                        bill.getAmount() + " " +
                        bill.getDueDate() + " " +
                        bill.getStatus()+ " " +
                        bill.getServiceProvider()
        );
    }

    private static void cashIn(String[] command){

        BigDecimal amount = new BigDecimal(command[1]);

        customer.addFund(amount);

        System.out.println("Your available balance: " + customer.getCusBalance());
    }
}