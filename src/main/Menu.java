package main;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.geometry.Pos;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

// Gridpane
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html?external_link=true

public class Menu extends Application {

    private Stage stage;
    private int minWidth = 720;
    private int minHeight = 480;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        primaryStage.setTitle("UL Portal");
        primaryStage.setResizable(false);
        primaryStage.setScene(startup());
        primaryStage.show();
    }

    private Scene startup(){
        Label header = new Label("University Portal");
        Label subtitle = new Label("Choose Login type.");
        Button faculty = new Button("Faculty");
        Button student = new Button("Student");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 
        subtitle.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        subtitle.setTextFill(Color.web("#FF0000"));
        faculty.setMinWidth(200);
        student.setMinWidth(200);

        faculty.setOnAction(e -> stage.setScene(login()));
        student.setOnAction(e -> stage.setScene(login()));

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

    // want to make a box of colour behind this
    private Scene login(){
        Label header = new Label("Portal Login");
        Button loginButton = new Button("Login");
        Label idLabel = new Label("ID/USERNAME:");
        Label pwLabel = new Label("PIN/PASSWORD:");
        TextField idHere = new TextField("");
        PasswordField pwHerhe = new PasswordField();

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 
        idLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        pwLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        idLabel.setTextFill(Color.web("#FF0000"));
        pwLabel.setTextFill(Color.web("#FF0000"));
        loginButton.setOnAction(e -> stage.setScene(homeMenu()));
        
        VBox layout =   new VBox(
                    header,
                    new Label(""), // this is here to act as a blank space
                    idLabel,
                    idHere,
                    pwLabel, 
                    pwHerhe, 
                    loginButton);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(600, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene homeMenu(){
        GridPane layout = new GridPane();

        Label header = new Label("Home");

        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 

        layout.add(header, 0, 1); // col, row   
        layout.add(myResults(), 0, 2);
        layout.add(myModules(), 0, 3);
        layout.add(options(), 1, 3);

        layout.setStyle("-fx-padding: 20;");
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth/2) - 50)); 
        layout.getColumnConstraints().add(new ColumnConstraints((minWidth/2) - 50)); 
        layout.setVgap(20);
        layout.setHgap(20);

        return new Scene(layout, minWidth, minHeight);
    }

    private VBox myResults(){
        Label header = new Label("My Results");
        Button currentResults = new Button("Current Results");
        Button transcript = new Button("Student Transcript");

        VBox layout =   new VBox(header,
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
        transcript.setOnAction(e -> stage.setScene(studentTranscript()));

        return layout;
    }

    private VBox myModules(){
        Label header = new Label("Current Modules");
        TableView<Module> table = new TableView<Module>();

        /* 
        should also include the below two rows but since im using the 'module' class which doesnt include these I deleted them
        TableColumn<Module, String> yearCol = new TableColumn<Module, String>("Year");
        TableColumn<Module, String> semCol = new TableColumn<Module, String>("Period");
        */
        TableColumn<StudentGrades, String> modCol = new TableColumn<StudentGrades, String>("Module");
        TableColumn<StudentGrades, String> credCol = new TableColumn<StudentGrades, String>("Credits");
        TableColumn<StudentGrades, String> nameCol = new TableColumn<StudentGrades, String>("Name");

        // this works but there is empty space in the table
        // and when I include cols for year and period it's too many cols
       /// table.getColumns().addAll(modCol, credCol, nameCol);

        // why does this not display in the table
       // table.getItems().add(new StudentGrades());

        VBox layout = new VBox(header, table);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 

        return layout;
    }

    private VBox options(){
        Label header = new Label("Options");
        Button pwChange = new Button("Change Password");
        Button details = new Button("View/Edit Personal Details");
        Button emailChange = new Button("Update Email Address");
        Button logout = new Button("Logout");

        VBox layout =   new VBox(header,
                        pwChange,
                        details,
                        emailChange,
                        logout);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(100, 100);
        layout.setStyle("-fx-padding: 10;");
        header.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        pwChange.setMinWidth(200);
        details.setMinWidth(200);
        emailChange.setMinWidth(200);
        logout.setMinWidth(200);

        pwChange.setOnAction(e -> stage.setScene(changePassword()));
        details.setOnAction(e -> stage.setScene(personalDetails()));
        emailChange.setOnAction(e -> stage.setScene(updateEmail()));
        // how would I get this to call a method upon being clicked?
        // logout.setOnAction(logout());

        return layout;
    }

    private Scene currentResults(){


        return new Scene(null);
    }

    private Scene studentTranscript(){


        return new Scene(null);
    }
    
    private Scene changePassword(){
        Button home = new Button("Home");
        home.setOnAction(e -> stage.setScene(homeMenu()));
        Button go = new Button("Go");
        // this should be changed to actually change the password
        go.setOnAction(e -> stage.setScene(homeMenu()));

        Label header = new Label("Change Password");
        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 

        Label old = new Label("Old Password");
        Label newPlabel = new Label("New Password");
        Label confirm = new Label("Confirm New Password");
        old.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        newPlabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        confirm.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        old.setTextFill(Color.web("#FF0000"));
        newPlabel.setTextFill(Color.web("#FF0000"));
        confirm.setTextFill(Color.web("#FF0000"));

        PasswordField oldpw = new PasswordField();
        PasswordField newpw = new PasswordField();
        PasswordField confirmpw = new PasswordField();

        VBox layout = new VBox(
                                home,
                                new Label(""), // this is here to act as a blank space
                                header,
                                old, 
                                oldpw, 
                                newPlabel,
                                newpw,
                                confirm,
                                confirmpw,
                                go);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(600, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }

    private Scene personalDetails(){
        Label header = new Label("Personal Details");
        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 

        // this is going to need so many labels and textfields
        Label title = new Label();

        return new Scene(null);
    }

    private Scene updateEmail(){
Button home = new Button("Home");
        home.setOnAction(e -> stage.setScene(homeMenu()));
        Button go = new Button("Go");
        // this should be changed to actually change the email
        go.setOnAction(e -> stage.setScene(homeMenu()));

        Label header = new Label("Change Email");
        header.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 20)); 

        Label old = new Label("Old Email");
        Label newPlabel = new Label("New Email");
        Label confirm = new Label("Confirm New Email");
        
        old.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        newPlabel.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        confirm.setFont(Font.font("Verdana", FontWeight.BOLD, 10)); 
        old.setTextFill(Color.web("#FF0000"));
        newPlabel.setTextFill(Color.web("#FF0000"));
        confirm.setTextFill(Color.web("#FF0000"));

        TextField oldpw = new TextField();
        TextField newpw = new TextField();
        TextField confirmpw = new TextField();

        VBox layout = new VBox(
                                home,
                                new Label(""), // this is here to act as a blank space
                                header,
                                old, 
                                oldpw, 
                                newPlabel,
                                newpw,
                                confirm,
                                confirmpw,
                                go);

        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setSpacing(6);
        layout.setMinSize(600, 500);
        layout.setStyle("-fx-padding: 180;");

        return new Scene(layout, minWidth, minHeight);
    }
    
    // the idea for a sign out button is to allow the user to log out and sign in as someone else
    // this would probably need to restart the program?
    // tried doing that here but I could not figure out how to direct the 'login' action to calling this method 
    private void logout(){
        startup();
    }

    

    public static void main(String[] args) {
        launch();
    }
}