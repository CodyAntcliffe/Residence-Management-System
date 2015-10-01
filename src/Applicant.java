
public class Applicant extends User {
	
	String name; //Identifier Variable
	int studentNum; //Identifier Variable
	
	int age;
	int yearLevel;
	String major;
	
	public Applicant(String UN, String PW){
		
		this.userName = UN;
		this.passWord = PW;
		this.accountType = "Applicant";
	}
	
	//This is incomplete example code.  Will need to access our database once it is implemented.
	//Returns all available(empty) rooms.
	public int[] getRooms(){
		
		int[] availRooms = new int[99];
		return availRooms;
	}
	
	
	
	public static void main(String[] args){
		
	}
	
}
