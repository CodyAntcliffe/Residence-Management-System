package Types;

public class Resident extends User {

	public String name; //Identifier Variable
	public Facility residence; //Which facility they occupy
	public int roomNum;// Identifier Variable
	public int studentNum; //Identifier Variable
	public int age;
	public int yearLevel;

	
	public Resident(String UN, String PW, String fullName, int SN, int Age, int year){
		
		super(UN, PW);
		this.name = fullName;
		this.accountType = "resident";
		this.studentNum = SN;
		this.age = Age;
		this.yearLevel = year;
		
		//this.residence;
		this.roomNum = 1;
	}
	
	//Changes room number.
	public void changeRoom(int newRoom){
	
	}
	
	
	public void cancelResidency(){
		
		//Drop row from Resident table
		//Drop from User table and re-add as new User to begin fresh
	}
}
