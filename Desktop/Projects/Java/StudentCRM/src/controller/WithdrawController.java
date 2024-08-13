package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableRow;
import model.Session;
import model.Student;

public class WithdrawController {

    @FXML
    private TableView<Student> studentsTable;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String> studentEmailColumn;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button closeButton;

    private Session session;

    private ObservableList<Student> studentsList = FXCollections.observableArrayList();

    public void setSession(Session session) {
        this.session = session;
        loadStudents();
    }

    @FXML
    private void initialize() {
        withdrawButton.setDisable(true);

        studentNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        studentEmailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));

        studentsTable.setItems(studentsList);

        withdrawButton.setOnAction(event -> handleWithdraw());
        closeButton.setOnAction(event -> handleClose());

        studentsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            withdrawButton.setDisable(newValue == null);
        });

        studentsTable.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Student rowData = row.getItem();
                    // Handle double-click action
                }
            });
            return row;
        });
    }

    private void loadStudents() {
        if (session != null && session.getEnrolledStudents() != null) {
            studentsList.setAll(session.getEnrolledStudents());
        } else {
            studentsList.clear();
        }
    }

    private void handleWithdraw() {
        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Implement withdrawal logic
            studentsList.remove(selectedStudent);
        }
        handleClose();
     
    }

    private void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
 
    }
}
