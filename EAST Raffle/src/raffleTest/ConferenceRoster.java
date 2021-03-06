//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.HashMap;

//class that reads in full conference roster and stores as hash map
public class ConferenceRoster
{
  private HashMap<String, Student> roster;
  
  public ConferenceRoster()
  {
    roster = new HashMap<String, Student>(); //initialize roster
    ReadRoster();
  }
  
  //populates roster
  public void ReadRoster()
  {
    try
    {
      File file = new File(System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/files/roster.csv");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;
      String[] info;
      
      //while not end of file, add line to roster
      while((line=bufferedReader.readLine())!=null)
      {
        info = line.split(",");
        roster.put(info[0], new Student(info[1], info[2]));
      }
      
      //close filereaders
      bufferedReader.close();
      fileReader.close();
    }
    catch(IOException e)
    {
      System.out.println(e.getLocalizedMessage());
      e.printStackTrace();
    }
  }

  //returns student object associated with unique id
  public Student getStudent(String id)
  {
    return roster.get(id);
  }
}
