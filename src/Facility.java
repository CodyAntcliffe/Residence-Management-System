/* Each facility is a collection of rooms */
public class Facility {

	Room[] rooms;
	int numberOfRooms = 40; //40 rooms per facility
	
	public Facility(){
		
		for(int i=0; i<this.numberOfRooms; i++){
			this.rooms[i] = new Room(i, this);
		}
		
	}
}
