package raffleTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Master extends JFrame
{
  private AbstractMenu[] menus;
  private ButtonListener listener;
  
  public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
  public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
  public final int CENTER = WIDTH / 2;
  public final int LEFT = WIDTH / 4;
  public final int RIGHT = (int) ((3 * WIDTH) / 4.0);

  public Master()
  {
    BuildMaster();
    BuildMenus();
    this.setVisible(true); 
  }
  
  private void BuildMaster()
  {
    this.setTitle("EastCon Raffle 2018");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
  }
  
  private void BuildMenus()
  {
    //create a single ButtonListener to pass to the menus
    listener = new ButtonListener(this);
    
    menus = new AbstractMenu[3];
    menus[0] = new EnterTicketMenu(this, listener);
    menus[1] = new MainMenu(this, listener);
    menus[2] = new DrawTicketMenu(this, listener);
    
    for (AbstractMenu menu : menus)
    {
      this.add(menu);
    }
    
    this.setContentPane(menus[1]);
  }

  public void changeScreen(int toScreen)
  {
    menus[toScreen].initialize();
    setContentPane(menus[toScreen]);
    menus[toScreen].requestFocus();
  }
  
  public void drawTicket()
  {
    DrawTicketMenu temp = (DrawTicketMenu)menus[2];
    temp.drawTicket();
  }
  
  public static void main(String[] args)
  {
    new Master();
  }
}
