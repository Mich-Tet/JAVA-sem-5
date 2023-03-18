/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.michaltetych.servlets;
import java.io.*;
import static java.lang.constant.ConstantDescs.NULL;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import pl.polsl.michaltetych.error.ErrorClass;
import pl.polsl.michaltetych.model.ModelClass;
/**
 * Servlet class of the program
 * 
 * @author Michał Tetych
 * @version 3.0
 */
@WebServlet("/Servlet")
public class ServletClass extends HttpServlet {
    /**
     * object of the class model
     */
    ModelClass model = new ModelClass();
    /**
     * storing output
     */
    String output;
    /**
     * storing id
     */
    int id = 0;
    /**
     * string for number of visits
     */
    static String numOfVisits = "";
    /**
     * string for number of encryptions
     */
    static String numOfEn = "";
    /**
     * string for number of decryptions
     */
    static String numOfDe = "";
    /**
     * Counter for cookies history
     */
    int cookieH = 1;
    /**
     * Counter for cookies encryption
     */    
    int cookieE = 1;
    /**
     * Counter for cookies decryption
     */    
    int cookieD = 1;
    /**
     * Inteeger storing number of checking history by the user
     */ 
    int hCheck;
    /**
     * Inteeger storing number of uses for encryption
     */ 
    int eCheck;
    /**
     * Inteeger storing number of uses for decryption
     */ 
    int dCheck;
    /**
     * Vector used for storing key history for encryption
     */
    private final Vector<String> keyHistory = new Vector<>();
    /**
     * Vector used for storing text history for encryption
     */
    private final Vector<String> textHistory = new Vector<>();
    /**
     * Vector used for storing output history for encryption
     */
    private final Vector<String> outHistory = new Vector<>(); 
    /**
     * Vector used for storing key history for decryption
     */
    private final Vector<String> keyHistory2 = new Vector<>();
    /**
     * Vector used for storing text history for decryption
     */
    private final Vector<String> textHistory2 = new Vector<>(); 
    /**
     * Vector used for storing output history for decryption
     */
    private final Vector<String> outHistory2 = new Vector<>(); 


    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ErrorClass own exeption
     * @throws SQLException if an SQL error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ErrorClass, SQLException {
        response.setContentType("text/plain; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        /**
         * getting values from text boxes
         */
        String key = request.getParameter("key");
        String text = request.getParameter("text");
        /**
         * button responsible for encrypting text in Playfair cipher
         */
        if(request.getParameter("encrypt") != null) {
            if (key.length() == 0 || text.length() == 0 || key.equals(NULL) || text.equals(NULL)) {
                response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
            } else { 
                try {
                   //storing output of the method in variable "output"
                   output = model.encrypt(key, text);
                   //adding values to history of performed actions for encryption in a sesion
                   keyHistory.add(key);
                   textHistory.add(text);
                   outHistory.add(output);
                   
                   Vector<HistoryPlayfair> vetorPojo = new Vector<>();
                   DBManager dbm = new DBManager();
                   HistoryPlayfair hPojo = new HistoryPlayfair();
                   vetorPojo = dbm.readStrings();

                   //check if is first element of the table
                   if(vetorPojo.size() == 0||vetorPojo.isEmpty())
                   {
                        id = 1;
                        
                   } else {
                       
                        id = vetorPojo.get(vetorPojo.size()-1).getId();
                        id++;
                        
                   }
                   //adding values to the database
                   hPojo.setKey(key);
                   hPojo.setText(text);
                   hPojo.setOutput(output);
                   hPojo.setId(id);

                   dbm.setStrings(hPojo);
                   
                   //printing output                   
                   out.println("Used key: " + key + ". ");
                   out.println("Used text: " + text + ". ");                 
                   out.println("Encrypted output is: " + output + ". ");
                   //cookie for encryption
                    try{
                    Cookie count = new Cookie("ck",String.valueOf(cookieE));
                    response.addCookie(count);
                    eCheck = Integer.parseInt(count.getValue());
                    cookieE++;
                    }catch(NumberFormatException ex) {
                            Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                } catch (ErrorClass ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /**
         * button responsible for decrypting text in Playfair cipher
         */
        if(request.getParameter("decrypt") != null) {
            if (key.length() == 0 || text.length() == 0 || key.equals(NULL) || text.equals(NULL)) {
                response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
            } else { 
                try {
                   //storing output of the method in variable "output"                    
                   output = model.decrypt(key, text);
                   //adding values to history of performed actions for decryption
                   keyHistory2.add(key);
                   textHistory2.add(text);
                   outHistory2.add(output);
                   
                   
                   Vector<HistoryPlayfair> vetorPojo = new Vector<>();
                   DBManager dbm = new DBManager();
                   HistoryPlayfair hPojo = new HistoryPlayfair();
                   vetorPojo = dbm.readStrings();

                   

                   //check if is first element of the table
                   if(vetorPojo.size() == 0||vetorPojo.isEmpty())
                   {
                        id = 1;
                   } else {
                       
                        id = vetorPojo.get(vetorPojo.size()-1).getId();
                        id++;
                   }
                   //adding values to the database
                   hPojo.setKey(key);
                   hPojo.setText(text);
                   hPojo.setOutput(output);
                   hPojo.setId(id);
                   

                   dbm.setStrings(hPojo);

                   
                   //printing output
                   out.println("Used key: " + key + ". ");
                   out.println("Used text: " + text + ". ");                 
                   out.println("Decrypted output is: " + output + ". ");
                   //cookie for decryption                   
                   try{
                   Cookie count = new Cookie("ck",String.valueOf(cookieD));
                   response.addCookie(count);
                   dCheck = Integer.parseInt(count.getValue());
                   cookieD++;
                   }catch (NumberFormatException ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                } catch (ErrorClass ex) {
                    Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        /**
         * button responsible for displaying database
         */
        if(request.getParameter("database") != null) {
            response.setContentType("text/plain; charset=ISO-8859-2");

            out.println("The Playfair Cipher database: ");
            out.println("");
            //use of include method 
            getServletContext().getRequestDispatcher("/Database").include(request, response);
        }
        /**
         * button responsible for displaying history of performed actions in current sesion
         */
        if(request.getParameter("history") != null) {
            out.println("HISTORY OF THE PROGRAM");
            out.println("");
            out.println("Encrypted: ");
            
            //cookie for history
            try{
            Cookie count = new Cookie("ck",String.valueOf(cookieH));
            response.addCookie(count);
            hCheck = Integer.parseInt(count.getValue());
            cookieH++;
            }catch(NumberFormatException ex) {
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
        /**
         * button responsible for displaying cookies
         */
        if(request.getParameter("cookie") != null) {
            response.setContentType("text/html;charset=UTF-8");
            //encryption
            if(eCheck==0){
                numOfEn = "Did not use encoding yet.";
                out.println(numOfEn+"<br>");
            }
            else{ 
                numOfEn = "User used encoding "+eCheck+" times.";
                out.println(numOfEn+"<br>");
            }
            //decryption
            if(dCheck==0){
                numOfDe = "Did not use decoding yet.";
                out.println(numOfDe+"<br>");
            }
            else{ 
                numOfDe = "User used decoding "+dCheck+" times.";
                out.println(numOfDe+"<br>");
            }
            //history
            if(hCheck==0){
                numOfVisits = "Did not entered history yet.";
                out.println(numOfVisits+"<br>");
            }
            else{ 
                numOfVisits = "User has visited history "+hCheck+" times.";
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
        try {
            processRequest(request,response);
        } catch (ErrorClass | SQLException ex) {
            Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request,response);
        } catch (ErrorClass | SQLException ex) {
            Logger.getLogger(ServletClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
