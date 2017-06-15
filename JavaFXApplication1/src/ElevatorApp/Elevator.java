/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElevatorApp;

/**
 *
 * @author marius
 */
public class Elevator {
    static private double speed;
    static private double up_time;
    static private double down_time;
    static private double interior_weight;
    static private double max_weight;
    static private double acceleration_time;
    static private double deceleration_time;
    
    Elevator(){
        //Initialize variables
        //TO DO (much math and physics)
    }

  
  
   
    public void set_speed(double q){
        this.speed = q;
    }
    public double get_speed(){
        return this.speed;
    }
    public void set_up_time(double q){
        this.up_time = q;
    }
    public double get_up_time(){
        return this.up_time;
    }
    public void set_down_time(double q){
        this.down_time = q;
    }
    public double get_down_time(){
        return this.down_time;
    }
    
    public void set_interior_weight(double q){
        this.interior_weight = q;
    }
    public double get_interior_weight(){
        return this.interior_weight;
    }
    public void set_max_weight(double q){
        this.max_weight = q;
    }
    public double get_max_weight(){
        return this.max_weight;
    }
    public void set_acceleration_time(double q){
        this.acceleration_time=q;
    }
    public double get_acceleartion_time() {
        return this.acceleration_time;
    }
    public void set_deceleration_time(double q) {
        this.deceleration_time = q;
    }
    public double get_deceleration_time() {
        return this.deceleration_time;
    }
     
}
