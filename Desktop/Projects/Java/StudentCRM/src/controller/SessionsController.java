package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Session;
import model.SessionModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;

import java.io.IOException;

public class SessionsController {

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
    private Session currentSession;
    private boolean isUpdating = false;

    private Validator validator;

    public void initialize() {
        dayComboBox.getItems().addAll("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        sessionModel = new SessionModel();
        validator = new Validator();
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

        if (isInputValid(subject, day, start, duration, room, capacity, enrolled)) {
            if (!sessionModel.isDuplicateSession(subject, day)) {
                sessionModel.addSession(subject, day, start, duration, room, capacity, enrolled);
                clearFields();
                closeWindow();
            } else {
                showError("Repetitive session!");
            }
        }
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

            if (isInputValid(subject, day, start, duration, room, capacity, enrolled)) {
                if (!sessionModel.isDuplicateSession(subject, day, currentSession)) {
                    sessionModel.updateSession(currentSession, subject, day, start, duration, room, capacity, enrolled);
                    closeWindow();
                } else {
                    showError("Repetitive session!");
                }
            }
        }
    }

    @FXML
    private void handleClose() {
        closeWindow();
    }

    private boolean isInputValid(String subject, String day, String start, String duration, String room, String capacity, String enrolled) {
        validator.clearErrors();
        validator.validateSessionFields(subject, day, start, duration, room, capacity, enrolled);

        if (validator.getErrors().isEmpty()) {
            return true;
        } else {
            showError(String.join("\n", validator.getErrors()));
            return false;
        }
    }
@FXML
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
    private void showError(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/error.fxml"));
            Parent root = loader.load();
            ErrorController errorController = loader.getController();
            errorController.setErrors(validator.getErrors());
            Stage errorStage = new Stage();
            errorStage.initModality(Modality.APPLICATION_MODAL);
            errorStage.setTitle("Input Exceptions");
            errorStage.setScene(new Scene(root));
            errorStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        subjectField.clear();
        dayComboBox.setValue(null);
        startField.clear();
        durationField.clear();
        roomField.clear();
        capacityField.clear();
        enrolledField.clear();
    }

    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setUpdating(boolean updating) {
        isUpdating = updating;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
        if (currentSession != null) {
            subjectField.setText(currentSession.getSubject());
            dayComboBox.setValue(currentSession.getDay());
            startField.setText(currentSession.getStart());
            durationField.setText(currentSession.getDuration());
            roomField.setText(currentSession.getRoom());
            capacityField.setText(currentSession.getCapacity());
            enrolledField.setText(currentSession.getEnrolled());
        }
    }
}
