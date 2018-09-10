//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

//SoundPlayer is used to play sounds based on the results of an enter or draw ticket action
public class SoundPlayer
{
//method that plays the sound specified by the filePath
  public static void playSound(String filePath) 
  {
    try
    {
      AudioInputStream sound = AudioSystem.getAudioInputStream(new File(filePath));
      Clip clip = AudioSystem.getClip();
      clip.open(sound);
      clip.start();
    }
    catch (UnsupportedAudioFileException|IOException|LineUnavailableException e)
    {
      e.printStackTrace();
    }
  }
}
