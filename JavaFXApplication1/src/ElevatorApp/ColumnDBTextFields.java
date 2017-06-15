/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 *
 * @author FriendlyZeppelin
 */
public class ColumnDBTextFields {
    protected static ArrayList<TextField> tf = new ArrayList<TextField>();
    protected static ArrayList<String> columns = new ArrayList<String>();
    protected static ArrayList<Integer> elev_ids = new ArrayList<Integer>();
    
    public static void add_tf(TextField e, String column, Integer index){
        tf.add(e);
        columns.add(column);
        elev_ids.add(index);
    }
    
    public static void save_tf_in_db(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/elevator", "Morris", "dumniezo");
            String query="TRUNCATE TABLE elevators;";
            Statement stmt = con.createStatement();
            stmt.execute(query);
            stmt.close();
            for(int i = 0; i < tf.size(); i++){
                TextField e = tf.get(i);
                String column = columns.get(i);
                Integer id = elev_ids.get(i);
                
                PreparedStatement pstmt1 = con.prepareStatement("SELECT id FROM elevators WHERE id = ?");
                pstmt1.setInt(1, id);
                pstmt1.execute();
                ResultSet rs = pstmt1.getResultSet();
                if(rs.next()){
                    if("max_weight".equals(column)){
                        PreparedStatement pstmt = con.prepareStatement("UPDATE elevators SET max_weight = ? WHERE id = ?");
                        pstmt.setInt(1, Integer.parseInt(e.getText()));
                        pstmt.setInt(2, id);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    if("time".equals(column)){
                        PreparedStatement pstmt = con.prepareStatement("UPDATE elevators SET time = ? WHERE id = ?");
                        pstmt.setDouble(1, Double.parseDouble(e.getText()));
                        pstmt.setInt(2, id);
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                }else{
                    if("max_weight".equals(column)){
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO elevators (id, situatie_id, max_weight) VALUES (?, 1, ?)");
                        pstmt.setInt(1, id);
                        pstmt.setInt(2, Integer.parseInt(e.getText()));
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                    if("time".equals(column)){
                        PreparedStatement pstmt = con.prepareStatement("INSERT INTO elevators (id, situatie_id, time) VALUES (?, 1, ?)");
                        pstmt.setInt(1, id);
                        pstmt.setDouble(2, Double.parseDouble(e.getText()));
                        pstmt.executeUpdate();
                        pstmt.close();
                    }
                }
            }
            con.close();
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
        tf.clear();
        columns.clear();
        elev_ids.clear();
    }
}
