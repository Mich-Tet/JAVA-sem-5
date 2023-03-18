/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.michaltetych.servlets;


/**
 * HistoryPlayfair class is a POJO class with getters and setters
 * 
 * @author Michał Tetych
 * @version 3.0
 */
public class HistoryPlayfair {
    
    /**
     * The id in database
     */
    private int id = 0;

    /**
     * The key used for encryption and decryption
     */
    private String key;
    /**
     * The text used for encryption and decryption
     */
    private String text;
    /**
     * The output of encryption and decryption
     */
    private String output;
    /**
     * Constructor for HistoryPlayfair
     */
    public HistoryPlayfair(){   
    
    
    }
    /**
     * Setter for id
     * @param id id
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Getter for id
     * @return id returns id - getter
     */
    public int getId(){
        return this.id;
    }
    /**
     * Setter for key
     * @param sk key
     */
    
    public void setKey(String sk){
        this.key = sk;
    }
    /**
     * Getter for key
     * @return key returns key - getter
     */
    public String getKey() {
        return this.key;
    }
    /**
     * Setter for text
     * @param sd text
     */ 
    public void setText(String sd) {
        this.text = sd;
    }
    /**
     * Getter for text
     * @return text returns text - getter
     */
    public String getText() {
        return this.text;
    }
    /**
     * Setter for output
     * @param ot output
     */
    public void setOutput(String ot){
        this.output = ot;
    }
    /**
     * Getter for output
     * @return output returns output - getter
     */
    public String getOutput() {
        return this.output;
    }
    
}
