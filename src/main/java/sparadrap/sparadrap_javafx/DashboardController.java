package sparadrap.sparadrap_javafx;



import DAO.gestion.AchatDAO;
import DAO.gestion.AdresseDAO;
import DAO.personne.ClientDAO;
import DAO.personne.MedecinDAO;
import DAO.personne.personneDAO;
import DAO.sante.CategorieMedicamentDAO;
import DAO.sante.MedicamentDAO;
import DAO.sante.MutuelleDAO;
import DAO.sante.OrdonnanceDAO;
import classMetier.Util.FilePdf;
import classMetier.Util.Regex;
import classMetier.Util.getData;
import classMetier.gestion.Achat;
import classMetier.gestion.Adresse;
import classMetier.gestion.Panier;
import classMetier.personne.Client;
import classMetier.personne.Medecin;
import classMetier.personne.Personne;
import classMetier.sante.CategorieMedicament;
import classMetier.sante.Medicament;
import classMetier.sante.Mutuelle;
import classMetier.sante.Ordonnance;
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
import java.text.ParseException;
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


    @FXML
    private Button addClient_AddBtn;

    @FXML
    private TextField addClient_AdrCP;

    @FXML
    private TextField addClient_AdrNum;

    @FXML
    private TextField addClient_AdrRue;

    @FXML
    private TextField addClient_AdrVille;

    @FXML
    private Button addClient_BackBtn;

    @FXML
    private TextField addClient_CliDateNaiss;

    @FXML
    private TextField addClient_CliNumSecu;

    @FXML
    private ComboBox<Medecin> addClient_Med;

    @FXML
    private ComboBox<Mutuelle> addClient_Mut;

    @FXML
    private TextField addClient_PerMail;

    @FXML
    private TextField addClient_PerNom;

    @FXML
    private TextField addClient_PerPrenom;

    @FXML
    private TextField addClient_PerTel;

    @FXML
    private Button addClient_ResetBtn;

    @FXML
    private AnchorPane addClient_form;

    @FXML
    private Button CreationClient_btn;

    @FXML
    private Button addClient_updateClient;

    @FXML
    private ComboBox<Client> purchase_ClientName;
    @FXML
    private ToggleGroup ord_btnGroup;
    @FXML
    private RadioButton purchase_NoOrd;

    @FXML
    private TextField purchase_NumOrd;

    @FXML
    private RadioButton purchase_Ord;

    @FXML
    private Label purchaselblOrd;

    @FXML
    private AnchorPane ListOrdonannce_form;

    @FXML
    private TableView<Ordonnance> ListOrdonnance_TableView;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_ID;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_Medecin;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_Meds;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_Mutuelle;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_Nom;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_NumOrd;

    @FXML
    private TableColumn<?, ?> ListOrdonnance_col_Prenom;

    @FXML
    private TextField ListOrdonnance_search;

    @FXML
    private Button ListOrdonnance_btn;

    @FXML
    private TableView<Achat> HistoriqueAchat_TableView;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_ID;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Medecin;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Meds;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Mutuelle;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Nom;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Ord;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_Prenom;

    @FXML
    private TableColumn<?, ?> HistoriqueAchat_col_PrixTot;

    @FXML
    private AnchorPane HistoriqueAchat_form;

    @FXML
    private TextField HistoriqueAchat_search;

    @FXML
    private Button HistoriqueAchat_btn;

    @FXML
    private Label AddClient_lblErreur_CP;

    @FXML
    private Label AddClient_lblErreur_Date;

    @FXML
    private Label AddClient_lblErreur_Mail;

    @FXML
    private Label AddClient_lblErreur_Nom;

    @FXML
    private Label AddClient_lblErreur_Num;

    @FXML
    private Label AddClient_lblErreur_NumSecu;

    @FXML
    private Label AddClient_lblErreur_Rue;

    @FXML
    private Label AddClient_lblErreur_Tel;

    @FXML
    private Label AddClient_lblErreur_Ville;

    @FXML
    private Label AddClient_lblErreur_prenom;



    private double x=0;
    private double y=0;



    float prixTot=0;

    /**
     *  Action déconnexion
      */
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


    /**
     * action boutton menu
     * change le form affiché
     */
    public void switch_form(ActionEvent event){
        // affichage du form dashboard + boutton mit en contraste
        if (event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);
            addClient_form.setVisible(false);
            ListOrdonannce_form.setVisible(false);
            HistoriqueAchat_form.setVisible(false);


            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListOrdonnance_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:TRANSPARENT");
        }
        // affichage du form ajout Medicament + boutton mit en contraste
        if (event.getSource() == addMeds_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(true);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);
            addClient_form.setVisible(false);
            ListOrdonannce_form.setVisible(false);
            HistoriqueAchat_form.setVisible(false);

            addMeds_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListOrdonnance_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:TRANSPARENT");

            addMedicineReset();
            add_type.getSelectionModel().select(0);


            addListType();
            addMedicineShowList();
            addMedicineSearch();
        }

        // affichage du form correspondant au boutton cliqué
        if (event.getSource() == purchase_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            ListPatient_form.setVisible(false);
            purchase_from.setVisible(true);
            ListOrdonannce_form.setVisible(false);
            HistoriqueAchat_form.setVisible(false);

            addClient_form.setVisible(false);

            purchase_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListOrdonnance_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:TRANSPARENT");

            purchaseListType();
            puchase_type.getSelectionModel().select(-1);
            purchaseMedicineShowList(null);



            prixTot=0;
            purchase_tot.setText(String.valueOf(prixTot));

            purchase_ClientList();

            purchaseResetAll();

        }
        if (event.getSource() == ListPatient_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(true);
            addClient_form.setVisible(false);
            ListOrdonannce_form.setVisible(false);
            HistoriqueAchat_form.setVisible(false);

            ListPatient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListOrdonnance_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:TRANSPARENT");

            ListPatientShowList();
        }
        if (event.getSource() == ListOrdonnance_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);
            ListOrdonannce_form.setVisible(true);
            addClient_form.setVisible(false);
            HistoriqueAchat_form.setVisible(false);

            ListOrdonnance_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:TRANSPARENT");

            ListOrdonnanceShowList();
        }
        if (event.getSource() == HistoriqueAchat_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            purchase_from.setVisible(false);
            ListPatient_form.setVisible(false);
            ListOrdonannce_form.setVisible(false);
            addClient_form.setVisible(false);
            HistoriqueAchat_form.setVisible(true);

            ListOrdonnance_btn.setStyle("-fx-background-color:TRANSPARENT");
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");
            addMeds_btn.setStyle("-fx-background-color:TRANSPARENT");
            dashboard_btn.setStyle("-fx-background-color:TRANSPARENT");
            purchase_btn.setStyle("-fx-background-color:TRANSPARENT");
            HistoriqueAchat_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");

            ListAchatShowList();
        }

    }


    /**
     * boutton reduit la fenetre
     */
    public void minimize(){
            Stage stage = (Stage)main_form.getScene().getWindow();
            stage.setIconified(true);
        }

    /**
     * boutton quitter ferme application
      */

        public  void close(){
            System.exit(0);
        }


    private MedicamentDAO medicamentDAO = new MedicamentDAO();

    CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
    private ObservableList<Medicament> addMedicineList;

    Panier panier;


    /**
     *  remplissage tableau gestion medicament
      */

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

    /**
     * remplissage automatique lorsque sélection médicament dans tableau
     */
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

    /**
     * Ajout d'un médicament dans la base de données
     */
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
        } else if (add_productName.getText().matches(Regex.getRegexNomMed())
                || add_price.getText().matches(Regex.getRegexPrix()) || add_date.getText().matches(Regex.getRegexDate())
                || add_qte.getText().matches(Regex.getRegexInt()) ) {
            alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill correctly all fields");
            alert.showAndWait();

        } else {
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

    /**
     * insertion d'image  -- non obligatoire
     */
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


    /**
     * initialisation de la page
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMedicineShowList();
        addListType();
    }

    /**
     * remplissage combobox type de médicament
     */
    public void addListType(){

        List<CategorieMedicament> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(categorieMedicamentDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        add_type.setItems(list);
    }


    /**
     * vide les champs d'ajout de médicament
     */
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


    /**
     * mise a jour d'un médicament
     */
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


    /**
     * supression du medicament sélectionner
     * demande confirmation avant supression
     */
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

    /**
     * tri du tableau en fonction du champ de recherche
     */
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


    /**
     * formulaire purchase
     */

    private ObservableList<Medicament> purchaseMedicineList = medicamentDAO.addMedecineList();

    /**
     * affichage list médicament en fonction de la catégorie sélectionné
     */
    public void purchaseType(){

        if (puchase_type.getSelectionModel().getSelectedItem()!=null) {
            purchaseMedicineList = medicamentDAO.selectMedsFromCat(puchase_type.getSelectionModel().getSelectedItem());
        }
        else {
            purchaseMedicineList = medicamentDAO.addMedecineList();
        }
        purchaseMedicineShowList(purchaseMedicineList);
    }

    /**
     * remplissage combobox catégorie médicament
     */
    public void purchaseListType()
    {

        List<CategorieMedicament> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(categorieMedicamentDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        puchase_type.setItems(list);
    }

    /**
     * filtre recherche médicament
     */
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


    /**
     * affichage liste médicament
     * @param List
     */
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


    /**
     * reset filtre recherche médicament
     */
    public void purchaseResetFilter(){
        purchaseMedicineList = medicamentDAO.addMedecineList();

        puchase_type.getSelectionModel().select(-1);
        purchase_contains.setText("");

    }


    /**
     * action ajouter au panier
     */
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
                int i=0;
                boolean find = false;
                while (!find && i<panier.getResumePanier().size() ) {
                    if (medicament.getId() == panier.getResumePanier().get(i).getId()) {
                        panier.getResumePanier().get(i).setQuantite(
                                panier.getResumePanier().get(i).getQuantite()+Integer.parseInt(purchase_Quantity.getText()));
                        prixTot-=panier.getResumePanier().get(i).getPrix();
                        panier.getResumePanier().get(i).setPrix(medicament.getPrix() * panier.getResumePanier().get(i).getQuantite());
                        prixTot += panier.getResumePanier().get(i).getPrix();
                        find = true;
                    }
                    i++;
                }

                if (!find){
                    Medicament medClone =new Medicament(medicament,Integer.parseInt(purchase_Quantity.getText()));
                    panier.getResumePanier().add(medClone);
                    prixTot += medicament.getPrix();
                }

            }else {
                Medicament medClone =new Medicament(medicament,Integer.parseInt(purchase_Quantity.getText()));
                panier.getResumePanier().add(medClone);
                prixTot += medClone.getPrix();
            }

            purchase_tot.setText(String.valueOf(prixTot));



            purchase_CartCol_medicineID.setCellValueFactory(new PropertyValueFactory<>("id"));
            purchase_CartCol_productName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            purchase_CartCol_type.setCellValueFactory(new PropertyValueFactory<>("categorieMed"));
            purchase_CartCol_Qty.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            purchase_CartCol_price.setCellValueFactory(new PropertyValueFactory<>("prix"));
            purchase_CartCol_date.setCellValueFactory(new PropertyValueFactory<>("dateMES"));

            purchase_CartTableView.setItems(panier.getResumePanier());
            purchase_CartTableView.refresh();

        }

    }


    /**
     * action supression article du panier
     */
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
    OrdonnanceDAO ordonnanceDAO = new OrdonnanceDAO();

    /**
     * Action boutton effectuer achat
     * @throws ParseException
     */
    public void purchasePay() throws ParseException {
        Alert alert;

        if(panier.getResumePanier().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Need at least one medicine in the cart");
            alert.showAndWait();
        }else{
            if (purchase_ClientName.getSelectionModel().getSelectedItem() == null){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("choose a customer");
                alert.showAndWait();
            }
            else {

                if (purchase_Ord.isSelected() && purchase_NumOrd.getText().isEmpty()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Fill Ord number field please");
                    alert.showAndWait();
                } else if (purchase_NoOrd.isSelected()) {
                    achatDAO.create(new Achat(0, purchase_ClientName.getSelectionModel().getSelectedItem(),
                            panier, prixTot, classMetier.Util.Date.newDate(), null));

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Achat effectué");
                    alert.showAndWait();

                    purchaseResetAll();
                } else{
                    Ordonnance ordonnance=ordonnanceDAO.find(Integer.parseInt(purchase_NumOrd.getText()));
                    if (ordonnance ==null ){
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Ord not exist continue  without ord ?");
                        alert.showAndWait();
                        Optional<ButtonType> option = alert.showAndWait();
                        if (option.get().equals(ButtonType.OK)) {

                            achatDAO.create(new Achat(0, purchase_ClientName.getSelectionModel().getSelectedItem(),
                                    panier, prixTot, classMetier.Util.Date.newDate(), null));

                            alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Achat effectué");
                            alert.showAndWait();

                            purchaseResetAll();
                        }
                    }
                    else {

                        achatDAO.create(new Achat(0, purchase_ClientName.getSelectionModel().getSelectedItem(),
                                panier, prixTot, classMetier.Util.Date.newDate(), ordonnance));

                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Achat effectué");
                        alert.showAndWait();

                        purchaseResetAll();
                    }
                }
            }
        }
    }


    private ObservableList<Client> ListPatientList;
    private ClientDAO clientDAO = new ClientDAO();

    /**
     * affichage de la liste de Client
     */
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

    AdresseDAO adresseDAO= new AdresseDAO();
    personneDAO personneDAO = new personneDAO();

    /**
     * Action boutton creation client
     */
    public  void createClient(){
        Alert alert;

        if (!checkFields().isEmpty()){
            String msgAlert = "Les champs suivant sont obligatoire :" + checkFields();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(msgAlert);
            alert.showAndWait();
        }
        else {
            try {

                Adresse adresse = new Adresse(0, Integer.parseInt(addClient_AdrNum.getText()),
                        addClient_AdrRue.getText(), addClient_AdrCP.getText(),
                        addClient_AdrVille.getText());


                Personne personne = new Personne(0,addClient_PerNom.getText(),
                        addClient_PerPrenom.getText(),
                        addClient_PerMail.getText(), addClient_PerTel.getText(),
                        adresse);

                Client client = new Client(0,
                        personne,
                        addClient_CliDateNaiss.getText(),
                        addClient_Med.getSelectionModel().getSelectedItem(),
                        addClient_Mut.getSelectionModel().getSelectedItem(),
                        addClient_CliNumSecu.getText());


                adresseDAO.verif_adr(adresse);

                personneDAO.verif_per(personne);

                clientDAO.verif_client(client);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Client ajouter a la BDD");
                alert.showAndWait();

                addClientReset();

            }catch (Exception e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }


        }
    }


    /**
     * Verifie que les champs sont remplis
     * A ajouter verification avec regex
     * @return
     */
    public StringBuilder checkFields(){

        StringBuilder msgAlert = new StringBuilder();
        if (addClient_PerNom.getText().isEmpty()){
            msgAlert.append("\n- Nom");
        } else if (!addClient_PerNom.getText().matches(Regex.getRegexPrenom())) {
            AddClient_lblErreur_Nom.setVisible(true);
        }else {
            AddClient_lblErreur_Nom.setVisible(false);
        }

        if (addClient_PerPrenom.getText().isEmpty()){
            msgAlert.append("\n- Prénom");
        } else if (!addClient_PerPrenom.getText().matches(Regex.getRegexPrenom())) {
            AddClient_lblErreur_prenom.setVisible(true);
        }else {
            AddClient_lblErreur_prenom.setVisible(false);
        }

        if (addClient_PerMail.getText().isEmpty()){
            msgAlert.append("\n- Mail");
        } else if (!addClient_PerMail.getText().matches(Regex.getRegexMail())) {

            AddClient_lblErreur_Mail.setVisible(true);
        }else {
            AddClient_lblErreur_Mail.setVisible(false);
        }

        if (addClient_PerTel.getText().isEmpty()){
            msgAlert.append("\n- Teléphone");
        } else if (!addClient_PerTel.getText().matches(Regex.getRegexTel())) {
            AddClient_lblErreur_Tel.setVisible(true);
        }else {
            AddClient_lblErreur_Tel.setVisible(false);
        }

        if (addClient_AdrNum.getText().isEmpty()){
            msgAlert.append("\n- Numéro");
        } else if (!addClient_AdrNum.getText().matches(Regex.getRegexNumeroAdresse())) {
            AddClient_lblErreur_Num.setVisible(true);
        }else {
            AddClient_lblErreur_Num.setVisible(false);
        }

        if (addClient_AdrRue.getText().isEmpty()){
            msgAlert.append("\n- Rue");
        } else if (!addClient_AdrRue.getText().matches(Regex.getRegexNomAdresse())) {
            AddClient_lblErreur_Rue.setVisible(true);
        }else {
            AddClient_lblErreur_Rue.setVisible(false);
        }

        if (addClient_AdrCP.getText().isEmpty()){
            msgAlert.append("\n- Code Postal");
        } else if (!addClient_AdrCP.getText().matches(Regex.getRegexCodePostal())) {
            AddClient_lblErreur_CP.setVisible(true);
        }else {
            AddClient_lblErreur_CP.setVisible(false);
        }

        if (addClient_AdrVille.getText().isEmpty()){
            msgAlert.append("\n- Ville");
        } else if (!addClient_AdrVille.getText().matches(Regex.getRegexVille())) {
            AddClient_lblErreur_Ville.setVisible(false);
        }else {
            AddClient_lblErreur_Ville.setVisible(false);
        }

        if (addClient_CliDateNaiss.getText().isEmpty()){
            msgAlert.append("\n- Date de Naissance");
        }else if (!addClient_CliDateNaiss.getText().matches(Regex.getRegexDateNaiss())){
            AddClient_lblErreur_Date.setVisible(true);
        }else {
            AddClient_lblErreur_Date.setVisible(false);
        }

        if (addClient_CliNumSecu.getText().isEmpty()){
            msgAlert.append("\n- Numéro de Sécurité Social");
        } else if (!addClient_CliNumSecu.getText().matches(Regex.getRegexNumSecu())) {
            AddClient_lblErreur_NumSecu.setVisible(true);
        }else {
            AddClient_lblErreur_NumSecu.setVisible(false);
        }

        if (addClient_Med.getSelectionModel().getSelectedIndex()==-1){
            msgAlert.append("\n- Medecin Traitant");
        }

        if (addClient_Mut.getSelectionModel().getSelectedIndex()==-1){
            msgAlert.append("\n- Mutuelle");
        }

        return msgAlert;
    }


    /**
     * remplissage combobox mutuelle
     */
    MutuelleDAO mutuelleDAO = new MutuelleDAO();
    public void AddClientMutList(){
        List<Mutuelle> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(mutuelleDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        addClient_Mut.setItems(list);
    }

    /**
     * remplissage combobox medecin
     */
    MedecinDAO medecinDAO = new MedecinDAO();
    public void AddClientMedList(){
        List<Medecin> listT = new ArrayList<>();
        CategorieMedicamentDAO categorieMedicamentDAO = new CategorieMedicamentDAO();
        listT.addAll(medecinDAO.findAll());

        ObservableList list = FXCollections.observableArrayList(listT);
        addClient_Med.setItems(list);
    }


    /**
     * réinitialise tout les champs de Ajout Client
     */
    public void addClientReset(){
        addClient_PerNom.setText("");
        addClient_PerPrenom.setText("");
        addClient_PerMail.setText("");
        addClient_PerTel.setText("");

        addClient_AdrNum.setText("");
        addClient_AdrRue.setText("");
        addClient_AdrCP.setText("");
        addClient_AdrVille.setText("");

        addClient_CliNumSecu.setText("");
        addClient_CliDateNaiss.setText("");

        addClient_Med.getSelectionModel().select(-1);
        addClient_Mut.getSelectionModel().select(-1);
    }


    /**
     * Retour a la list de Client
     */
    public void addClientRetun(){
        addClient_form.setVisible(false);
        ListPatient_form.setVisible(true);
        ListPatient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
    }

    /**
     * Affichage Panel Ajout Client
     */
    public void ListPatientAddClient(){
        ListPatient_form.setVisible(false);
        addClient_form.setVisible(true);
        ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");

        addClient_AddBtn.setVisible(true);
        addClient_ResetBtn.setVisible(true);
        addClient_updateClient.setVisible(false);


        hideAllErrorLbl();
        AddClientMedList();
        AddClientMutList();
        addClientReset();

    }


    /**
     * Affichae formClient avec champs completer automatiquement selon Client sélectionner
     */
    public void ListPatientUpdateClient(){
        int num = ListPatient_TableView.getSelectionModel().getSelectedIndex();

        Alert alert;

        if (num < 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Selectionné un client dans la liste");
            alert.showAndWait();
        }
        else {
            ListPatient_form.setVisible(false);
            addClient_form.setVisible(true);
            ListPatient_btn.setStyle("-fx-background-color:TRANSPARENT");

            AddClientMedList();
            AddClientMutList();
            addClientReset();
            Client client = ListPatient_TableView.getSelectionModel().getSelectedItem();
            Adresse adresse = client.getPersonne().getAdresse();
            Personne personne = client.getPersonne();


            addClient_PerNom.setText(personne.getNom());
            addClient_PerPrenom.setText(personne.getPrenom());
            addClient_PerMail.setText(personne.getMail());
            addClient_PerTel.setText(personne.getTel());

            addClient_AdrNum.setText(String.valueOf(adresse.getNumero()));
            addClient_AdrRue.setText(adresse.getRue());
            addClient_AdrCP.setText(adresse.getCodePostal());
            addClient_AdrVille.setText(adresse.getVille());

            addClient_CliNumSecu.setText(client.getNumSecu());
            addClient_CliDateNaiss.setText(client.getDateNaiss());

            addClient_Med.getSelectionModel().select(client.getMedecin());
            addClient_Mut.getSelectionModel().select(client.getMutuelle());

            addClient_AddBtn.setVisible(false);
            addClient_ResetBtn.setVisible(false);
            addClient_updateClient.setVisible(true);

        }
    }


    /**
     * Action Mise a jour Client
     */
    public void updateClient(){
            Alert alert;

        if (!checkFields().isEmpty()){

            String msgAlert = "Les champs suivant sont obligatoire :" + checkFields();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(msgAlert);
            alert.showAndWait();
        }
        else {
            try {

                Client client = ListPatient_TableView.getSelectionModel().getSelectedItem();
                Adresse adresse = client.getPersonne().getAdresse();
                Personne personne = client.getPersonne();




                adresse.setNumero(Integer.parseInt(addClient_AdrNum.getText()));
                //        addClient_AdrRue.getText(), addClient_AdrCP.getText(),
                //        addClient_AdrVille.getText()


                // setter client + adresse a faire

                adresseDAO.update(adresse);

                personneDAO.update(personne);

                clientDAO.update(client);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Mise a Jour effectué");
                alert.showAndWait();

                addClientReset();

                ListPatient_TableView.refresh();
                ListPatient_TableView.setItems(clientDAO.findAll());

            }catch (Exception e){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        }
    }


    /**
     * Suppr Patient sélectionner
     */
    public void ListPatientDelete(){

        int num = ListPatient_TableView.getSelectionModel().getSelectedIndex();

        Alert alert;

        if (num < 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Selectionné un client dans la liste");
            alert.showAndWait();
        }
        else {
            Client client = ListPatient_TableView.getSelectionModel().getSelectedItem();
            Adresse adresse = client.getPersonne().getAdresse();
            Personne personne = client.getPersonne();

            clientDAO.delete(client);
            ListPatient_TableView.refresh();
            ListPatient_TableView.setItems(clientDAO.findAll());
        }
    }

    /**
     * Affihchage Panel Achat avec client Préselectionner
     */
    public void ListPatientPurchase(){
        int num = ListPatient_TableView.getSelectionModel().getSelectedIndex();

        Alert alert;

        if (num < 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Selectionné un client dans la liste");
            alert.showAndWait();
        }
        else{
            purchase_ClientList();
            ListPatient_form.setVisible(false);
            purchase_from.setVisible(true);
            purchase_ClientName.getSelectionModel().select(ListPatient_TableView.getSelectionModel().getSelectedItem());
        }

    }

    /**
     * Remplissage Combobox Client
     */
    public void purchase_ClientList(){
        ObservableList<Client> listClient = clientDAO.findAll();
        purchase_ClientName.setItems(listClient);

    }

    public void purchaseResetAll(){
        purchaseResetFilter();
        purchaseResetCart();
        purchase_Quantity.setText("");

        purchase_ClientName.getSelectionModel().select(-1);

    }

    public void purchaseResetCart(){
        panier = new Panier(0,FXCollections.observableArrayList());
        purchase_CartTableView.setItems(null);
    }

    public void purchaseOrd(){
        if (purchase_Ord.isSelected()){
            purchase_NumOrd.setVisible(true);
            purchase_NumOrd.setText("");
            purchaselblOrd.setVisible(true);

        }
        if (purchase_NoOrd.isSelected()){
            purchase_NumOrd.setVisible(false);
            purchaselblOrd.setVisible(false);
        }
    }


    private ObservableList<Ordonnance> ListOrdonannce;

    public void ListOrdonnanceShowList(){


        // affichage de la liste de Client
        ListOrdonannce = ordonnanceDAO.findAll();

        // remplissage des colonnes en fonction des getter+propriété de l'objet Client
        ListOrdonnance_col_ID.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        ListOrdonnance_col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ListOrdonnance_col_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        ListOrdonnance_col_NumOrd.setCellValueFactory(new PropertyValueFactory<>("NumOrd"));
        ListOrdonnance_col_Meds.setCellValueFactory(new PropertyValueFactory<>("ListMeds"));
        ListOrdonnance_col_Medecin.setCellValueFactory(new PropertyValueFactory<>("nomMed"));
        ListOrdonnance_col_Mutuelle.setCellValueFactory(new PropertyValueFactory<>("nomMut"));

        ListOrdonnance_TableView.setItems(ListOrdonannce);
    }


    private ObservableList<Achat> ListAchat;
    public void ListAchatShowList(){


        // affichage de la liste de Client
        ListAchat = achatDAO.findAll();

        // remplissage des colonnes en fonction des getter+propriété de l'objet Client
        HistoriqueAchat_col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        HistoriqueAchat_col_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        HistoriqueAchat_col_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        HistoriqueAchat_col_Ord.setCellValueFactory(new PropertyValueFactory<>("Ord"));
        HistoriqueAchat_col_PrixTot.setCellValueFactory(new PropertyValueFactory<>("PrixTot"));
        HistoriqueAchat_col_Meds.setCellValueFactory(new PropertyValueFactory<>("ListMeds"));
        HistoriqueAchat_col_Mutuelle.setCellValueFactory(new PropertyValueFactory<>("nomMut"));

        HistoriqueAchat_TableView.setItems(ListAchat);
    }

    /**
     * Action génération et ouverture PDF Ordonnance
     */
    public void ListOrdonnancePDF(){
       String name = "";
       String date = "";

       int num = ListOrdonnance_TableView.getSelectionModel().getSelectedIndex();

       Alert alert;

       if (num < 0){
           alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Selectionné dans la liste");
           alert.showAndWait();
       }
       else {
           Ordonnance ord = ListOrdonnance_TableView.getSelectionModel().getSelectedItem();
           StringBuilder listMed = new StringBuilder();
           name = ord.getClient().getNom() + " " + ord.getClient().getPrenom();
           date = ord.getDate().replace("/", "-");
           String titre = "Ordonnance du " + date + " " + name;
           String medecin = ord.getMedecin().getPersonne().getNom() + " " + ord.getMedecin().getPersonne().getPrenom();

           for (Medicament medicament : ord.getCompose().getListMedic()){
               listMed.append("\n"+medicament.getNom());
           }

           try {
               FilePdf.createPdf(titre, listMed.toString(), name, date, medecin);
               FilePdf.openPdf(name,date);
           }catch (Exception e){
               e.printStackTrace();
           }
       }


   }

   public void ListOrdonnanceSearch(){
       FilteredList<Ordonnance> filter = new FilteredList<>(ListOrdonannce, e-> true);

       ListOrdonnance_search.textProperty().addListener((Observable,oldValue,newValue) -> {
           filter.setPredicate(predicateOrd -> {
               if ( newValue == null || newValue.isEmpty()){
                   return true;
               }
               String searchKey = newValue.toLowerCase();

               if (String.valueOf(predicateOrd.getId()).toLowerCase().contains(searchKey)){
                   return true;
               } else if (predicateOrd.getClient().getPrenom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateOrd.getClient().getNom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateOrd.getDate().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateOrd.getMedecin().getPersonne().getNom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateOrd.getListMeds().toLowerCase().contains(searchKey)) {
                   return true;
               }
               else {
                   return false;
               }
           });
       });

       SortedList<Ordonnance> sortList = new SortedList<>(filter);
       sortList.comparatorProperty().bind(ListOrdonnance_TableView.comparatorProperty());
       ListOrdonnance_TableView.setItems(sortList);
   }

   public void ListClientSearch(){
       FilteredList<Client> filter = new FilteredList<>(ListPatientList, e-> true);

       ListPatient_Search.textProperty().addListener((Observable,oldValue,newValue) -> {
           filter.setPredicate(predicateCli -> {
               if ( newValue == null || newValue.isEmpty()){
                   return true;
               }
               String searchKey = newValue.toLowerCase();

               if (String.valueOf(predicateCli.getIdClient()).toString().toLowerCase().contains(searchKey)){
                   return true;
               } else if (predicateCli.getPrenom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateCli.getNom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateCli.getMail().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateCli.getMedecin().getPersonne().getPrenom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateCli.getTel().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateCli.getMutuelle().getNom().toLowerCase().contains(searchKey)) {
                   return true;
               }
               else {
                   return false;
               }
           });
       });

       SortedList<Client> sortList = new SortedList<>(filter);
       sortList.comparatorProperty().bind(ListPatient_TableView.comparatorProperty());
       ListPatient_TableView.setItems(sortList);
   }

   public void ListAchatSearch(){
       FilteredList<Achat> filter = new FilteredList<>(ListAchat, e-> true);

       HistoriqueAchat_search.textProperty().addListener((Observable,oldValue,newValue) -> {
           filter.setPredicate(predicateAch -> {
               if ( newValue == null || newValue.isEmpty()){
                   return true;
               }
               String searchKey = newValue.toLowerCase();

               if (String.valueOf(predicateAch.getId()).toLowerCase().contains(searchKey)){
                   return true;
               } else if (predicateAch.getPrenom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateAch.getNom().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateAch.getDate().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateAch.getListMeds().toLowerCase().contains(searchKey)) {
                   return true;
               } else if (predicateAch.getOrd() !=null) {
                   if (predicateAch.getOrd().toLowerCase().contains(searchKey)) {
                   return true;
                   }
                   else {
                       return false;
                   }
               }
               else {
                   return false;
               }
           });
       });

       SortedList<Achat> sortList = new SortedList<>(filter);
       sortList.comparatorProperty().bind(HistoriqueAchat_TableView.comparatorProperty());
       HistoriqueAchat_TableView.setItems(sortList);
   }

    /**
     * Reset tous les message d'erreur du formulaire Ajout Client
     */
    public void hideAllErrorLbl(){

        AddClient_lblErreur_CP.setVisible(false);
        AddClient_lblErreur_Date.setVisible(false);
        AddClient_lblErreur_Mail.setVisible(false);
        AddClient_lblErreur_Nom.setVisible(false);
        AddClient_lblErreur_Num.setVisible(false);
        AddClient_lblErreur_NumSecu.setVisible(false);
        AddClient_lblErreur_Rue.setVisible(false);
        AddClient_lblErreur_Tel.setVisible(false);
        AddClient_lblErreur_Ville.setVisible(false);
        AddClient_lblErreur_prenom.setVisible(false);
    }


}

