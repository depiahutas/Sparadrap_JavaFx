package sparadrap.sparadrap_javafx;



import com.dlsc.formsfx.model.util.ResourceBundleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;


public class DashboardController {
    @FXML
    private AnchorPane addMedicine_form;

    @FXML
    private Button addMeds_btn;

    @FXML
    private TextField add_brand;

    @FXML
    private Button add_btnAdd;

    @FXML
    private Button add_btnClear;

    @FXML
    private Button add_btnDelete;

    @FXML
    private Button add_btnImport;

    @FXML
    private Button add_btnUpdate;

    @FXML
    private TableColumn<?, ?> add_col_brand;

    @FXML
    private TableColumn<?, ?> add_col_date;

    @FXML
    private TableColumn<?, ?> add_col_medicineID;

    @FXML
    private TableColumn<?, ?> add_col_price;

    @FXML
    private TableColumn<?, ?> add_col_product;

    @FXML
    private TableColumn<?, ?> add_col_status;

    @FXML
    private TableColumn<?, ?> add_col_type;

    @FXML
    private AnchorPane add_form;

    @FXML
    private ImageView add_imgView;

    @FXML
    private TextField add_medicineID;

    @FXML
    private TextField add_price;

    @FXML
    private TextField add_productName;

    @FXML
    private TextField add_search;

    @FXML
    private ComboBox<?> add_status;

    @FXML
    private ComboBox<?> add_type;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_customer;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane dashboard_medicine;

    @FXML
    private AnchorPane dashboard_others;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private ComboBox<?> puchase_type;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private TextField purchase_amount;

    @FXML
    private Label purchase_balance;

    @FXML
    private ComboBox<?> purchase_brand;

    @FXML
    private Button purchase_btn;

    @FXML
    private TableColumn<?, ?> purchase_col_Qty;

    @FXML
    private TableColumn<?, ?> purchase_col_brand;

    @FXML
    private TableColumn<?, ?> purchase_col_medicineID;

    @FXML
    private TableColumn<?, ?> purchase_col_price;

    @FXML
    private TableColumn<?, ?> purchase_col_productName;

    @FXML
    private TableColumn<?, ?> purchase_col_type;

    @FXML
    private AnchorPane purchase_from;

    @FXML
    private ComboBox<?> purchase_medicineID;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private ComboBox<?> purchase_productName;

    @FXML
    private TableView<?> purchase_tableView;

    @FXML
    private Label purchase_tot;


    private double x=0;
    private double y=0;
    public void logout(){

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();


            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void switch_form(ActionEvent event){
        if (event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            add_form.setVisible(false);
            purchase_from.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
        }

        if (event.getSource() == addMeds_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(true);
            purchase_from.setVisible(false);

            addMeds_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");

        }

        if (event.getSource() == purchase_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            purchase_from.setVisible(true);

            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");

        }
    }



        public void minimize(){
            Stage stage = (Stage)main_form.getScene().getWindow();
            stage.setIconified(true);
        }


        public  void close(){
            System.exit(0);
        }

        public void initialize(URL location, ResourceBundleService ressources){

        }


}

