package com.example.ticketsystemgui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



import java.net.URL;
import java.util.*;

public class TicketController implements Initializable {

    @FXML
    private Tab TabReportanewBug;
    @FXML
    private Tab TabCurrentBugs;
    @FXML
    private Tab TabResolvedBugs;
    @FXML
    private Label LabelUnResolvedCategory;
    @FXML
    private Label LabelUnResolvedReport;
    @FXML
    private Label LabelUnResolvedHowToReproduce;
    @FXML
    private Button RefreshUnResolvedBtn;
    @FXML
    private Button ResolveBtn;
    @FXML
    private Button UnResolvedDeleteBtn;
    @FXML
    private Label LabelLanguage;
    @FXML
    private Label LabelIsResolvedCategory;
    @FXML
    private Label LabelIsResolvedReport;
    @FXML
    private Label LabelIsResolvedHowToReproduce;
    @FXML
    private Button RefreshIsResolvedBtn;
    @FXML
    private Button DeleteBtn1;
    @FXML
    private Tab TabSettings;
    @FXML
    private Button englishBtn;
    @FXML
    private Button chineseBtn;
    @FXML
    private Label LabelCategory;
    @FXML
    private Button ResolvedBtn;
    @FXML
    private Label LabelDescribeBug;
    @FXML
    private Label LabelDescribeSteps;
    @FXML
    private Button DeleteBtn;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton radio1;
    @FXML
    private TextField isResolvedMoreDetailsCategory;

    @FXML
    private TextField isResolvedMoreDetailsId;

    @FXML
    private TextArea isResolvedMoreDetailsReport;

    @FXML
    private TextArea isResolvedMoreDetailsSteps;
    @FXML
    private TableView<Ticket> isResolvedTable;
    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private TextArea report;

    @FXML
    private TextArea steps;

    @FXML
    private Button submitNewBugBtn;
    @FXML
    private TextField unResolvedMoreDetailsCategory;

    @FXML
    private TextField unResolvedMoreDetailsId;

    @FXML
    private TextArea unResolvedMoreDetailsReport;

    @FXML
    private TextArea unResolvedMoreDetailsSteps;
    @FXML
    private TableColumn<Ticket, String> isResolvedCategory;

    @FXML
    private TableColumn<Ticket, String> isResolvedId;

    @FXML
    private TableColumn<Ticket, String> isResolvedReport;

    @FXML
    private TableColumn<Ticket, String> isResolvedSteps;

    @FXML
    private TableColumn<Ticket, String> unResolvedCategory;

    @FXML
    private TableColumn<Ticket, String> unResolvedId;

    @FXML
    private TableColumn<Ticket, String> unResolvedReport;

    @FXML
    private TableColumn<Ticket, String> unResolvedSteps;

    @FXML
    private TableView<Ticket> unResolvedTable;
    Locale currentLocale = new Locale("en");
    ResourceBundle bundle = ResourceBundle.getBundle("text",currentLocale);
    @FXML
    void Resolved(ActionEvent event) {
        System.out.println("Bug Resolved submitted");
        System.out.println("Bug id " + unResolvedTable.getSelectionModel().getSelectedItem().getId());

        HttpResponse<String> response = Unirest.put("http://localhost:8080/api/resolveTicket=" + unResolvedTable.getSelectionModel().getSelectedItem().getId())
                .asString();
        addUnResolvedTicketShowListData();

    }

