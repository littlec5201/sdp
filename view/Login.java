package com.development.software.finance.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.development.software.finance.MainApp;



/**
 *
 * @author scz5487
 */
public class Login {

	MainApp mainApp;

	/* ArrayList to hold a list of temporary users until DB is constructed. */
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db;
	Document document;

	private String username;
	private String password;

	/* Default constructor */
	Login(String username, String password) {
		this.username = username;
		this.password = password;
        try {
            File file = new File("accounts.xml");
            boolean fileExists = file.exists();
            if (!fileExists) {
                createXML();
            }
            db = dbf.newDocumentBuilder();
            document = db.parse(file);

        } catch (Exception e) {}
        logInAttempt(username, password);
//        mainApp = new MainApp();
//        WelcomeLayoutController.loginStage.hide();
	}

	/*
	 * Iterates throiugh the arraylist checking for any usernames that match the
	 * input username but with passwords that dont match the input password.
	 * Returns false If the username matches but the password doesn't.
	 */
	public boolean checkPassword(String searchUsername, String searchPassword) {
		NodeList accountList = document.getElementsByTagName("account");
		for (int i = 0; i < accountList.getLength(); i++) {
			Element account = (Element) accountList.item(i);
			NodeList usernameList = account.getElementsByTagName("username");
			String username = usernameList.item(0).getTextContent();
			if (username.compareTo(searchUsername) == 0) {
				NodeList passwordList = account.getElementsByTagName("password");
				String password = passwordList.item(0).getTextContent();
				if (searchPassword.compareTo(decryptPassword(password)) == 0) {
					JOptionPane.showMessageDialog(new JFrame(), "Login successful!");
					//////////////////////
					mainApp = new MainApp(username);
					// mainApp.setUserName(userName);
			        WelcomeLayoutController.loginStage.hide();
					return true;
				}
				JOptionPane.showMessageDialog(new JFrame(), "Incorrect password!");
				return false;
			}
		}
		JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist!");
		return false;
	}

	/* This method handles the user login */
	public boolean logInAttempt(String username, String password) {
		if (checkPassword(username, password)) {
			return true;
		}
		return false;
	}

	public String decryptPassword(String password) {
		char[] passChar = password.toCharArray();
		char[] decryptChar = new char[passChar.length];
		String decryptPassword = "";
		for (int i = 0; i < passChar.length; i++) {
			int charASCII = (int) passChar[i];
			decryptChar[i] = (char) ((charASCII - 5) / 2);
			decryptPassword += decryptChar[i];
		}
		return decryptPassword;
	}

	public void createXML() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("accounts.xml", false));
			bw.write("");
			bw.append("<accounts>");
			bw.append("</accounts>" + System.lineSeparator());
			bw.close();
		} catch (IOException ex) {
			Logger.getLogger(RegisterXML.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}