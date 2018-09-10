//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//BarcodeListener allows barcodes to be scanned on EnterTicketMenu
//Barcode scan acts as simulated keystrokes, one entry at a time, followed by enter
public class BarcodeListener implements KeyListener
{
  private EnterTicketMenu menu; //used to pass information back to menu
  private String buffer; //buffer for holding input
  
  public BarcodeListener(EnterTicketMenu menu)
  {
    this.menu = menu;
    buffer = "";
  }
  
  @Override //required function for KeyListener object
  public void keyPressed(KeyEvent arg0)
  {}

  @Override
  public void keyReleased(KeyEvent arg0)
  {
    char key = arg0.getKeyChar(); //gets ascii key value
    
    //if key = enter, trim, and attempt to enter value as ticket
    if (key == '\n')
    {
      buffer.trim();
      menu.enterTicket(buffer);
      buffer=""; //reset buffer
    }

    else //if key != enter, add value to buffer
    {
      if (Integer.valueOf(key)!=65535) //used to eliminate unusual character in some nametags
      {
        buffer+=key;
      }
    }
  }

  @Override //required method for KeyListener object
  public void keyTyped(KeyEvent arg0)
  {}

}
