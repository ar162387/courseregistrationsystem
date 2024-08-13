package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Session;
import model.SessionModel;

public class SessionController {

    @FXML
    private TextField subjectField;

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private TextField startField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField roomField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextField enrolledField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button closeButton;

    private SessionModel sessionModel;
    private Session currentSession; // Store the currently selected session for update
    private boolean isUpdating = false; // Flag to indicate if updating an existing session

    public void initialize() {
        // Initialize your ComboBox and other controls if needed
        dayComboBox.getItems().addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        // Initialize your model
        sessionModel = new SessionModel();
    }

    @FXML
    private void handleAdd() {
        String subject = subjectField.getText();
        String day = dayComboBox.getValue();
        String start = startField.getText();
        String duration = durationField.getText();
        String room = roomField.getText();
        String capacity = capacityField.getText();
        String enrolled = enrolledField.getText();

        sessionModel.addSession(subject, day, start, duration, room, capacity, enrolled);
        clearFields();
    }

    @FXML
    private void handleUpdate() {
        if (currentSession != null && isUpdating) {
            String subject = subjectField.getText();
            String day = dayComboBox.getValue();
            String start = startField.getText();
            String duration = durationField.getText();
            String room = roomField.getText();
            String capacity = capacityField.getText();
            String enrolled = enrolledField.getText();

            sessionModel.updateSession(currentSession, subject, day, start, duration, room, capacity, enrolled);
            clearFields();
            isUpdating = false; // Reset updating flag
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    // Method to set fields for updating an existing session
    public void setSession(Session session, SessionModel model) {
        currentSession = session;
        sessionModel = model;

        if (session != null) {
            subjectField.setText(session.getSubject());
            dayComboBox.setValue(session.getDay());
            startField.setText(session.getStart());
            durationField.setText(session.getDuration());
            roomField.setText(session.getRoom());
            capacityField.setText(session.getCapacity());
            enrolledField.setText(session.getEnrolled());

            addButton.setVisible(false); // Hide add button
            updateButton.setVisible(true); // Show update button
            isUpdating = true; // Set updating flag
        } else {
            clearFields();
            addButton.setVisible(true); // Show add button
            updateButton.setVisible(false); // Hide update button
            isUpdating = false; // Reset updating flag
        }
    }

    // Method to clear input fields
    private void clearFields() {
        subjectField.clear();
        dayComboBox.getSelectionModel().clearSelection();
        startField.clear();
        durationField.clear();
        roomField.clear();
        capacityField.clear();
        enrolledField.clear();
    }
}
