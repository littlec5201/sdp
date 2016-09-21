import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author scz5487
 */
public class LogIn implements ActionListener, KeyListener{
    /*ArrayList to hold a list of temporary users until DB is constructed.*/
    private ArrayList<String[]> users;
    private Scanner input = new Scanner(System.in);
    
    public JFrame frame;
    public JPanel mainPanel, usernamePanel, passwordPanel, buttonPanel; 
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JLabel usernameLabel, passwordLabel;
    public JButton loginButton, cancelButton;
    
    /*Default constructor*/
    LogIn(){
       this.users = new ArrayList<String[]>();
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       double screenWidth = screenSize.getWidth();
       double screenHeight = screenSize.getHeight();
       frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize((int)screenWidth/4, (int)screenHeight/4);
       
       mainPanel = new JPanel();
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
       
       
       usernamePanel = new JPanel(new GridLayout(2, 4, 5, 5));
       passwordPanel = new JPanel(new GridLayout(1, 4, 5, 5));
       buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
       
       mainPanel.add(usernamePanel);
       mainPanel.add(passwordPanel);
       mainPanel.add(buttonPanel);
       
       usernameLabel = new JLabel("Username: ");
       usernameField = new JTextField();
       usernamePanel.add(Box.createHorizontalStrut(1));
       usernamePanel.add(Box.createHorizontalStrut(1));
       usernamePanel.add(Box.createHorizontalStrut(1));
       usernamePanel.add(Box.createHorizontalStrut(1));
       usernamePanel.add(Box.createHorizontalStrut(1));
       usernamePanel.add(usernameLabel);
       usernamePanel.add(usernameField);
       usernamePanel.add(Box.createHorizontalStrut(1));
       
       passwordLabel = new JLabel("Password: ");
       passwordField = new JPasswordField();
       passwordPanel.add(Box.createHorizontalStrut(1));
       passwordPanel.add(passwordLabel);
       passwordPanel.add(passwordField);
       passwordPanel.add(Box.createHorizontalStrut(1));
       
       cancelButton = new JButton("Cancel");
       loginButton = new JButton("Login");
       buttonPanel.add(Box.createHorizontalStrut(1));
       buttonPanel.add(cancelButton);
       buttonPanel.add(loginButton);
       buttonPanel.add(Box.createHorizontalStrut(1));
       buttonPanel.add(Box.createHorizontalStrut(5));
       buttonPanel.add(Box.createHorizontalStrut(5));
       buttonPanel.add(Box.createHorizontalStrut(5));
       buttonPanel.add(Box.createHorizontalStrut(5));
       
       frame.add(mainPanel);
       frame.setVisible(true);
       
       
       
       loginButton.addActionListener(this);
       cancelButton.addActionListener(this);
       usernameField.addKeyListener(this);
       passwordField.addKeyListener(this);
       
       
//       frame.pack();
       
       Dimension frameSize = frame.getSize();
       double frameWidth = frameSize.getWidth();
       double frameHeight = frameSize.getHeight();
       frame.setLocation((int)(screenWidth/2-frameWidth/2), (int)(screenHeight/2-frameHeight/2));
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
        	String passwordText = new String(passwordField.getPassword());
            logInAttempt(usernameField.getText(), passwordText);
            passwordText = null;
        }
        if(e.getSource() == cancelButton){
        	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
    
    /*Adding temporary users to the ArrayList*/
    public boolean add(String username, String password){
        String[] userInfo = {username, password};
        return users.add(userInfo);
    }
    
    /*Iterates through the arraylist checking for any usernames that match the input username. Returns false if none match.*/
    public boolean checkUsername(String username){
        int incorrectUsername = 0;
        for(int i = 0; i < users.size(); i++){
            if(!users.get(i)[0].equals(username)){
                incorrectUsername++;
            }
        }
        if(incorrectUsername == users.size()){
        	JOptionPane.showMessageDialog(frame, "Incorrect Username!");
//            System.out.println("Incorrect Username!");
            return false;
        }
//        System.out.println("Correct Username!");
        return true;
    }
    
    /*Iterates throiugh the arraylist checking for any usernames that match the input username but with passwords that dont match 
    the input password. Returns false If the username matches but the password doesn't.*/
    public boolean checkPassword(String username, String password){
        int incorrectPassword = 0;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i)[0].equals(username) && !users.get(i)[1].equals(password)){
                incorrectPassword++;
            }
        }
        if(incorrectPassword == 1){
        	JOptionPane.showMessageDialog(frame, "Incorrect password!");
//            System.out.println("Incorrect password!");
            return false;
        }
//        System.out.println("Correct password!");
        return true;
    }
    
    /*Iterates through the arraylist checking for any username and password pairings that match the input username and password.
    Returns true if there is a match.*/
    public boolean checkUsernameAndPassword(String username, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i)[0].equals(username) && users.get(i)[1].equals(password)){
            	JOptionPane.showMessageDialog(frame, "Log in successful!");
            	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//                    System.out.println("Log in successful!");
                    return true;
            }
        }
//        JOptionPane.showMessageDialog(frame, "Log in unsuccessful!");
//        System.out.println("Log in unsuccessful!");
        return false;
    }
    
    /*This method handles the user login*/
    public boolean logInAttempt(String username, String password){
        /*Checks for a matching username, and if there is, performs one of the two options.*/
        if(checkUsername(username)){
            /*Checks for mathing username and password pairing. If there is one it returns true and the user is logged in.*/
            if(!checkUsernameAndPassword(username, password)){
                /*If there isn't a matching username and password pairing it returns false.*/
                if(!checkPassword(username, password)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
    	String user1 = "Ethan";
    	String pass1 = "1234";
    			
    	String user2 = "Dave";
    	String pass2 = "5678";
    			
        LogIn login = new LogIn();
        
        login.add(user1, pass1);
        login.add(user2, pass2);
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ENTER){
			String passwordText = new String(passwordField.getPassword());
            logInAttempt(usernameField.getText(), passwordText);
            passwordText = null;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

   
}

