package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentUser extends User {

    private final ObservableList<UPassSession> sessions = FXCollections.observableArrayList();
    private UPMApplication application;

    public StudentUser(String email, String name, String password) {
        super(email, name, password);
    }

    public UPMApplication getApplication() {
        return application;
    }

    public void setApplication(UPMApplication application) {
        this.application = application;
    }

    public final ObservableList<UPassSession> getSessions() {
        return sessions;
    }

    public boolean isEnrolledIn(UPassSession activity) {
        return sessions.contains(activity);
    }

    public void enrol(UPassSession activity) throws Exception {
        if (!activity.canEnrol())
            throw new Exception("Activity is already full");
        UPassSession existingActivity = findSession(activity.getSubject(), activity.getDay());
        if (existingActivity != null)
            withdraw(existingActivity);
        sessions.add(activity);
        activity.enrol();
    }

    public void withdraw(UPassSession activity) {
        if (sessions.remove(activity))
            activity.withdraw();
    }

    private UPassSession findSession(int subjectNumber, String day) {
        for (UPassSession session : sessions)
            if (session.matches(subjectNumber, day))
                return session;
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
