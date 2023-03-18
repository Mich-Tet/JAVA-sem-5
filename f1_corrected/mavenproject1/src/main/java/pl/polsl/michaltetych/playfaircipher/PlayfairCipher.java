package pl.polsl.michaltetych.playfaircipher;

import pl.polsl.michaltetych.controller.ControllerClass;
import pl.polsl.michaltetych.error.ErrorClass;
import pl.polsl.michaltetych.view.ViewClass;
/**
 * Program that performs both encoding and decoding of text and implements the Playfair cipher algorithm.
 * Program should work on a 5x5 matrix, user has to input the plaintext and the key word (or phrase). 
 * 
 * @author Michał Tetych
 * @version 3.0
 */
public class PlayfairCipher {

    /**
     * There is a main function in this class. This is where the program begins.
     * The program starts by creating an object of the ViewClass class and runs the run menu function of this class.
     * @param args command line arguments.
     */
    public static void main(String args[]) {
        /**
         * object of the class view
         */
        ViewClass view = new ViewClass();
    /**
     * Syntax for cmd:
     * java -jar mavenproject1-1.0-SNAPSHOT.jar - to open program same as run
     * java -jar mavenproject1-1.0-SNAPSHOT.jar encrypt JAVA JAVATPOINT - with args
     */
        if (args.length >= 2)
        {
            ControllerClass controller = new ControllerClass();
            try{
                controller.setKey(args[1]);
                
                String text = "";
                for (int i = 2; i < args.length; i++) text += args[i];
                
                if (args[0].equals("encrypt"))
                {
                    controller.setDecodedText(text);
                    controller.encryption();
                    view.show(controller.getEncodedText());
                }
                else if (args[0].equals("decrypt"))
                {
                    controller.setEncodedText(text);
                    controller.decryption();
                    view.show(controller.getDecodedText());
                }
                else
                {
                    view.show("Unknown operation: " + args[0]);
                }
            }catch(ErrorClass e){
                view.show(e.getMessage());
            }
        }
        else{
        view.menuRun();
        }
    }
    
}
