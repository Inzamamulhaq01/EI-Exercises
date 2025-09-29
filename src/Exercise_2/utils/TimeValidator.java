//src/utils/TimeValidator.java

package Exercise_2.utils;

public class TimeValidator
{
    public static boolean isValidTime(String time) {
        // Regex for HH:MM format (24-hour clock)
        if (!time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            System.out.println("Error: Invalid time format. Use 24 hour format");
            return false;
        }

        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            System.out.println("Error: Time must be between 00:00 and 23:59.");
            return false;
        }

        return true;
    }
}
