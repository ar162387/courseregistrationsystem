package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class SessionModel {

    public static final String AVAILABLE_SESSIONS_FILE = "available_sessions.txt";
    public static final String ENROLLED_SESSIONS_FILE = "enrolled_sessions.txt";

    private ObservableList<Session> mySessions;
    private ObservableList<Session> availableSessions;

    public SessionModel() {
        mySessions = FXCollections.observableArrayList();
        availableSessions = FXCollections.observableArrayList();

        loadSessionsFromFile(AVAILABLE_SESSIONS_FILE, availableSessions);
        loadSessionsFromFile(ENROLLED_SESSIONS_FILE, mySessions);
    }

    public ObservableList<Session> getMySessions() {
        return mySessions;
    }

    public ObservableList<Session> getAvailableSessions() {
        return availableSessions;
    }

    public void addSession(String subject, String day, String start, String duration, String room, String capacity, String enrolled) {
        Session newSession = new Session(subject, day, start, duration, room, capacity, enrolled);
        availableSessions.add(newSession);
        saveSessionsToFile(AVAILABLE_SESSIONS_FILE, availableSessions);
    }

    public void updateSession(Session sessionToUpdate, String subject, String day, String start, String duration, String room, String capacity, String enrolled) {
        sessionToUpdate.subjectProperty().set(subject);
        sessionToUpdate.dayProperty().set(day);
        sessionToUpdate.startProperty().set(start);
        sessionToUpdate.durationProperty().set(duration);
        sessionToUpdate.roomProperty().set(room);
        sessionToUpdate.capacityProperty().set(capacity);
        sessionToUpdate.enrolledProperty().set(enrolled);
        
        saveSessionsToFile(AVAILABLE_SESSIONS_FILE, availableSessions);
    }

    public void deleteSession(Session session) {
        availableSessions.remove(session);
        saveSessionsToFile(AVAILABLE_SESSIONS_FILE, availableSessions);
    }

    public void enrolInSession(Session session) {
        if (!mySessions.contains(session)) {
            mySessions.add(session);
            saveSessionsToFile(ENROLLED_SESSIONS_FILE, mySessions);
        }
    }

    public void withdrawFromSession(Session session) {
        mySessions.remove(session);
        saveSessionsToFile(ENROLLED_SESSIONS_FILE, mySessions);
    }

    public void loadSessionsFromFile(String filename, ObservableList<Session> sessionList) {
        sessionList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    sessionList.add(new Session(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSessionsToFile(String filename, ObservableList<Session> sessionList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Session session : sessionList) {
                bw.write(String.join(",", session.getSubject(), session.getDay(), session.getStart(),
                        session.getDuration(), session.getRoom(), session.getCapacity(), session.getEnrolled()));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDuplicateSession(String subject, String day) {
        for (Session session : availableSessions) {
            if (session.getSubject().equals(subject) && session.getDay().equals(day)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDuplicateSession(String subject, String day, Session currentSession) {
        for (Session session : availableSessions) {
            if (session != currentSession && session.getSubject().equals(subject) && session.getDay().equals(day)) {
                return true;
            }
        }
        return false;
    }
}
