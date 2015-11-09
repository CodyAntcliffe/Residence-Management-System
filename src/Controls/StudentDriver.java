package Controls;
import Types.*;
import java.sql.*;
import java.util.Arrays;

public class StudentDriver extends Driver {

	//Attempts to connect to our database and returns the connection if successful, null if not
	public Connection connect(){
		return super.connect(dbUser, dbPass);
	}
	
	//Allows the logged-on applicant to request a room
	public void requestRoom(String roomNum, String userName){
		
		Connection C = connect();
		roomNum = "'"+roomNum+"'";
		userName = "'"+userName+"'";
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else
			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;
				
				//Set the roomNum in the Users table
				String sql = "update Users set roomNum= "+roomNum+" where userName= "+userName;
				myStmt.executeUpdate(sql);
				
			}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addRegistration(Register R){
		
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
				String checkIfExists = "SELECT 1 FROM users where userName = '"+R.getUserName()+"'";				
				RS = myStmt.executeQuery(checkIfExists);
				
				//Add the user to the table otherwise
				if(!RS.next()){
					String sql = "insert into users "+ " (userName, passWord, accountType, name, phone, major, email, studentNumber, yearLevel)" + " values ('"+R.getUserName()+"', '"+LogIn.encryptPassword(R.getPassword())+"', 'applicant', '"+R.getName()+"', '"+R.getPhone()+"', '"+R.getMajor()+"', '"+R.getEmail()+"', '"+R.getStudentNumber()+"', '"+R.getYearLevel()+"' )";
					myStmt.executeUpdate(sql);
					System.out.println("Added to table...");
				}
				else
					System.out.println(R.getUserName()+" already exists in USERS table");
				
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void assignRoom(int roomNum){
		
		
	}
	
	
		
}
	
	
