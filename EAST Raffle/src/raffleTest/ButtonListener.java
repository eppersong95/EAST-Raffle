//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//ButtonListener gives functionality to on-screen buttons
public class ButtonListener implements ActionListener
{
  private Master master; //communicates with master JFrame
  
  //sets master
  public ButtonListener(Master master)
  {
   this.master = master; 
  }
  
  //completes action requested by button
  public void actionPerformed(ActionEvent arg0)
  {
	//gets the text of button pressed, and executes appropriate codeS
    String buttonPressed = arg0.getActionCommand();
    
    if (buttonPressed.equals("Main Menu"))
    {
      master.changeScreen(1); //go to main menu
    }
    else if(buttonPressed.equals("Enter Tickets"))
    {
      master.changeScreen(0); //go to enter ticket menu
    }
    
    else if (buttonPressed.equals("Draw Tickets"))
    {
      master.changeScreen(2); //go to draw ticket menu
    }
    
    else
    {
      master.drawTicket(); //otherwise, draw ticket
    }
  }
}