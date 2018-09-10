//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.Dimension;

import javax.swing.*;

//MainMenu acts as an intermediary between DrawTicketMenu and EnterTicketMenu
public class MainMenu extends AbstractMenu
{
  //GUI elements
  private JButton toEnterMenu;
  private JButton toDrawMenu;
  private JLabel logo;
  
  public MainMenu(Master master, ButtonListener listener)
  {
	//set master JFrame and ButtonListener
    super(master, listener);
    
    //initialize GUI elements
    toEnterMenu = new JButton("Enter Tickets");
    toDrawMenu = new JButton("Draw Tickets");
    
    addButton(toEnterMenu, master.LEFT, 150);
    addButton(toDrawMenu, master.RIGHT, 150);
    
    setLogo();
    setBackground();
  }
  
  //because this screen never changes (no input), there is no need to reset GUI elements
  public void initialize()
  {}
  
  //adds the EASTCon18 logo to center of the screen
  public void setLogo()
  {
    String path = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/images/logo.png";
    logo = new JLabel();
    try
    {
      ImageIcon temp = new ImageIcon(path);
      logo.setIcon(temp);
      
      int logoWidth = temp.getIconWidth();
      int logoHeight = temp.getIconHeight();
      
      if (logoHeight > master.HEIGHT) { logoHeight = master.HEIGHT; }
      
      logo.setBounds(master.CENTER - (logoWidth/2), 0, logoWidth, master.HEIGHT);
      
      this.add(logo);
    }
    catch(NullPointerException e)
    {
      System.out.println(System.getProperty("user.dir"));
    }
  }
}