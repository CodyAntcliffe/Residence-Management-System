package Types;
/* Holds info for the room */
public class Room {
	
	int number;
	Resident occupant; //Which Resident is currently in the room
	
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
	
	//Assign a resident to the room
	public void assign(Resident R){
		
		if(this.isOccupied()){
			System.out.println("Room is already in use.");
		}
		else
			this.occupant = R;
	}


}
