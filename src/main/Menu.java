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
    Login loginObj;
    Faculty facultyObj;

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

    // --------------------------------------------------------------------------------------------------
    //                                  Student Methods
    // --------------------------------------------------------------------------------------------------


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
                    loginObj = new Login(Integer.parseInt((idHere.getText())), (pwHere.getText()));
                    if (loginObj.read()){
                        loginID = idHere.getText();
                        loginPW = pwHere.getText();
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

    private VBox myResults() {
        Label header = new Label("My Results");
        Button transcript = new Button("Student Transcript");

        VBox layout = new VBox(header,
                transcript);

        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        transcript.setMinWidth(200);
        transcript.setOnAction(e -> {
            try {
                stage.setScene(studentTranscript());
            } catch (NumberFormatException | FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        return layout;
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
        current.setFont(Font.font("Consolas"));

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
        logout.setOnAction(e -> logout());
        
        return layout;
    }

    private Scene studentTranscript() throws NumberFormatException, FileNotFoundException {

        recsysObj = new RecSys(Integer.parseInt(loginID));
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

    // --------------------------------------------------------------------------------------------------
    //                                  Faculty Methods
    // --------------------------------------------------------------------------------------------------

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
                    loginObj = new Login((idHere.getText()), (pwHere.getText()));
                    if (loginObj.read()){
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

    private VBox editStudent() {
        Label header = new Label("Manage Student Info");
        Button transcript = new Button("View Student Transcript");
        Button editGrades = new Button("Edit Student Grades");
        
        VBox layout = new VBox( header,
                                transcript,
                                editGrades);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        transcript.setMinWidth(200);
        editGrades.setMinWidth(200);

        transcript.setOnAction(e -> {
            try {
                stage.setScene(viewStudentTranscript());
            } catch (NumberFormatException | FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        editGrades.setOnAction(e -> stage.setScene(editStudentGrades()));
        
        return layout;
    }

    /**
     * allows faculty to view a specified student's transcript
     * the student textfield is the desired student's id
     * the go button is supposed to take the textfield and then regenerate the scene with the transcript
     * when you click on this in the GUI it does not work
     */
    private Scene viewStudentTranscript() throws NumberFormatException, FileNotFoundException{
        TextField student = new TextField("Insert Student's ID here"); 
        Label header = new Label("View Student Transcript");
        Button home = new Button("home");
        home.setOnAction(e -> stage.setScene(studentHomeMenu()));
        Button go = new Button("Go");
        go.setOnAction(e -> {
            try {
                stage.setScene(viewStudentTranscript());
            } catch (NumberFormatException | FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        ScrollPane sp = new ScrollPane(facultyView(Integer.parseInt(student.getText())));

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setTop(student);
        root.setTop(go);
        root.setBottom(home);
        root.setCenter(sp); 

        root.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(root, minWidth, minHeight);
    }

    private ScrollPane facultyView(int id) throws FileNotFoundException{

        RecSys facultyRecSys = new RecSys(id);
        TextArea transcript = new TextArea(facultyRecSys.transcript());
        transcript.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        ScrollPane scrollPane = new ScrollPane(transcript);
        scrollPane.setPrefSize(minWidth, minHeight);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        transcript.setEditable(false);
        transcript.setFont(Font.font("Consolas", 12));

        return scrollPane;
    }

    private Scene editStudentGrades(){
        Label header = new Label("Edit Student Grade");
        TextField id = new TextField("");
        TextField courseCode = new TextField("");
        TextField semNumber = new TextField("");
        TextField modCode = new TextField("");
        TextField result = new TextField("");
        Label status = new Label("");

        Label l1 = new Label("Student ID");
        Label l2 = new Label("Course Code");
        Label l3 = new Label("Semester Number");
        Label l4 = new Label("Module Code");
        Label l5 = new Label("Student's Result");

        Button home = new Button("home");
        home.setOnAction(e -> stage.setScene(studentHomeMenu()));
        Button go = new Button("Go");
        go.setOnAction(e -> {
            try {
                facultyObj = new Faculty();
                facultyObj.updateGrade(id.getText(), courseCode.getText(), Integer.parseInt(semNumber.getText()), modCode.getText(), Double.valueOf(result.getText()));
                status.setText("Student's Result Updated!");
            } catch (NumberFormatException e1) {
                status.setText("Update Unsuccessful. Please check that your details are correct.");
                e1.printStackTrace();
            }
        });

        VBox layout = new VBox(home, header, l1, id, 
                            l2, courseCode, l3, semNumber, 
                            l4, modCode, l5, result, 
                            go, status);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(layout, minWidth, minHeight);
    }


    
    // --------------------------------------------------------------------------------------------------
    //                                      General
    // --------------------------------------------------------------------------------------------------

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