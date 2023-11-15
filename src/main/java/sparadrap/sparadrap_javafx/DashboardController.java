package sparadrap.sparadrap_javafx;



import DAO.gestion.AchatDAO;
import DAO.personne.ClientDAO;
import DAO.sante.CategorieMedicamentDAO;
import DAO.sante.MedicamentDAO;
import classMetier.Util.getData;
import classMetier.gestion.Achat;
import classMetier.gestion.Panier;
import classMetier.personne.Client;
import classMetier.personne.Medecin;
import classMetier.personne.Personne;
import classMetier.sante.CategorieMedicament;
import classMetier.sante.Medicament;
import classMetier.sante.Mutuelle;
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
    private TableView<Medicament> addMedicines_tableView;

    @FXML
    private Button addMeds_btn;

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
    private ComboBox<CategorieMedicament> add_type;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<CategorieMedicament> puchase_type;

    @FXML
    private Button purchase_btn;

    @FXML
    private AnchorPane purchase_from;

    @FXML
    private AnchorPane addMedicine_form;

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
    private ComboBox<?> add_status;

    @FXML
    private Button close;

    @FXML
    private AnchorPane dashboard_customer;

    @FXML
    private AnchorPane dashboard_medicine;

    @FXML
    private AnchorPane dashboard_others;

    @FXML
    private Button minimize;

    @FXML
    private TextField purchase_Quantity;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private TextField purchase_amount;

    @FXML
    private Label purchase_balance;

    @FXML
    private ComboBox<?> purchase_brand;

    @FXML
    private TableColumn<Medicament, Integer> purchase_col_Qty;

    @FXML
    private TableColumn<Medicament, Date> purchase_col_date;

    @FXML
    private TableColumn<Medicament, Integer> purchase_col_medicineID;

    @FXML
    private TableColumn<Medicament, Float> purchase_col_price;

    @FXML
    private TableColumn<Medicament, String> purchase_col_productName;

    @FXML
    private TableColumn<Medicament, String> purchase_col_type;

    @FXML
    private ComboBox<?> purchase_medicineID;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private ComboBox<Medicament> purchase_productName;

    @FXML
    private TableView<Medicament> purchase_tableView;

    @FXML
    private Label purchase_tot;


    @FXML
    private TableColumn<Medicament, Integer> purchase_CartCol_Qty;

    @FXML
    private TableColumn<Medicament, Date> purchase_CartCol_date;

    @FXML
    private TableColumn<Medicament, Integer> purchase_CartCol_medicineID;

    @FXML
    private TableColumn<Medicament, Float> purchase_CartCol_price;

    @FXML
    private TableColumn<Medicament, String> purchase_CartCol_productName;

    @FXML
    private TableColumn<Medicament, String> purchase_CartCol_type;

    @FXML
    private TableView<Medicament> purchase_CartTableView;

    @FXML
    private Button purchase_RemoveFromCart;

    @FXML
    private TextField purchase_contains;


    @FXML
    private Button ListPatient_Btn_Create;

    @FXML
    private Button ListPatient_Btn_Delete;

    @FXML
    private Button ListPatient_Btn_Infos;

    @FXML
    private Button ListPatient_Btn_Purchase;

    @FXML
    private Button ListPatient_Btn_Update;

    @FXML
    private TextField ListPatient_Search;

    @FXML
    private TableView<Client> ListPatient_TableView;

    @FXML
    private Button ListPatient_btn;

    @FXML
    private TableColumn<?, ?> ListPatient_col_ID;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Mail;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Mutuelle;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Nom;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Prenom;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Tel;

    @FXML
    private TableColumn<?, ?> ListPatient_col_Medecin;

    @FXML
    private AnchorPane ListPatient_form;

    @FXML
    private Button purchase_addBtn1;




    private double x=0;
    private double y=0;



    float prixTot=0;

    // déconnexion
    public void logout(){

        try {
            //demande de confirmation de déconnexion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();


            // si boutton cliqué est de type OK alors déconnexion et retour fenettre de connexion
            // + affichage  de la nouvelle fenetre à l'emplacement du curseur
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


    // action boutton menu
    // change le form affiché
    public void switch_form(ActionEvent event){
        // affichage du form dashboard + boutton mit en contraste
        if (event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);


            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
        }
        // affichage du form ajout Medicament + boutton mit en contraste
        if (event.getSource() == addMeds_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(true);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);

            addMeds_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");

            addMedicineReset();
            add_type.getSelectionModel().select(0);


            addListType();
            addMedicineShowList();
            addMedicineSearch();
        }

        // affichage du form achat médicament + boutton mit en contraste
        if (event.getSource() == purchase_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            ListPatient_form.setVisible(false);
            purchase_from.setVisible(true);

            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");

            purchaseListType();
            puchase_type.getSelectionModel().select(-1);
            purchaseMedicineShowList(null);

            panier = new Panier(0,FXCollections.observableArrayList());
            purchase_CartTableView.setItems(null);

            prixTot=0;
            purchase_tot.setText(String.valueOf(prixTot));

        }
        if (event.getSource() == ListPatient_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(true);

            ListPatient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");

            ListPatientShowList();
        }
    }


        // boutton reduit la fenetre
        public void minimize(){
            Stage stage = (Stage)main_form.getScene().getWindow();
            stage.setIconified(true);
        }

        // boutton quitter ferme application
        public  void close(){
            System.exit(0);
        }


    private MedicamentDAO medicamentDAO = new MedicamentDAO();

    CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
    private ObservableList<Medicament> addMedicineList;

    Panier panier;


    // remplissage tableau gestion medicament
    public void addMedicineShowList(){
        addMedicineList = medicamentDAO.addMedecineList();

        // remplissage des colonnes en fonction des attribut de l'objet Medicament
        add_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
        add_col_product.setCellValueFactory(new PropertyValueFactory<>("nom"));
        add_col_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
        add_col_qte.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        add_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        add_col_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

        addMedicines_tableView.setItems(addMedicineList);

    }

    private Image image;

    // remplissage automatique lorsque sélection médicament dans tableau
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
        add_type.getSelectionModel().select(medicament.getCategorie());


        String url = "file:"+medicament.getImage();

        image = new Image(url,120,180,false,true);

        add_imgView.setImage(image);

        getData.path=medicament.getImage();
    }

    // Ajout d'un médicament dans la base de données
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

    // insertion d'image  -- non obligatoire
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


    // initialisation de la page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMedicineShowList();
        addListType();
    }

    // remplissage combobox type de médicament
    public void addListType(){

        List<CategorieMedicament> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(categorieMedicamentDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        add_type.setItems(list);
    }



    // vide les champs d'ajout de médicament
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


    // mise a jour d'un médicament
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


    // supression du medicament sélectionner
    // demande confirmation avant supression
    public void addMedicineDelete(){
        String uri = getData.path;

        //affichaqe message de confirmation
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Medicine ID : " + add_medicineID.getText());
        Optional<ButtonType> option = alert.showAndWait();

        Medicament medicament;

        //si pas de médicament sélectionner informe l'utilisateur
        if (add_medicineID.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select a medicine");
            alert.showAndWait();
        }

        // en fonction de si image ou pas lié supression
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
        // si confirmation valider alors supression et affiche confirmation supression
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

    // tri du tableau en fonction du champ de recherche
    public void addMedicineSearch(){

        FilteredList<Medicament> filter = new FilteredList<>(addMedicineList, e-> true);

        add_search.textProperty().addListener((Observable,oldValue,newValue) -> {
          filter.setPredicate(predicateMedecine -> {
              if ( newValue == null || newValue.isEmpty()){
                  return true;
              }
              String searchKey = newValue.toLowerCase();

              if (String.valueOf(predicateMedecine.getId()).toString().toLowerCase().contains(searchKey)){
                  return true;
              } else if (predicateMedecine.getNom().toString().toLowerCase().contains(searchKey)) {
                  return true;
              } else if (predicateMedecine.getCategorieMed().toString().toLowerCase().contains(searchKey)) {
                    return true;
              } else if (String.valueOf(predicateMedecine.getPrix()).toString().contains(searchKey)) {
                  return true;
              } else if (predicateMedecine.getDateMES().toString().toLowerCase().contains(searchKey)) {
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



    // formulaire purchase

    private ObservableList<Medicament> purchaseMedicineList = medicamentDAO.addMedecineList();

    public void purchaseType(){

        if (puchase_type.getSelectionModel().getSelectedItem()!=null) {
            purchaseMedicineList = medicamentDAO.selectMedsFromCat(puchase_type.getSelectionModel().getSelectedItem());
        }
        else {
            purchaseMedicineList = medicamentDAO.addMedecineList();
        }
        purchaseMedicineShowList(purchaseMedicineList);
    }

    public void purchaseListType()
    {

        List<CategorieMedicament> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(categorieMedicamentDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        puchase_type.setItems(list);
    }

    public void purchaseContain(){
        FilteredList<Medicament> filter = new FilteredList<>(purchaseMedicineList, e-> true);

        purchase_contains.textProperty().addListener((Observable,oldValue,newValue) -> {
            filter.setPredicate(predicateMedecine -> {
                if ( newValue == null || newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateMedecine.getId()).toString().toLowerCase().contains(searchKey)){
                    return true;
                } else if (predicateMedecine.getNom().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateMedecine.getCategorieMed().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateMedecine.getPrix()).toString().contains(searchKey)) {
                    return true;
                } else if (predicateMedecine.getDateMES().toString().toLowerCase().contains(searchKey)) {
                    return true;
                }
                else {
                    return false;
                }
            });
        });

        SortedList<Medicament> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(purchase_tableView.comparatorProperty());
        purchase_tableView.setItems(sortList);

    }


    public void purchaseMedicineShowList(ObservableList<Medicament> List){

        if (List==null){
            List = medicamentDAO.addMedecineList();
        }
        // remplissage des colonnes en fonction des attribut de l'objet Medicament
        purchase_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
        purchase_col_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        purchase_col_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
        purchase_col_Qty.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
        purchase_col_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

        purchase_tableView.setItems(List);
    }


    public void purchaseResetFilter(){
        purchaseMedicineList = medicamentDAO.addMedecineList();

        puchase_type.getSelectionModel().select(-1);
        purchase_contains.setText("");

    }


    public void purchaseAddToCart(){
        Alert alert;

        if (purchase_Quantity.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Fill quantity field please");
            alert.showAndWait();
        }
        else {
            Medicament medicament = purchase_tableView.getSelectionModel().getSelectedItem();
            int num = purchase_tableView.getSelectionModel().getSelectedIndex();

            if ((num - 1) < -1) {
                return;
            }

            if (!panier.getResumePanier().isEmpty()) {
                for (Medicament medicament1 : panier.getResumePanier()) {
                    if (medicament.getId() == medicament1.getId()) {
                        medicament.setQuantite(Integer.parseInt(purchase_Quantity.getText()));
                        medicament1.setQuantite(medicament1.getQuantite() + medicament.getQuantite());
                        medicament1.setPrix(medicament.getPrix() * medicament1.getQuantite());
                        prixTot += medicament1.getPrix();
                    }else {
                        medicament.setQuantite(Integer.parseInt(purchase_Quantity.getText()));
                        medicament.setPrix(medicament.getQuantite() * medicament.getPrix());
                        panier.getResumePanier().add(medicament);
                        prixTot += medicament.getPrix();
                    }
                }
            }else {
                medicament.setQuantite(Integer.parseInt(purchase_Quantity.getText()));
                medicament.setPrix(medicament.getQuantite() * medicament.getPrix());
                panier.getResumePanier().add(medicament);
                prixTot += medicament.getPrix();
            }

            purchase_tot.setText(String.valueOf(prixTot));



            purchase_CartCol_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
            purchase_CartCol_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            purchase_CartCol_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
            purchase_CartCol_Qty.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            purchase_CartCol_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
            purchase_CartCol_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

            purchase_CartTableView.setItems(panier.getResumePanier());

        }

    }


    public void purchaseRemoveFromCart() {
        Alert alert;

        if (purchase_CartTableView.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Select a Meds in the cart");
            alert.showAndWait();
        } else {
            Medicament medicament = purchase_CartTableView.getSelectionModel().getSelectedItem();
            int num = purchase_CartTableView.getSelectionModel().getSelectedIndex();

            prixTot-=medicament.getPrix();
            purchase_tot.setText(String.valueOf(prixTot));

            if ((num - 1) < -1) {
                return;
            }

            panier.getResumePanier().remove(medicament);

            purchase_CartCol_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
            purchase_CartCol_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            purchase_CartCol_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
            purchase_CartCol_Qty.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            purchase_CartCol_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
            purchase_CartCol_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

            if (!panier.getResumePanier().isEmpty()){
                purchase_CartTableView.setItems(panier.getResumePanier());
            }
            else {
                purchase_CartTableView.setItems(null);
            }

        }
    }


    AchatDAO achatDAO = new AchatDAO();
    public void purchasePay(){
        Alert alert;

        if(panier.getResumePanier().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Need at least one medicine in the cart");
            alert.showAndWait();
        }else{
            achatDAO.create(new Achat(0,null,panier,prixTot,new Date().toString(),null));
        }
    }


    private ObservableList<Client> ListPatientList;
    private ClientDAO clientDAO = new ClientDAO();
    public void ListPatientShowList(){
        ListPatientList = clientDAO.findAll();

        // remplissage des colonnes en fonction des getter+propriété de l'objet Client
        ListPatient_col_ID.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        ListPatient_col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ListPatient_col_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ListPatient_col_Mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        ListPatient_col_Tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        ListPatient_col_Medecin.setCellValueFactory(new PropertyValueFactory<>("nomMed"));
        ListPatient_col_Mutuelle.setCellValueFactory(new PropertyValueFactory<>("nomMut"));

        ListPatient_TableView.setItems(ListPatientList);

    }

}

