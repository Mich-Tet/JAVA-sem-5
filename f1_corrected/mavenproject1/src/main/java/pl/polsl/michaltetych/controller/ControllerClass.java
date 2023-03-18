package pl.polsl.michaltetych.controller;
import pl.polsl.michaltetych.model.ModelClass;
import pl.polsl.michaltetych.error.ErrorClass;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Controller class for Playfair Cipher
 * 
 * @author Michał Tetych
 * @version 3.0
 */
public class ControllerClass {
    /**
     * object of the class model
     */
    ModelClass model = new ModelClass();
    /**
     * The key used for encryption and decryption
     */
    private String key;
    /**
     * String for decoded text
     */
    private String decodedText;
    /**
     * String for encoded text
     */
    private String encodedText;
    /**
     * Setter for key
     * @param sk key
     * @throws ErrorClass own exception class if fail
     */
    public void setKey(String sk) throws ErrorClass{
        if (validateString(sk) == false) throw new ErrorClass("Error: Invalid key, only characters allowed.");
        key = sk;
    }
    /**
     * Getter for key
     * @return key
     */
    public String getKey() {
        return key;
    }
    /**
     * Setter for decoded text
     * @param sd decoded text
     * @throws ErrorClass own exception class if fail
     */ 
    public void setDecodedText(String sd) throws ErrorClass {
        sd = sd.replace(" ","");
        if (sd.length() % 2 != 0){
        sd += "X";
        }
        if (validateString(sd) == false) throw new ErrorClass("Error: Invalid text, only characters allowed.");
        decodedText = sd;
    }
    /**
     * Getter for decoded text
     * @return decodedText returns decoded text - getter
     */
    public String getDecodedText() {
        return decodedText;
    }
    /**
     * Setter for encoded text
     * @param se encoded text
     * @throws ErrorClass own exception class if fail
     */
    public void setEncodedText(String se) throws ErrorClass {
        se = se.replace(" ","");
        if (validateString(se) == false) throw new ErrorClass("Error: Invalid text, only characters allowed.");
        encodedText = se;
    }
    /**
     * Getter for encoded text
     * @return encodedText returns encoded text - getter
     */
    public String getEncodedText() {
        return encodedText;
    }

    /**
     * the function checks if the given string is all characters
     * @param vs string to be checked
     * @return boolean 
     */
    public boolean validateString(String vs){
        for (int i = 0; i < vs.length(); i++){
            if (vs.charAt(i) == ' '){
                continue;
            }
            if ( !((vs.charAt(i) >= 65 && vs.charAt(i) <= 90) || (vs.charAt(i) >= 97 && vs.charAt(i) <= 122)) ){
                return false;
            }
        }
        return true;
    }
    
    /**
     * method calling for encryption
     * @throws ErrorClass own exception class if fail
     */ 
    public void encryption() throws ErrorClass{
        /*
        if (key == null){
            System.out.println("Invlid key - null pointer exception.\nEnter valid key");
        }
        else{*/
        if (key == null) throw new ErrorClass("Error in encryption: No key is given (null)");
        //try catch - handling an exception from the model class
        try{
            //In order for the algorithm to work, we remove the repeated characters from the key and put all letters in uppercase
            encodedText = model.encrypt(key, decodedText);
            //having already encrypted text, we compare which letters were lowercase and we change the same in the encrypted text
            encodedText = upLowLetters(decodedText, encodedText);
        }
        catch(ErrorClass exe){
            //System.out.println(e.toString());
            throw new ErrorClass(exe.getMessage());
        }
    }

    /**
    * method calling for decryption
    * @throws ErrorClass own exception class if fail
    */
    public void decryption() throws ErrorClass{
        /*
        if (key == null){
            System.out.println("Invlid key - null pointer exception.\nEnter valid key");
        }
        else{
        */
        if (key == null) throw new ErrorClass("Error in decrytpion: No key is given (null)");
        try{
            decodedText = model.decrypt(key, encodedText.toUpperCase());
            decodedText = upLowLetters(encodedText, decodedText);
        }
        catch(ErrorClass e){
            //System.out.println(e.toString());
            throw new ErrorClass(e.getMessage());
        }
    }
    /**
     * Method used for converting capital letters to lower case, according to the original text
     * @param a checked text 
     * @param b changed text 
     * return ret converted text 
     */
    private String upLowLetters(String a, String b) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < a.length();i++){
            if(Character.isLowerCase(a.charAt(i))){
                ret.append(Character.toLowerCase(b.charAt(i)));
            }
            else ret.append(b.charAt(i));
        }
        return ret.toString();
    } 
}
