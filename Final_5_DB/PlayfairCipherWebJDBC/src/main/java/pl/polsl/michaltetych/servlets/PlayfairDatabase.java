/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.michaltetych.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import pl.polsl.michaltetych.error.ErrorClass;


/**
 * PlayfairDatabase class of the program - printing the database
 * 
 * @author Michał Tetych
 * @version 3.0
 */
@WebServlet("/Database")
public class PlayfairDatabase extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        
        
        
        DBManager dbm = null;
        try {
            dbm = new DBManager();
        } catch (SQLException ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Vector<HistoryPlayfair> vetorPojo = new Vector<>();
        try {
            vetorPojo = dbm.readStrings();
        } catch (SQLException ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorClass ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        // printing 
        for(int i = 0; i < vetorPojo.size();i++){

            out.println(vetorPojo.get(i).getId()+": " + "Key:" + vetorPojo.get(i).getKey() +" \tText: "+ vetorPojo.get(i).getText());
            out.println("Output: "+ vetorPojo.get(i).getOutput());
            out.println("");
        }
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        
        
        DBManager dbm = null;
        try {
            dbm = new DBManager();
        } catch (SQLException ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Vector<HistoryPlayfair> vetorPojo = new Vector<>();
        try {
            vetorPojo = dbm.readStrings();
        } catch (SQLException ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ErrorClass ex) {
            Logger.getLogger(PlayfairDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
         

        // printing 
  
        for(int i = 0; i < vetorPojo.size();i++){

            out.println(vetorPojo.get(i).getId()+": " + "Key:" + vetorPojo.get(i).getKey() +" \tText: "+ vetorPojo.get(i).getText());
            out.println("Output: "+ vetorPojo.get(i).getOutput());
            out.println("");
        }
    }
}