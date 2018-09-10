//Garrett Epperson
//EAST Conference 2018 Raffle Program

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

//TicketManager is used for accepting valid tickets and selecting winners at random
//It also handles the file IO for the tickets.csv and winners.csv files
public class TicketManager
{
  private ConferenceRoster roster; //full roster of participants
  private String ticketsFile; //file path of tickets.csv
  private String winnersFile; //file path of winners.csv
  private ArrayList<String> tickets; //
  
  public TicketManager()
  {
    roster = new ConferenceRoster();
    ticketsFile = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/files/tickets.csv";
    winnersFile = System.getProperty("user.home") + "/Desktop/EAST Raffle/resources/files/winners.csv";
    tickets = new ArrayList<String>();
  }
  
  //verifies that a barcode scan matches a unique id number 
  public Student verifyTicket(String idNum)
  {
    Student temp = roster.getStudent(idNum); //checks the full roster
    if(temp != null) //if temp isn't null, there was a match. add to tickets.csv
    {
      acceptTicket(idNum);
    }

    return temp;
  }
  
  //writes valid barcode scan to tickets.csv
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
  
  //draws a random ticket from tickets.csv
  //writes winner to winners.csv
  //returns associated Student object for display 
  public Student drawTicket()
  {
    readTickets(); //populates tickets with all of tickets.csv
    if(tickets.size()==0) { return null; } //if no tickets to draw from
    
    //generates random number
    Random rand = new Random();
    int winningIndex = rand.nextInt(tickets.size());
    
    //gets winning ID and corresponding Student object
    //removes this ticket from remaining ticketsS
    String winnerID = tickets.remove(winningIndex);
    Student winner = roster.getStudent(winnerID);
    
    //writes winner, and repopulates tickets.csv with remaining tickets
    writeWinner(winnerID, winner);
    writeRemainingTickets();
    
    //return winning Student object
    return winner;
  }
  
  //reads all tickets from tickets.csv 
  private void readTickets()
  {  
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
  
  //writes winner information to winners.csv
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
  
  //writes remaining tickets to tickets.csv
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
