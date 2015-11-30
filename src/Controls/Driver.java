/* This class holds all of our Database related methods */

package Controls;
import Types.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Controls.Driver;
import ManagedBeans.LogIn;

public class Driver {

	static String url = "jdbc:mysql://localhost:3306/residence";
	static String dbUser = "root";
	static String dbPass = "password";

	public static Connection C = connect(dbUser,dbPass);
	
	/**
	 * Attempts to connect to our database and returns the connection if successful, null if not
	 * @param dbUser
	 * @param dbPass
	 * @return
	 */
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

	/**
	 * Gets accountType by userName
	 * @param UN
	 * @return
	 */
	public String getAccountTypeByUserName(String UN){

		
		UN = "'"+UN+"'";
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

	/**
	 * Checks if log-in was successful. 
	 * @param UN
	 * @param PW
	 * @return
	 */
	public static Boolean checkLogin(String UN, String PW){


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

	/**
	 * Returns all rooms as room objects
	 * @return
	 */
	public Room[] getAllRooms(){
		
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

	/**Returns all available rooms.
	 * An available room is one which has either no occupant, or an empty space
	 * 
	 * @return
	 */
	
	public Room[] getAvailRooms(){

		Room[] allRooms = getAllRooms();
		Room[] tempHold = new Room[1000];
		
		int count = 0;
		for(int i= 0; i<allRooms.length;i++){
			if(!allRooms[i].isOccupied()){
				tempHold[count] = allRooms[i];
				count++;
			}
		}

		Room[] availRooms = new Room[count];
		
		for(int i= 0; i<count;i++){
			availRooms[i] = tempHold[i];
		}

		return availRooms;
	}

	/**
	 * Returns all rooms that have at least one occupant
	 * @return
	 */
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
	
	/**
	 * Searches for a room by its roomNum
	 * @param roomNum
	 * @param facility
	 * @return
	 */
	public Room getRoomByNumber(String roomNum, String facility){
		
		Room[] allRooms = getRoomsByFacility(facility);		
		int i=0;
		while(i<allRooms.length){
			if(allRooms[i].roomNum.equals(roomNum))
				return allRooms[i];
			i++;
		}
		
		System.out.println("Room with room number "+roomNum+" does not exist.");
		return null;
	}
	
	/**
	 * Returns the image of the room, as it's image location and room number
	 * @param roomNum
	 * @param facility
	 * @return
	 */
	public String getImageByRoomNum(String roomNum, String facility){
		
		String typeID = getRoomByNumber(roomNum, facility).typeID;
		
		//Connection C = connect(dbUser, dbPass);
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
	
	/**
	 * Gets image by roomType
	 * @param typeID
	 * @return
	 */
	public String getImageByRoomType(String typeID){
		
		RoomType[] R =  getAllRoomTypes();
		
		for(int i = 0; i < R.length; i++)
			if(R[i].typeID.equals(typeID))
				return R[i].image;
		
		return null;
	}

	/**
	 * Gets all room types
	 * @return
	 */
	public RoomType[] getAllRoomTypes(){
		
			if(C == null){
				System.out.println("Connection unsuccessful.");
			}
			else

				try{
					Statement myStmt = C.createStatement();
					ResultSet RS;

					String getRooms  = "SELECT * FROM roomtype";	
					RS = myStmt.executeQuery(getRooms);

					String info[] = {"typeID","name","capacity", "image", "accessible", "description"};
					RS.last();
					
					RoomType[] roomtypes = new RoomType[RS.getRow()];
					RS.beforeFirst();
					int x = 0;
					while(RS.next()){
						RoomType R = new RoomType(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]),RS.getString(info[3]),RS.getString(info[4]),RS.getString(info[5]));
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
	
	/**
	 * Gets roomtype info by the room number and facility
	 * @param roomNum
	 * @param facility
	 * @return
	 */
	public RoomType getRoomTypeInfoByRoomNum(String roomNum, String facility){
		
		RoomType[] RT = getAllRoomTypes();
		Room R = getRoomByNumber(roomNum, facility);
		
		for(int i=0; i<RT.length; i++)
			if(R.typeID.equals(RT[i].typeID)){
				return RT[i];
			}
		
		return null;
		
	}
	/**
	 * Gives a list of all rooms of a specific facility
	 * @param facility
	 * @return
	 */
	public Room[] getRoomsByFacility(String facility){
		
		Room[] allRooms = getAllRooms();
	
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
	
	/**
	 * Gets a list of all available rooms by facility
	 * @param facility
	 * @return
	 */
	public Room[] getAvailRoomsByFacility(String facility){
		
		Room[] allRooms = getRoomsByFacility(facility);
		Room[] tempHold = new Room[100];
		
		int count = 0;
		for(int i= 0; i<allRooms.length;i++){
			if(!allRooms[i].isOccupied()){
				tempHold[count] = allRooms[i];
				count++;
			}
		}

		Room[] availRooms = new Room[count];
		
		for(int i= 0; i<count;i++){
			availRooms[i] = tempHold[i];
		}

		return availRooms;
	}

	/**
	 * Returns all facilities
	 * @return
	 */
		public Facility[] getLowerYearFacilities(){
			
			if(C == null){
				System.out.println("Connection unsuccessful.");
			}
			else

				try{
					Statement myStmt = C.createStatement();
					ResultSet RS;

					String getRooms  = "SELECT * FROM facilities WHERE type = 'Residence'";	
					RS = myStmt.executeQuery(getRooms);

					String info[] = {"name","type","image"};
					RS.last();
					Facility[] facs = new Facility[RS.getRow()];
					RS.beforeFirst();
					int x = 0;
					while(RS.next()){
						Facility R = new Facility(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]));
						facs[x] = R;
						x++;

					}
					return facs;

				}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
			
		}
	
	/**
	 * Returns all facilities
	 * @return
	 */
	public Facility[] getAllFacilities(){
		
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getRooms  = "SELECT * FROM facilities";	
				RS = myStmt.executeQuery(getRooms);

				String info[] = {"name","type","image"};
				RS.last();
				Facility[] facs = new Facility[RS.getRow()];
				RS.beforeFirst();
				int x = 0;
				while(RS.next()){
					Facility R = new Facility(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]));
					facs[x] = R;
					x++;

				}
				return facs;

			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * Makes a new bulletin
	 * @param title
	 * @param date
	 * @param message
	 * @return
	 */
	public String postBulletin(String title, String date, String message){
		
		String newtitle = "";		
		String[] parts = title.split("");
		for(int i=0;i<parts.length;i++){
			if(parts[i].equals("'"))
				parts[i] = "''";
			newtitle = newtitle+parts[i];
		}
		title= newtitle;
		
		newtitle = "";		
		parts = date.split("");
		for(int i=0;i<parts.length;i++){
			if(parts[i].equals("'"))
				parts[i] = "''";
			newtitle = newtitle+parts[i];
		}
		date = newtitle;
		
		newtitle = "";		
		parts = message.split("");
		for(int i=0;i<parts.length;i++){
			if(parts[i].equals("'"))
				parts[i] = "''";
			newtitle = newtitle+parts[i];
		}
		message = newtitle;
		
		title = "'"+title+"'";
		date = "'"+date+"'";
		message = "'"+message+"'";
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String sql = "insert into bulletin (title, date, text)" + " values ("+title+","+ date+","+ message+")";
				myStmt.executeUpdate(sql);

				}

		catch(Exception e){
			e.printStackTrace();
			return "Something went wrong!";
		}
		return "Bulletin posted!";
	}
	
	/**
	 * Posts a bulletin
	 * @param B
	 * @return
	 */
	public String postBulletin(Bulletin B){
		String response;
		response=postBulletin(B.title, B.date, B.text);
		return response;
	}
	
	/**
	 * Gets all bulletins
	 * @return
	 */
	public Bulletin[] getBulletins(){
		
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getRooms  = "SELECT * FROM bulletin";	
				RS = myStmt.executeQuery(getRooms);

				String info[] = {"title","date","text"};
				RS.last();
				Bulletin[] buls = new Bulletin[RS.getRow()];
				RS.beforeFirst();
				int x = 0;
				while(RS.next()){
					Bulletin B = new Bulletin(RS.getString(info[0]),RS.getString(info[1]), RS.getString(info[2]));
					buls[x] = B;
					x++;
				}
				return buls;

			}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
}