    @FXML
    void Delete(ActionEvent event) {
        System.out.println("Bug Deletion submitted");
        System.out.println("Bug id " + unResolvedTable.getSelectionModel().getSelectedItem().getId());

        HttpResponse<String> response = Unirest.delete("http://localhost:8080/api/deleteTicket=" + unResolvedTable.getSelectionModel().getSelectedItem().getId())
                .asString();
        addUnResolvedTicketShowListData();
    }
    @FXML
    void DeleteResolved(ActionEvent event) {
        System.out.println("Bug Deletion submitted");
        System.out.println("Bug id " + isResolvedTable.getSelectionModel().getSelectedItem().getId());

        HttpResponse<String> response = Unirest.delete("http://localhost:8080/api/deleteTicket=" + isResolvedTable.getSelectionModel().getSelectedItem().getId())
                .asString();
        addUnResolvedTicketShowListData();
    }
    @FXML
    void submitNewBug(ActionEvent event) throws IOException {
        System.out.println("Submitting new Ticket");
        RadioButton selected = (RadioButton) category.getSelectedToggle();
        System.out.println(selected.getText());
        System.out.println(report.getText());
        System.out.println(steps.getText());
        System.out.println("false");
        HttpResponse<String> response = Unirest.post("http://localhost:8080/api/addTicket")
                .header("Content-Type", "application/json")
                .body("{\r\n    \"category\":\""+selected.getText()+"\",\r\n    \"isResolved\":\"false\",\r\n    \"report\":\""+report.getText()+"\",\r\n    \"stepsToReproduce\":\""+steps.getText()+"\"\r\n\r\n}")
                .asString();
        report.setText("");
        steps.setText("");

        BugSuccessController.resourceBundle = ResourceBundle.getBundle("text", currentLocale);
        Parent root = FXMLLoader.load(getClass().getResource("BugSuccess.fxml"),bundle);
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Bug submitted");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }

    public ObservableList<Ticket> addUnResolvedTicketListData() {

        ObservableList<Ticket> listData = FXCollections.observableArrayList();

        HttpResponse<List<Map<String, String>>> response1 = Unirest.get("http://localhost:8080/api/getUnResolvedTickets")
                .asObject(new GenericType<List<Map<String,String>>>() {});

        //System.out.println(response1.getStatus());
        //System.out.println(response1.getBody().get(1).get("id"));

        Ticket temp;
        for (int i = 0; i<response1.getBody().size(); i++){
            temp = new Ticket(Long.parseLong(response1.getBody().get(i).get("id")));
            temp.setCategory(response1.getBody().get(i).get("category"));
            temp.setReport(response1.getBody().get(i).get("report"));
            temp.setStepsToReproduce(response1.getBody().get(i).get("stepsToReproduce"));
            listData.add(temp);
        }
        System.out.println("showingUnResolvedTicketListData");
        return listData;
    }
    private ObservableList<Ticket> addUnResolvedTicketList;

    public void addUnResolvedTicketShowListData() {
        addUnResolvedTicketList = addUnResolvedTicketListData();

        unResolvedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        unResolvedCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        unResolvedReport.setCellValueFactory(new PropertyValueFactory<>("report"));
        unResolvedSteps.setCellValueFactory(new PropertyValueFactory<>("stepsToReproduce"));
        unResolvedTable.setItems(addUnResolvedTicketList);

    }

    public ObservableList<Ticket> addIsResolvedTicketListData() {

        ObservableList<Ticket> listData = FXCollections.observableArrayList();

        HttpResponse<List<Map<String, String>>> response1 = Unirest.get("http://localhost:8080/api/getResolvedTickets")
                .asObject(new GenericType<List<Map<String,String>>>() {});

        //System.out.println(response1.getStatus());
        //System.out.println(response1.getBody().get(1).get("id"));

        Ticket temp;
        for (int i = 0; i<response1.getBody().size(); i++){
            temp = new Ticket(Long.parseLong(response1.getBody().get(i).get("id")));
            temp.setCategory(response1.getBody().get(i).get("category"));
            temp.setReport(response1.getBody().get(i).get("report"));
            temp.setStepsToReproduce(response1.getBody().get(i).get("stepsToReproduce"));
            listData.add(temp);
        }
        System.out.println("showingIsResolvedTicketListData");
        return listData;
    }
    private ObservableList<Ticket> addIsResolvedTicketList;

    public void addIsResolvedTicketShowListData() {
        addIsResolvedTicketList = addIsResolvedTicketListData();

        isResolvedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        isResolvedCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        isResolvedReport.setCellValueFactory(new PropertyValueFactory<>("report"));
        isResolvedSteps.setCellValueFactory(new PropertyValueFactory<>("stepsToReproduce"));
        isResolvedTable.setItems(addIsResolvedTicketList);

    }

    // will auto refresh the unResolvedTicket table view
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private void refreshTableView() {
        addUnResolvedTicketShowListData();
        addIsResolvedTicketShowListData();
    }


    public void startTableViewAutoRefresh() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(this::refreshTableView, 0, 3, TimeUnit.SECONDS);
    }

    // -------------------------------------
    @FXML
    public void unReslovedShowDetails(){
        System.out.println("Showing more details");
        System.out.println("Selected Bug id " + unResolvedTable.getSelectionModel().getSelectedItem().getId());
        unResolvedMoreDetailsId.setText(unResolvedTable.getSelectionModel().getSelectedItem().getId().toString());
        unResolvedMoreDetailsCategory.setText(unResolvedTable.getSelectionModel().getSelectedItem().getCategory());
        unResolvedMoreDetailsReport.setText(unResolvedTable.getSelectionModel().getSelectedItem().getReport());
        unResolvedMoreDetailsSteps.setText(unResolvedTable.getSelectionModel().getSelectedItem().getStepsToReproduce());
    }

