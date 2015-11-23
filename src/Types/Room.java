package Types;

import Controls.Driver;

/* Holds info for the room */
public class Room {

	public String roomNum;
	
	//Specific rooms can have up to four occupants.
	public String occupant1; //Which Resident is currently in the room
	public String occupant2;
	public String occupant3;
	public String occupant4;
	
	public String typeID; //Corresponds to a row in the roomtype table
	public String facility;// Which facility the room is located in
	
	Driver D = new Driver();
	
	private String roomType;//single, basic single or double
	//private String facility;//name of the facility (these should be unique in the DB)
	private boolean accessible;
	private String description;//short paragraph describing the room

	public Room(String facility, String rmNum, String typeID, String occ1, String occ2, String occ3, String occ4){
		this.roomNum = rmNum;
		this.occupant1 = occ1;
		this.occupant2 = occ2;
		this.occupant3 = occ3;
		this.occupant4 = occ4;
		this.typeID = typeID;
		this.facility = facility;
	}
	
	//Prints the room info for testing purposes
	public void printRoom(){
		System.out.println("Room num: "+this.roomNum);
		System.out.println("Occupant(s): "+this.occupant1+this.occupant2+this.occupant3+this.occupant4);
		System.out.println("TypeID: "+this.typeID);
		System.out.println("Facility: "+this.facility);
	}

	//Gets the number of occupants currently in the room
	public String countOccupants(){
		
		int count = 0;
		if(occupant1!=null)
			count++;
		if(occupant2!=null)
			count++;
		if(occupant3!=null)
			count++;
		if(occupant4!=null)
			count++;
		
		return String.valueOf(count);
	}
	
	//Check if room is currently occupied
	public Boolean isOccupied(){
		
		RoomType RT = D.getRoomTypeInfoByRoomNum(this.roomNum, this.facility);

		if(this.countOccupants().equals(RT.capacity)){
			return true;
		}
		else
			return false;
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
