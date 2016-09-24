/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdp.project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Callum
 *
 */
public class RegisterLogic {

    private String userName = "";
    private String password = "";
    private String errorMessage = "";
    private boolean validUsername = true;
    private boolean validPassword = true;
    private boolean accountAdded = false;

    public RegisterLogic(String username, String password, String confirmPassword) {
        setUsername(username);
        setPassword(password);

        if (validUsername == true && validPassword == true && password.compareTo(confirmPassword) == 0) {
            RegisterXML r = new RegisterXML(username, password);
            accountAdded = r.accountAdded();
        } else {
            if (password.compareTo(confirmPassword) != 0) {
                errorMessage += "\nPasswords do not match";
            }
            JOptionPane.showMessageDialog(new JFrame(), errorMessage);
        }
    }
    
    public boolean accountAdded() {
        return this.accountAdded;
    }

    public boolean setUsername(String username) {
        this.userName = username;
        usernameLength();
        return false;
    }

    public boolean setPassword(String password) {
        this.password = password;
        passwordLength();
        passwordContainsLowerCase();
        passwordContainsUpperCase();
        passwordContainsNumbers();
        return false;
    }

    public boolean usernameLength() {
        if (userName.length() < 6) {
            errorMessage += "\nUsername doesn't meet minimum length of 6";
            validUsername = false;
            return false;
        } else {
            return true;
        }
    }

    public boolean passwordLength() {
        if (password.length() < 8) {
            validPassword = false;
            errorMessage += "\nPassword doesn't meet minimum length of 8";
            return false;
        } else {
            return true;
        }
    }

    public boolean passwordContainsNumbers() {
        int numbers = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                numbers++;
            }
        }
        if (numbers > 1) {
            return true;
        } else {
            validPassword = false;
            errorMessage += "\nPassword doesn't contain any numbers";
            return false;
        }
    }

    public boolean passwordContainsUpperCase() {
        int uppercase = 0;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercase++;
            }
        }
        if (uppercase > 0) {
            return true;
        } else {
            validPassword = false;
            errorMessage += "\nPassword doesn't contains any uppercase characters";
            return false;
        }
    }

    public boolean passwordContainsLowerCase() {
        int lowercase = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowercase++;
            }
        }
        if (lowercase > 4) {
            return true;
        } else {
            validPassword = false;
            errorMessage += "\nPassword contains less than 4 lowercase characters";
            return false;
        }
    }
}
