package sparadrap.sparadrap_javafx;

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

    @FXML
    void close(MouseEvent event) {
    }

    Alert alert;

    public void checkLogin() throws IOException {

        if (username.getText().isEmpty() || password.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();


        }
        else if (username.getText().equals("toto") && password.getText().equals("toto")) {

            login_btn.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
    }

}
