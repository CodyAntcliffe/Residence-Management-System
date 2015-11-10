package ManagedBeans;
import Controls.Driver;
import Types.Room;
import Controls.StudentDriver;
import javax.faces.bean.ManagedProperty;
import java.util.Arrays;
import javax.annotation.PostConstruct;

public class RoomPageBean{
	//List for populating drop down menu
	private Room[] roomList = new Room[18];
	private String roomSelected;
	private StudentDriver studentDriver = new StudentDriver();
	private Driver driver = new Driver();
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
		
		if (userType.equals("applicant") || userType.equals("resident")) {
			//**TODO** Fill list with AVAILABLE ROOMS
			roomList = driver.getAvailRooms();
		}
		if (userType.equals("manager")) {
			//**TODO** Call the function to fill list with ALL ROOMS
			//test
			roomList = driver.getAllRooms();
		}
	}
	
	public String applyForRoom() {
		System.out.println(userType +" makes request to apply for " + roomSelected);
		studentDriver.requestRoom(roomSelected, userName);
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
}
