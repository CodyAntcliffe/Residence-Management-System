/* Holds info for the room */
public class Room {
	
int number;
Facility fac;// Which facility it belongs to
Resident occupant; //Which Resident is currently in the room

public Room(int rmNum, Facility F){
	
	this.number = rmNum;
	this.fac = F;
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
