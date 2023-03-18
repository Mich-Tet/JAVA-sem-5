package pl.polsl.michaltetych.view;

import java.util.Scanner;
import pl.polsl.michaltetych.controller.ControllerClass;
// View class
/**
 * @author Michał Tetych
 * @version 2.0
 */
public class ViewClass implements Printable {
    
    /*
    Class ViewClass communicates with the user. Displays the menu and allows you to select functions from the menu.
    the class has an object of the class controller
    */
    ControllerClass controller = new ControllerClass();
    
    //here a simple function that displays a message on the screen, 
    //a function is done so that you don't have to type a long string every time: System.out.println
    private void show(String s) {
        System.out.println(s);       
    }
    // static method used for printing (lambda function) 
    static void printThing(Printable thing)
    {
        thing.print();
    }
    //function that gets an integer from the user
    private int keyboardInputInteger() {
        Scanner keyboardInput = new Scanner(System.in);
        int i = 0;
        boolean getInput = true;
        
        while(getInput){
            String s = keyboardInput.nextLine();
            try{
                i = Integer.parseInt(s);
                getInput = false;
            }
            catch(Exception e){
                   getInput = true;
                   show("Invalid input - only integers allowed.");
            }
        }
        return i;
    }
    
    //the function checks if the given string is all characters
    private boolean validateString(String s){
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){
                continue;
            }
            if ( !((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122)) ){
                return false;
            }
        }
        return true;
    }
    

    

    //function that takes an entire string line
    private String keyboardInputLineString() {
        Scanner keyboardInput = new Scanner(System.in);
        String s = "";
        boolean getInput = true;
        
        while(getInput) {
            String temp = keyboardInput.nextLine();
            
             if (validateString(temp)){
                  getInput = false;
                  s = temp;
             }
             else{
                   getInput = true;
                   show("Invalid input - only letters without numbers.");
            }
        }
        return s;
    }

    //function with the main menu of the program
    public void menuRun() {
        
       boolean run = true;
       ViewClass view = new ViewClass();
       while (run){
           menu();
           
           int choiceMenu = keyboardInputInteger();   
           String s;
           String[] arr;
           switch (choiceMenu){
               case 1:
                   //show("1. Encrypt text.");
                   //usage of lambda function for printing Strings
                    printThing(() -> {
                    System.out.println("1. Encrypt text.");       
                    });
                   //show("Enter text to encrypt:");
                    printThing(() -> {
                    System.out.println("Enter text to encrypt:");       
                    });
                   s = keyboardInputLineString();
                   arr = s.split(" ");   
                   //show("Encrypted text:");
                    printThing(() -> {
                    System.out.println("Encrypted text:");       
                    });
                   for ( String ss : arr) {
                    controller.setDecodedText(ss);
                    controller.encryption(); 
                    show(controller.getEncodedText());
                   }
                   break;
               
               case 2:
                   //show("2. Decrypt text.");
                   printThing(() -> {
                    System.out.println("2. Decrypt text.");       
                    });
                   //show("Enter text to decrypt:");
                   printThing(() -> {
                    System.out.println("Enter text to decrypt:");       
                    });
                   s = keyboardInputLineString();
                   arr = s.split(" ");   
                   //show("Decrypted text:");
                   printThing(() -> {
                    System.out.println("Decrypted text:");       
                    });
                   for ( String ss : arr) {
                    controller.setEncodedText(ss);
                    controller.decryption();
                    show(controller.getDecodedText());
                   }
                   break;
                   
               case 3: 
                   //show("3. Change key.");
                   printThing(() -> {
                    System.out.println("3. Change key.");       
                    });
                   //show("Input new key:");
                   printThing(() -> {
                    System.out.println("Input new key:");       
                    });
                   s = keyboardInputLineString();
                   controller.setKey(s);
                   break;
                   
               case 4: 
                   run = false;
                   //show("Program end");
                   printThing(() -> {
                    System.out.println("Program end");       
                    });
                   break;
                   
               default:
                   //show("Choose number: 1 - 4.");
                   printThing(() -> {
                    System.out.println("Choose number: 1 - 4.");       
                    });
           }
       }
    }
    
    //here is a simple function to display the text in the menu
    private void menu(){
        //show("1. Encrypt text.");
        printThing(() -> {
        System.out.println("1. Encrypt text.");       
        });
        //show("2. Decrypt text.");
        printThing(() -> {
        System.out.println("2. Decrypt text.");       
        });
        //show("3. Change key" + " (current key: " + controller.getKey() + ").");
        printThing(() -> {
        System.out.println("3. Change key" + " (current key: " + controller.getKey() + ").");       
        });
        //show("4. Program end.");
        printThing(() -> {
        System.out.println("4. Program end.");       
        });
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
