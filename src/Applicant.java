
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
	
	public static void main(String[] args){
		
	}
	
}
