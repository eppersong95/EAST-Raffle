package raffleTest;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class EnterTicketMenu extends AbstractMenu
{
  private JButton toMainMenu;
  private JLabel scanName;
  private JLabel scanStatus;
  private String buffer;
  
  public EnterTicketMenu(Master master, ButtonListener listener)
  {
    super(master, listener);
    
    toMainMenu = new JButton("Main Menu");
    addButton(toMainMenu, master.CENTER,150);
    
    scanStatus = new JLabel();
    this.add(scanStatus);
    
    scanName = new JLabel();

    this.add(scanName);
    
    buffer = "";
    

    this.setFocusable(true);
    this.addKeyListener(new BarcodeListener(this));
    
    initialize();
    setBackground();
  }
  
  public void initialize()
  {
    scanStatus.setText("Begin scanning");
    scanStatus.setFont(new Font("CooperBlack", Font.PLAIN, 70));
    scanStatus.setForeground(Color.WHITE);
    
    scanName.setFont(new Font("CooperBlack", Font.PLAIN, 50));
    scanName.setForeground(Color.WHITE);
    scanName.setVisible(false);
    
    centerComponent(scanStatus, -140);
    centerComponent(scanName, -40);
  }
  
  public void enterTicket(String id)
  {
    Student student = ticketManager.verifyTicket(id);
    String status = "Invalid scan";
    String name = "Please try again";
    String soundPath = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/sounds//invalidScan.wav";
    
    if(student!=null)
    {
      status = "Scan successful!";
      name = student.getName();
      soundPath = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/sounds/validScan.wav";
    }
    
    setStatus(status);
    setName(name);
    centerComponent(scanStatus, -140);
    centerComponent(scanName, -40);
    SoundPlayer.playSound(soundPath);
  }
  
  public void setStatus(String status)
  {
    scanStatus.setText(status);
    centerComponent(scanStatus, -140);
  }
  
  public void setName(String name)
  {
    scanName.setVisible(true);
    scanName.setText(name);
    centerComponent(scanName, -40);
  }
}
