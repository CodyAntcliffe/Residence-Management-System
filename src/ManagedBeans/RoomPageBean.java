package ManagedBeans;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;


import javax.annotation.PostConstruct;
public class RoomPageBean{
	//List for populating drop down menu
	private List<String> roomNames;
	private String roomSelected;
	
	/*Injects an instance variable from another bean class
	 * Scope must be broader than this class
	 * MANAGED PROPERTY TAG MUST BE ADDED MANUALLY TO FACES-CONFIG.XML
	 */
	@ManagedProperty(value="#{logIn.userType}")
	private String userType;
	
	//PostConstruct is called immediately after constructor, before page view is generated
	//This allows List to be ready before page so it can show values, must return void, take no arguments
	@PostConstruct
	public void init() {

		System.out.println("RoomPage Postconstruct init called.");
		System.out.println("Usertype is: " + userType + ".");
		
		if (userType.equals("Student")) {
			//**TODO** Fill list with AVAILABLE ROOMS
			roomNames = new ArrayList<String>();
			roomNames.add(0, "Test 1");
			roomNames.add(1, "Test 2");
			roomNames.add(2, "Test 3");
			roomNames.add(3, "Test 4");
			roomNames.add(4, "Test 5");
			roomNames.add(5, "Test 6");
		}
		if (userType.equals("Manager")) {
			//**TODO** Call the function to fill list with ALL ROOMS
			//test
			roomNames = new ArrayList<String>();
			roomNames.add(0, "Test 1");
			roomNames.add(1, "Test 2");
			roomNames.add(2, "Test 3");
			roomNames.add(3, "Test 4");
			roomNames.add(4, "Test 5");
			roomNames.add(5, "Test 6");
		}
	}
	
	public String applyForRoom() {
		//**TODO** make an Application object with roomID of roomSelected, save it to database
		System.out.println(userType +" makes request to apply for " + roomSelected);
		return "";
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	public void setRoomNames (final List<String> roomNames){
		this.roomNames = roomNames;
	}
	public List<String> getRoomNames() {
		return roomNames;
	}	
	public void setRoomSelected (final String roomSelected){
		this.roomSelected = roomSelected;
	}
	public String getRoomSelected() {
		return roomSelected;
	}
}
