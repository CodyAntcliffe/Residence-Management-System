package Controls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Types.*;

public class ManagerDriver extends Driver {

	Connection C;
	// Attempts to connect to our database and returns the connection if
	// successful, null if not
	public Connection connect() {
		return super.connect(dbUser, dbPass);
	}
	
	public ManagerDriver(){
		C = connect();
	}

	// Returns all Students who have requested a room, but have yet to be assigned
	public Student[] getAllRoomRequests() {

		// First get list of all applicants
		Student[] allApplicants = getApplicants();

		// Now see which ones have a room number associated with them. These are
		// the ones who have requested a room.
		int count = 0;
		for (int i = 0; i < allApplicants.length; i++) {
			if (allApplicants[i].roomNum != null)
				count++;
		}

		Student[] roomRequests = new Student[count];
		int j = 0;
		for (int i = 0; i < allApplicants.length; i++) {
			if (allApplicants[i].roomNum != null) {
				roomRequests[j] = allApplicants[i];
				j++;
			}
		}
		return roomRequests;
	}
	
	public void setRoomNull(String name){

		//Connection C = connect();
		name = "'"+name+"'";
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else
			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				//Set the roomNum in the Users table
				String sql = "update Users set roomNum= null where name= "+name;
				myStmt.executeUpdate(sql);

			}

		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Gets a specific student's info based on a roomNum
	public Student[] getStudentsFromRoom(String facility, String roomNum) {
		Student[] students = getResidents();
		
		int count = 0;
		
		Student[] temp = new Student[4];//Can have up to  people in a room
		
		//Count how many occupants are in the room
		for (int i = 0; i < students.length; i++) {
			if (students[i].roomNum.equals(roomNum)&& students[i].facility.equals(facility)){
				temp[count] = students[i];
				count++;
		}
		}
		
		Student[] roomOccs = new Student[count];
		
		for (int i = 0; i < count; i++) {
			roomOccs[i] = temp[i];
		}
		
		return roomOccs;
	}

