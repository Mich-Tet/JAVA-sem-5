package pl.polsl.michaltetych.view;

import java.util.Scanner;
import pl.polsl.michaltetych.controller.ControllerClass;
import pl.polsl.michaltetych.error.ErrorClass;

/**
 * Class ViewClass communicates with the user. Displays the menu and allows you to select functions from the menu.
 * @author Michał Tetych
 * @version 3.0
 */
public class ViewClass {
    
    /**
    * the class has an object of the class controller
    */
    ControllerClass controller = new ControllerClass();
    /**
    * here a simple function that displays a message on the screen,
    * a function is done so that you don't have to type a long string every time: System.out.println
    * @param shown beeing a String to be outputed
    */
    public void show(String shown){
        System.out.println(shown);
    }
    /**
     * function that gets an integer from the user
     * @return i returns int for choice of action to be performed
     */
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
            catch(NumberFormatException e){
                   getInput = true;
                   show("Invalid input - only integers allowed.");
            }
        }
        return i;
    }
    /**
     * function that takes an entire string line
     * @return s returns string from input
     */
    private String keyboardInputLineString() {
        Scanner keyboardInput = new Scanner(System.in);
        String s = "";
        s = keyboardInput.nextLine();
        /*
        boolean getInput = true;
        
        while(getInput) {
            String temp = keyboardInput.nextLine();
            
             if (controller.validateString(temp)){
                  getInput = false;
                  s = temp;
             }
             else{
                   getInput = true;
                   show("Invalid input - only letters without numbers.");
            }
        }
        */
        return s;
    } 
    /**
     * function with the main menu of the program
     */
    public void menuRun() {
        
       boolean run = true;
       
       while (run){
           menu();
           
           int choiceMenu = keyboardInputInteger();   
           String s;
           String[] arr;
           switch (choiceMenu){
               case 1:
                   show("1. Encrypt text.");
                   show("Enter text to encrypt:");
                   s = keyboardInputLineString();

                   try{
                    controller.setDecodedText(s);
                    controller.encryption(); 
                    show(controller.getEncodedText());
                   }catch (ErrorClass e){
                       System.out.println(e.getMessage());
                   }
                   break;
               
               case 2:
                   show("2. Decrypt text.");
                   show("Enter text to decrypt:");
                   s = keyboardInputLineString();

                   try {
                    controller.setEncodedText(s);
                    controller.decryption();
                    show(controller.getDecodedText());
                   } catch(ErrorClass e){
                       System.out.println(e.getMessage());
                   }
                   break;
                   
               case 3: 
                   show("3. Change key.");
                   show("Input new key:");
                   s = keyboardInputLineString();
                   try{
                   controller.setKey(s);
                   }catch(ErrorClass e){
                       System.out.println(e.getMessage());
                   }
                   break;
                   
               case 4: 
                   run = false;
                   show("Program end");
                   break;
                   
               default:
                   show("Choose number: 1 - 4.");
           }
       }
    }
    
    /**
     * here is a simple function to display the text in the menu
     */
    private void menu(){
        show("1. Encrypt text.");
        show("2. Decrypt text.");
        show("3. Change key" + " (current key: " + controller.getKey() + ").");
        show("4. Program end.");
    }
}
