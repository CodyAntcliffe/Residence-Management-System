package ManagedBeans;
import Controls.ManagerDriver;
import Types.Facility;
import Types.Room;
import Types.RoomType;
import Controls.StudentDriver;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;


/**
 * Controls logic for roomPage.xhtml, calls on driver functions
 * for database access. Manipulates objects in the data model
 * so they are properly formatted for display and saving to DB.
 */
public class RoomPageBean{
	
	private Room[] roomList;
	private Facility[] facilityList;
	private RoomType[] roomTypeList;
	
	/**
	 * Holds the value user selects from the page
	 */
	private String roomSelected;
	private String facilitySelected;
	
	/**
	 * Contains information related to user's selection
	 */
	private Room resultRoom;
	private RoomType resultRoomType;
	
	/**
	 * Holds whatever value needs to be shown to the user as feedback for their actions
	 */
	private String serverResponse;
	
	/**
	 * Driver instances
	 */
	private StudentDriver studentDriver = new StudentDriver();
	private ManagerDriver managerDriver = new ManagerDriver();
	
	/**
	 * Body text for the room request sent email
	 */
	private String requestEmailText = "Your request has been submitted. Give the manager a few days to review your request. You will recieve a new email letting you know if you were accepted or rejected.";
	
	/**
	 * Usertype is pulled from the logIn session bean, used to control access to elements of page
	 */
	@ManagedProperty(value="#{logIn.userType}")
	private String userType;
	
	/**
	 * userName is the primary key for the users table in DB, so it is pulled from the
	 * logIn bean so it can be used to query the database.
	 */
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	/**
	 * An instance of emailBean used to send a confirmation email to the user
	 */
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	/** Fills the list of facilities before the page is rendered
	*   Facility names are used in a drop down menu for the page's search function
	*	@return void
	**/
	@PostConstruct
	public void init() {
		
		if (userType.equals("applicant")){
			String yearLevel= studentDriver.getStudentInfo(userName).getYearLevel();
			if (yearLevel.equals("1") || yearLevel.equals("2") )
				facilityList = studentDriver.getLowerYearFacilities();
			else
				facilityList = studentDriver.getAllFacilities();
		}else
			facilityList = studentDriver.getAllFacilities();
	}
	
	/** Fills a list of rooms based on user type and facility selected
	 *  Calls on getAvailRoomsByFacility or getRoomsByFacility from Controls
	 * @return void
	 */
	public void searchRooms() {
		//Calls function to return a list of rooms matching the criteria of user's search
		resultRoom=null;
		if (userType.equals("applicant")) {
			roomList = studentDriver.getAvailRoomsByFacility(facilitySelected);
		}
		if (userType.equals("manager")) {
			roomList = managerDriver.getRoomsByFacility(facilitySelected);
		}
	}
	
	/**
	 * User's selection from the page can only be sent back as a string, so string must be used
	 * to find the room and roomType in the object arrays.
	 * @return void
	 */
	public void getSearchResults(String roomNumber) {
		roomSelected=roomNumber;
		for (int i=0; i < roomList.length; i++) {
			if (roomList[i].roomNum.equals(roomSelected))
				setResultRoom(roomList[i]);
		}
		resultRoomType = studentDriver.getRoomTypeInfoByRoomNum(resultRoom.roomNum, resultRoom.facility);
	}
	
	/**
	 * Calls on the driver to make a row in the database. Sends an email to the user if successful
	 * @return void
	 */
	public void applyForRoom() {
		serverResponse = studentDriver.requestRoom(resultRoom.facility, resultRoom.roomNum, userName);
		if (serverResponse.equals("Request Submitted!")) {
			emailBeanInstance.sendEmail(managerDriver.getEmailByName(userName), "Application for " +resultRoom.facility + " Room: " + resultRoom.roomNum, requestEmailText);
		}
	}
	
	//Getters and setters
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	public void setRoomList (final Room[] roomList){
		this.roomList = roomList;
	}
	public Room[] getRoomList() {
		return roomList;
	}	
	public void setRoomSelected (final String roomSelected){
		System.out.println("Writing roomSelected");
		this.roomSelected = roomSelected;
	}
	public String getRoomSelected() {
		return roomSelected;
	}
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public Room getResultRoom() {
		return resultRoom;
	}
	public void setResultRoom(Room resultRoom) {
		this.resultRoom = resultRoom;
	}
	public String getStringFacility() {
		return facilitySelected;
	}

	public void setStringFacility(String stringFacility) {
		this.facilitySelected = stringFacility;
	}

	public Facility[] getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(Facility[] facilityList) {
		this.facilityList = facilityList;
	}

	public RoomType getResultRoomType() {
		return resultRoomType;
	}

	public void setResultRoomType(RoomType resultRoomType) {
		this.resultRoomType = resultRoomType;
	}

	public RoomType[] getRoomTypeList() {
		return roomTypeList;
	}

	public void setRoomTypeList(RoomType[] roomTypeList) {
		this.roomTypeList = roomTypeList;
	}

	public String getServerResponse() {
		return serverResponse;
	}

	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}
}
