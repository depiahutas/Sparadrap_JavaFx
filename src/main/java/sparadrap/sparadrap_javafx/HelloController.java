package sparadrap.sparadrap_javafx;

import DAO.gestion.LoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class HelloController {

    @FXML
    private Button close;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private LoginDAO loginDAO = new LoginDAO();

    public  void close(){
        System.exit(0);
    }

    Alert alert;
    private double x = 0;
    private double y = 0;

    public void checkLogin() throws IOException {

        if (username.getText().isEmpty() || password.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();


        }
        else if (loginDAO.loginAdmin(password.getText(),username.getText())) {

            login_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage stage = new Stage();
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
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("wrong username/password");
            alert.showAndWait();
        }
    }

}
