package Worlds_Collide.__Utils;

import java.sql.*;

/// class that save all the necessary player stats in local data base
public class DataBase {

    protected Connection c;
    protected Statement stmt;


    /// connects to data base
    public DataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:GameData.db");
            stmt=c.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /// get specific data form table
    public int getData(String data,String table){
        int q=0;
        try{
            ResultSet rs=stmt.executeQuery("SELECT "+data+" FROM "+table);
            q= rs.getInt(data);
            rs.close();
        }catch (Exception e){
            e.printStackTrace();
        }
       return q;
    }

    /// updates settings
    public void updateSettings(int quality,int difficulty){
        try{
            stmt.executeUpdate("UPDATE SETTINGS SET QUALITY = "+quality);
            stmt.executeUpdate("UPDATE SETTINGS SET DIFFICULTY = "+difficulty);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///updated current map
    public void updateMap(int map){
        try{
            stmt.executeUpdate("UPDATE PLAYER SET MAP = "+map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///updates player's life
    public void updateLife(int life){
        try{
            stmt.executeUpdate("UPDATE PLAYER SET LIFE = "+life);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///updates number of deaths
    public void updateDeaths(int deaths){
        try{
            stmt.executeUpdate("UPDATE PLAYER SET DEATHS = "+deaths);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///updates number of player's heal potions
    public void updatePotions(int potions){
        try{
            stmt.executeUpdate("UPDATE PLAYER SET BOTTLES = "+potions);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///update player start map position
    public void updatePosition(int x,int y){
        try{
            stmt.executeUpdate("UPDATE PLAYER SET X = "+x);
            stmt.executeUpdate("UPDATE PLAYER SET Y = "+y);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    ///close the statement and the connection
    public void close() {
        try{
            stmt.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
