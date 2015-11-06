package Controls;
import Types.*;
import java.sql.*;
import java.util.Arrays;

public class StudentDriver extends Driver {

	//Attempts to connect to our database and returns the connection if successful, null if not
	public Connection connect(){
		return super.connect(dbUser, dbPass);
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
					String sql = "insert into users "+ " (userName, passWord, accountType, name, phone, major, email, studentNumber, yearLevel)" + " values ('"+R.getUserName()+"', '"+R.getPassword()+"', 'student', '"+R.getName()+"', '"+R.getPhone()+"', '"+R.getMajor()+"', '"+R.getEmail()+"', '"+R.getStudentNumber()+"', '"+R.getYearLevel()+"' )";
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
	
	
		
	}
	
	
