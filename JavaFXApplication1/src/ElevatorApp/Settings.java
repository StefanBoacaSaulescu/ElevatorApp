/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import ElevatorApp.Elevator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marius
 */
public class Settings {
    //Lista de lifturi
    static private List elevators = Collections.synchronizedList(new LinkedList());
    //Indexul ultimului element din lista de lifturi
    static private Integer elev_number = 0;
    static private Integer floors_number=0;
    Settings(){
        elevators.add(new Elevator());
    }
   
    static public Integer get_elev_number(){
        return elev_number;
    }
    static public void set_elev_number(Integer nr, Integer qid){
        elev_number=nr;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/elevator", "Morris", "dumniezo");
            String query="UPDATE situatii SET nr_lifturi = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, nr);
            stmt.setInt(2, qid);
            stmt.executeUpdate(); 
            stmt.close();
            con.close();
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getCause() + ex.getMessage());
        }
    }
    static public void set_floors_number(Integer nr, Integer qid){
        floors_number=nr;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/elevator", "Morris", "dumniezo");
            String query="UPDATE situatii SET nr_etaje = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, floors_number);
            stmt.setInt(2, qid);
            stmt.executeUpdate();
            stmt.close();
            con.close();
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }
    static public void add_elevator(Elevator e){
        elevators.add(e);
    }
    static public void remove_elevator(Integer i){
        elevators.remove(i);
    }
}
