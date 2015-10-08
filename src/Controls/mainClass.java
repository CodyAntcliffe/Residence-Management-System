package Controls;
import Types.*;
import UserInterface.*;
import Controls.*;
/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){
	
		User currUser = Register.createNewAccount("User", "User");
		LogIn.checkLogin("Gregdd", "Ginnd");
		
		Applicant currentApplicant = Register.applyForResidence(currUser);
		Driver D = new Driver();
		Resident currentResident = Register.acceptApplicant(currentApplicant);
		
	
	}
}
