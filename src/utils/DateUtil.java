package utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
    * Parse a date string to LocalDate.
     *
     * @param dateStr date string
     * @return LocalDate object
     **/
    public static LocalDate parseDate(String dateStr) {

        // Convert string to LocalDate
        return LocalDate.parse(dateStr, FORMATTER);
    }

    /**
     * Format LocalDate to string.
     *
     * @param date LocalDate
     * @return formatted date string
     */
    public static String format(LocalDate date) {

        // Convert LocalDate to formatted string
        return date.format(FORMATTER);
    }

}
