package raffleTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BarcodeListener implements KeyListener
{
  private EnterTicketMenu menu;
  private String buffer;
  
  public BarcodeListener(EnterTicketMenu menu)
  {
    this.menu = menu;
    buffer = "";
  }
  
  @Override
  public void keyPressed(KeyEvent arg0)
  {}

  @Override
  public void keyReleased(KeyEvent arg0)
  {
    char key = arg0.getKeyChar();
    if (key == '\n')
    {
      buffer.trim();
      menu.enterTicket(buffer);
      buffer="";
    }

    else
    {
      if (Integer.valueOf(key)!=65535) 
      {
        buffer+=key;
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent arg0)
  {}

}
