package raffleTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonListener implements ActionListener
{
  private Master master;
  
  public ButtonListener(Master master)
  {
   this.master = master; 
  }
  
  public void actionPerformed(ActionEvent arg0)
  {
    String buttonPressed = arg0.getActionCommand();
    
    if (buttonPressed.equals("Main Menu"))
    {
      master.changeScreen(1);
    }
    else if(buttonPressed.equals("Enter Tickets"))
    {
      master.changeScreen(0);
    }
    
    else if (buttonPressed.equals("Draw Tickets"))
    {
      master.changeScreen(2);
    }
    
    else
    {
      master.drawTicket();
    }
  }
}