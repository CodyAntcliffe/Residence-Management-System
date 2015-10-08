package Types;
/* Each facility is a collection of rooms */
public class Facility {

	Room[] rooms;
	int numberOfRooms = 40; //40 rooms per facility
	int occupiedRooms; //Keeps track of how many rooms are occupied
	
	public Facility(){
		
		//Create the rooms, assign room number to each
		rooms = new Room[40];
		occupiedRooms = 0;
		for(int i=0; i<this.numberOfRooms; i++){
			rooms[i] = new Room(i);
		}
	}
	
	//returns an array of available rooms
	public int[] availRooms(){
		
		int[] roomsAvailable = new int[numberOfRooms-occupiedRooms];
		int x = 0;//simple index for keeping track where we are in the int array
		
		for(int i=0; i<numberOfRooms; i++){
			if(!rooms[i].isOccupied()){
				roomsAvailable[x] = i;
				x++;
			}
		}
		return roomsAvailable;
	}
	
	
}
