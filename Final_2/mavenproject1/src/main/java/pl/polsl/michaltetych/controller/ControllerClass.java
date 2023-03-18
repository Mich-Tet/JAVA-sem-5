package pl.polsl.michaltetych.controller;
import pl.polsl.michaltetych.model.ModelClass;
import java.util.Arrays;
import java.util.Scanner;
//Controller class
/**
 * @author Michał Tetych
 * @version 2.0
 */

public class ControllerClass {
    
    //model object
    ModelClass model = new ModelClass();
    
    //all fields are private
    private String key;
    private String decodedText;
    private String encodedText;
    

    //getters and setters for private fields
    public void setKey(String s) {
        key = s;
    }

    public String getKey() {
        return key;
    }
    
    public void setDecodedText(String s) {
        decodedText = s;
    }

    public String getDecodedText() {
        return decodedText;
    }
    
    public void setEncodedText(String s) {
        encodedText = s;
    }

    public String getEncodedText() {
        return encodedText;
    }

    //method calling for encryption
    public void encryption() {
        /*
        if (key == null){
            System.out.println("Invlid key - null pointer exception.\nEnter valid key");
        }
        else{*/
        //try catch - handling an exception from the model class
        try{
            //In order for the algorithm to work, we remove the repeated characters from the key and put all letters in uppercase
            encodedText = model.encrypt(key, decodedText);
            //having already encrypted text, we compare which letters were lowercase and we change the same in the encrypted text
            encodedText = upLowLetters(decodedText, encodedText);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    /*
    same as method above but for decryption.
    everything is the same, only another method is called from the model class
    */
    public void decryption() {
        /*
        if (key == null){
            System.out.println("Invlid key - null pointer exception.\nEnter valid key");
        }
        else{
        */
        try{
            decodedText = model.decrypt(key, encodedText.toUpperCase());
            decodedText = upLowLetters(encodedText, decodedText);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
    //capital letters are converted to lower case, according to the original text
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
