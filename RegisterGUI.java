/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdp.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.transform.Source;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RegisterGUI {

    private static JPasswordField passwordField;
    private static JPasswordField confirmPasswordField;
    private static JTextField usernameTextField;
    
    public static void focusListener(JTextField field) {
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                field.select(0, field.getText().length());
                field.setBackground(new Color(144, 238, 144));
            }
            @Override
            public void focusLost(FocusEvent e) {
                field.setBackground(new Color(255, 255, 255));
            }  
        });
    }

    public static void main(String[] args) {
        int setDim = 300;
        JFrame f = new JFrame("Register");
        f.setSize(setDim, setDim);
        f.setMinimumSize(new Dimension(setDim, setDim));
        f.setLocation(300, 200);
        f.pack();

        int xPos = f.getHeight() / 2 - 50;
        int yPos = f.getHeight() / 2 - 20;

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        f.pack();
        f.getContentPane().setLayout(null);

        // Username text field
        usernameTextField = new JTextField();
        usernameTextField.setBounds(xPos, yPos - 100, 100, 20);
        focusListener(usernameTextField);
        f.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(20);

        // Username label
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setBounds(xPos, yPos - 75, 100, 20);
        labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
        f.getContentPane().add(labelUsername);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(xPos, yPos - 25, 100, 20);
        focusListener(passwordField);
        f.getContentPane().add(passwordField);

        // Password label
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(xPos, yPos, 100, 20);
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        f.getContentPane().add(labelPassword);

        // Confirm password field
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(xPos, yPos + 50, 100, 20);
        focusListener(confirmPasswordField);
        f.getContentPane().add(confirmPasswordField);

        // Confirm password label
        JLabel labelConfirmPassword = new JLabel("Confirm Password");
        labelConfirmPassword.setBounds(xPos - 25, yPos + 75, 150, 20);
        labelConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
        f.getContentPane().add(labelConfirmPassword);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                String textUsername = usernameTextField.getText();
                String textPassword = new String(passwordField.getPassword());
                String textConfirmPassword = new String(confirmPasswordField.getPassword());
                RegisterLogic r = new RegisterLogic(textUsername, textPassword, textConfirmPassword);
//                if (r.accountAdded() == true) {
                    usernameTextField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
//                }
            }
        });
        registerButton.setBounds(xPos + 55, yPos + yPos - 30, 100, 20);
        f.getContentPane().add(registerButton);

        // Home button
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(xPos - 55, yPos + yPos - 30, 100, 20);
        f.getContentPane().add(homeButton);
        f.getLocation(
                new Point((screenWidth / 2) - (f.getWidth() / 2), (screenHeight / 2) - (f.getHeight() / 2)));
        f.setVisible(true);

    }
}
