package Types;
/* Holds info for the room */
public class Room {

	public String roomNum;
	public String occupant;//Which Resident is currently in the room
	private String roomType;//single, basic single or double
	private String facility;//name of the facility (these should be unique in the DB)
	private boolean accessible;
	private String description;//short paragraph describing the room

	public Room(String rmNum){

		this.roomNum = rmNum;
		this.occupant = null; // Set to null since it hasn't been assigned 	
	}
	public Room(int rmNum) {
		this.roomNum = Integer.toString(rmNum);
		this.occupant = null;
	}

	public Room(String rmNum, String name){
		this.roomNum = rmNum;
		this.occupant = name;
		this.setRoomType("Room Type");
		this.setFacility("Facility Name");
		this.setAccessible(false);
		this.setDescription("Short description of the room to display on the page<br>Breaks should be used so they fit on the page<br>");
	}

	//Check if room is currently occupied
	public Boolean isOccupied(){

		if(this.occupant == null){
			return false;
		}
		else
			return true;
	}
	public Boolean notNull() {
		if (this.roomNum == null) {
			return false;
		}else
			return true;
	}
	
	public String roomInfo() {
		return "Facility: " + facility + "<br>" +
				"Room Number: " + roomNum + "<br>" +
				"Room Type: " + roomType + "<br>" +
				"Description: " + description + "<br>";
	}

	public String toString() {
		return roomNum;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getOccupant() {
		return occupant;
	}
	public void setOccupant(String occupant) {
		this.occupant = occupant;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
