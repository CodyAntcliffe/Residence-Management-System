package ManagedBeans;
import Controls.ManagerDriver;
import Types.Facility;
import Types.Room;
import Types.RoomType;
import Controls.StudentDriver;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;

public class RoomPageBean{
	
	private Room[] roomList;
	private Facility[] facilityList;
	private RoomType[] roomTypeList;
	
	//holds the value user selects from the page
	private String roomSelected;
	private String facilitySelected;
	
	//contains information related to user's selection
	private Room resultRoom;
	private RoomType resultRoomType;
	
	//Holds whatever value needs to be shown to the user as feedback for their actions
	private String serverResponse;
	
	//Driver instances
	private StudentDriver studentDriver = new StudentDriver();
	private ManagerDriver managerDriver = new ManagerDriver();
	
	//Body text for the room request sent email
	private String requestEmailText = "Your request has been submitted. Give the manager a few days to review your request. You will recieve a new email letting you know if you were accepted or rejected.";
	
	/*/
	 * Usertype and username are pulled from the logIn session bean which holds session info
	 * Both are used for conditional rendering of html elements and querying the database
	 */
	@ManagedProperty(value="#{logIn.userType}")
	private String userType;
	
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	//Creates an instance of emailBean so an email can be sent to the user when they apply for a room
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	//Fills the list of facilities before the page is rendered
	//Facility names are used in a drop down menu for the page's search function
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
	
	/*
	 * User's selection from the page can only be sent back as a string, so string must be used
	 * to find the room and roomType in the arrays
	 */
	public void getSearchResults(String roomNumber) {
		roomSelected=roomNumber;
		for (int i=0; i < roomList.length; i++) {
			if (roomList[i].roomNum.equals(roomSelected))
				setResultRoom(roomList[i]);
		}
		resultRoomType = studentDriver.getRoomTypeInfoByRoomNum(resultRoom.roomNum, resultRoom.facility);
	}
	
	//Calls on the driver to make a row in the database. Sends an email to the user if successful
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
