package pl.polsl.michaltetych.playfaircipher;

import pl.polsl.michaltetych.view.ViewClass;
/**
 * Program that performs both encoding and decoding of text and implements the Playfair cipher algorithm.
 * Program should work on a 5x5 matrix, user has to input the plaintext and the key word (or phrase). 
 * @author Michał Tetych
 * @version 2.0
 */
public class PlayfairCipher {

    /**
     * There is a main function in this class. This is where the program begins.
     * The program starts by creating an object of the ViewClass class and runs the run menu function of this class.
     */
    public static void main(String[] args) {
        ViewClass view = new ViewClass();
        view.menuRun();
    }
    
}
