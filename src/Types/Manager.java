package Types;

public class Manager extends User {

	public Manager(String UN, String PW){
		
		super(UN, PW);
		this.accountType = "Manager";
	}
	
	//Check all requests
	public void checkRequests(){
		
	}
	
	//Change the resident room number
	public void assignRoom(Resident R, int room){
		
		R.roomNum = room;
	}
	
	//Checks the info of a resident
	public void getResidentInfo(Resident R){
		
		System.out.println("Resident Name: "+R.name);
		System.out.println("Room Number: "+R.roomNum);
	}
	
	//Searches and returns info of resident by room number
	public void getRoomInfo(int roomNum){
		
		//check if room is empty
		
	}
	public static void main(String[] args){
		
	}
}
