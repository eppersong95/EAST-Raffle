//Garrett Epperson
//EAST Conference 2018 Raffle Program

/*This program acts as a raffle program for Fusion Night at EAST Conference 2018
  -Functionality is divided into three screens: MainMenu, EnterTicketMenu, and DrawTicketMenu
  -MainMenu simply allows you to switch between the Enter and Draw ticket menus
  -EnterTicketMenu accepts input via barcode scan (or keystrokes). This input is 
   compared with a full roster of attendees (roster.csv), and if there is a match,
   that person is added to the list of possible winners (tickets.csv). Each person
   may have multiple entries.
  -DrawTicketMenu selects a potential winner (from tickets.csv) at random, displays
   their name and organization, and adds their information to the winners file (winners.csv).
*/

package raffleTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//Master extends JFrame because it acts as a container which can hold all other screens
public class Master extends JFrame
{
  private AbstractMenu[] menus; //an array for managing different screens
  private ButtonListener listener; //an object for interpreting button clicks
  
  //use Toolkit to set screen size, as well as the x-coordinates for center, left, and right
  public final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
  public final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
  public final int CENTER = WIDTH / 2;
  public final int LEFT = WIDTH / 4;
  public final int RIGHT = (int) ((3 * WIDTH) / 4.0);

//Constructor
  public Master()
  {
    BuildMaster();
    BuildMenus();
    this.setVisible(true); //shows screen upon running
  }
  
  //set basic information for the JFrame
  private void BuildMaster()
  {
    this.setTitle("EastCon Raffle 2018");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH); //sets fullscreen
  }
  
  //builds the three menus mentioned above
  private void BuildMenus()
  {
    //create a single ButtonListener to pass to the menus
    listener = new ButtonListener(this);
    
    //initialize menus
    menus = new AbstractMenu[3];
    menus[0] = new EnterTicketMenu(this, listener);
    menus[1] = new MainMenu(this, listener);
    menus[2] = new DrawTicketMenu(this, listener);
    
    //add menus to JFrame
    for (AbstractMenu menu : menus)
    {
      this.add(menu);
    }
    
    //set content pane to main menu upon launch
    this.setContentPane(menus[1]);
  }

  //swaps content from one menu to another
  public void changeScreen(int toScreen)
  {
    menus[toScreen].initialize(); //sets screen to its default state
    setContentPane(menus[toScreen]);
    menus[toScreen].requestFocus(); //binds keyboard, mouse to new screen
  }
  
  //draws a ticket at random from tickets.csv
  public void drawTicket()
  {
    DrawTicketMenu temp = (DrawTicketMenu)menus[2]; //typecast the menu
    temp.drawTicket();
  }
  
  //Main function - creates Master object
  public static void main(String[] args)
  {
    new Master();
  }
}
