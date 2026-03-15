package utils;

public class PaymentUtil {

    private static int counter = 1;

    public static String generatePaymentId() {

        return "MOMOTST-" + counter++;
    }
}
