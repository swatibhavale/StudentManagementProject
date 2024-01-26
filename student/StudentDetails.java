package student;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import student.MyConnection;

/*
 * Student Management System
Student: rollNo,name,dob,percent,city,email_id,phone_no, add constraints

1.insert Student record 
2.View all Student details
3.View Student by city name
4.update student details by rollno
5.delete student by rollno
6.Search Student on the basis of Percentage
		enter min range:
		enter max range
7.display details of 1st Ranker
8.search student details by rollno
9.sort student by percent asc
10.display student who are living in same city
 */
public class StudentDetails {
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	public StudentDetails() throws ClassNotFoundException, SQLException
	{
		connection = MyConnection.getMyConnection();
		//System.out.println("Connection is established....\n");
	}
	
	//1.insert Student record 
	public void insertRecord(int rollNo, String name, String dob, String city, float percentage, String email_id, String phone_no) throws SQLException
	{
		preparedStatement= connection.prepareStatement("insert into Student values(?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, rollNo);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, dob);
		preparedStatement.setString(4, city);
		preparedStatement.setFloat(5, percentage);
		preparedStatement.setString(6, email_id);
		preparedStatement.setString(7, phone_no);
		
		int n = preparedStatement.executeUpdate();
		System.out.println(n+" record is inserted");
	}
	
	//2.View all Student details
	public void searchByCity(String city) throws SQLException
	{
		preparedStatement=connection.prepareStatement("select * from Student where city=?");
		preparedStatement.setString(1, city);
		
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\tPhone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}

	}
	
	
	//3.View Student by city name
	public void getAllRecords() throws SQLException
	{
		
		
		// execute query
		preparedStatement = connection.prepareStatement("select * from Student");
		resultSet=preparedStatement.executeQuery();
		
		// retriving result
		   System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\t  Phone No");
		   while(resultSet.next()) 
		   {
			   System.out.print(resultSet.getInt(1)+"\t");
			   System.out.print(resultSet.getString(2)+"\t");
			   System.out.print(resultSet.getString(3)+"\t");
			   System.out.print(resultSet.getString(4)+"\t");
			   System.out.print(resultSet.getFloat(5)+"\t");
			   System.out.print(resultSet.getString(6)+"\t");
			   System.out.print(resultSet.getString(7)+"\t");
			   System.out.println();
		   }
		}
	
	//4.update student details by rollno
		public void updateRecord(String city, int rollNo) throws RollNoNotFoundException, SQLException
		{
			preparedStatement=connection.prepareStatement("update Student set city=? where rollNo = ?");
			preparedStatement.setString(1, city);
			preparedStatement.setInt(2, rollNo);
			
			int n = preparedStatement.executeUpdate();
			System.out.println(n+" Student Record is Updated");
			
			if(n==0) {
				throw new RollNoNotFoundException(rollNo+" Roll number is not present... Please Enter valid roll number..");
			}
		}
		
		
	//5.delete student by rollno
	public void deleteRecord(int rollNo) throws RollNoNotFoundException, SQLException
	{
		preparedStatement=connection.prepareStatement("delete from Student where rollNo = ?");
		preparedStatement.setInt(1, rollNo);
		
		int n = preparedStatement.executeUpdate();
		System.out.println(n+" Record is deleted");
		System.out.println();
		
		if(n==0) {
			throw new RollNoNotFoundException(rollNo+" Roll number is not present... Please Enter valid roll Number..");
		}
	}
	
