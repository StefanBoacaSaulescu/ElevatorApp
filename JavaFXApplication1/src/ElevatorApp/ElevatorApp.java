/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.input.KeyCombination;
/**
 *
 * @author marius
 */
public class ElevatorApp extends Application {
    static protected Stage stage;
    static protected Meniu menu = new Meniu();
    static protected Config cfg = new Config();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Elevator"); //Title
        StackPane first_stack = new StackPane();
        //CSS StyleSheet
        File f = new File("src/ElevatorApp/first_style.css");
        first_stack.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        //First button
        Button all_press = new Button("Click anywhere to continue");
        //Setam clasa butonului pentru CSS
        all_press.getStyleClass().add("enter_button");
        //Facem butonul la fel de mare cat ecranul
        all_press.setPrefWidth(Screen.getPrimary().getVisualBounds().getWidth());
        all_press.setPrefHeight(Screen.getPrimary().getBounds().getHeight());
        //Asezam butonul jos pe mijlocul ecranului
        all_press.setAlignment(Pos.BOTTOM_CENTER);
        
        first_stack.getChildren().add(all_press);
        //Click event pe buton
        all_press.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change_scene("1");
            }
        });
        primaryStage.setScene(new Scene(first_stack, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight())); //First scene
        //primaryStage.setFullScreen(true); //Fullscreen
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); //Disable "Press ESC to exit fullscreen mode" message
        primaryStage.show(); //Start the stage
        stage = primaryStage; //assign stage to a static variable for later control
    }
    
    public static void change_scene(String r){
        switch(r){
            case "1": 
                stage.getScene().setRoot(Meniu.get_parent());
                break;
            case "2":
                stage.getScene().setRoot(Config.get_parent());
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
