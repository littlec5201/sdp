import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroFrame implements ActionListener{
	public JFrame frame;
	public JPanel labelPanel, buttonPanel, picPanel;
	public JButton loginButton, registerButton, cancelButton;
	public JLabel welcomeLabel;
	
	IntroFrame(){
		/*Initialising frame, setting layout and close action*/
		frame = new JFrame("CERG Financial Calculator");
		frame.setLayout(new GridLayout(3, 1, 1, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*Setting size of the frame*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    double screenWidth = screenSize.getWidth();
	    double screenHeight = screenSize.getHeight();
	    frame.setSize((int)screenWidth/3, (int)screenHeight/3);
	    
	    /*Setting the orientation of the frame*/
	    Dimension frameSize = frame.getSize();
	    double frameWidth = frameSize.getWidth();
	    double frameHeight = frameSize.getHeight();
	    frame.setLocation((int)(screenWidth/2-frameWidth/2), (int)(screenHeight/2-frameHeight/2)); 
	    
	    /*Setting panel content and font size*/
	    welcomeLabel = new JLabel("Welcome to the CERG Financial Calculator!");
	    welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 22));
	    
	    /*Setting button contents and adding ActionListeners*/
	    cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(this);
	    
	    registerButton = new JButton("Register");
	    registerButton.addActionListener(this);
	    
	    loginButton = new JButton("Log In");
	    loginButton.addActionListener(this);
	    
	    /*Adding logo to panel*/
	    picPanel = new JPanel();
	    Image logo = null;
		try {
			logo = ImageIO.read(new File("money pile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    JLabel picLabel = new JLabel(new ImageIcon(logo.getScaledInstance(frame.getHeight()/3, frame.getHeight()/3, Image.SCALE_DEFAULT)));
//	    picLabel.setSize(picPanel.getHeight(), picPanel.getHeight());
	    picPanel.add(picLabel);
	    
	    /*Adding welcome message to panel*/
	    labelPanel = new JPanel();
	    labelPanel.add(welcomeLabel);
	    
	    /*Formatting button panel layout and adding buttons to it*/
	    buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
	    buttonPanel.add(Box.createHorizontalStrut((int)screenWidth/3/8));
	    buttonPanel.add(cancelButton);
	    buttonPanel.add(Box.createHorizontalStrut((int)screenWidth/3/8));
	    buttonPanel.add(registerButton);
	    buttonPanel.add(Box.createHorizontalStrut((int)screenWidth/3/8));
	    buttonPanel.add(loginButton);
	    
	    /*Adding all panels to frame and setting visibility*/
	    frame.add(picPanel);
	    frame.add(labelPanel);
	    frame.add(buttonPanel);
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton){
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		if(e.getSource() == registerButton){
			System.out.println("Register button pressed");
			/*Call Register class*/
		}
		if(e.getSource() == loginButton){
			frame.setVisible(false);
			LogIn login = new LogIn();
		}
	}

	public static void main(String[] args){
		IntroFrame intro = new IntroFrame();
	}
}
