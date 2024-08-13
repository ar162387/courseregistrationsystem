
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class UPMfxApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/home.fxml"));
        stage.getIcons().add(new Image("view/book.png"));
        stage.setTitle("UPM Main Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