    @FXML
    public void isReslovedShowDetails(){
        System.out.println("Showing more details");
        System.out.println("Selected Bug id " + isResolvedTable.getSelectionModel().getSelectedItem().getId());
        isResolvedMoreDetailsId.setText(isResolvedTable.getSelectionModel().getSelectedItem().getId().toString());
        isResolvedMoreDetailsCategory.setText(isResolvedTable.getSelectionModel().getSelectedItem().getCategory());
        isResolvedMoreDetailsReport.setText(isResolvedTable.getSelectionModel().getSelectedItem().getReport());
        isResolvedMoreDetailsSteps.setText(isResolvedTable.getSelectionModel().getSelectedItem().getStepsToReproduce());
    }

    @FXML
    public void RefreshIsResolved(){
        addIsResolvedTicketShowListData();
    }

    @FXML
    public void RefreshUnResolved(){
        addUnResolvedTicketShowListData();
    }

    public void languageMapper(){
        TabReportanewBug.setText(LanguageManager.getString("ReportanewBug"));
        LabelCategory.setText(LanguageManager.getString("Category"));
        radio1.setText(LanguageManager.getString("BillingIssue"));
        radio2.setText(LanguageManager.getString("TextandAudio"));
        radio3.setText(LanguageManager.getString("Technical"));
        radio4.setText(LanguageManager.getString("Graphical"));
        LabelDescribeBug.setText(LanguageManager.getString("DescribetheBug"));
        report.setPromptText(LanguageManager.getString("Report"));
        LabelDescribeSteps.setText(LanguageManager.getString("Describethestepstoreproducethebug"));
        steps.setPromptText(LanguageManager.getString("Describestepshere"));
        submitNewBugBtn.setText(LanguageManager.getString("Submit"));
        TabCurrentBugs.setText(LanguageManager.getString("CurrentBugs"));
        unResolvedCategory.setText(LanguageManager.getString("Category"));
        unResolvedReport.setText(LanguageManager.getString("Report"));
        unResolvedSteps.setText(LanguageManager.getString("HowToReproduce"));
        LabelUnResolvedCategory.setText(LanguageManager.getString("Category"));
        unResolvedMoreDetailsCategory.setPromptText(LanguageManager.getString("Category"));
        LabelUnResolvedReport.setText(LanguageManager.getString("Report"));
        unResolvedMoreDetailsReport.setPromptText(LanguageManager.getString("Report"));
        LabelUnResolvedHowToReproduce.setText(LanguageManager.getString("HowToReproduce"));
        unResolvedMoreDetailsSteps.setPromptText(LanguageManager.getString("HowToReproduce"));
        RefreshUnResolvedBtn.setText(LanguageManager.getString("RefreshTable"));
        ResolvedBtn.setText(LanguageManager.getString("Resolve"));
        UnResolvedDeleteBtn.setText(LanguageManager.getString("Delete"));
        TabResolvedBugs.setText(LanguageManager.getString("ResolvedBugs"));
        isResolvedCategory.setText(LanguageManager.getString("Category"));
        isResolvedReport.setText(LanguageManager.getString("Report"));
        isResolvedSteps.setText(LanguageManager.getString("HowToReproduce"));
        LabelIsResolvedCategory.setText(LanguageManager.getString("Category"));
        isResolvedMoreDetailsCategory.setPromptText(LanguageManager.getString("Category"));
        LabelIsResolvedReport.setText(LanguageManager.getString("Report"));
        isResolvedMoreDetailsReport.setPromptText(LanguageManager.getString("Report"));
        LabelIsResolvedHowToReproduce.setText(LanguageManager.getString("HowToReproduce"));
        isResolvedMoreDetailsSteps.setPromptText(LanguageManager.getString("HowToReproduce"));
        RefreshIsResolvedBtn.setText(LanguageManager.getString("RefreshTable"));
        DeleteBtn1.setText(LanguageManager.getString("Delete"));
        TabSettings.setText(LanguageManager.getString("Settings"));
        LabelLanguage.setText(LanguageManager.getString("Language"));
    }
    @FXML
    public void switchToChinese(){
        System.out.println("Switching to Chinese");
        LanguageManager.loadResourceBundle("zh");
        String zhFont = "SimSun";
        languageMapper();
        currentLocale = new Locale("zh");
        bundle = ResourceBundle.getBundle("text",currentLocale);


    }
    @FXML
    public void switchToEnglish(){
        System.out.println("Switching to English");
        LanguageManager.loadResourceBundle("en");
        String enFont = "Arial";
        currentLocale = new Locale("en");
        bundle = ResourceBundle.getBundle("text",currentLocale);
        languageMapper();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert report != null : "fx:id=\"report\" was not injected: check your FXML file 'hello-view.fxml'.";
            assert steps != null : "fx:id=\"steps\" was not injected: check your FXML file 'hello-view.fxml'.";

        addUnResolvedTicketShowListData();
        addIsResolvedTicketShowListData();


        //startTableViewAutoRefresh();

    }

}
