package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

// Gridpane
// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html?external_link=true

public class Menu extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        primaryStage.setTitle("UL Portal");
        primaryStage.setScene(login());
        primaryStage.show();
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
        pwLabel.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10)); 
        loginButton.setOnAction(e -> stage.setScene(homeMenu()));
        idLabel.setTextFill(Color.web("#FF0000"));
        pwLabel.setTextFill(Color.web("#FF0000"));
        
        VBox p1 =   new VBox(
                    header,
                    new Label(""), // this is here to act as a blank space
                    idLabel,
                    idHere,
                    pwLabel, 
                    pwHerhe, 
                    loginButton);

        p1.setAlignment(Pos.CENTER_LEFT);
        p1.setSpacing(6);
        p1.setMinSize(600, 500);
        p1.setStyle("-fx-padding: 180;");

        return new Scene(p1, 720, 640);
    }


    private Scene homeMenu(){
        GridPane layout = new GridPane();

        // the idea for a sign out button is to allow the user to log out and sign in as someone else
        // this would probably need to restart the program?
        Button signOut = new Button();

        

        return new Scene(layout);
    }

    public static void main(String[] args) {
        launch();
    }
}

        // This will be the class to contain the main method
        // (Or not depending on if we require more classes).
        // It will print out all the data to produce a transcript.

        /*
         * testing the student transcript
         * 
         * Student me = new Student();
         * 
         * System.out.println(me.transcript());
         * 
         */

        /*
         * this is the first thing that pops up when the project is run
         * it creates a scanner and asks if you are logging in as a student or faculty
         * member
         * if a student is logging in then it asks for an id number.
         * If faculty is logging in it asks for first and last names and checks through
         * a csv file of faculty to grant access
         * if anyone has a better check for this feel free to change it cuz there is a
         * fault where a student can just enter
         * a lecturers name for access
         *
         *
         * NOTE FOR MYSELF: when an id number is put in the system should scan through a
         * csv file of students
         * and it pull the names and addresses from there. the userstudent class should
         * hold a method for this scan
         * UserStudent should grant the permissions to change personal details like
         * address etc.
         */
       