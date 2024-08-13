package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.List;

public class ErrorController {

    @FXML
    private TextArea errorsTextArea;

    public void setErrors(List<String> errors) {
        StringBuilder errorText = new StringBuilder();
        for (String error : errors) {
            errorText.append(error).append("\n");
        }
        errorsTextArea.setText(errorText.toString());
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) errorsTextArea.getScene().getWindow();
        stage.close();
    }
}
