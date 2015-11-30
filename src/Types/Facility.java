package Types;

import Controls.*;

/* Each facility is a collection of rooms */

public class Facility {
	Driver D = new Driver(); //For accessing db info
	
	public Room[] rooms; //The collection of rooms associated with the facility
	public String name; //Name of the facility
	public String type; //Apartment, residence hall, town-house etc.
	public String image; //Location of image on disk
	
	public Facility(String name, String type, String image){

		this.name = name;
		this.type = type;
		this.image = image;
		
		//Grabs all of the rooms
		rooms = D.getRoomsByFacility(this.name);
		
	}
	
	/**
	 * Prints info for testing purposes
	 */
	public void printFacility(){
		System.out.println("Name: "+this.name);
		System.out.println("Type: "+this.type);
		System.out.println("Image: "+this.image);
	}
	public String toString() {
		return name;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
