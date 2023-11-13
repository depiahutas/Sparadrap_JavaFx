package sparadrap.sparadrap_javafx;



import DAO.sante.CategorieMedicamentDAO;
import DAO.sante.MedicamentDAO;
import classMetier.Util.getData;
import classMetier.sante.CategorieMedicament;
import classMetier.sante.Medicament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.*;


public class DashboardController  implements Initializable {

    @FXML
    private AnchorPane addMedicine_form;

    @FXML
    private TableView<Medicament> addMedicines_tableView;

    @FXML
    private Button addMeds_btn;

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
    private TableColumn<Medicament, Date> add_col_date;

    @FXML
    private TableColumn<Medicament, Integer> add_col_medicineID;

    @FXML
    private TableColumn<Medicament, Double> add_col_price;

    @FXML
    private TableColumn<Medicament, String> add_col_product;

    @FXML
    private TableColumn<Medicament, Integer> add_col_qte;

    @FXML
    private TableColumn<Medicament, String> add_col_type;

    @FXML
    private TextField add_date;

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
    private TextField add_qte;

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

            addMedicineShowList();
            addMedicineSearch();

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


    private MedicamentDAO medicamentDAO = new MedicamentDAO();

    CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
    private ObservableList<Medicament> addMedicineList;
    public void addMedicineShowList(){
        addMedicineList = medicamentDAO.addMedecineList();

        add_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
        add_col_product.setCellValueFactory(new PropertyValueFactory<>("nom"));
        add_col_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
        add_col_qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        add_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        add_col_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

        addMedicines_tableView.setItems(addMedicineList);

    }

    private Image image;
    public void addMedicineSelect(){
        Medicament medicament = addMedicines_tableView.getSelectionModel().getSelectedItem();
        int num = addMedicines_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1){
            return;
        }
        add_medicineID.setText(String.valueOf(medicament.getId()));
        add_productName.setText(medicament.getNom());
        add_price.setText(String.valueOf(medicament.getPrix()));
        add_qte.setText(String.valueOf(medicament.getQuantite()));
        add_date.setText(medicament.getDateMES());


        String url = "file:"+medicament.getImage();

        image = new Image(url,120,180,false,true);

        add_imgView.setImage(image);

        getData.path=medicament.getImage();
    }

    public void addMedicinesAdd(){
        String uri = getData.path;


        Alert alert;

        if (add_productName.getText().isEmpty()
                || add_price.getText().isEmpty() || add_date.getText().isEmpty()
                || add_qte.getText().isEmpty() || add_type.getSelectionModel().getSelectedItem() == null){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else {
                Medicament medicament;

                if ( uri==null || uri.isEmpty()) {

                    medicament = new Medicament(0,
                            add_productName.getText(),
                            Float.parseFloat(add_price.getText()),
                            add_date.getText(),
                            Integer.parseInt(add_qte.getText()),
                            categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()),
                            null
                    );
                }
                else {
                    uri = uri.replace("\\","\\\\");
                    medicament = new Medicament(0,
                            add_productName.getText(),
                            Float.parseFloat(add_price.getText()),
                            add_date.getText(),
                            Integer.parseInt(add_qte.getText()),
                            categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()),
                            uri);
                }
                medicamentDAO.create(medicament);
                addMedicineShowList();
                addMedicineReset();

        }

    }

    public void addMedicineInsertImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Import image File");
        open.getExtensionFilters().add((new FileChooser.ExtensionFilter("image file","*jpg","*png")));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file!=null){
            image = new Image(file.toURI().toString(),120,180,false,true);

            add_imgView.setImage(image);

            getData.path = file.getAbsolutePath();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMedicineShowList();
        addMedicineListType();
        addMedicineSearch();
    }

    public void addMedicineListType(){

        List<CategorieMedicament> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(categorieMedicamentDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        add_type.setItems(list);
    }

    public void addMedicineReset(){
        add_medicineID.setText("");
        add_productName.setText("");
        add_price.setText("");
        add_qte.setText("");
        add_date.setText("");
        add_type.getSelectionModel().clearSelection();
        add_imgView.setImage(null);
        getData.path="";

    }


    public void addMedicineUpdate(){
        String uri = getData.path;
        Medicament medicament;
        Alert alert;

        if (add_medicineID.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a medicine");
            alert.showAndWait();
        }
        else {

            if (uri == null) {
                medicament = new Medicament(Integer.parseInt(add_medicineID.getText()), add_productName.getText(),
                        Float.parseFloat(add_price.getText()), add_date.getText(), Integer.parseInt(add_qte.getText()),
                        categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()), null
                );
            } else {
                uri = uri.replace("\\", "\\\\");
                medicament = new Medicament(Integer.parseInt(add_medicineID.getText()), add_productName.getText(),
                        Float.parseFloat(add_price.getText()), add_date.getText(), Integer.parseInt(add_qte.getText()),
                        categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()), uri
                );
            }
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Medicine ID : " + add_medicineID.getText());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){

                medicamentDAO.update(medicament);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated");


                addMedicineShowList();
                addMedicineReset();
            }
        }

    }

    public void addMedicineDelete(){
        String uri = getData.path;
        Alert alert;

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Medicine ID : " + add_medicineID.getText());
        Optional<ButtonType> option = alert.showAndWait();

        Medicament medicament;
        if (add_medicineID.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a medicine");
            alert.showAndWait();
        }

        if (uri==null)
        medicament = new Medicament(Integer.parseInt(add_medicineID.getText()),
                add_productName.getText(),Float.parseFloat(add_price.getText()),
                add_date.getText(),Integer.parseInt(add_qte.getText()),
                categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()),
                null);
        else {
            medicament = new Medicament(Integer.parseInt(add_medicineID.getText()),
                    add_productName.getText(),Float.parseFloat(add_price.getText()),
                    add_date.getText(),Integer.parseInt(add_qte.getText()),
                    categorieMedicamentDAO.findLibelle(add_type.getSelectionModel().getSelectedItem().toString()),
                    uri);
        }
        if (option.get().equals(ButtonType.OK)){
            medicamentDAO.delete(medicament);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Delete");


            addMedicineShowList();
            addMedicineReset();
        }
    }

    public void addMedicineSearch(){

        FilteredList<Medicament> filter = new FilteredList<>(addMedicineList, e-> true);

        add_search.textProperty().addListener((Observable,oldValue,newValue)-> {
          filter.setPredicate(predicateMedecine-> {
              if (newValue == null || newValue.isEmpty()){
                  return true;
              }
              String searchKey = newValue.toLowerCase();

              if (String.valueOf(predicateMedecine.getId()).toString().contains(searchKey)){
                  return true;
              } else if (predicateMedecine.getNom().toString().contains(searchKey)) {
                  return true;
              } else if (predicateMedecine.getCategorieMed().toString().contains(searchKey)) {
                    return true;
              } else if (String.valueOf(predicateMedecine.getPrix()).toString().contains(searchKey)) {
                  return true;
              } else if (predicateMedecine.getDateMES().toString().contains(searchKey)) {
                  return true;
              }
              else {
                  return false;
              }
          });
        });

        SortedList<Medicament> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(addMedicines_tableView.comparatorProperty());
        addMedicines_tableView.setItems(sortList);
    }
}

