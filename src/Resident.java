
public class Resident extends User {

	String name; //Identifier Variable
	Facility residence; //Which facility they occupy
	int roomNum;// Identifier Variable
	int studentNum; //Identifier Variable
	int age;
	int yearLevel;

	
	public Resident(String UN, String PW){
		
		this.userName = UN;
		this.passWord = PW;
		this.accountType = "Resident";
	}
	
	public static void main(String[] args){
	}
	
	//Incomplete example code
	//Changes room number.
	public void changeRoom(int newRoom){
		
		this.roomNum = newRoom;
	}
	
	
	public void cancelResidency(){
		
		//Drop row from Resident table
		//Drop from User table and re-add as new User to begin fresh
	}
}
