//Super class for Manager, Applicant and Resident
//Test Line 3
public class User {
	private String userName; // Can't change once registered, only accessible by log-in methods
	private String passWord; // Can't change once registered, only accessible by log-in methods
	
	String accountType;
	
	void register(){
		this.userName = "cody";
		this.passWord = "p";
	}
	
	public static void main(String[] args){
		
		User U = new Manager();
		U.register();
		System.out.println(U.userName);
	}
}
