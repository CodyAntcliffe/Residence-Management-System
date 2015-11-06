package Types;
/* Holds info for the room */
public class Room {
	
	int number;
	Student occupant; //Which Resident is currently in the room
	
	public Room(int rmNum){
		
		this.number = rmNum;
		this.occupant = null; // Set to null since it hasn't been assigned 
		
	}
	
	//Check if room is currently occupied
	public Boolean isOccupied(){
		
		if(this.occupant == null){
			return false;
		}
		else
			return true;
	}
	


}
