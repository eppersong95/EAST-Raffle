package raffleTest;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class DrawTicketMenu extends AbstractMenu
{
  private JButton toMainMenu;
  private JButton drawTicket;
  private JLabel winnerName;
  private JLabel winnerId;
  
  public DrawTicketMenu(Master master, ButtonListener listener)
  {
    super(master, listener);
    
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
   
    setBackground();
  }
  
  public void initialize()
  {
    winnerName.setText("Press button to draw ticket");
    centerComponent(winnerName, -140);
    winnerId.setText("");
  }
  
  public void drawTicket()
  {
    Student winner = ticketManager.drawTicket();
    String name = "No tickets found";
    String id = "Please scan more tickets";
    String soundLoc = System.getProperty("user.home")+"/Desktop/EAST Raffle/resources/sounds/invalidScan.wav";
    
    if (winner != null)
    {
      id = winner.getSchool();
      name = winner.getName();
      soundLoc = System.getProperty("user.home")+"/Desktop/EAST Raffle/resources/sounds/win.wav";
    }
    
    winnerId.setText(id);
    winnerName.setText(name);
    centerComponent(winnerName, -140);
    centerComponent(winnerId, -40);
    SoundPlayer.playSound(soundLoc);
  }
}
