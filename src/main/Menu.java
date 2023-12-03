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

    /**
     * Setting the primary stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("UL Portal");
        primaryStage.setResizable(false);
        primaryStage.setScene(startup());
        primaryStage.show();
    }

    /**
     * first stage 
     * choose which type of login (student/faculty)
     * @return returns the scene so it is easy to change to this scene whenever needed
     */
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
    //
    //                                  Student Methods
    //
    // --------------------------------------------------------------------------------------------------


    /**
     * Takes the inputted student id and student password
     * checks if the inputted textfields match an existing student in the csv
     * if so, progresses to student home page
     * if not, displays login failed message
     * @return returns a scene
     */
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

    /**
     * home menu for student, allows them to view their grades and personal info
     * also allows user to logout
     * it is important to return to this scene in later scenes
     * @return returns a scene
     */
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

    /**
     * displays header and button for the user to choose from
     * buttons redirect to new scenes
     * @return returns VBox to be used in studentHomeMenu()
     */
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
                stage.setScene(studentHomeMenu());
            }
        });

        return layout;
    }

    /**
     * displayed on student home menu
     * displays all modules that the student is currently undertaking
     * @return returns a vbox to be used in studentHomeMenu()
     */
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

    /**
     * displays all modules that the student is currently undertaking 
     * @return returns a textfield to be used in myModules()
     */
    private TextArea modules(){
        RecSys modulesString;

        TextArea current;

        try {
        modulesString = new RecSys(Integer.parseInt(loginID));
        
        String list = "Module, Credits, Module Name\n" + modulesString.getModulesInfo(modulesString.getLastSemesterNum());
        
        current = new TextArea(list);
        current.setFont(Font.font("Consolas"));
        current.setEditable(false);
        return current;

        } catch (NumberFormatException | FileNotFoundException e) {
            current = new TextArea("Error!");
            return current;
        }

    }

    /**
     * displays header and buttons for the user to choose from
     * buttons redirect to new scenes
     * @return returns VBox to be used in studentHomeMenu()
     */
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

        details.setOnAction(e -> {
            try {
                stage.setScene(personalDetails());
            } catch (NumberFormatException | FileNotFoundException e1) {
                stage.setScene(facultyHomeMenu());
            }
        });
        logout.setOnAction(e -> logout());
        
        return layout;
    }

    /**
     * allows user to view but not edit their full student transcript
     * @return returns a scene
     * @throws NumberFormatException if can not parse string to int
     * @throws FileNotFoundException if can not find the csv file
     */
    private Scene studentTranscript() throws NumberFormatException, FileNotFoundException {

        recsysObj = new RecSys(Integer.parseInt(loginID));
        TextArea transcript = new TextArea(recsysObj.transcript());
        
        Label header = new Label("Student Transcript");
        Button home = new Button("Home");
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

    /**
     * allows user to view but not edit their personal info
     * @return returns a scene
     * @throws NumberFormatException if can not parse string to int
     * @throws FileNotFoundException if can not find the csv file
     */
    private Scene personalDetails() throws NumberFormatException, FileNotFoundException {
        recsysObj = new RecSys(Integer.parseInt(loginID));
        TextArea info = new TextArea(recsysObj.getStudentInfo());
        
        Label header = new Label("Student Information");
        Button home = new Button("Home");
        home.setOnAction(e -> stage.setScene(studentHomeMenu()));

        info.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        ScrollPane scrollPane = new ScrollPane(info);
        scrollPane.setPrefSize(minWidth, minHeight);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        BorderPane root = new BorderPane();
        root.setBottom(home);
        root.setTop(header);
        root.setCenter(scrollPane); 

        info.setEditable(false);
        info.setFont(Font.font("Consolas", 12));
        root.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(root, minWidth, minHeight);
    }

    // --------------------------------------------------------------------------------------------------
    //
    //                                  Faculty Methods
    //
    // --------------------------------------------------------------------------------------------------

    /**
     * Takes the inputted email and faculty password
     * checks if the inputted textfields match an existing faculty member in the csv
     * if so, progresses to faculty home page
     * if not, displays login failed message
     * @return returns a scene
     */
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

    /**
     * home menu for faculty, allows them to view and edit students' grades
     * also allows user to logout
     * it is important to return to this scene in later scenes
     * @return returns a scene
     */
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

    /**
     * displays options for faculty to view/edit student info
     * @return returns a VBox
     */
    private VBox editStudent() {
        Label header = new Label("Manage Student Info");
        Button transcript = new Button("View Student Transcript");
        Button editGrades = new Button("Edit Student Grades");
        Button editComment = new Button("Edit Student Comment");
        
        VBox layout = new VBox(header, transcript, editGrades, editComment);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        transcript.setMinWidth(200);
        editGrades.setMinWidth(200);
        editComment.setMinWidth(200);

        transcript.setOnAction(e -> {
            try {
                stage.setScene(viewStudentTranscript());
            } catch (NumberFormatException | FileNotFoundException e1) {
                stage.setScene(facultyHomeMenu());
            }
        });
        editGrades.setOnAction(e -> stage.setScene(editStudentGrades()));
        editComment.setOnAction(e -> stage.setScene(editStudentComment()));
        
        return layout;
    }

    /**
     * this scene allows the user to specify the student id of the student whose transcript they want to view
     * @return returns scene 
     * @throws NumberFormatException if parsing fails
     * @throws FileNotFoundException if file path can not be found
     */
    private Scene viewStudentTranscript() throws NumberFormatException, FileNotFoundException{
        Label header = new Label("View Student Transcript");
        Label txtName = new Label("Student's ID");
        Label status = new Label("");
        TextField id = new TextField("");
        Button home = new Button("Home");
        home.setOnAction(e -> stage.setScene(facultyHomeMenu()));
        Button go = new Button("Go");
        go.setOnAction(e -> {
            try {
                int temp = Integer.parseInt(id.getText());
                stage.setScene(facultyViewTranscript(temp));
            } catch (NumberFormatException | FileNotFoundException e1) {
                status.setText("Invalid Student ID");
            }
        });

        VBox layout = new VBox(home, header, txtName, id, go, status);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 140;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(layout, minWidth, minHeight);
    }


    /**
     * this scene displays the transcript of the specified student ID
     * @param id specified student's ID
     * @return returns a scene
     * @throws NumberFormatException
     * @throws FileNotFoundException
     */
    private Scene facultyViewTranscript(int id) throws NumberFormatException, FileNotFoundException {

        recsysObj = new RecSys(id);
        TextArea transcript = new TextArea(recsysObj.transcript());
        
        Label header = new Label("Student Transcript: " + id);
        Button home = new Button("Home");
        home.setOnAction(e -> stage.setScene(facultyHomeMenu()));

        transcript.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        ScrollPane scrollPane = new ScrollPane(transcript);
        scrollPane.setPrefSize(minWidth, minHeight);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        BorderPane layout = new BorderPane();
        layout.setBottom(home);
        layout.setTop(header);
        layout.setCenter(scrollPane); 

        transcript.setEditable(false);
        transcript.setFont(Font.font("Consolas", 12));
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(layout, minWidth, minHeight);
    }


    /**
     * allows faculty to edit students' grades
     * to specify which grade the user wants to update it takes:
     * student id, course code, semester number, module code, and then the result the user wants to update
     * @return returns a scene
     */
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
        Label l3 = new Label("Semester Number (first semester = 0)");
        Label l4 = new Label("Module Code");
        Label l5 = new Label("Student's Result");

        Button home = new Button("home");
        home.setOnAction(e -> stage.setScene(facultyHomeMenu()));
        Button go = new Button("Go");
        go.setOnAction(e -> {
            try {
                facultyObj = new Faculty();
                facultyObj.updateGrade(id.getText(), courseCode.getText(), Integer.parseInt(semNumber.getText()), modCode.getText(), Double.valueOf(result.getText()));
                status.setText("Student's Result Updated!");
            } catch (NumberFormatException e1) {
                status.setText("Update Unsuccessful. Please check that your details are correct.");
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

    private Scene editStudentComment(){
        Label header = new Label("Edit Student Comment");
        TextField id = new TextField("");
        TextField courseCode = new TextField("");
        TextField semNumber = new TextField("");
        TextField modCode = new TextField("");
        TextField comment = new TextField("");
        Label status = new Label("");

        Label l1 = new Label("Student ID");
        Label l2 = new Label("Course Code");
        Label l3 = new Label("Semester Number (first semester = 0)");
        Label l4 = new Label("Module Code");
        Label l5 = new Label("Student Comment (e.g., Repeat please!)");

        Button home = new Button("home");
        home.setOnAction(e -> stage.setScene(facultyHomeMenu()));
        Button go = new Button("Go");
        go.setOnAction(e -> {
            try {
                facultyObj = new Faculty();
                facultyObj.updateComment(id.getText(), courseCode.getText(), Integer.parseInt(semNumber.getText()), modCode.getText(), comment.getText());
                status.setText("Student's Comment Added!");
            } catch (NumberFormatException e1) {
                status.setText("Update Unsuccessful. Please check that your details are correct.");
            }
        });

        VBox layout = new VBox(home, header, l1, id, 
                            l2, courseCode, l3, semNumber, 
                            l4, modCode, l5, comment, 
                            go, status);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        return new Scene(layout, minWidth, minHeight);
    }



    // --------------------------------------------------------------------------------------------------
    //
    //                                      General
    //
    // --------------------------------------------------------------------------------------------------

    /**
     * resets variables that would be used for a specific user while they are logged in
     */
    private void cleanup(){
        loginID = "";
        loginPW = "";
    }

    /**
     * uses cleanup() to reset variables
     * returns user to the start screen
     */
    private void logout() {
        cleanup();
        stage.setScene(startup());
    }

    /**
     * launch javafx
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}