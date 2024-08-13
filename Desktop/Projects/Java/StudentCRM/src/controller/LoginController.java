package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label errorLabel;

    public String getEmail() {
        return emailField.getText().trim();
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    @FXML
    private void handleOk() {
        String email = getEmail();
        String password = getPassword();
        String userType = authenticateUser(email, password);

        if (userType.equals("admin")) {
            openAdminWindow();
        } else if (userType.equals("student")) {
            openStudentWindow();
        } else {
            errorLabel.setText("Incorrect login details");
            errorLabel.setVisible(true);
        }

        emailField.clear();
        passwordField.clear();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private String authenticateUser(String email, String password) {
        // Dummy authentication logic (replace with actual authentication)
        if (email.equals("admin@example.com") && password.equals("admin")) {
            return "admin";
        } else if (email.equals("student@example.com") && password.equals("student")) {
            return "student";
        } else {
            return "error";
        }
    }

    private void openAdminWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminWindow.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("view/book.png"));
            stage.setTitle("Admin Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            // Close login window
            Stage loginStage = (Stage) okButton.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openStudentWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("view/book.png"));
            stage.setTitle("Student Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
            // Close login window
            Stage loginStage = (Stage) okButton.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
