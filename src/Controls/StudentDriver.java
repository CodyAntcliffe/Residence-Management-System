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
					String sql = "insert into users "+ " (userName, passWord, accountType, name, phone, major, email, studentNumber, yearLevel, age)" + " values ('"+R.getUserName()+"', '"+LogIn.encryptPassword(R.getPassword())+"', 'applicant', '"+R.getName()+"', '"+R.getPhone()+"', '"+R.getMajor()+"', '"+R.getEmail()+"', '"+R.getStudentNumber()+"', '"+R.getYearLevel()+"', '"+R.getAge()+"' )";
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
	
	//Returns instance of Student by studentName
	public Student getStudentInfo(String userName){
		
		Connection C = connect();
		userName = "'"+userName+"'";
		//System.out.println(studentName);
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getNames  = "SELECT * FROM users where username = "+userName;	
				RS = myStmt.executeQuery(getNames);
				String info[] = {"userName","passWord","accountType","name","email","phone","major","yearLevel","studentNumber","roomNum","age"};

				while(RS.next()){
					Student S = new Student(RS.getString(info[0]),RS.getString(info[1]),RS.getString(info[2]),RS.getString(info[3]),RS.getString(info[4]),RS.getString(info[5]),RS.getString(info[6]),RS.getString(info[7]),RS.getString(info[8]),RS.getString(info[9]),RS.getString(info[10]));
					return S;
				}

					return null;
				
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}
	
	
		
}
	
	
