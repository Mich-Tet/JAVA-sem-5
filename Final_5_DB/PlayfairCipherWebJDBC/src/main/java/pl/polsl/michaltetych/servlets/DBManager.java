/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.michaltetych.servlets;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import pl.polsl.michaltetych.error.ErrorClass;

/**
 * Database manager class 
 * 
 * @author Michał Tetych
 * @version 3.0
 */
public class DBManager {
    /**
     * Database manager constructor
     * @throws SQLException if a servlet-specific error occurs
     */
    public DBManager() throws SQLException{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Playfair", "lab", "lab");
        //create table if not exists
        try
        {
            
            DatabaseMetaData dbmd = connection.getMetaData();

            ResultSet rs = dbmd.getTables(null, null, "HISTORYPLAYFAIR", new String[] {"TABLE"});

            if(!rs.next())
            {
                Statement statement = connection.createStatement();

                    statement.executeUpdate("CREATE TABLE HISTORYPLAYFAIR "
                                            + "(kkey VARCHAR(50), text VARCHAR(50), ooutput VARCHAR(50), ID INTEGER )");
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        connection.close();
    }
    /**
     * Method responsible for reading data from database
     * @return outString output for database
     * @throws ErrorClass own exception
     * @throws SQLException if a servlet-specific error occurs
     */
    public Vector<HistoryPlayfair> readStrings() throws ErrorClass, SQLException{
        
        Vector<HistoryPlayfair> outString = new Vector<>();
        
        Boolean error=false;
        
        
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Playfair", "lab", "lab");
        try
        {
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery("SELECT * FROM HISTORYPLAYFAIR")) {
                while (rs.next()) {
                    HistoryPlayfair hPlay = new HistoryPlayfair();
                    hPlay.setKey(rs.getString("kkey"));
                    hPlay.setText(rs.getString("text"));
                    hPlay.setOutput(rs.getString("ooutput"));
                    hPlay.setId(rs.getInt("ID"));
                    outString.add(hPlay);
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        connection.close();
        return outString;
    }
    /**
     * Method responsible for instering data to database
     * @param pojo object of HistoryPlayfair
     * @throws SQLException if a servlet-specific error occurs
     */
    public void setStrings(HistoryPlayfair pojo) throws SQLException{
        
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/Playfair", "lab", "lab");
        
        try
        {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO HISTORYPLAYFAIR VALUES (? ,? ,? ,?)");

            statement.setString(1, pojo.getKey());
            statement.setString(2,  pojo.getText());
            statement.setString(3, pojo.getOutput());
            statement.setInt(4, pojo.getId());
            
            statement.executeUpdate();

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        connection.close();
    }
    
}
