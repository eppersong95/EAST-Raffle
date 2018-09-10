//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

//EnterTicketMenu allows users to scan barcodes, which then act as raffle tickets
public class EnterTicketMenu extends AbstractMenu
{
//GUI elements for the screen
  private JButton toMainMenu;
  private JLabel scanName;
  private JLabel scanStatus;
  private String buffer;
  
  public EnterTicketMenu(Master master, ButtonListener listener)
  {
	//set master & listener
    super(master, listener);
    
    //initialize gui elements
    toMainMenu = new JButton("Main Menu");
    addButton(toMainMenu, master.CENTER,150);
    
    scanStatus = new JLabel();
    this.add(scanStatus);
    
    scanName = new JLabel();

    this.add(scanName);
    
    buffer = "";
    
    //adds BarcodeListener object
    this.setFocusable(true);
    this.addKeyListener(new BarcodeListener(this));
    
    //initialize screen, set background
    initialize();
    setBackground();
  }
  
  //initialize GUI components
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
  
  //uses TicketManager object to enter barcode scan as a raffle ticket
  public void enterTicket(String id)
  {
	//method used for entering tickets
    Student student = ticketManager.verifyTicket(id);
    
    //default values for result of entry (assuming unsuccesful scan)
    String status = "Invalid scan";
    String name = "Please try again";
    String soundPath = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/sounds//invalidScan.wav";
    
    //if scan was valid, change output to show success
    if(student!=null)
    {
      status = "Scan successful!";
      name = student.getName();
      soundPath = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/sounds/validScan.wav";
    }
    
    //set GUI elements to show results
    setStatus(status);
    setName(name);
    centerComponent(scanStatus, -140);
    centerComponent(scanName, -40);
    SoundPlayer.playSound(soundPath); //plays sound :)
  }
  
  //sets the status of scan
  public void setStatus(String status)
  {
    scanStatus.setText(status);
    centerComponent(scanStatus, -140);
  }
  
  //sets the name of student entered 
  public void setName(String name)
  {
    scanName.setVisible(true);
    scanName.setText(name);
    centerComponent(scanName, -40);
  }
}
