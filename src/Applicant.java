
public class Applicant extends User {
	
	String name; //Identifier Variable
	int studentNum; //Identifier Variable
	int age;
	int yearLevel;
	
	public Applicant(String UN, String PW, String aName, int SN, int uAge, int yrLvl){
		
		this.userName = UN;
		this.passWord = PW;
		this.name = aName;
		this.accountType = "applicant";
		this.studentNum = SN;
		this.age = uAge;
		this.yearLevel = yrLvl;
	}
	
	public static void printUser(User U){
		
		if(U.accountType == "Applicant"){
			printApplicant((Applicant) U);
		}
	}
	
	public static void printApplicant(Applicant A){
		
		System.out.println(A.yearLevel);
	}
	//Returns all available(empty) rooms.
	public int[] getRooms(Facility fac){
		
		return fac.availRooms();
	}
	
	public static void main(String[] args){
		
	}
	
}
