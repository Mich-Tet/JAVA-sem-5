/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * @author Michał Tetych
 * @version 2.2
 * GUI class of the program
 */
package pl.polsl.michaltetych.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.michaltetych.controller.ControllerClass;

public class GUIClass extends WindowAdapter implements ActionListener {
    ControllerClass controller = new ControllerClass();
    private JTextField textField, textField2, textField3, p2textField, p2textField2, p2textField3;
    private JButton button, button2;
    private JFrame frame;
    private JPanel mainPanel,panel, panel2;
    private JLabel label, label2, label3,p2label, p2label2, p2label3;
    private JTabbedPane tabbedPane;
    private JTextArea txtarea;
    private JMenuBar menuBar;
    private JMenu m1,m2;
    private JMenuItem mI1,mI2,history,info;
    List<String> keyHistory = new ArrayList<>();
    List<String> textHistory = new ArrayList<>();
    List<String> outputHistory = new ArrayList<>();
    public GUIClass() {
        
        initialize();
    }

    private void initialize(){
        //Creating a frame of the program
        frame = new JFrame();
        frame.setTitle("Playfair");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setSize(520   , 580);
        frame.setLocationRelativeTo(null);
        
        txtarea = new JTextArea(200,200);
        //Creating a menu
        menuBar = new JMenuBar();
        m1 = new JMenu("File");
        m2 = new JMenu("Help");
        
        menuBar.add(m1);
        menuBar.add(m2);
        mI1 = new JMenuItem("Hint");
        mI2 = new JMenuItem("Exit");
        info = new JMenuItem("Info");
        history = new JMenuItem("History");
        
        m1.add(mI1);    
        m1.add(history);
        m1.add(mI2);
        m2.add(info);
        
        mI1.addActionListener(this);
        mI2.addActionListener(this);
        info.addActionListener(this);
        history.addActionListener(this);
        //Labels 
        label = new JLabel("Enter key: ");
        label2 = new JLabel("Enter plaintext: ");
        label3 = new JLabel("Output: ");
        
        label.setBounds(30, 20, 100, 40);
        label2.setBounds(30, 70, 100, 40);
        label3.setBounds(30, 130, 100, 40);
        
        p2label = new JLabel("Enter key: ");
        p2label2 = new JLabel("Enter plaintext: ");
        p2label3 = new JLabel("Output: ");
        
        p2label.setBounds(30, 20, 100, 40);
        p2label2.setBounds(30, 70, 100, 40);
        p2label3.setBounds(30, 130, 100, 40);
        //Creating panels 
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10  , 10, 300, 300);
        panel.add(txtarea);
        
        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(10  , 10, 300, 300);
        panel2.add(txtarea);
        //Creating tabs 
        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(50,50 , 200   , 200);
        tabbedPane.add("Encrytping",panel);
        tabbedPane.add("Decrypting",panel2);
        
        mainPanel = new JPanel();
        mainPanel.add(label);
        
        textField = new JTextField(20);
        textField.setBounds(130, 20, 200, 40);
        textField2 = new JTextField(20);
        textField2.setBounds(130, 70, 200, 40);
        textField3 = new JTextField(20);
        textField3.setBounds(130, 130, 200, 40);
        
        p2textField = new JTextField(20);
        p2textField.setBounds(130, 20, 200, 40);
        p2textField2 = new JTextField(20);
        p2textField2.setBounds(130, 70, 200, 40);
        p2textField3 = new JTextField(20);
        p2textField3.setBounds(130, 130, 200, 40);
        //Creating buttons
        button = new JButton("Encrypt");
        button.setBounds(100, 400, 100 , 50);
        button2 = new JButton("Decrypt");
        button2.setBounds(100, 400, 100, 50);
        //Button responsible for encrypting 
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Encrypted text: ");
                String key = textField.getText();
                //saving history of keys 
                if(!textField.getText().isEmpty()){
                keyHistory.add(key);
                }
                if (validateString(key)){
                    controller.setKey(key);
                    String text = textField2.getText();
                    //saving history of texts
                    if(!textField2.getText().isEmpty()){
                    textHistory.add(text);
                    }
                    if (validateString(text)){
                        controller.setDecodedText(text);
                        controller.encryption(); 
                        textField3.setText(controller.getEncodedText());
                        //saving history of outputs
                        if(!textField3.getText().isEmpty()){
                        outputHistory.add(controller.getEncodedText());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The text must contain letters only\n-No numbers\n-No special characters");
                    textField2.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The key must contain letters only\n-No numbers\n-No special characters");
                    textField.setText("");
                }
            }
        });
        //Button responsible for decrypting 
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Decrypted text: ");
                String key = p2textField.getText();
                if(!p2textField.getText().isEmpty()){
                keyHistory.add(key);
                }
                if (validateString(key)){
                    controller.setKey(key);
                    String text = p2textField2.getText();
                    if(!p2textField2.getText().isEmpty()){
                    textHistory.add(text);
                    }
                    if (validateString(text)){
                        controller.setEncodedText(text);
                        controller.decryption(); 
                        p2textField3.setText(controller.getDecodedText());
                        if(!p2textField3.getText().isEmpty()){
                        outputHistory.add(controller.getDecodedText());
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "The text must contain letters only\n-No numbers\n-No special characters");
                    textField2.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The key must contain letters only\n-No numbers\n-No special characters");
                    textField.setText("");
                }
            }
        });
        //adding action listeners for buttons
        button.addActionListener(this);
        button2.addActionListener(this);
        //adding elements to panels
        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(textField);
        panel.add(textField2);
        panel.add(textField3);
        panel.add(button);
        
        panel2.add(p2label);
        panel2.add(p2label2);
        panel2.add(p2label3);
        panel2.add(p2textField);
        panel2.add(p2textField2);
        panel2.add(p2textField3);
        panel2.add(button2);
        
        mainPanel.add(tabbedPane);
        //adding elements to frame
        frame.add(BorderLayout.NORTH, menuBar);
        frame.add(tabbedPane, BorderLayout.CENTER);
        //frame.add(panel, BorderLayout.CENTER);
    }

    public void show() {
        this.frame.setVisible(true);
    }
    //Methods for menu bar management
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(textField.getText());
        //message box for "help" menu option
        if(e.getSource()==mI1){
            JOptionPane.showMessageDialog(mI1, "Example key: JAVA\nExample text: JAVATPOINT\nExample output for encryption: AVBVPQKCOS"
                    + "\nExample output for decryption: CIAISTKCOS");
                   }
        //"exit" menu option
        if(e.getSource()==mI2){
            System.exit(0);
        }
        //message box for "info" menu option
        if(e.getSource()==info){
            //System.out.println("This is a program for encoding and decoding in Playfair Cipher");
            JOptionPane.showMessageDialog(info, "This is a program for encoding and decoding in Playfair Cipher");
        }
        //message box for "history" menu option, showing all actions performed by user
        if(e.getSource()==history){
            List<String> a = new ArrayList<>();
            for(int i=0;i<outputHistory.size();i++)
            {
                a.add("\n"+(i+1)+":\n Key used: " + keyHistory.get(i));
                a.add(" Text used: "+ textHistory.get(i));
                a.add(" Output: " + outputHistory.get(i) );
            }
            JOptionPane.showMessageDialog(history, a);
        }
    }
    //Method responsible for showing confirmation message box whether the user surely want to exit 
    @Override
    public void windowClosing(WindowEvent e){
        int a = JOptionPane.showConfirmDialog(frame,"Do you want to exit?");
        if (a == JOptionPane.YES_OPTION){
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
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
}