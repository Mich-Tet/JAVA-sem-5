/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.michaltetych.servlets;
import java.io.*;
import static java.lang.constant.ConstantDescs.NULL;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import pl.polsl.michaltetych.model.ModelClass;
/**
 * @author Michał Tetych
 * @version 2.2
 * Servlet class of the program
 */
@WebServlet("/Servlet")
public class ServletClass extends HttpServlet {
    ModelClass model = new ModelClass();
    String output;
    static String numOfVisits = "";
    static String numOfEn = "";
    static String numOfDe = "";
    
    int l = 1;
    int e = 1;
    int d = 1;
    int b,b2,b3;
    //vectors used for storing history of performed actions
    private final Vector<String> keyHistory;
    private final Vector<String> textHistory; 
    private final Vector<String> outHistory; 
    private final Vector<String> keyHistory2;
    private final Vector<String> textHistory2; 
    private final Vector<String> outHistory2; 

    public ServletClass() {
        this.keyHistory = new Vector<>();
        this.textHistory = new Vector<>();
        this.outHistory = new Vector<>();
        this.keyHistory2 = new Vector<>();
        this.textHistory2 = new Vector<>();
        this.outHistory2 = new Vector<>();
    }

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        //getting values from text boxes
        String key = request.getParameter("key");
        String text = request.getParameter("text");
        
        //button responsible for encrypting text in Playfair cipher
        if(request.getParameter("encrypt") != null) {
            if (key.length() == 0 || text.length() == 0) {
                response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
            } else { 
                try {
                   //storing output of the method in variable "output"
                   output = model.encrypt(key, text);
                   //adding values to history of performed actions for encryption
                   keyHistory.add(key);
                   textHistory.add(text);
                   outHistory.add(output);
                   //printing output                   
                   out.println("Used key: " + key + ". ");
                   out.println("Used text: " + text + ". ");                 
                   out.println("Encrypted output is: " + output + ". ");
                   //cookie for encryption
                    try{
                    Cookie count = new Cookie("ck",String.valueOf(e));
                    response.addCookie(count);
                    b2 = Integer.parseInt(count.getValue());
                    e++;
                    }catch(Exception ex) {
                            Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                } catch (Exception ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //button responsible for decrypting text in Playfair cipher
        if(request.getParameter("decrypt") != null) {
            if (key.length() == 0 || text.length() == 0) {
                response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
            } else { 
                try {
                   //storing output of the method in variable "output"                    
                   output = model.decrypt(key, text);
                   //adding values to history of performed actions for decryption
                   keyHistory2.add(key);
                   textHistory2.add(text);
                   outHistory2.add(output);
                   //printing output
                   out.println("Used key: " + key + ". ");
                   out.println("Used text: " + text + ". ");                 
                   out.println("Decrypted output is: " + output + ". ");
                   //cookie for decryption                   
                   try{
                   Cookie count = new Cookie("ck",String.valueOf(d));
                   response.addCookie(count);
                   b3 = Integer.parseInt(count.getValue());
                   d++;
                   }catch (Exception ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                } catch (Exception ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //button responsible for displaying date
        if(request.getParameter("date") != null) {
            response.setContentType("text/plain; charset=ISO-8859-2");

            out.println("Current date:");
            //use of include method 
            getServletContext().getRequestDispatcher("/UserDate").include(request, response);
        }
        //button responsible for displaying history of performed actions
        if(request.getParameter("history") != null) {
            out.println("HISTORY OF THE PROGRAM");
            out.println("");
            out.println("Encrypted: ");
            //cookie for history
            try{
            Cookie count = new Cookie("ck",String.valueOf(l));
            response.addCookie(count);
            b = Integer.parseInt(count.getValue());
            l++;
            }catch(Exception ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(int i =0;i<outHistory.size();i++){
                
                out.println((i+1)+": Used key: "+keyHistory.get(i) +" \tUsed text: "+ textHistory.get(i) +" \t\tObtained output: "+ outHistory.get(i)+"\n");
            }
            out.println("\n");
            out.println("Decrypted: ");
            for(int j =0;j<outHistory2.size();j++){
                out.println((j+1)+": Used key: "+keyHistory2.get(j) +" \tUsed text: "+ textHistory2.get(j) +" \t\tObtained output: "+ outHistory2.get(j)+"\n");
            }
        }
        //button responsible for displaying cookies
        if(request.getParameter("cookie") != null) {
            response.setContentType("text/html;charset=UTF-8");
            //encryption
            if(b2==0){
                numOfEn = "Did not use encoding yet.";
                out.println(numOfEn+"<br>");
            }
            else{ 
                numOfEn = "User used encoding "+b2+" times.";
                out.println(numOfEn+"<br>");
            }
            //decryption
            if(b3==0){
                numOfDe = "Did not use decoding yet.";
                out.println(numOfDe+"<br>");
            }
            else{ 
                numOfDe = "User used decoding "+b3+" times.";
                out.println(numOfDe+"<br>");
            }
            //history
            if(b==0){
                numOfVisits = "Did not entered history yet.";
                out.println(numOfVisits+"<br>");
            }
            else{ 
                numOfVisits = "User has visited history "+b+" times.";
                out.println(numOfVisits+"<br>");
            }
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        processRequest(request,response);
    } 
     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        processRequest(request,response);
    }
}
