package model;

import java.util.*;

public class UPMApplication {

    private Users users;
    private UPassSessions sessions;
    private User loggedInUser;

    public UPMApplication() {
        users = new Users(new User[]{
                new AdminUser("huan.h@uts.net", "Huan Huo", "pw"),
                new AdminUser("david.d@uts.net", "David Dyer", "46david"),
                new StudentUser("zarael.t@uts.net", "Zarael Thorne", "qwe"),
                new StudentUser("seraphina.v@uts.net", "Seraphina Vale", "sv0987"),
                new StudentUser("calyx.s@uts.net", "Calyx Sterling", "123"),
                new StudentUser("alaric.e@uts.net", "Alaric Ember", "ember456"),
                new StudentUser("s", "SSS", "s"),
                new StudentUser("ss", "SSS", "s"),
                new StudentUser("sss", "SSS", "s"),
                new AdminUser("a", "AAA", "a"),
        });
        sessions = new UPassSessions(new UPassSession[]{
                new UPassSession(48024, "Wed", 18, 1, "CB11.00.405", 20),
                new UPassSession(48024, "Fri", 19, 2, "CB11.B1.401", 3),
                new UPassSession(48024, "Thu", 19, 2, "CB11.B1.402", 2),
                new UPassSession(31284, "Thu", 14, 1, "CB02.03.002", 30),
                new UPassSession(31284, "Mon", 9, 2, "CB11.B1.103", 2),
                new UPassSession(31284, "Tue", 9, 2, "CB11.B1.103", 2),
        });
        sessions.setApplication(this);
    }

    public void cancelAllBookings(UPassSession session) {
        users.cancelAllBookings(session);
    }

    public User getUser(String email, String password) {
        return users.getUser(email, password);
    }

    public ArrayList<StudentUser> getAllStudents(){
        return users.getStudentUsers();
    }

    public void setUser(User e) {
        this.loggedInUser = e;
    }

    public UPassSessions getSessions() {
        return sessions;
    }
}
