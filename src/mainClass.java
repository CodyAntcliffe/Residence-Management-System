//Main class.
//Use this for testing!
//andrew
public class mainClass {

	
	public static void main(String[] args){
		User U = new User();
		U.register("Cody", "Password");
		
		U = new Resident(U.userName, U.passWord);
		
		U.verifyLogin("Cody", "Password");
		
	}
}
