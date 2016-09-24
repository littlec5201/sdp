/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdp.project;

import java.io.BufferedWriter;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Callum
 */
public class RegisterXML {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;
    Document document;
    private boolean accountAdded = false;

    RegisterXML(String username, String password) {
        try {
            File file = new File("accounts.xml");
            boolean fileExists = file.exists();
            if (!fileExists) {
                createXML();
            }
            db = dbf.newDocumentBuilder();
            document = db.parse(file);
            addNewAccount(username, password);
        } catch (Exception ex) {
        }
    }
    
    public boolean accountAdded() {
        return this.accountAdded;
    }

    public void addNewAccount(String username, String password) {
        accountAdded = false;
        if (containsUser(username)) {
            JOptionPane.showMessageDialog(new JFrame(), "User already exists!");
            return;
        }
        try {
            Element nList = document.getDocumentElement();

            //root a new account
            Element newAccount = document.createElement("account");

            //Username component
            Element newUsername = document.createElement("username");
            newUsername.appendChild(document.createTextNode(username));
            newAccount.appendChild(newUsername);
            
            //Password component
            Element newPassword = document.createElement("password");
            newPassword.appendChild(document.createTextNode(encryptPassword(password)));
            newAccount.appendChild(newPassword);

            nList.appendChild(newAccount);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //initialize StreamResult with File object to save to file
            StreamResult result = new StreamResult(new File("accounts.xml"));
            DOMSource source = new DOMSource(document);
            transformer.transform(source, result);
            JOptionPane.showMessageDialog(new JFrame(), "Successfully registered!");
            accountAdded = true;
        } catch (Exception e) {
        }
    }
    
    public String encryptPassword(String password) {
        char[] passChar = password.toCharArray();
        char[] encrpytChar = new char[passChar.length];
        String encrpytPassword = "";
        for (int i = 0; i < passChar.length; i++) {
            int charASCII = (int)passChar[i];
            encrpytChar[i] = (char)((charASCII * 2) + 5);
            encrpytPassword += encrpytChar[i];
        }
        return encrpytPassword;
    }
    
    public String decryptPassword(String password) {
        char[] passChar = password.toCharArray();
        char[] decryptChar = new char[passChar.length];
        String decryptPassword = "";
        for (int i = 0; i < passChar.length; i++) {
            int charASCII = (int)passChar[i];
            decryptChar[i] = (char)((charASCII - 5) / 2);
            decryptPassword += decryptChar[i];
        }
        return decryptPassword;
    }

    public boolean containsUser(String search) {
        ArrayList<String> userList = new ArrayList<String>();
        try {
            db = dbf.newDocumentBuilder();
            NodeList accountList = document.getElementsByTagName("account");
            for (int i =0; i < accountList.getLength(); i++) {
                Element account = (Element)accountList.item(i);
                NodeList usernameList = account.getElementsByTagName("username");
                String username = usernameList.item(0).getTextContent();
                System.out.println(username);
                
                NodeList passwordList = account.getElementsByTagName("password");
                String password = passwordList.item(0).getTextContent();
                System.out.println(decryptPassword(password));
                if (username.compareTo(search) == 0) {
                    return true;
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RegisterXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
