//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;

//AbstractMenu class
//Extends JPanel & includes functions all screens share (ex. addButton)
//Acts as a base class for all other screens
abstract class AbstractMenu extends JPanel
{
  //allows for all screens to share one TicketManager object
  protected static TicketManager ticketManager = new TicketManager();
  
  protected Master master; //master JFrame which manages the different screens
  protected ButtonListener listener; //listens for button presses
  private JLabel background; //background image
  
  public AbstractMenu(Master master, ButtonListener listener)
  {
    this.master = master; //master JPanel
    this.listener = listener; //shared button listener
    formatPanel(); 
  }
  
  //formats JPanel
  private void formatPanel()
  {
    this.setLayout(null); //null LayoutManager
    this.setSize(new Dimension(master.WIDTH, master.HEIGHT)); //sets size to fullscreen
  }
  
  //abstract function to set screen to its default state
  abstract void initialize();
  
  //sets background of JPanel
  public void setBackground()
  {
	//looks for image in folder on user's desktop
    String path = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/images/space.jpg";
    
    //uses JLabel to set background
    background = new JLabel();
    try
    {
      ImageIcon bg = new ImageIcon(path);
      background.setIcon(bg);
      background.setBounds(0,0,master.WIDTH, master.HEIGHT);
      this.add(background);
    }
    catch(NullPointerException e)
    {
      System.out.println("ERROR: Could not load background image.");
    }
  }
  
  
  //adds a button to JPanel
  public void addButton(JButton newButton, int xAlignment, int yFromBottom)
  {
	//button information - standard sizing
    int buttonWidth = 200;
    int buttonHeight = 50;
    
    //sets text, adds listener, and places button
    newButton.setFont(new Font("CooperBlack", Font.PLAIN, 25));
    newButton.setBounds(xAlignment - buttonWidth/2, master.HEIGHT - (yFromBottom + buttonHeight), buttonWidth, buttonHeight);
    newButton.addActionListener(listener);
    
    //adds button to JPanel
    this.add(newButton);
  }
  
  //sets component in center of screen
  //used for displaying names, error messages
  public void centerComponent(Component comp, int heightOffset)
  {
    Dimension temp = comp.getPreferredSize();
    int width = master.WIDTH;
    int height = master.HEIGHT;
    int xCorner = (int)(width/2) - (int)(temp.getWidth()/2);
    int yCorner = (int)(height/2) + heightOffset;
    
    comp.setBounds(xCorner, yCorner, (int)temp.getWidth(), (int)temp.getHeight());
  }
}

