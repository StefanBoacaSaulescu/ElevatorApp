/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author marius
 */
public class Meniu{
    static protected double swidth = Screen.getPrimary().getVisualBounds().getWidth();
    static protected double sheight = Screen.getPrimary().getVisualBounds().getHeight();
    static private StackPane parinte = new StackPane();
    
    Meniu(){
        //super(parinte, swidth, sheight);

        //Quit Button
        Button quit_btn = new Button("QUIT");
        //Event pe click
        quit_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //Setam clasa butonului pentru CSS
        quit_btn.getStyleClass().add("menu_button");
        //Start Button
        Button start_btn = new Button("START ELEVATORS SIMULATION");
        //Setam clasa butonului pentru CSS
        start_btn.getStyleClass().add("menu_button");
        //Configure Button
        Button conf_btn = new Button("CONFIGURE ELEVATORS");
        //Event pe click
        conf_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ElevatorApp.change_scene("2");
            }
        });
        //Setam clasa butonului pentru CSS
        conf_btn.getStyleClass().add("menu_button");
        //Center left menu (clm)
        GridPane clm = new GridPane(); // == tabel
        clm.add(start_btn, 0, 0); //coloana 0, randul 0
        clm.add(conf_btn, 0, 1); //coloana 0, randul 1
        clm.add(quit_btn, 0, 2); //coloana 0, randul 2
        clm.setAlignment(Pos.CENTER_LEFT); //il punem aliniat la stanga la mijlocul ecranului
        parinte.getChildren().add(clm);
        
        //CSS StyleSheet
        File f = new File("src/ElevatorApp/meniu_style.css");
        parinte.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

    }
    
    static StackPane get_parent(){
        return parinte;
    }
}
