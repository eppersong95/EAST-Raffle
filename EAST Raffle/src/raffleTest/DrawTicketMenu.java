//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

//DrawTicketMenu allows users to draw winners from valid raffle tickets
public class DrawTicketMenu extends AbstractMenu
{
  //GUI elements of screen
  private JButton toMainMenu;
  private JButton drawTicket;
  private JLabel winnerName;
  private JLabel winnerId;
  
  public DrawTicketMenu(Master master, ButtonListener listener)
  {
	  //set master JFrame and button listener
    super(master, listener);
    
    //initialize GUI components
    toMainMenu = new JButton("Main Menu");
    addButton(toMainMenu, master.LEFT,150);
    drawTicket = new JButton("Draw Ticket");
    addButton(drawTicket, master.RIGHT, 150);
    winnerName = new JLabel();
    winnerName.setFont(new Font("CooperBlack", Font.PLAIN, 70));
    winnerName.setForeground(Color.WHITE);
    this.add(winnerName);
    winnerId = new JLabel();
    winnerId.setFont(new Font("CooperBlack", Font.PLAIN, 50));
    winnerId.setForeground(Color.WHITE);
    this.add(winnerId);
   
    //sets background image
    setBackground();
  }
  
  //returns screen to default stateS
  public void initialize()
  {
    winnerName.setText("Press button to draw ticket");
    centerComponent(winnerName, -140);
    winnerId.setText("");
  }
  
  //uses TicketManager to draw winning ticket
  public void drawTicket()
  {
	//method that draws ticket
    Student winner = ticketManager.drawTicket();
    
    //default GUI values (assuming failure-no tickets to draw)
    String name = "No tickets found";
    String id = "Please scan more tickets";
    String soundLoc = System.getProperty("user.home")+"/Desktop/EAST Raffle/resources/sounds/invalidScan.wav";
    
    //if draw was succesful, update GUI dataS
    if (winner != null)
    {
      id = winner.getSchool();
      name = winner.getName();
      soundLoc = System.getProperty("user.home")+"/Desktop/EAST Raffle/resources/sounds/win.wav";
    }
    
    //shows winner, and plays soundS
    winnerId.setText(id);
    winnerName.setText(name);
    centerComponent(winnerName, -140);
    centerComponent(winnerId, -40);
    SoundPlayer.playSound(soundLoc);
  }
}
