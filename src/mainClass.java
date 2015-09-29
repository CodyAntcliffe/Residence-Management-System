//Main class.
//Use this for testing!
//Andrew is putting his name back in...
public class mainClass {

	
	public static void main(String[] args){
		User U = new User();
		U.register("Cody", "Password");
		
		U = new Resident(U.userName, U.passWord);
		
		U.verifyLogin("Cody", "Password");
		
	}
}
