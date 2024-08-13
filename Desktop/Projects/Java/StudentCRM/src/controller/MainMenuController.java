package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    private void handleLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("view/book.png"));
            stage.setTitle("Sign In");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        System.out.println("Opening admin window...");
    }

    private void openStudentWindow() {
        System.out.println("Opening student window...");
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
