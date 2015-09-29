
public class Resident extends User {

	String name; //Identifier Variable
	int roomNum;// Identifier Variable
	int studentNum; //Identifier Variable
	int age;
	int yearLevel;
	String major;
	
	public Resident(String UN, String PW){
		
		this.userName = UN;
		this.passWord = PW;
		this.accountType = "Resident";
	}
	
	public static void main(String[] args){
	}
	
}
