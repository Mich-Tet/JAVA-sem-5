package pl.polsl.michaltetych.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Model class
/**
 * @author Michał Tetych
 * @version 2.0
 */

public class ModelClass {
    
    //all fields private
    //private char[][] table = new char[5][5];
    // two dimensional list of Characters
    private List<List<Character>> table = new ArrayList<List<Character>>();
    private String key;
    private String text;
    
    public String encrypt(String k, String txt) throws Exception{

        String returnText = "";
        //according to the playfair cipher algorithm that I found, we change the letters "j" to "i"
        txt = txt.replaceAll("j", "i");
        txt = txt.replaceAll("J", "I");
        //then, if the length of the text is odd, we add the letter "x" to the end
        if (txt.length() % 2 != 0){
            txt += "X";
        }
        k = k.replaceAll("j", "i");
        k = k.replaceAll("J", "I");
        k = k.replace(" ", "");
        key = keyWithremovedDuplicate(k).toUpperCase();
        text = txt.toUpperCase();
        table.clear();
        for (int i = 0; i < 5 ;i++) table.add(new ArrayList<Character>());
        
        //exceptions
        if (key == "") throw new Exception ("Key is null.");
        else if (text == "") throw new Exception ("Text is null.");
        
        //generating an encryption algorithm table
        generateTable();
        //printing generated table with key
        printTable();
        
        //algorithm operation
        returnText = encrypting();
        return returnText;
    }
    