	//6.Search Student on the basis of Percentage
	//enter min range:
	//enter max range
	public void searchByPercentage(float minpercent, float maxpercent) throws SQLException
	{
		preparedStatement=connection.prepareStatement("SELECT * FROM Student WHERE percentage between  ? and  ?");
		preparedStatement.setFloat(1, minpercent);
		preparedStatement.setFloat(2, maxpercent);
		
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\tPhone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}
	}
	
	//7.display details of 1st Ranker
	public void displayFirstRanker() throws SQLException
	{
		preparedStatement = connection.prepareStatement(" select * from Student order by percentage desc limit 0, 1 ");
		
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\tPhone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}
	}
	
	//8.search student details by rollno
	public void searchByRollNo(int rollNo) throws SQLException
	{
		preparedStatement=connection.prepareStatement("select * from Student where rollNo=?");
		preparedStatement.setInt(1, rollNo);
		
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\tPhone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}
		
	}
	
	//9.sort student by percent asc
	public void sortStudentByPercent() throws SQLException
	{
		preparedStatement = connection.prepareStatement("select * from Student order by percentage asc");
		
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\tPhone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}
	}
	
	//10.display student who are living in same city
	public void liveInSameCity() throws SQLException
	{
		preparedStatement=connection.prepareStatement("SELECT Student.* FROM Student WHERE (SELECT COUNT(*) FROM Student AS s GROUP BY city HAVING s.city = Student.city) > 1");
		resultSet=preparedStatement.executeQuery();
		System.out.println("Roll No\tName\tDate Of Birth\tCity\tPercent\tEmail Id\t Phone No");
		while(resultSet.next())
		{
			System.out.print(resultSet.getInt(1)+"\t");
			System.out.print(resultSet.getString(2)+"\t");
			System.out.print(resultSet.getString(3)+"\t");
			System.out.print(resultSet.getString(4)+"\t");
			System.out.print(resultSet.getFloat(5)+"\t");
			System.out.print(resultSet.getString(6)+"\t");
			System.out.print(resultSet.getString(7)+"\t");
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		StudentDetails stud= new StudentDetails();
		int rollNo;
		String name, city, email_id, phone_no;
		String dob;  
		float percentage, minpercent, maxpercent;  
		int choice;
		System.out.println("\n**************************************************************************************************\n");
		System.out.println("                            WELCOME TO STUDENT MANAGEMENT SYSTEM\n");
		System.out.println("**************************************************************************************************\n");
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("\n 1)  Insert new record in Student\n 2)  Update Details by Student Roll No. \n 3)  Search student details by city. \n 4)  Delete student record by Roll No. \n 5)  Search student record by Percentage. \n 6)  Display Details of 1st ranker. \n 7)  Search student by Roll no. \n 8)  Sort student by Percentage. \n 9)  Display student who are living in same city. \n 10) Display all records\n 11) Exit");
			System.out.println("\nEnter your choice.");
			choice=sc.nextInt();
        
		switch(choice)
		{
		case 1:     System.out.println("-----------------Insert Student Data----------------------");
					System.out.println("Enter Id : ");
					rollNo=sc.nextInt();
					
					System.out.println("Enter Name : ");
					name=sc.next();
					
					System.out.println("Enter date of birth : (YYYY-MM-DD) ");
					dob=sc.next();
					
					System.out.println("Enter city : ");
					city=sc.next();
					
					System.out.println("Enter percentage : ");
					percentage=sc.nextFloat();
					
					System.out.println("Enter Email : ");
					email_id=sc.next();
				
					System.out.println("Enter Phone no : ");
					phone_no=sc.next();
			
					
					stud.insertRecord(rollNo, name, dob, city, percentage, email_id, phone_no);
					break;
					
		case 2:     System.out.println("-----------------Update Student by City ----------------------");
		            System.out.println("Enter Roll No : ");
		            rollNo=sc.nextInt();
		              
		            System.out.println("Enter City : ");
		            city=sc.next();
				 
			try {
				stud.updateRecord(city, rollNo);
			} catch (RollNoNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());		
			}
			        break;
		             
		case 3:  	System.out.println("----------------Search Student by City-----------------------");
					System.out.println("Enter City : ");
					city=sc.next();
			
					System.out.println("Search by city : "+city);
					stud.searchByCity(city);
					break;
					
		case 4:    System.out.println("-----------------Delete Student recorde by Roll Number----------------------");
			       System.out.println("Enter Roll No : ");
			       rollNo=sc.nextInt();
			       
			try {
				stud.deleteRecord(rollNo);
			} catch (RollNoNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());			
			}
		           break;
			    	 
		           
		case 5:    System.out.println("-----------------Search Student By Percentage----------------------");
	       		   System.out.println("Enter Minimum Percentage : ");
	       		   minpercent=sc.nextFloat();
	       		   
	       		   System.out.println("Enter Maximum Percentage : ");
	       		   maxpercent=sc.nextFloat();
	       		 
	       		   stud.searchByPercentage(minpercent, maxpercent);
	       		   break;     
	       		   
		case 6:    System.out.println("-----------------Display 1st ranker Student Details----------------------");
		 
		   		   stud.displayFirstRanker();
		   		   break;
		   		   
		case 7:    System.out.println("----------------Search Student by Roll No-----------------------");
				   System.out.println("Enter Roll No : ");
				   rollNo=sc.nextInt();

				  
				   stud.searchByRollNo(rollNo);
				   break;
				   
		case 8:    System.out.println("----------------Sort Student by Percentage-----------------------");

				   stud.sortStudentByPercent();
				   break;
				   
		case 9:    System.out.println("----------------Display Student Living in Same City-----------------------");

		   		   stud.liveInSameCity();
		           break;
		            
		case 10:   System.out.println("-----------------Display Records----------------------\n");
		           stud.getAllRecords();
		           break;
		            
		default:   System.out.println("Invalid Choice");		
		
		}
		
		} while(choice!=11);
		

	

	}

}
