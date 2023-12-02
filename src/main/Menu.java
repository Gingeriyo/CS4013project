package main;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class Menu extends Application {

    private Stage stage;
    private int minWidth = 720;
    private int minHeight = 480;
    public String loginID;
    public String loginPW;
    RecSys recsysObj;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("UL Portal");
        primaryStage.setResizable(false);
        primaryStage.setScene(startup());
        primaryStage.show();
    }

    private Scene startup() {
        Label header = new Label("University Portal");
        Label subtitle = new Label("Choose Login type.");
        Button faculty = new Button("Faculty");
        Button student = new Button("Student");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
        subtitle.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        subtitle.setTextFill(Color.web("#FF0000"));
        faculty.setMinWidth(200);
        student.setMinWidth(200);

        faculty.setOnAction(e -> stage.setScene(facultyLogin()));
        student.setOnAction(e -> stage.setScene(studentLogin()));

        VBox layout = new VBox(
                header,
                new Label(""), // this is here to act as a blank space
                subtitle,
                faculty,
                student);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(500, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene facultyLogin() {
        Label header = new Label("Portal Login");
        Button loginButton = new Button("Double Click to Login!");
        Label idLabel = new Label("EMAIL:");
        Label pwLabel = new Label("PASSWORD:");
        TextField idHere = new TextField("");
        PasswordField pwHere = new PasswordField();
        Label failure = new Label("");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
        idLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        pwLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        idLabel.setTextFill(Color.web("#FF0000"));
        pwLabel.setTextFill(Color.web("#FF0000"));
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    Login loginOBJ;
                    loginOBJ = new Login((idHere.getText()), (pwHere.getText()));
                    if (loginOBJ.read()){
                        loginID = idHere.getText();
                        loginPW = pwHere.getText();
                        loginButton.setOnAction(e -> stage.setScene(facultyHomeMenu())); 
                    } else{
                        failure.setText("Login Failed");
                        loginButton.setOnAction(e -> stage.setScene(facultyLogin())); 
                    }
                } catch (NumberFormatException | FileNotFoundException e) {
                    failure.setText("Login Failed");
                }
            }
        });

        VBox layout = new VBox(
                header,
                new Label(""), // this is here to act as a blank space
                idLabel,
                idHere,
                pwLabel,
                pwHere,
                loginButton,
                failure);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(600, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene studentLogin() {
        Label header = new Label("Portal Login");
        Button loginButton = new Button("Double Click to Login!");
        Label idLabel = new Label("ID:");
        Label pwLabel = new Label("PASSWORD:");
        TextField idHere = new TextField("");
        PasswordField pwHere = new PasswordField();
        Label failure = new Label("");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));
        idLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        pwLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        idLabel.setTextFill(Color.web("#FF0000"));
        pwLabel.setTextFill(Color.web("#FF0000"));
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    Login loginOBJ;
                    loginOBJ = new Login(Integer.parseInt((idHere.getText())), (pwHere.getText()));
                    if (loginOBJ.read()){
                        loginID = idHere.getText();
                        loginPW = pwHere.getText();
                        recsysObj = new RecSys(Integer.valueOf(loginID));
                        loginButton.setOnAction(e -> stage.setScene(studentHomeMenu())); 
                    } else{
                        failure.setText("Login Failed");
                        loginButton.setOnAction(e -> stage.setScene(studentLogin())); 
                    }
                } catch (NumberFormatException | FileNotFoundException e) {
                    failure.setText("Login Failed");
                }
            }
        });

        VBox layout = new VBox(
                header,
                new Label(""), // this is here to act as a blank space
                idLabel,
                idHere,
                pwLabel,
                pwHere,
                loginButton,
                failure);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(600, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene studentHomeMenu() {
        GridPane layout = new GridPane();

        Label header = new Label("Home");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));

        layout.add(header, 0, 1); // col, row
        layout.add(myResults(), 0, 2);
        layout.add(options(), 0, 3);
        layout.add(myModules(), 1, 2);

        layout.setStyle("-fx-padding: 20;");
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth / 2) - 50));
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth / 2) - 50));
        layout.setVgap(20);
        layout.setHgap(20);

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene facultyHomeMenu(){
        GridPane layout = new GridPane();

        Label header = new Label("Home");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));

        layout.add(header, 0, 1); // col, row
        layout.add(editStudent(), 0, 2);
        layout.add(options(), 0, 3);

        layout.setStyle("-fx-padding: 20;");
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth / 2) - 50));
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth / 2) - 50));
        layout.setVgap(20);
        layout.setHgap(20);

        return new Scene(layout, minWidth, minHeight);
    }

    private VBox myResults() {
        Label header = new Label("My Results");
        Button currentResults = new Button("Current Results");
        Button transcript = new Button("Student Transcript");

        VBox layout = new VBox(header,
                currentResults,
                transcript);

        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        currentResults.setMinWidth(200);
        transcript.setMinWidth(200);
        currentResults.setOnAction(e -> stage.setScene(currentResults()));
        transcript.setOnAction(e -> {
            try {
                stage.setScene(studentTranscript());
            } catch (NumberFormatException | FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        return layout;
    }

    private VBox editStudent(){
        Label header = new Label("Manage Student Info");
        Button details = new Button("Edit Student Details");
        Button editGrades = new Button("Edit Student Grades");
        
        VBox layout = new VBox( header,
                                details,
                                editGrades);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        details.setMinWidth(200);
        editGrades.setMinWidth(200);

        details.setOnAction(e -> stage.setScene(editStudentDetails()));
        editGrades.setOnAction(e -> stage.setScene(editStudentGrades()));
        
        return new VBox();
    }

    private Scene editStudentDetails(){

        return new Scene(null);
    }

    private Scene editStudentGrades(){

        

        return new Scene(null);
    }

    private VBox myModules() {
        Label header = new Label("Current Modules");

        VBox layout = new VBox(header, modules());
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return layout;
    }

    private Label modules(){
        RecSys modulesString;

        Label current = new Label();

        try {
        modulesString = new RecSys(Integer.parseInt(loginID));
        
        String list = "Module, Credits, Module Name\n" + modulesString.getModulesInfo(modulesString.getLastSemesterNum());
        
        current = new Label(list);

        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return current;
    }

    private VBox options() {
        Label header = new Label("Options");
        Button details = new Button("View Personal Details");
        Button logout = new Button("Logout");
        logout.setOnAction(e -> logout());

        VBox layout = new VBox( header,
                                details,
                                logout);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        details.setMinWidth(200);
        logout.setMinWidth(200);

        details.setOnAction(e -> stage.setScene(personalDetails()));
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                logout();
            }
        });
        return layout;
    }

    private Scene currentResults() {

        return new Scene(null);
    }

    private Scene studentTranscript() throws NumberFormatException, FileNotFoundException {

        TextArea transcript = new TextArea(recsysObj.transcript());
        
        Label header = new Label("Student Transcript");
        Button home = new Button("home");
        home.setOnAction(e -> stage.setScene(studentHomeMenu()));

        transcript.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        ScrollPane scrollPane = new ScrollPane(transcript);
        scrollPane.setPrefSize(minWidth, minHeight);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        BorderPane root = new BorderPane();
        root.setBottom(home);
        root.setTop(header);
        root.setCenter(scrollPane); 

        transcript.setEditable(false);
        transcript.setFont(Font.font("Consolas", 12));
        root.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(root, minWidth, minHeight);
    }

    private Scene personalDetails() {
        Label header = new Label("Personal Details");
        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20));

        // this is going to need so many labels and textfields

        return new Scene(null);
    }

    private void cleanup(){
        loginID = "";
        loginPW = "";
    }

    private void logout() {
        cleanup();
        stage.setScene(startup());
    }

    public static void main(String[] args) {
        launch();
    }
}