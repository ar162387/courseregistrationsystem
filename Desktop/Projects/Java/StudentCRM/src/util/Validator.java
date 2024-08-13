package util;

public class Validator {

    public static boolean validateSession(String subject, String day, String start, String duration,
            String room, String capacity, String enrolled) {
        if (subject == null || subject.trim().isEmpty()) {
            return false;
        }
        if (day == null || day.trim().isEmpty()) {
            return false;
        }
        if (start == null || !start.matches("\\d{1,2}(:\\d{2})?")) {
            return false;
        }
        if (duration == null || !duration.matches("\\d{1,2}( hrs)?")) {
            return false;
        }
        if (room == null || room.trim().isEmpty()) {
            return false;
        }
        if (capacity == null || !capacity.matches("\\d+")) {
            return false;
        }
        if (enrolled == null || !enrolled.matches("\\d+")) {
            return false;
        }

        return true;
    }
}
