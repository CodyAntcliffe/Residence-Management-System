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

	//Constructor
	public ManagerDriver(){
		C = connect();
	}

	/**
	 * Returns all Students who have requested a room, but have yet to be assigned
	 * @return
	 */
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
				System.out.println(roomRequests[j]);
				j++;
			}
		}
		return roomRequests;
	}

	
	/**
	 * Sets user's room and facility to null.
	 * Used when a manager rejects a room request.
	 * @param name
	 */
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

	/**
	 * Gets a list of all students in a specific room
	 * @param facility
	 * @param roomNum
	 * @return
	 */
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
	/**
	 * Sets a user's accounttype to 'Resident"
	 * @param userName
	 */
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

	/**
	 * Assigns a room to a student
	 * @param facility
	 * @param roomNum
	 * @param userName
	 */
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

	/**
	 * Gets the first available open slot in the room. 
	 * Assists the assignRoomToStudent method 
	 * @param roomNum
	 * @param facility
	 * @return
	 */
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

	/**
	 * Returns, as an array of strings, the list of possible slots in a room.
	 * ie// A double-occupant room has two slots.
	 * @param roomNum
	 * @param facility
	 * @return
	 */
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

	/**
	 * Returns a list of all registered student's names
	 * @return
	 */
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

	/**
	 * Returns a list of all student user names.
	 * @return
	 */
	public String[] getAllUserNames(){

		String userNames[] = new String[99];
		// Check if connection successful
		if (C == null) {
			System.out.println("Connection unsuccessful.");
		} else

			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;

				// Check if the user exists
				String getNames = "SELECT userName FROM users";
				RS = myStmt.executeQuery(getNames);

				int i = 0;
				// Add the user to the table otherwise
				while (RS.next()) {
					String n = RS.getString("userName");
					if (!n.equals("manager")) {
						userNames[i] = n;
						i++;
					}
				}
				int count = 0;
				while (userNames[count] != null)
					count++;
				String namesNotNull[] = new String[count];
				for (int j = 0; j < count; j++)
					namesNotNull[j] = userNames[j];

				return namesNotNull;

			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * Returns all users with accountType==resident
	 * @return
	 */
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

	/**
	 * Returns instance of Student by studentName
	 * @param studentName
	 * @return
	 */
	public Student getStudentInfo(String studentName) {

		studentName = "'" + studentName + "'";
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

	/**
	 * Returns studentInfo by userName
	 * @param userName
	 * @return
	 */
	public Student getStudentInfoByUserName(String userName){

		//Connection C = connect();
		userName = "'" + userName + "'";
		// Check if connection successful
		if (C == null) {
			System.out.println("Connection unsuccessful.");
		} else

			try {
				Statement myStmt = C.createStatement();
				ResultSet RS;

				String getNames = "SELECT * FROM users where userName = " + userName;
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
	
	/**
	 * Returns all applicants
	 * @return
	 */
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

	/**
	 * Returns all students
	 * @return
	 */
	public Student[] getAllStudents() {
		
		String[] studentNames = getAllUserNames();
		
		Student[] students = new Student[studentNames.length];

		int j = 0;
		for (int i = 0; i < studentNames.length; i++) {
			students[j] = getStudentInfoByUserName(studentNames[i]);
			j++;
		}

		return students;

	}
	
	/**
	 * Creates a new room 
	 * @param facility
	 * @param roomNum
	 * @param typeID
	 */
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

	/**
	 * Creates a whole new facility
	 * @param name
	 * @param facilityType
	 * @param roomTypeID
	 * @return
	 */
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
		return "Facility " + name + " created!";
	}

	/**
	 * Deletes a facility
	 * @param facilityName
	 * @return
	 */
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
		return "Facility " + facilityName + " removed!";
	}

	/**
	 * Removes room from facility
	 * @param roomNum
	 * @param facility
	 */
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

	/**
	 * Removes a student from a room and sets them back to applicant
	 * @param userName
	 */
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

	/**
	 * Rejects a room request by userName
	 * @param userName
	 */
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

	/**
	 * Gets a list of all student email addresses
	 * @return
	 */
	public String[] getAllEmail(){

		Student[] allStudents = getAllStudents();

		//Need all student email addresses
		String[] studentEmailAddresses = new String[allStudents.length];


		for(int i = 0; i<allStudents.length;i++){
			studentEmailAddresses[i] = allStudents[i].email;
		}

		return studentEmailAddresses;
	}

	/**
	 * Returns a specific email address
	 * @param userName
	 * @return
	 */
	public String getEmailByName(String userName){

		Student S = getStudentInfoByUserName(userName);

		return S.email;

	}

	/**
	 * Removes a bulletin
	 * @param title
	 */
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
	/**
	 * Returns all students who are residents in a specific facility
	 * @param facilityName
	 * @return
	 */
	public Student[] getResidentsByFacility(String facilityName) {
		Student[] residentList = getResidents();
		Student tempHold[] = new Student[residentList.length];
		
		//Find all residents in the facility given
		int count=0;
		for (int i = 0; i< residentList.length; i++) {
			if (residentList[i].getFacility().equals(facilityName)){
				tempHold[count] = residentList[i];
				count++;
				System.out.println(count);
			}
		}
		
		//Make a properly sized array and fill it with residents
		Student[] facilityResidents = new Student[count];
		
		for (int i=0; i < count; i++) {
			facilityResidents[i]=tempHold[i];
		}
		return facilityResidents;
	}
}
