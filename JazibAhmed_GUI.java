/*
Jazib's Encryption Program in Java
Author: Jazib Ahmed
Email: jazib7537@gmail.com
*/

//Importing multiple packages for the GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JazibAhmed_GUI extends JFrame implements ActionListener //Extending JFrame and Action Listener for the program to work
{
    //Making new labels, buttons, text areas, fonts and the frame and assigning them to variables
    public JLabel title, text1, text2, label;
    public JButton encrypt, decrypt, clear;
    public JTextArea textarea, textarea2;
    public Font t, t1, t2;
    ImageIcon back;
    Container frame;
    
    public JazibAhmed_GUI () //The GUI method
    {
	super ("Jazib's Encryption Program"); //Naming the GUI "Jazib's Encryption Program"
	
	//Getting a background image and putting it on a JLabel to put on the GUI
	back = new ImageIcon ("background.jpg"); 
	label = new JLabel (back, JLabel.CENTER);

	//Making new fonts
	t = new Font ("Georgia", Font.PLAIN, 25);
	t1 = new Font ("Georgia", Font.PLAIN, 16);
	t2 = new Font ("Comic Sans MS", Font.PLAIN | Font.BOLD, 18);
    
	//Getting content pane for frame and setting layout and location
	frame = getContentPane ();
	frame.setLayout (null);
	setLocation (400, 50);

	//Making JLabels with text and setting their color and font 
	title = new JLabel ("Jazib's Language Encryptor");
	text1 = new JLabel ("Secret Message:");
	text2 = new JLabel ("Encrypted Language: ");
	text1.setFont (t1);
	text2.setFont (t1);
	title.setFont (t); //sets font for title
	title.setForeground (new Color (255, 215, 0, 255));
	text1.setForeground (new Color (255, 215, 50, 255));
	text2.setForeground (new Color (255, 215, 50, 255));

	//Making a textbox for user to enter input and receive output in the other textbox
	textarea = new JTextArea ();
	textarea.setLineWrap (true); //makes JTextArea wrap around
	textarea.setWrapStyleWord (true); //makes JTextArea wrap around by words, rather than characters
	textarea2 = new JTextArea ();
	textarea2.setLineWrap (true); //makes JTextArea wrap around
	textarea2.setWrapStyleWord (true); //makes JTextArea wrap around by words, rather than characters
	
	//Setting background color and text font for textboxes
	textarea.setBackground (Color.LIGHT_GRAY);
	textarea2.setBackground (Color.LIGHT_GRAY);
	textarea.setFont (t2);
	textarea2.setFont (t2);

	//Making new buttons with text of their functions
	encrypt = new JButton ("Encrypt");
	decrypt = new JButton ("Decrypt");
	clear = new JButton ("Clear");
	
	//Setting the background color and removing the border for the buttons
	clear.setBackground (Color.BLUE);
	encrypt.setBackground (new Color (255, 40, 0, 255));
	decrypt.setBackground (new Color (0, 235, 84, 255));
	encrypt.setBorderPainted (false);
	decrypt.setBorderPainted (false);
	clear.setBorderPainted (false);

	//Adding all labels, buttons and textboxes to the frame
	frame.add (title);
	frame.add (text1);
	frame.add (text2);
	frame.add (textarea);
	frame.add (textarea2);
	frame.add (encrypt);
	frame.add (decrypt);
	frame.add (clear);
	frame.add (label);
	//setting and positioning components

	//Setting the location and boundaries for all labels, buttons and textboxes
	title.setBounds (135, 10, 400, 35);
	label.setBounds (0, 0, 600, 600);
	text1.setBounds (35, 45, 300, 40);
	text2.setBounds (35, 285, 300, 40);
	textarea.setBounds (35, 90, 500, 140);
	textarea2.setBounds (35, 325, 500, 140);
	encrypt.setBounds (100, 245, 380, 35);
	decrypt.setBounds (100, 475, 380, 35);
	clear.setBounds (40, 520, 490, 30);
	
	setSize (600, 600); //setting size of the frame
	show (); //setting visibility
	
	//Adding action listeners to the buttons
	encrypt.addActionListener (this);
	decrypt.addActionListener (this);
	clear.addActionListener (this);
    }


    public void actionPerformed (ActionEvent e) //ActionListener method
    {
	if (e.getSource () == encrypt) //If statement to perform action when the "encrypt" button is pressed
	{
	    String encryption = ""; //Declaring encryption storing variable
	    encryption += JazibAhmed_Encryption.encrypt(textarea.getText ()); //Calling on encrypt method from the encrypt/decrypt program and storing value in "encryption" variable
	    textarea2.setText (encryption); //Giving output in the encrypted language box
	    textarea.setText(""); //Removing text from first box
	}
	else if (e.getSource () == decrypt) //Else if statement to perform action when the "decrypt" button is pressed
	{
	    String decryption = ""; //Declaring decryption storing variable
	    decryption += JazibAhmed_Encryption.decrypt (textarea2.getText ()); //Calling on decrypt method from the encrypt/decrypt program and storing value in "decryption" variable
	    textarea.setText (decryption); //Giving output in the secret message box
	    textarea2.setText(""); //Removing text from second box
	}
	else if (e.getSource () == clear) //Else if statement to perform action when the "clear" button is pressed
	{
	    //Clearing both text boxes
	    textarea.setText (""); 
	    textarea2.setText ("");
	}
    }


    public static void main (String[] args)
    {
	new JazibAhmed_GUI (); //Making a new GUI

    } // main method
} // JazibAhmed_GUI class
