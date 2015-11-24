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
	
	public String roomTypeInfo() {
		String niceName;
		if (name.equals("bartleySingle"))
			niceName = "Basic Single";
		else
			niceName = "Single";
		return "Capacity:  " + capacity + "<br>" +
				"Accessible:  " + accessible + "<br>" +
				"Name:  " + niceName + "<br>";
	}
	
	
	
	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAccessible() {
		return accessible;
	}

	public void setAccessible(String accessible) {
		this.accessible = accessible;
	}
}
