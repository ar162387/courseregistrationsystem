package model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class UPassSession {
    private final IntegerProperty subject;
    private final StringProperty day;
    private final IntegerProperty start;
    private final IntegerProperty duration;
    private final StringProperty room;
    private final IntegerProperty capacity;
    private final IntegerProperty enrolled;
    private UPassSessions sessions;

    public UPassSession(int subject, String day, int start, int duration, String room, int capacity) {
        this.subject = new SimpleIntegerProperty();
        this.subject.set(subject);
        this.duration = new SimpleIntegerProperty();
        this.duration.set(duration);
        this.start = new SimpleIntegerProperty();
        this.start.set(start);
        this.capacity = new SimpleIntegerProperty();
        this.capacity.set(capacity);
        this.enrolled = new SimpleIntegerProperty();
        this.enrolled.set(0);
        this.day = new SimpleStringProperty();
        this.day.set(day);
        this.room = new SimpleStringProperty();
        this.room.set(room);
    }

    public void update(int subject, String day, int start, int duration, String room, int capacity, int enrolled) {
        this.subject.set(subject);
        this.duration.set(duration);
        this.start.set(start);
        this.capacity.set(capacity);
        this.enrolled.set(enrolled);
        this.day.set(day);
        this.room.set(room);
    }

    public UPassSessions getSessions() {
        return sessions;
    }

    public void setSessions(UPassSessions sessions) {
        this.sessions = sessions;
    }

    public boolean canEnrol() {
        return enrolled.get() < capacity.get();
    }

    public void enrol() {
        enrolled.set(enrolled.get() + 1);
    }

    public void withdraw() {
        enrolled.set(enrolled.get() - 1);
    }

    public ArrayList<StudentUser> getAllEnrolledStudents(){
        return this.sessions.getAllEnrolledStudents(this);
    }

    private String formattedTime() {
        if (start.get() < 10)
            return "0" + start.get() + ":00";
        else
            return start.get() + ":00";
    }

    public int getAvailability() {
        return capacity.get() - enrolled.get();
    }

    public int getSubject() {
        return subject.get();
    }

    public IntegerProperty subjectProperty() {
        return subject;
    }

    public String getDay() {
        return day.getValue();
    }

    public ReadOnlyStringProperty dayProperty() {
        return day;
    }

    public int getStart() {
        return start.get();
    }

    public IntegerProperty startProperty() {
        return start;
    }

    public int getDuration() {
        return duration.get();
    }

    public IntegerProperty durationProperty() {
        return duration;
    }

    public String getRoom() {
        return room.getValue();
    }

    public ReadOnlyStringProperty roomProperty() {
        return room;
    }

    public int getCapacity() {
        return capacity.get();
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled.get();
    }

    public IntegerProperty enrolledProperty() {
        return enrolled;
    }

    public boolean hasSubject(String subject) {
        return Integer.toString(getSubject()).toLowerCase().contains(subject.toLowerCase().trim());
    }

    public boolean hasDay(String day) {
        return getDay().toLowerCase().contains(day.toLowerCase().trim());
    }

    public boolean matches(int subject, String day) {
        return this.subject.get() == subject && this.day.getValue().equals(day);
    }

    public boolean matches(UPassSession session) {
        return matches(session.subject.get(), session.day.getValue());
    }
}