	// created this method to simply convert the student's type to resident
	public void setApplicantToResident(String userName) {

		//Connection C = connect();
		userName = "'" + userName + "'";
		if (C == null) {
			System.out.println("Connection unsuccessful");
		} else {
			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;

				// Update accountType to Resident
				String sql = "update Users set accountType= 'resident' where userName= " + userName;
				myStmt.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	//Assigns a room to a student
	public void assignRoomToStudent(String facility, String roomNum, String userName) {

		//First check if the room is full
		if(getRoomByNumber(roomNum, facility).isOccupied()){
			System.out.println("Room is already full. ");
			return;
		}
			
		String slot = getAvailSlot(roomNum, facility);
		roomNum = "'" + roomNum + "'";
		userName = "'" + userName + "'";
		facility = "'" + facility + "'";
		
		if (C == null) {
			System.out.println("Connection unsuccessful.");
		} else
			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;
				
				// Set the roomNum in the Users table
				String sql = "update Users set roomNum= " + roomNum + ", facility= " + facility + " where userName= " + userName;
				myStmt.executeUpdate(sql);
				// Assign the student name to the room
				sql = "update rooms set "+slot+"= " + userName + " where roomNum= " + roomNum +"  AND facility= " + facility;
				myStmt.executeUpdate(sql);
				// Update accountType to Resident
				sql = "update Users set accountType= 'resident' where userName= " + userName;
				myStmt.executeUpdate(sql);
				System.out.println("Assigned roomNum to Student");
			}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//gets the first available open slot in the room.  
	//Assists the assignRoomToStudent method
	public String getAvailSlot(String roomNum, String facility){
		
		String[] possibleSlots = getAllSlotsByRoom(roomNum, facility);
		Room R = getRoomByNumber(roomNum, facility);
		
		if(R.occupant1 == null)
			return "occupant1";
		
		if(R.occupant2 == null)
			return "occupant2";
		
		if(R.occupant3 == null)
			return "occupant3";
		
		if(R.occupant4 == null)
			return "occupant4";
		
		return null;
		
	}
	
	//way to access an available occupant slot in the room db
	public String[] getAllSlotsByRoom(String roomNum, String facility){
		
		RoomType RT = getRoomTypeInfoByRoomNum(roomNum,facility);
		List<String> info = new ArrayList<String>();
		
		if(RT.capacity.equals("1"))
			info.add("occupant1");
		
		if(RT.capacity.equals("2")){
			info.add("occupant1");
			info.add("occupant2");
		}
		
		if(RT.capacity.equals("3")){
			info.add("occupant1");
			info.add("occupant2");
			info.add("occupant3");
		}
		
		if(RT.capacity.equals("4")){
			info.add("occupant1");
			info.add("occupant2");
			info.add("occupant3");
			info.add("occupant4");
		}
		
		String[] slots = new String[info.size()];
		slots = info.toArray(slots);
		
		return slots;
	}
	
	// Returns a list of all registered student's names
	public String[] getAllStudentsNames() {

		//Connection C = connect();
		String names[] = new String[99];
		// Check if connection successful
		if (C == null) {
			System.out.println("Connection unsuccessful.");
		} else

			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;

				// Check if the user exists
				String getNames = "SELECT name FROM users";
				RS = myStmt.executeQuery(getNames);

				int i = 0;
				// Add the user to the table otherwise
				while (RS.next()) {
					String n = RS.getString("name");
					if (n != null) {
						names[i] = n;
						i++;
					}
				}
				int count = 0;
				while (names[count] != null)
					count++;
				String namesNotNull[] = new String[count];
				for (int j = 0; j < count; j++)
					namesNotNull[j] = names[j];

				return namesNotNull;

			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	//returns a list of all student user names
	public String[] getAllUserNames(){
		
		Student[] S = getAllStudents();
		
		String[] userNames = new String[S.length];
		
		for(int i=0; i<S.length; i++)
			userNames[i] = S[i].userName;
		
		return userNames;
	}

	// Returns all users with accountType==resident
	public Student[] getResidents() {
		
		Student[] allStudents = getAllStudents();
		int residentCount = 0;

		for (int i = 0; i < allStudents.length; i++) {
			if (allStudents[i].accountType.equals("resident"))
				residentCount++;
		}
		Student[] residents = new Student[residentCount];
		
		int j = 0;
		for (int i = 0; i < allStudents.length; i++)
			if (allStudents[i].accountType.equals("resident")) {
				residents[j] = allStudents[i];
				j++;
			}

		return residents;
	}

	//Returns student based on userName
	public Student getStudentInfoByUserName(String userName){
		
		Student[] S = getAllStudents();
		
		for(int i =0; i<S.length; i++)
			if(S[i].userName.equals(userName))
				return S[i];
		
		return null;
	}
	
	
	// Returns instance of Student by studentName
	public Student getStudentInfo(String studentName) {

		//Connection C = connect();
		studentName = "'" + studentName + "'";
		// System.out.println(studentName);
		// Check if connection successful
		if (C == null) {
			System.out.println("Connection unsuccessful.");
		} else

			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getNames = "SELECT * FROM users where name = " + studentName;
				RS = myStmt.executeQuery(getNames);
				String info[] = {"userName","passWord","accountType","name","email","phone","major","yearLevel","studentNumber","roomNum","age", "facility"};
				while (RS.next()) {
					Student S = new Student(RS.getString(info[0]), RS.getString(info[1]), RS.getString(info[2]),
							RS.getString(info[3]), RS.getString(info[4]), RS.getString(info[5]), RS.getString(info[6]),
							RS.getString(info[7]), RS.getString(info[8]), RS.getString(info[9]),
							RS.getString(info[10]),RS.getString(info[11]));
					return S;
				}

				return null;

			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;

	}

	// returns all applicants
	public Student[] getApplicants() {

		String[] studentNames = getAllStudentsNames();
		int applicantCount = 0;

		for (int i = 0; i < studentNames.length; i++) {
			if (getStudentInfo(studentNames[i]).accountType.toString().equals("applicant"))
				applicantCount++;
		}
		Student[] applicants = new Student[applicantCount];
		int j = 0;
		for (int i = 0; i < studentNames.length; i++)
			if (getStudentInfo(studentNames[i]).accountType.toString().equals("applicant")) {
				applicants[j] = getStudentInfo(studentNames[i]);
				j++;
			}
		return applicants;
	}

	//Returns all students
	public Student[] getAllStudents() {
		String[] studentNames = getAllStudentsNames();

		Student[] students = new Student[studentNames.length];

		int j = 0;
		for (int i = 0; i < studentNames.length; i++) {
			students[j] = getStudentInfo(studentNames[i]);
			j++;
		}

		return students;

	}
	//Creates a new room 
	public void createRoom(String facility, String roomNum, String typeID){
		
		facility = "'"+facility+"'";
		roomNum = "'"+roomNum+"'";
		typeID = "'"+typeID+"'";
		
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String sql = "insert into rooms (facility, roomNum, typeID)" + " values ("+facility+","+ roomNum+","+typeID+")";
				myStmt.executeUpdate(sql);
				}

		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Creates a whole new facility
	public String createFacility(String name, String facilityType, String roomTypeID){
		
		name = "'"+name+"'";
		facilityType = "'"+facilityType+"'";
		roomTypeID = "'"+roomTypeID+"'";
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String sql = "insert into facilities (name, type)" + " values ("+name+","+ facilityType+")";
				myStmt.executeUpdate(sql);
				
				for(int i=10; i<30; i++){
					String roomNum = String.valueOf(i);
					roomNum = "'"+roomNum+"'";
					sql = "insert into rooms (facility, roomNum, typeID)" + " values ("+name+","+ roomNum+","+roomTypeID+")";
					myStmt.executeUpdate(sql);
				}
				
			}

		catch(Exception e){
			e.printStackTrace();
		}
		return "Facility" + name + " created!";
	}
	
	//Deletes a facility
	public String removeFacility(String facilityName){
		
		//Set all students back to applicant that are in that facility
		Student[] residents = getResidents();
		Student[] applicants = getApplicants();
		
		for(int i = 0; i< residents.length; i++){
			if(residents[i].facility!=null)
				if(residents[i].facility.equals(facilityName)){
					removeStudentFromRoom(residents[i].userName);
				}
		}
		for(int i = 0; i<applicants.length; i++)
			if(applicants[i].facility!=null)
				if(applicants[i].facility.equals(facilityName))
					rejectRoomRequest(applicants[i].userName);
		
		//Remove all rooms that are part of facility
		for(int i = 10; i<30; i++){
			removeRoom(String.valueOf(i), facilityName);
		}
		
		//Need to remove facility from facilities table
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;
				facilityName = "'"+facilityName+"'";
				String sql = "delete from facilities where name= "+facilityName;
				myStmt.executeUpdate(sql);
				}

		catch(Exception e){
			e.printStackTrace();
			return "";
		}
		return "Facility" + facilityName + " removed!";
	}
	
	//Removes room from facility
	public void removeRoom(String roomNum, String facility){
		
		facility = "'"+facility+"'";
		roomNum = "'"+roomNum+"'";
		
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String sql = "delete from rooms where roomNum= "+roomNum+" AND facility ="+facility;
				myStmt.executeUpdate(sql);
				}

		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Removes a student from a room and sets them back to applicant
	public void removeStudentFromRoom(String userName){
		
		Student[] allResidents = getResidents();
		
		for(int i = 0; i<allResidents.length; i++){
			if(allResidents[i].userName.equals(userName)){
				if(C == null){
					System.out.println("Connection unsuccessful.");
				}
				else
					try{
						
						
						Student S = getStudentInfoByUserName(userName);
						Room R = getRoomByNumber(S.roomNum, S.facility);
						String UN = userName;
						userName = "'"+userName+"'";
						
						Statement myStmt = C.createStatement();
						ResultSet RS;
						//Make changes in users table
						String sql = "update Users set roomNum= NULL, facility = NULL, accountType = 'applicant' where userName= "+userName;
						myStmt.executeUpdate(sql);
						
						
						//Make changes in Rooms table
						String space = "";
						
						if(R.occupant1.equals(S.getName()))
							space = "occupant1";
						else
						if(R.occupant2.equals(S.getName()))
							space = "occupant2";
						else
						if(R.occupant3.equals(S.getName()))
							space = "occupant3";
						else
						if(R.occupant4.equals(S.getName()))
							space = "occupant4";
						System.out.println(space);
						sql = "update rooms set "+space+"= NULL where "+space+"= "+"'" +S.getName() + "'";
						myStmt.executeUpdate(sql);
					}

				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	//Rejects a room request by userName
	public void rejectRoomRequest(String userName){
		
		Student[] roomRequests = getAllRoomRequests();
		
		for(int i = 0; i<roomRequests.length; i++){
			if(roomRequests[i].userName.equals(userName)){
				if(C == null){
					System.out.println("Connection unsuccessful.");
				}
				else
					try{
						userName = "'"+userName+"'";
						Statement myStmt = C.createStatement();
						ResultSet RS;

						//Set the roomNum in the Users table
						String sql = "update Users set roomNum= NULL, facility = NULL where userName= "+userName;
						myStmt.executeUpdate(sql);
					}

				catch(Exception e){
					e.printStackTrace();
				}
				
				return;
			}
		}
				
	}
	
	//Gets a list of all student email addresses
	public String[] getAllEmail(){
		
		Student[] allStudents = getAllStudents();
		
		//Need all student email addresses
		String[] studentEmailAddresses = new String[allStudents.length];
		
		
		for(int i = 0; i<allStudents.length;i++){
			studentEmailAddresses[i] = allStudents[i].email;
		}
		
		return studentEmailAddresses;
	}
	
	//Returns a specific email address
	public String getEmailByName(String userName){
		
		Student S = getStudentInfo(userName);
		
		return S.email;
		
	}
	
	//Removes a bulletin
	public void removeBulletin(String title){
		
		title = "'"+title+"'";
		
		if(C == null){
			System.out.println("Connection unsuccessful.");
		}
		else

			try{
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String sql = "delete from bulletin where title= "+title;
				myStmt.executeUpdate(sql);
				}

		catch(Exception e){
			e.printStackTrace();
		}
	}
}
