package Types;
/* Holds info for the room */
public class Room {

	public String roomNum;
	public String occupant; //Which Resident is currently in the room

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

	public String toString() {
		return roomNum;
	}
}
