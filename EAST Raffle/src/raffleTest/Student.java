//Garrett Epperson
//EAST Conference 2018 Raffle Program

package raffleTest;

//Student contains the name and school/organization associated with unique id
public class Student
{
  private String name;
  private String school;
  
  public Student(String name, String school)
  {
    this.name = name;
    this.school = school;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getSchool()
  {
    return school;
  }
}
