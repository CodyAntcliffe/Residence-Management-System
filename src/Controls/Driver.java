package Controls;
/* This class holds all of our Database related methods */
import Types.*;
import java.sql.*;

public class Driver {
	
	static String url = "jdbc:mysql://localhost:3306/residence";
	static String dbUser = "root";
	static String dbPass = "password";
	
	//Attempts to connect to our database and returns the connection if successful, null if not
	static Connection connect(){
		
		try{
			Connection myConn = DriverManager.getConnection(url, dbUser, dbPass);
			System.out.println("Connection succesful");
			return myConn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Adds to the USERS table
	public void addToDB(User U){
		
		Connection C = connect();
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else
			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;
				
				//Check if the user exists
				String checkIfExists = "SELECT 1 FROM users where userName = '"+U.userName+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the user to the table otherwise
				if(!RS.next()){
					String sql = "insert into Users "+ " (userName, passWord)" + " values ('"+U.userName+"', '"+U.passWord+"' )";
					myStmt.executeUpdate(sql);
					System.out.println("Added to table...");
				}
				else
					System.out.println(U.userName+" already exists in USERS table");
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Adds to the APPLICANTS table
	public void addToDB(Applicant A){
		
		Connection C = connect();
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else
			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;
				
				//Check if the Applicant exists in table already...
				String checkIfExists = "SELECT 1 FROM applicant where userName = '"+A.userName+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the Applicant to the table otherwise
				if(!RS.next()){
					String sql = "insert into Applicant "+ " (userName, passWord, studentNum, age, fullName, yearLevel )" + " values ('"+A.userName+"', '"+A.passWord+"', '"+A.studentNum+"', '"+A.age+"', '"+A.name+"', '"+A.yearLevel+"' )";
					myStmt.executeUpdate(sql);
					System.out.println("Added to table...");
				}
				else
					System.out.println(A.userName+" already exists in Applicants table");
				
				updateAccountType(A.userName, A.accountType);
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Update accountType in Users table
	public void updateAccountType(String UN, String accT){
		
		Connection C = connect();
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else
			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;
				
				//Check if the Applicant exists in table already...
				String checkIfExists = "SELECT 1 FROM users where userName = '"+UN+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the Applicant to the table otherwise
				if(!RS.next()){
					System.out.println(UN+" is not a User.  Please begin registration process.");
				}
				else{
					String sql = "update Users set accountType='"+accT+"' where userName='"+UN+"'";
					myStmt.executeUpdate(sql);
					System.out.println("Updated users table with account type");
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Adds to the RESIDENTS table
	private void addResident(User U){
		
		
	}

}
