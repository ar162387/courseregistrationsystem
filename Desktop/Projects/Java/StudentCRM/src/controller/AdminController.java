package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Session;
import model.SessionModel;

import java.io.IOException;

public class AdminController {

    @FXML
    private Text adminName;

    @FXML
    private TableView<Session> sessionsTable;

    @FXML
    private TableColumn<Session, String> subjectColumn;

    @FXML
    private TableColumn<Session, String> dayColumn;

    @FXML
    private TableColumn<Session, String> startColumn;

    @FXML
    private TableColumn<Session, String> durationColumn;

    @FXML
    private TableColumn<Session, String> roomColumn;

    @FXML
    private TableColumn<Session, String> capacityColumn;

    @FXML
    private TableColumn<Session, String> enrolledColumn;

    @FXML
    private TextField subjectFilter;

    @FXML
    private TextField dayFilter;

    @FXML
    private Button addButton;

    @FXML
    private Button selectButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button withdrawButton;

    private ObservableList<Session> sessionsList;
    private SessionModel sessionModel;

    @FXML
    private void initialize() {
        sessionModel = new SessionModel();
        initializeTable();
        sessionsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateButtons(newValue));
        updateButtons(null);
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) adminName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAdd() {
        openSessionWindow(null);
        updateSessionsTable();
    }

    @FXML
    private void handleSelect() {
        Session selectedSession = sessionsTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            openSessionWindow(selectedSession);
        }
    }

    @FXML
    private void handleDelete() {
        Session selectedSession = sessionsTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            sessionModel.deleteSession(selectedSession);
            updateSessionsTable();
            updateButtons(null);
        }
    }

    @FXML
    private void handleWithdraw() {
        Session selectedSession = sessionsTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            openWithdrawWindow(selectedSession);
        }
    }

    @FXML
    private void handleFilter() {
        String subjectText = subjectFilter.getText().toLowerCase();
        String dayText = dayFilter.getText().toLowerCase();

        ObservableList<Session> filteredList = FXCollections.observableArrayList();
        for (Session session : sessionsList) {
            if ((subjectText.isEmpty() || session.getSubject().toLowerCase().contains(subjectText))
                    && (dayText.isEmpty() || session.getDay().toLowerCase().contains(dayText))) {
                filteredList.add(session);
            }
        }
        sessionsTable.setItems(filteredList);
    }

    private void initializeTable() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        enrolledColumn.setCellValueFactory(new PropertyValueFactory<>("enrolled"));

        sessionsList = sessionModel.getAvailableSessions();
        sessionsTable.setItems(sessionsList);
    }

    private void updateButtons(Session session) {
        boolean isSelected = session != null;
        selectButton.setDisable(!isSelected);
        deleteButton.setDisable(!isSelected);
        withdrawButton.setDisable(!isSelected);
    }

    private void openSessionWindow(Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SessionWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            SessionsController controller = loader.getController();
            controller.setSession(session, sessionModel);

            stage.showAndWait();
            updateSessionsTable(); // Update table after window closes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openWithdrawWindow(Session session) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/WithdrawWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            WithdrawController controller = loader.getController();
            controller.setSession(session);

            stage.showAndWait();
            updateSessionsTable(); // Update table after window closes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSessionsTable() {
        sessionsList.setAll(sessionModel.getAvailableSessions());
        sessionsTable.setItems(sessionsList);
    }
}
