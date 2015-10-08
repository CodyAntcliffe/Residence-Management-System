package Controls;

import Types.*;
import UserInterface.*;

/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args){
	
		User currUser = Register.createNewAccount("Gregd", "Ginnd");
		LogIn.checkLogin("Gregd", "Ginnd");
		
		Applicant currentApplicant = Register.applyForResidence(currUser);

	
	}
}
