package model;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final StringProperty email;
    private final StringProperty name;
    private final String password;

    public User(String email, String name, String password) {
        this.email = new SimpleStringProperty();
        this.email.set(email);
        this.name = new SimpleStringProperty();
        this.name.set(name);
        this.password = password;
    }

    public boolean authenticate(String email, String password) {
        return this.email.getValue().equals(email) && this.password.equals(password);
    }

    public boolean matches(String email, String name) {
        return (email == null || email.isEmpty() || email.equals(this.email.getValue()))
                && (name == null || name.isEmpty() || name.equals(this.email.getValue()));
    }

    @Override
    public String toString() {
        return email + " " + name;
    }

    protected String getEmail() {
        return email.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public ReadOnlyStringProperty nameProperty() {
        return this.name;
    }

    public ReadOnlyStringProperty emailProperty() {
        return this.email;
    }
}
