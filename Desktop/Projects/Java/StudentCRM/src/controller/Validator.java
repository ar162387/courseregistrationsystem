package controller;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<String> errors;

    public Validator() {
        errors = new ArrayList<>();
    }

    public List<String> validateSessionFields(String subject, String day, String start, String duration, String room, String capacity, String enrolled) {
        errors.clear();

        if (subject == null || subject.trim().isEmpty()) {
            errors.add("Subject cannot be empty.");
        }
        if (day == null || day.trim().isEmpty()) {
            errors.add("Day cannot be empty.");
        }
        if (start == null || start.trim().isEmpty()) {
            errors.add("Start time cannot be empty.");
        }
        if (duration == null || duration.trim().isEmpty()) {
            errors.add("Duration cannot be empty.");
        }
        if (room == null || room.trim().isEmpty()) {
            errors.add("Room cannot be empty.");
        }
        if (capacity == null || capacity.trim().isEmpty() || !isNumeric(capacity)) {
            errors.add("Capacity must be a number.");
        }
        if (enrolled == null || enrolled.trim().isEmpty() || !isNumeric(enrolled)) {
            errors.add("Enrolled must be a number.");
        }

        return errors;
    }

    public void clearErrors() {
        errors.clear();
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
