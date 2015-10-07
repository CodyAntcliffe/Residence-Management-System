
public class Applicant extends User {
	
	String name; //Identifier Variable
	int studentNum; //Identifier Variable
	int age;
	int yearLevel;
	
	public Applicant(String UN, String PW){
		
		this.userName = UN;
		this.passWord = PW;
		this.accountType = "Applicant";
	}
	
	//Returns all available(empty) rooms.
	public int[] getRooms(Facility fac){
		
		return fac.availRooms();
	}
	
	public static void main(String[] args){
		
	}
	
}
