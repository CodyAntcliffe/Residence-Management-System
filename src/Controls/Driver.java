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
			System.out.println("Connection successful"); //Tells us if our db is working. Can remove before deployment.
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

				String info[] = {"facility","roomNum","typeID","occupant1","occupant2","occupant3","occupant4"};
				RS.last();
				//System.out.print(RS.last());
				Room[] rooms = new Room[RS.getRow()];
				RS.beforeFirst();
				int x = 0;
				while(RS.next()){
					Room R = new Room(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]), RS.getString(info[3]), RS.getString(info[4]), RS.getString(info[5]), RS.getString(info[6]));
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

	//Returns all rooms that are full 
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
	
	//Searches for a room by its roomNum
	public Room getRoomByNumber(String roomNum){
		
		Room[] allRooms = getAllRooms();
		
		int i=0;
		while(i<allRooms.length){
			if(allRooms[i].roomNum.equals(roomNum))
				return allRooms[i];
			i++;
		}
		
		System.out.println("Room with room number "+roomNum+" does not exist.");
		return null;
	}
	
	//Returns the image of the room, as it's image location and room number
	public String getImageByRoomNum(String roomNum){
		
		String typeID = getRoomByNumber(roomNum).typeID;
		
		Connection C = connect(dbUser, dbPass);
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getRooms  = "SELECT image FROM roomtype where typeID = '"+typeID+"'";	
				RS = myStmt.executeQuery(getRooms);
				while(RS.next()){
					String image = RS.getString("image");
					}
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//Gets image by roomType
	public String getImageByRoomType(String typeID){
		
		RoomType[] R =  getAllRoomTypes();
		
		for(int i = 0; i < R.length; i++)
			if(R[i].typeID.equals(typeID))
				return R[i].image;
		
		return null;
	}

	//Gets all room types
	public RoomType[] getAllRoomTypes(){
		
			Connection C = connect(dbUser, dbPass);
			if(C == null){
				System.out.println("Connection unsuccessful.");
			}
			else

				try{
					Statement myStmt = C.createStatement();
					ResultSet RS;

					String getRooms  = "SELECT * FROM roomtype";	
					RS = myStmt.executeQuery(getRooms);

					String info[] = {"typeID","name","capacity", "image", "accessible"};
					RS.last();
					
					RoomType[] roomtypes = new RoomType[RS.getRow()];
					RS.beforeFirst();
					int x = 0;
					while(RS.next()){
						RoomType R = new RoomType(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]),RS.getString(info[3]),RS.getString(info[4]));
						roomtypes[x] = R;
						x++;

					}
					return roomtypes;

				}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	
	//Gets roomtype info by the room number
	public RoomType getRoomTypeInfoByRoomNum(String roomNum){
		
		RoomType[] RT = getAllRoomTypes();
		Room R = getRoomByNumber(roomNum);
		
		for(int i=0; i<RT.length; i++)
			if(R.typeID.equals(RT[i].typeID))
				return RT[i];
		
		return null;
		
	}
	//Gives a list of all rooms of a specific facility
	public Room[] getRoomsByFacility(String facility){
		
		Room[] allRooms = getAllRooms();
	
		//Need to get room count t avoid putting in hard coded numbers
		int count = 0;
		for(int i=0; i<allRooms.length; i++){
			if(allRooms[i].facility.equals(facility)){
				count++;
			}
		}
		Room[] facRooms = new Room[count];
		int nRoom = 0;
		for(int i=0; i<allRooms.length; i++){
			if(allRooms[i].facility.equals(facility)){
				facRooms[nRoom] = allRooms[i];
				nRoom++;
			}
			if(nRoom==count)
				return facRooms;
		}
		return facRooms;
	}
	

}
