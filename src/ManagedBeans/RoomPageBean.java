package ManagedBeans;
import Controls.ManagerDriver;
import Types.Facility;
import Types.Room;
import Controls.StudentDriver;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;

public class RoomPageBean{
	//List for populating drop down menu
	private Room[] roomList;
	private String roomSelected;
	private StudentDriver studentDriver = new StudentDriver();
	private ManagerDriver managerDriver = new ManagerDriver();
	private Facility facilitySelected;
	private Room resultRoom;
	
	//Criteria for the room search
	private String roomType;//'Single', 'Double' or 'Basic Double'.
	private String livingStyle;//'Residence Hall', 'Townhouse' or 'Apartment'.
	private Boolean isAccessible;//Yes or No.
	private String noteToManager;//Input from a text field for manager to see.
	
	/*Injects an instance variable from another bean class
	 * Scope must be broader than this class
	 * MANAGED PROPERTY TAG MUST BE ADDED MANUALLY TO FACES-CONFIG.XML
	 */
	@ManagedProperty(value="#{logIn.userType}")
	private String userType;
	
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	//PostConstruct is called immediately after constructor, before page view is generated
	//This allows List to be ready before page so it can show values, must return void, take no arguments
	
	@PostConstruct
	public void init() {
		System.out.println("RoomPage Postconstruct init called.");
		System.out.println("Usertype is: " + userType + ".");
		//**TODO** get facility types from the database
		//Get facility names from the database
	}
	
	public String searchRooms() {
		//Calls function to return a list of rooms matching the criteria of user's search
		resultRoom=null;
		if (userType.equals("applicant") || userType.equals("resident")) {
			//**TODO** Change the driver implementation to return rooms based on criteria in requirements (year level, accessibility etc.)
			roomList = studentDriver.getAvailRooms().clone();
		}
		if (userType.equals("manager")) {
			//**TODO** add new driver function
			roomList = managerDriver.getAllRooms().clone();
		}
		return "";
	}
	public String getSearchResults(String roomNumber) {
		System.out.println(roomNumber);
		roomSelected=roomNumber;
		for (int i=0; i < roomList.length; i++) {
			if (roomList[i].roomNum.equals(roomSelected))
				setResultRoom(roomList[i]);
		}
		//call on driver function to get room based on roomNumber and facility
		//set the return equal to room selected
		//if successful return a string toRoomSearchResults
		return "";
	}
	public String applyForRoom() {
		System.out.println(userType + " makes request to apply for " + roomSelected);
		studentDriver.requestRoom(roomSelected, userName);//TODO Change so the primary key for the room is passed instead
		return "";
	}
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
	public Facility getFacilitySelected() {
		return facilitySelected;
	}

	public void setFacilitySelected(Facility facilitySelected) {
		this.facilitySelected = facilitySelected;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getLivingStyle() {
		return livingStyle;
	}

	public void setLivingStyle(String livingStyle) {
		this.livingStyle = livingStyle;
	}

	public Boolean getIsAccessible() {
		return isAccessible;
	}

	public void setIsAccessible(Boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	public String getNoteToManager() {
		return noteToManager;
	}

	public void setNoteToManager(String noteToManager) {
		this.noteToManager = noteToManager;
	}

	public Room getResultRoom() {
		return resultRoom;
	}

	public void setResultRoom(Room resultRoom) {
		this.resultRoom = resultRoom;
	}
}
