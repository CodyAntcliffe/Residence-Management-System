package Types;
import Controls.*;

public class Manager extends User {

	public Manager(String UN, String PW){

		super(UN, PW);
		this.accountType = "Manager";
	}
	
	public static void acceptApplication(User U){

		Driver D = new Driver();
	}

}