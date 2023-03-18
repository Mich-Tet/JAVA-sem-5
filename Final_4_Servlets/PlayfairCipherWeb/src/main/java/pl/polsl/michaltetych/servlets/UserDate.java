/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.michaltetych.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;


/**
 * @author Michał Tetych
 * @version 2.2
 * UserDate class of the program
 */
@WebServlet("/UserDate")
public class UserDate extends HttpServlet {

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
        Date date = new Date();
        out.println(date.toString());
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        Date date = new Date();
        out.println(date.toString());
    }
}