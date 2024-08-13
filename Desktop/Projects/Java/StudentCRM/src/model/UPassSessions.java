package model;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UPassSessions {
    private final ObservableList<UPassSession> sessions;
    private ObservableList<UPassSession> current;
    private UPMApplication application;


    public UPassSessions(UPassSession[] ups) {
        sessions = FXCollections.observableArrayList(ups);
        current = FXCollections.<UPassSession>observableArrayList();
        current.addAll(sessions);
        sessions.addListener((ListChangeListener<UPassSession>) c -> {
            current.clear();
            current.addAll(sessions);
        });
    }

    public UPMApplication getApplication() {
        return application;
    }

    public void setApplication(UPMApplication application) {
        this.application = application;
    }

    public void addSession(UPassSession student) {
        sessions.add(student);
    }

    public void addSession(int subject, String day, int start, int duration, String room, int capacity) {
        sessions.add(new UPassSession(subject, day, start, duration, room, capacity));
    }

    public void cancelAllBookings(UPassSession session) {
        this.sessions.remove(session);
        this.application.cancelAllBookings(session);
    }

    public ArrayList<StudentUser> getAllEnrolledStudents(UPassSession session){
        ArrayList<StudentUser> enrolledUser = new ArrayList<>();
        for (StudentUser user: this.application.getAllStudents()) {
            if(user.isEnrolledIn(session)){
                enrolledUser.add(user);
            }
        }
        return enrolledUser;
    }

    public boolean hasSession(int subject, String day) {
        for (UPassSession e : sessions)
            if (!day.isEmpty() && e.matches(subject, day))
                return true;
        return false;
    }

    public UPassSession getSession(int subject, String day) {
        for (UPassSession e : sessions)
            if (!day.isEmpty() && e.matches(subject, day))
                return e;
        return null;
    }

    public ObservableList<UPassSession> getSessions() {
        return current;
    }

    public void filterList(String subject, String day) {

        List<UPassSession> temp = new ArrayList<>();

        sessions.forEach((UPassSession p) -> {
            try {
                if ((p.hasSubject(subject)) || (p.hasDay(day))) {
                    temp.add(p);
                }
            } catch (NumberFormatException e) {
            }
        });

        this.current.clear();
        this.current.addAll(temp);
    }
}
