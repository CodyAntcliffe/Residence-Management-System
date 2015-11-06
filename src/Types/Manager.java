package Types;
import Controls.*;

public class Manager extends User {

	public Manager(String UN, String PW){
		
		super(UN, PW);
		this.accountType = "Manager";
	}
	
	//Check all requests
	public void checkRequests(){
		
	}
	

	
	public static void acceptApplication(User U){
		
		Driver D = new Driver();
		//D.addToDB(R);
	}
	

	
	//Searches and returns info of resident by room number
	public void getRoomInfo(int roomNum){
		
		//check if room is empty
		
	}
	
}