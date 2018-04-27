package raffleTest;

import java.awt.Dimension;

import javax.swing.*;

public class MainMenu extends AbstractMenu
{
  private JButton toEnterMenu;
  private JButton toDrawMenu;
  private JLabel logo;
  
  public MainMenu(Master master, ButtonListener listener)
  {
    super(master, listener);
    
    toEnterMenu = new JButton("Enter Tickets");
    toDrawMenu = new JButton("Draw Tickets");
    
 
    addButton(toEnterMenu, master.LEFT, 150);
    addButton(toDrawMenu, master.RIGHT, 150);
    
    setLogo();
    setBackground();
  }
  
  public void initialize()
  {}
  
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