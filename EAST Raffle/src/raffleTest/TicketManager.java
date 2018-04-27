package raffleTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Random;

public class TicketManager
{
  private ConferenceRoster roster;
  private String ticketsFile;
  private String winnersFile;
  private ArrayList<String> tickets;
  
  public TicketManager()
  {
    roster = new ConferenceRoster();
    ticketsFile = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/files/tickets.csv";
    winnersFile = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/files/winners.csv";
    tickets = new ArrayList<String>();
  }
  
  public Student verifyTicket(String idNum)
  {
    Student temp = roster.getStudent(idNum);
    if(temp != null)
    {
      acceptTicket(idNum);
    }

    return temp;
  }
  
  private void acceptTicket(String toEnter)
  {
    FileWriter fw;
    try
    {
        fw = new FileWriter(ticketsFile,true); //the true will append the new data
        fw.write(toEnter + "\n");//appends the string to the file
        fw.close();
    }
    catch(IOException ioe)
    {
        System.err.println("IOException: " + ioe.getMessage());
    }
  }
  
  public Student drawTicket()
  {
    readTickets();
    if(tickets.size()==0) { return null; }
    
    Random rand = new Random();
    int winningIndex = rand.nextInt(tickets.size());
    String winnerID = tickets.remove(winningIndex);
    Student winner = roster.getStudent(winnerID);
    
    writeWinner(winnerID, winner);
    writeRemainingTickets();
    
    return winner;
  }
  
  private void readTickets()
  {
    Student temp;
    
    try
    {
      File file = new File(ticketsFile);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String info;
      while((info=bufferedReader.readLine())!=null)
      {
        tickets.add(info);
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
  
  private void writeWinner(String id, Student winner)
  {
    FileWriter fw;
    String name = winner.getName();
    String school = winner.getSchool();    
    try
    {
        fw = new FileWriter(winnersFile, true); 
        fw.write(id + "," + name + "," + school + "\n");
        fw.close();
    }
    catch(IOException ioe)
    {
        System.err.println("IOException: " + ioe.getMessage());
    }
  }
  
  private void writeRemainingTickets()
  {
    FileWriter fw;
    
    try
    {
        fw = new FileWriter(ticketsFile,false); 
        for (String id : tickets)
        {
          fw.write(id + "\n");
        }
        fw.close();
        tickets.clear();
    }
    catch(IOException ioe)
    {
        System.err.println("IOException: " + ioe.getMessage());
    }
  }
}
