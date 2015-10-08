package Controls;
import Types.*;

/*
 * Contains methods relating to:
 * Register as a new user
 * Applying for residence
 */
public class Register {

	//Called when a new user decides to create a user account for first log in.
	//UN and PW will taken from the relevant text field
	public static User createNewAccount(String UN, String PW){
		
		User U = new User(UN, PW);
		Driver D = new Driver();
		D.addToDB(U); //Adds the User to the Database
		
		return U;
	}
	
	/********NEED TO MAKE THESE PULL FROM THE INFO ENTERED INTO THE GUI********/ 
	//Called when a user decides to register for residence
	//UN and PW will taken from the relevant text field
	public static Applicant applyForResidence(User U){
		
		//These will be the values pulled from the GUI
		String name;
		int SN, age, year;
		 
		Applicant A = new Applicant(U.userName, LogIn.decryptPassword(U.passWord), "Chris Fake", 0473673, 24, 4);//Testing
		
		Driver D = new Driver();
		D.addToDB(A);
		return A;
	}
		
}
	
