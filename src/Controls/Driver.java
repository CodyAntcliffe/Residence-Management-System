/* This class holds all of our Database related methods */

package Controls;
import Types.*;
import java.sql.*;

import Controls.Driver;

public class Driver {

	static String url = "jdbc:mysql://localhost:3306/residence";
	static String dbUser = "root";
	static String dbPass = "password";

	//Attempts to connect to our database and returns the connection if successful, null if not
	static Connection connect(String dbUser, String dbPass){

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection myConn = DriverManager.getConnection(url, dbUser, dbPass);
			return myConn;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//Gets accountType by userName
	public String getAccountTypeByUserName(String UN){

		Connection C = connect(dbUser,dbPass);
		UN = "'"+UN+"'";
		//System.out.println(studentName);
		//Check if connection successful
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				//Check if the user exists

				String getNames  = "SELECT * FROM users where userName = "+UN;	
				RS = myStmt.executeQuery(getNames);

				while(RS.next()){
					return RS.getString("accountType");
				}

				return null;

			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	//Checks if log-in was successful. 
	public static Boolean checkLogin(String UN, String PW){

		Connection C = Driver.connect(dbUser, dbPass);

		try{
			Statement myStmt = C.createStatement();
			ResultSet RS;

			//Check if the userName UN exists
			String checkUserName = "SELECT 1 FROM users where userName = '"+UN+"'";				
			RS = myStmt.executeQuery(checkUserName);

			//Log in unsuccessful if not in Users table
			if(!RS.next()){
				System.out.println("Invalid user name.  Log-in unsuccessful.");
				return false;
			}
			//Now check to make sure the password matches the stored encrypted password.
			else{
				String checkPW = "SELECT 1 FROM users where passWord = '"+LogIn.encryptPassword(PW)+"'";
				System.out.println(LogIn.encryptPassword(PW));
				RS = myStmt.executeQuery(checkPW);

				//If does not match
				if(!RS.next()){
					System.out.println("Invalid passsword.  Log-in unsuccessful.");
					return false;
				}
				//User name and password are both found in the database
				else
				{
					System.out.println("Log-in Successful.");
					return true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static void removeFromTable(String userName, String tableName){

		Connection C = connect(null, null);
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

	//Returns all rooms
	public Room[] getAllRooms(){
		Connection C = connect(dbUser, dbPass);
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getRooms  = "SELECT * FROM rooms";	
				RS = myStmt.executeQuery(getRooms);

				String info[] = {"roomNum","occupant"};
				RS.last();
				System.out.print(RS.last());
				Room[] rooms = new Room[RS.getRow()];
				RS.beforeFirst();
				int x = 0;
				while(RS.next()){
					Room R = new Room(RS.getString(info[0]),RS.getString(info[1]));
					rooms[x] = R;
					x++;

				}
				return rooms;

			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	//Returns all available rooms
	public Room[] getAvailRooms(){

		Room[] allRooms = getAllRooms();

		int count = 0;
		for(int i= 0; i<allRooms.length;i++){
			if(!allRooms[i].isOccupied())
				count++;
		}

		Room[] availRooms = new Room[count];
		int x = 0;
		for(int i= 0; i<allRooms.length;i++){
			if(!allRooms[i].isOccupied()){
				availRooms[x] = allRooms[i];
				x++;
			}
		}

		return availRooms;
	}

	//Returns all rooms that are occupied
	public Room[] getOccupiedRooms(){

		Room[] allRooms = getAllRooms();

		int count = 0;
		for(int i= 0; i<allRooms.length;i++){
			if(allRooms[i].isOccupied())
				count++;
		}
		Room[] occRooms = new Room[count];

		int x = 0;
		for(int j= 0; j<allRooms.length;j++){
			if(allRooms[j].isOccupied()){
				occRooms[x] = allRooms[j];
				x++;
			}
		}
		return occRooms;

	}
}
