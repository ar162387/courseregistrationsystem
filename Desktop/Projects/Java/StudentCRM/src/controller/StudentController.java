package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Session;
import model.SessionModel;

public class StudentController {

    @FXML
    private Label studentName;

    @FXML
    private TableView<Session> mySessionsTable;

    @FXML
    private TableColumn<Session, String> sessionIdColumn;

    @FXML
    private TableColumn<Session, String> sessionNameColumn;

    @FXML
    private TableColumn<Session, String> startTimeColumn;

    @FXML
    private TableColumn<Session, String> durationColumn;

    @FXML
    private Label notEnrolledLabel;

    @FXML
    private Button withdrawButton;

    @FXML
    private TableView<Session> availableSessionsTable;

    @FXML
    private TableColumn<Session, String> availSessionIdColumn;

    @FXML
    private TableColumn<Session, String> availSessionNameColumn;

    @FXML
    private TableColumn<Session, String> availStartTimeColumn;

    @FXML
    private TableColumn<Session, String> availDurationColumn;

    @FXML
    private TableColumn<Session, String> availSeatsColumn;

    @FXML
    private TableColumn<Session, String> availEnrolledColumn;

    @FXML
    private Button enrolButton;

    private ObservableList<Session> mySessionsList = FXCollections.observableArrayList();
    private ObservableList<Session> availableSessionsList = FXCollections.observableArrayList();

    private SessionModel sessionModel = new SessionModel();

    @FXML
    private void initialize() {
        // Initialize the tables with sample data and set the cell value factories
        initializeTables();

        // Update UI with initial data
        updateUI();

        // Add listeners to enable/disable buttons based on table selections
        addTableListeners();

        // Load sessions from text files
//        loadSessionsFromFile("enrolled_sessions.txt", mySessionsList);
        loadSessionsFromFile("available_sessions.txt", availableSessionsList);
    }

    @FXML
    private void handleLogout() {
        // Code to logout and close the student window
        Stage stage = (Stage) studentName.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleWithdraw() {
        // Code to handle withdrawing from a session
        Session selectedSession = mySessionsTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            sessionModel.withdrawFromSession(selectedSession);
            mySessionsList.remove(selectedSession);
            updateUI();
        }
    }

    @FXML
    private void handleEnrol() {
        // Code to handle enrolling into a session
        Session selectedSession = availableSessionsTable.getSelectionModel().getSelectedItem();
        if (selectedSession != null) {
            mySessionsList.add(selectedSession);
            sessionModel.enrolInSession(selectedSession);
            updateUI();
        }
    }

    private void initializeTables() {
        // Cell value factories for the columns
        sessionIdColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        sessionNameColumn.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        startTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatStartTime(cellData.getValue().getStart())));
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration() + " hrs"));

        availSessionIdColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        availSessionNameColumn.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        availStartTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(formatStartTime(cellData.getValue().getStart())));
        availDurationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration() + " hrs"));
        availSeatsColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        availEnrolledColumn.setCellValueFactory(cellData -> cellData.getValue().enrolledProperty());

        // Populate tables with actual data
        mySessionsTable.setItems(mySessionsList);
        availableSessionsTable.setItems(availableSessionsList);
    }

    private void updateUI() {
        boolean hasSessions = !mySessionsList.isEmpty();
        notEnrolledLabel.setVisible(!hasSessions);
        withdrawButton.setDisable(!hasSessions);

        updateEnrolButtonState();
    }

    private void updateEnrolButtonState() {
        Session selectedAvailableSession = availableSessionsTable.getSelectionModel().getSelectedItem();
        boolean enrolButtonDisabled = selectedAvailableSession == null || selectedAvailableSession.getCapacity().equals(selectedAvailableSession.getEnrolled()) || isAlreadyEnrolled(selectedAvailableSession);
        enrolButton.setDisable(enrolButtonDisabled);
    }

    private boolean isAlreadyEnrolled(Session session) {
        for (Session enrolledSession : mySessionsList) {
            if (enrolledSession.getSubject().equals(session.getSubject()) && enrolledSession.getDay().equals(session.getDay())) {
                return true;
            }
        }
        return false;
    }

    private void addTableListeners() {
        mySessionsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> withdrawButton.setDisable(newValue == null));
        availableSessionsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateEnrolButtonState());
    }

    private void loadSessionsFromFile(String filePath, ObservableList<Session> sessionList) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7) {
                    sessionList.add(new Session(details[0], details[1], details[2], details[3], details[4], details[5], details[6]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatStartTime(String startTime) {
        return startTime.replace("-", ":");
    }
}
