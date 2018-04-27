package raffleTest;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
//Abstract menu class
//Completes basic formatting + handles commanalities
//Actual menus will extend from this class
abstract class AbstractMenu extends JPanel
{
  protected static TicketManager ticketManager = new TicketManager();
  
  protected Master master;
  protected ButtonListener listener;
  private JLabel background;
  
  public AbstractMenu(Master master, ButtonListener listener)
  {
    this.master = master;
    this.listener = listener;
    formatPanel();
  }
  
  //formats JPanel
  private void formatPanel()
  {
    this.setLayout(null);
    this.setSize(new Dimension(master.WIDTH, master.HEIGHT));
  }
  
  abstract void initialize();
  
  //sets background of JPanel
  public void setBackground()
  {
    String path = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/images/space.jpg";
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
      System.out.println(System.getProperty("user.dir"));
    }
  }
  
  
  //adds a button to JPanel
  public void addButton(JButton newButton, int xAlignment, int yFromBottom)
  {
    int buttonWidth = 200;
    int buttonHeight = 50;
    
    newButton.setFont(new Font("CooperBlack", Font.PLAIN, 25));
    newButton.setBounds(xAlignment - buttonWidth/2, master.HEIGHT - (yFromBottom + buttonHeight), buttonWidth, buttonHeight);
    newButton.addActionListener(listener);
    
    this.add(newButton);
  }
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

