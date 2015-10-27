/* This class holds all of our Database related methods */

package Controls;
import Types.*;
import java.sql.*;

public class Driver {
	
	
	/*similarly to how the user type was consolidated from resident, applicant, and manager types,
	we will do the same for the driver class. All DB driver methods here are only needed for the general
	user class, as there is no database distinction between resident, applicant, or manager*/
	
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
					//String sql = "insert into resident "+ " (userName, passWord, userType studentNum, age, fullName, yearLevel, roomNum)" + " values ('"+R.userName+"', '"+R.passWord+"', '"+R.studentNum+"', '"+R.age+"', '"+R.name+"', '"+R.yearLevel+"', '"+R.roomNum+"' )";

					myStmt.executeUpdate(sql);
					System.out.println("Added to table...");
				}
				else
					System.out.println(U.userName+" already exists in USERS table");
				
				updateAccountType(U.userName, U.accountType);
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void removeFromTable(String userName, String tableName){
		
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
				String checkIfExists = "SELECT 1 FROM "+tableName+" where userName = '"+userName+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the Applicant to the table otherwise
				if(RS.next()){
					String sql = "Delete FROM "+tableName+" where userName = '"+userName+"'";
					myStmt.executeUpdate(sql);
					System.out.println("Removed "+userName+" from "+tableName+" table.");
				}
				else
					System.out.println(userName+" is not in the "+tableName+" table.");

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
				
				//Check if the user even exists...
				String checkIfExists = "SELECT 1 FROM users where userName = '"+UN+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Update the accountType field in Users
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
	

}
