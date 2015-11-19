package Controls;
import Types.*;
import Controls.*;
/*THIS IS THE MAIN CLASS */
import ManagedBeans.ApplicationsPageBean;

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){	
		
		Driver D = new Driver();
		ManagerDriver MD = new ManagerDriver();
		StudentDriver SD = new StudentDriver();
		//SD.requestRoom("Bartley", "10", "Cody");
		Student S[] = MD.getAllRoomRequests();
		S[0].printStudent();
//		Room[] RT = D.getAllRooms();
//		Room[] FR = D.getRoomsByFacility("Bartley");
//		System.out.println(RT[0].isOccupied());
	/*FRONT END
	 *
	 *-Make the bar across the top that is always there
	 *-Need feedback from actions
	 *-If login unsuccessful, write that and return to Login page.
	 *-Make more visually appealing with some CSS
	 *-Handle images through getImage
	 *-Look at facility.  Has floor plans.
	 *
	 */
	
	/*BACK END
	 * 
	 * DATABASE:
	 * -RoomTypes table.  Holds all all different room combinations.  
	 * -Rooms needs to be changed to simply hold different room types. Has correct image number, has occupant name if occupied.
	 * -Facilities.  Name of facility, type (town house, apartment), picture of floor plan.  
	 * -Images Table.  Holds image location, image number, description.
	 *
	 * DRIVERS:
	 * -getRoomType(facilityName, roomNum), returns Room
	 * -getImage(Room), returns the image location.
	 * -getFacilityInfo
	 * -getRoomInfo(Facility, roomNum)
	 */
	}
}
