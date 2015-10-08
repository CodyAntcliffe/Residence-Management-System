package Types;

public class Applicant extends User {
	
	public String name; //Identifier Variable
	public int studentNum; //Identifier Variable
	public int age;
	public int yearLevel;
	
	public Applicant(String UN, String PW, String aName, int SN, int uAge, int yrLvl){
		
		super(UN, PW);
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
