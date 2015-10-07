import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*THIS IS THE MAIN CLASS */

//PLEASE USE THIS FOR TESTING IN ORDER TO KEEP OTHER CLASS FILES CLEAN!

public class mainClass {
	
	public static void main(String[] args) throws SQLException{
		
		//Currently testing database functions
		User test = new User();
		test.register("Randy", "password");
		Driver D = new Driver();
//		D.addToDB(test);
//		test.accountType = "dfsdfhaksdfj";
//		D.addToDB(test);
		Applicant A = new Applicant(test.userName, test.passWord, "Cody", 43, 33, 3);
		//D.addUser(test);
		//D.addApplicant(A);
		
		//D.updateAccountType(A.userName, A.accountType);
		
		LogIn.checkLogin("Cody", "password");
		//D.updateAccountType(A.userName, A.accountType);

	
	}
}
