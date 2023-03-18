/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.michaltetych.test;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.michaltetych.model.ModelClass;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.michaltetych.controller.ControllerClass;
//Test Class
/**
 * @author Michał Tetych
 * @version 2.0
 */
public class TestClass {
    private ModelClass modelClass = new ModelClass();
    //testing whether encryption works fine
    @Test
    public void encryptTest1()
    {
        try{
            
        modelClass.encrypt("GENERE", "IAZZ");
        assertEquals(modelClass.encrypt("GENERE", "IAZZ"), "OGVV");
        }  catch (Exception e){
         fail("something went wrong");          
        }           
    }
    // testing whether encryption with multiword key works fine
    @Test
     public void encryptTest2()
    {
        try{
            
        modelClass.encrypt("PLAYFAIR CIPHER", "TEST");
        assertEquals(modelClass.encrypt("PLAYFAIR CIPHER", "TEST"), "ZMTN");
        }  catch (Exception e){
         fail("something went wrong");          
        }           
    }
    //testing whether decryption works fine
        @Test
    public void decryptTest1()
    {
        try{
        modelClass.decrypt("GENERE", "IAZZ");
        assertEquals(modelClass.decrypt("GENERE", "OGVV"), "IAZZ");
        }  catch (Exception e){
         fail("something went wrong");          
        }           
    }
    // testing whether decryption with multiword key works fine
    @Test
     public void decryptTest2()
    {
        try{
        modelClass.decrypt("PLAYFAIR CIPHER", "ZMTN");
        assertEquals(modelClass.decrypt("PLAYFAIR CIPHER", "ZMTN"), "TEST");
        }  catch (Exception e){
         fail("something went wrong");          
        }           
    }
     /**
      * Parameterized test responsible for testing encryption
      * @param key user input to encrypt
      * @param txt user key to encrypt
      * @param output expected encrypted outcome
      */
     @ParameterizedTest
     @CsvSource({"JAVA,JAVATPOINT,AVBVPQKCOS", "GENERE,JAZZ,OVGG"})       
     public void test(String key, String txt, String output) throws Exception
     {
         try{
             modelClass.encrypt(key, txt);       
     }  catch (Exception e){
            fail("something went wrong");    
        }  
        String possibleOutput = modelClass.encrypt(key, txt);

        assertEquals(modelClass.encrypt(key, txt), possibleOutput, "Arrays are the same");
    }
}
