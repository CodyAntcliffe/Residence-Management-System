/* This class holds all of our Database related methods */

import java.sql.*;

public class Driver {
	
	String url = "jdbc:mysql://localhost:3306/residence";
	String dbUser = "root";
	String dbPass = "password";
	
	//Attempts to connect to our database and returns the connection if successful, null if not
	Connection connect(){
		
		try{
			Connection myConn = DriverManager.getConnection(url, "root", "password");
			System.out.println("Connection succesful");
			return myConn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Adds to the USERS table
	private void addUser(String UN, String PW){
		
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
				String checkIfExists = "SELECT 1 FROM users where userName = '"+UN+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the user to the table otherwise
				if(!RS.next()){
					String sql = "insert into Users "+ " (userName, passWord)" + " values ('"+UN+"', '"+PW+"' )";
					myStmt.executeUpdate(sql);
					System.out.println("Added to table...");
				}
				else
					System.out.println(UN+" already exists in USERS table");
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Adds to the APPLICANTS table
	private void addApplicant(User U){
		
	}
	
	//Adds to the RESIDENTS table
	private void addResident(User U){
		
	}
	
	//Adds to the DB table marked by accountType
	public void addToDB(User U){

		if(U.accountType == null){
			this.addUser(U.userName, U.passWord);
			return;
		}
		if(U.accountType == "Applicant"){
			this.addApplicant(U);
			return;
		}
		if(U.accountType == "Resident"){
			this.addResident(U);
			return;
		}
		
		System.out.println("Could not add to database.");
		
		
	}
	public static void main(String[] args) {
		
		
	}

}