    public String decrypt(String k, String txt) throws Exception{
        
        String returnText = "";
        //according to the playfair cipher algorithm that I found, we change the letters "j" to "i"
        txt = txt.replaceAll("j", "i");
        txt = txt.replaceAll("J", "I");
        //then, if the length of the text is odd, we add the letter "x" to the end
        if (txt.length() % 2 != 0){
            txt += "X";
        }
        k = k.replaceAll("j", "i");
        k = k.replaceAll("J", "I");
        k = k.replace(" ", "");
        key = keyWithremovedDuplicate(k).toUpperCase();
        text = txt.toUpperCase(); 
        table.clear();
        for (int i = 0; i < 5 ;i++) table.add(new ArrayList<Character>());
        
        if (key == "") throw new Exception ("Key is null.");
        else if (text == "") throw new Exception ("Text is null.");
        
        generateTable();
        //printing generated table with key
        printTable();
        
        returnText = decrypting();
        return returnText;
    }
       private String keyWithremovedDuplicate(String k)
    {
        int j, index = 0, len = k.length();
 
        char c[] = k.toCharArray();
 
        for (int i = 0; i < len; i++) {
 
            for (j = 0; j < i; j++) {
 
                if (c[i] == c[j])
                    break;
            }
            if (i == j)
                c[index++] = c[i];
        }
        return new String (Arrays.copyOf(c, index));
    }
    //function that generates the algorithm tables
    private void generateTable(){
        int indexOfSt = 0, indexOfKey = 0;
        
        //at the beginning, all letters are removed from the letters that are in the key
        String st = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        st = removeKeyChars(st, key); //in this method we delete letters that are already in the key
        
        //we generate arrays 5 x 5
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //first we enter all the letters from the key
                if (indexOfKey < key.length()){
                    //table[i][j] = key.charAt(indexOfKey++);
                    //table.get(i).get(j) = key.charAt(indexOfKey++);
                    //change needed to be done because of change from two dimensional table into two dimensional container
                    table.get(i).add(key.charAt(indexOfKey++));
                    //indexOfKey++;
                }   
                else{ //after entering the letters from the key, we enter the remaining letters 
                    //table.get(i).get(j) = st.charAt(indexOfSt++);
                    table.get(i).add(st.charAt(indexOfSt++));

                }
            }
        }
    }
    
    //to check if it works correctly, we can display tables here
    private void printTable(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) System.out.print(table.get(i).get(j) + " ");
            System.out.println();
        }
    }

    //here is deleting those letters of the alphabet that already show up from the key
    private String removeKeyChars(String s, String k) {
       
        char[] kChar = k.toCharArray();
        char[] sChar = s.toCharArray();
        
        for (int i = 0; i < k.length(); i++){
            for (int j = 0; j < s.length(); j++){
                if ((kChar[i]) == sChar[j]){
                    sChar[j] = ' ';
                }
            }
        }
        String ret = new String(sChar);
        ret = ret.replace(" ", "");
        return ret;
    }
    
    //the method looks up the position of the letter given as an argument in the array
    private int[] findPositionInTable(char ch){
        int[] point = new int[2];
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                if (table.get(i).get(j) == ch){
                    point[0] = i;
                    point[1] = j;
                }
            }         
        }  
        return point;   //function returns arrays int [column position, row position]
    }

    //encryption method
    private String encrypting() {
        String cryptedText = ""; // we store the ciphertext in this string
        
        //we check all letters in the string
        for (int i = 0; i < text.length(); i += 2){
            int [] xy1 = new int[2];
            int [] xy2 = new int[2];
            
            //we are looking for a location in an array of two consecutive letters
            xy1 = findPositionInTable(text.charAt(i));
            xy2 = findPositionInTable(text.charAt(i+1));
            
            //Letters localization cases
            if (xy1[0] == xy2[0]){
                //cryptedText += table[xy1[0]][(xy1[1]+1) % 5];
                //cryptedText += table[xy2[0]][(xy2[1]+1) % 5];
                cryptedText += table.get(xy1[0]).get((xy1[1]+1) % 5);
                cryptedText += table.get(xy2[0]).get((xy2[1]+1) % 5);
            }
            else if (xy1[1] == xy2[1]){
                //cryptedText += table[(xy1[0] + 1) % 5][xy1[1]];
                //cryptedText += table[(xy2[0]+ 1) % 5][xy2[1]];
                cryptedText += table.get((xy1[0] + 1) % 5).get(xy1[1]);
                cryptedText += table.get((xy2[0]+ 1) % 5).get(xy2[1]);
            }
            else{
                //cryptedText += table[xy2[0]][xy1[1]];
                //cryptedText += table[xy1[0]][xy2[1]];
                cryptedText += table.get(xy1[0]).get(xy2[1]);
                cryptedText += table.get(xy2[0]).get(xy1[1]);
            }
        }
        return cryptedText;
    }
    
    //the same as in the method above, but the other way around
    private String decrypting() {
        String cryptedText = "";
        
        for (int i = 0; i < text.length(); i += 2){
            int [] xy1 = new int[2];
            int [] xy2 = new int[2];
            
            xy1 = findPositionInTable(text.charAt(i));
            xy2 = findPositionInTable(text.charAt(i+1));
            
            if (xy1[0] == xy2[0]){
                if(xy1[1] - 1 < 0){
                    //cryptedText += table[xy1[0]][4];
                    cryptedText += table.get(xy1[0]).get(4);
                }
                else{
                    //cryptedText += table[xy1[0]][xy1[1]-1];
                    cryptedText += table.get(xy1[0]).get(xy1[1]-1);
                }
                
                if(xy2[1] - 1 < 0){
                    cryptedText += table.get(xy2[0]).get(4);
                }
                else{
                    cryptedText += table.get(xy2[0]).get(xy2[1]-1);
                }      
            }
            else if (xy1[1] == xy2[1]){
                if(xy1[0] - 1 < 0){
                    cryptedText += table.get(4).get(xy1[1]);
                }
                else{
                    cryptedText += table.get(xy1[0]-1).get(xy1[1]);
                }
                
                if(xy2[0] - 1 < 0){
                    cryptedText += table.get(4).get(xy2[1]);
                }
                else{
                    cryptedText += table.get(xy2[0]-1).get(xy2[1]);
                }  
            }
            else{
                
                //cryptedText += table[xy2[0]][xy1[1]];
                //cryptedText += table[xy1[0]][xy2[1]];
                //cryptedText += table[xy1[0]][xy2[1]];
                //cryptedText += table[xy2[0]][xy1[1]];
                cryptedText += table.get(xy1[0]).get(xy2[1]);
                cryptedText += table.get(xy2[0]).get(xy1[1]);
            }
        }
        return cryptedText;
    }
}