/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

import static ElevatorApp.ElevatorApp.stage;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author marius
 */
public class Config{
    static protected double swidth = Screen.getPrimary().getVisualBounds().getWidth();
    static protected double sheight = Screen.getPrimary().getVisualBounds().getHeight();
    static private StackPane parinte = new StackPane();
    Button configElevator_btn=new Button("Configureaza Lift");
    Button configMall_btn=new Button("Configureaza mall");
    Button back_btn = new Button("BACK");
    int liftIndex=1; 
    Config(){
       
        //Setam clasa butonului pentru CSS
        back_btn.getStyleClass().add("menu_button");
        configMall_btn.getStyleClass().add("menu_button");
        configElevator_btn.getStyleClass().add("menu_button");
        parinte.setStyle("-fx-background-color: #00FF00");
        parinte.getStyleClass().add("config_pane");
        
        GridPane gp = new GridPane();
        ScrollPane sc_pane = new ScrollPane();
        
        sc_pane.getStyleClass().add("config_pane");
        gp.add(back_btn,0,0);
        gp.add(configMall_btn,1,0);
        gp.add(configElevator_btn,2,0);
        configMall_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMallConfig(gp);
            }
        });
        configElevator_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showElevatorConfig(gp);
            }
        });
        back_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ElevatorApp.change_scene("1");
                gp.getChildren().clear();
                gp.add(back_btn,0,0);
                gp.add(configMall_btn,1,0);
                gp.add(configElevator_btn,2,0);
            }
        });
        
        sc_pane.setContent(gp);
        parinte.getChildren().add(sc_pane);
        
        //CSS StyleSheet
        File f = new File("src/ElevatorApp/meniu_style.css");
        parinte.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));   
    }
    public void showMallConfig(GridPane gp){
        Button save_btn=new Button("Salveaza datele introduse");
        GridPane config_mall = new GridPane();
        gp.getChildren().clear();
        gp.add(back_btn,0,0);
        gp.add(configMall_btn,1,0);
        gp.add(configElevator_btn,2,0);
        Label etaje=new Label("Nr. Etaje: ");
        Label lifturi=new Label("Nr. Lifturi: ");
        TextField nrEtaje=new TextField();
        TextField nrLifturi=new TextField();
        config_mall.add(etaje, 1, 1);
        config_mall.add(nrEtaje, 2, 1, 1, 1);
        config_mall.add(lifturi, 1, 2);
        config_mall.add(nrLifturi, 2, 2);
        Pane spring = new Pane();
        
        gp.add(spring, 0, 3);
        gp.add(config_mall, 0, 4);
        
        gp.add(new Label("Configureaza Mall"), 0, 2, 3, 1);
        gp.add(save_btn, 0, 5);
        save_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Settings.set_elev_number(Integer.parseInt(nrLifturi.getText()),1);
                Settings.set_floors_number(Integer.parseInt(nrEtaje.getText()),1);
                save_btn.setVisible(false);
            }
        });
        spring.minHeightProperty().bind(etaje.heightProperty());
    }
    public void showElevatorConfig(GridPane gp){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(stage);
        gp.getChildren().clear();
        gp.add(back_btn,0,0);
        gp.add(configMall_btn,1,0);
        gp.add(configElevator_btn,2,0);
        GridPane lowpane = new GridPane();

        int nr=Settings.get_elev_number();
        if(nr!=0){
            for( int i = 0 ; i<nr; i++){
                GridPane elevator = new GridPane();
                Label lift_nr = new Label("Liftul "+(i+1));
                lift_nr.getStyleClass().add("config_btn");
                elevator.add(lift_nr, 0, 1);
                Label max_wgh = new Label("Greutate maxima: ");
                TextField max_wgh_tf = new TextField();
                elevator.add(max_wgh, 0, 2);
                elevator.add(max_wgh_tf, 1, 2);
                Label time_l = new Label("Timp urcare/coborare: ");
                TextField time_tf = new TextField();
                elevator.add(time_l, 0,3);
                elevator.add(time_tf, 1, 3);
                Pane spring = new Pane();
                spring.minHeightProperty().bind(back_btn.heightProperty());
                elevator.add(spring, 0, 4);
                lowpane.add(elevator,i%3,6+i/3);
                
                ColumnDBTextFields.add_tf(max_wgh_tf, "max_weight", i+1);
                ColumnDBTextFields.add_tf(time_tf, "time", i+1);
            }
            gp.add(lowpane, 0, 5, 3, 1);
            Button save_cfg_elevs = new Button("Salveaza datele");
            save_cfg_elevs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ColumnDBTextFields.save_tf_in_db();
                }
            });
            gp.add(save_cfg_elevs,0,6);
        } else {
                alert.setHeaderText("Nu se stie numarul de lifturi ");
                alert.setContentText("Nu ai introdus numarul de etaje/lifturi");
                alert.showAndWait();
        }
        //Nu o sa mai avem nevoie de asta
//        for(int i=0;i<nr;i++){
//            for(int j=0;j<10;j++)
//            ((GridPane)(lowpane.getChildren().get(i))).getChildren().get(j);
//            //aici luam din gridpane
//        }
            
    }   
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
    for (Node node : gridPane.getChildren()) {
        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
            return node;
        }
    }
    return null;
}
   
    static StackPane get_parent(){
        return parinte;
    }
}
