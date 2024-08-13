package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Users {
    private ObservableList<User> users;

    public Users(User[] users) {
        this.users = FXCollections.observableArrayList(users);
    }

    public boolean hasUser(String name, String password) {
        return users.stream().anyMatch(p -> (p.authenticate(name, password)));
    }

    public User getUser(String name, String password) {
        for (User e : users)
            if (e.authenticate(name, password))
                return e;
        return null;
    }

    public void cancelAllBookings(UPassSession session) {
        for (User user : users) {
            if (user instanceof StudentUser) {
                ((StudentUser) user).withdraw(session);
            }
        }
    }

    public ArrayList<StudentUser> getStudentUsers() {
        ArrayList<StudentUser> userList = new ArrayList<>();
        for (User user :
                this.users) {
            if (user instanceof StudentUser) {
                userList.add((StudentUser) (user));
            }
        }
        return userList;
    }
}
