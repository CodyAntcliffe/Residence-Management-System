package Types;

//Each room has a type, which by itself has specific attributes
public class RoomType {

	public String typeID; //From the db
	public String name; //Name of the room type
	public String capacity; //How many people can be in a specific room
	public String image; //Location of the image
	public String accessible; //Whether the room is accessible
	
	public RoomType(String t, String n, String c, String i, String a){
		this.typeID = t;
		this.name = n;
		this.capacity = c;
		this.image = i;
		this.accessible = a;
	}
	
	public void printRoomType(){
		System.out.println("TypeID: "+typeID);
		
	}
}
