package sparadrap.sparadrap_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    private double x = 0;
    private double y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));


        Scene scene = new Scene(root);

        root.setOnMousePressed((mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getY();
        }));

        root.setOnMouseDragged((mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
            stage.setOpacity(.8);
        }));

        root.setOnMouseReleased((mouseEvent -> {
            stage.setOpacity(1);
        }));

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}