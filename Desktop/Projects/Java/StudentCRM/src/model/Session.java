package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private StringProperty subject;
    private StringProperty day;
    private StringProperty start;
    private StringProperty duration;
    private StringProperty room;
    private StringProperty capacity;
    private StringProperty enrolled;

    private List<Student> enrolledStudents;

    public Session(String subject, String day, String start, String duration, String room, String capacity, String enrolled) {
        this.subject = new SimpleStringProperty(subject);
        this.day = new SimpleStringProperty(day);
        this.start = new SimpleStringProperty(start);
        this.duration = new SimpleStringProperty(duration);
        this.room = new SimpleStringProperty(room);
        this.capacity = new SimpleStringProperty(capacity);
        this.enrolled = new SimpleStringProperty(enrolled);
        this.enrolledStudents = new ArrayList<>();
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public StringProperty dayProperty() {
        return day;
    }

    public StringProperty startProperty() {
        return start;
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public StringProperty roomProperty() {
        return room;
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    public StringProperty enrolledProperty() {
        return enrolled;
    }

    public String getSubject() {
        return subject.get();
    }

    public String getDay() {
        return day.get();
    }

    public String getStart() {
        return start.get();
    }

    public String getDuration() {
        return duration.get();
    }

    public String getRoom() {
        return room.get();
    }

    public String getCapacity() {
        return capacity.get();
    }

    public String getEnrolled() {
        return enrolled.get();
    }

    public static String formatTime(String time) {
        return time; // Adjust this method if you need specific time formatting
    }

    private String formatDuration(String duration) {
        return duration + " hrs";
    }
}
