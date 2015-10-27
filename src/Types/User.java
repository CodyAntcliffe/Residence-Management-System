package Types;
import Controls.LogIn;
/*User class will encompass the resident, applicant, and manager types.

In order to eliminate the issue of changing an object's type, we will use only one general user type.
We only need to modify the accountType variable, which will redirect the user accordingly to the 
right page
*/

public class User {
	
	public String userName; // Can't change once registered, only accessible by log-in methods
	public String passWord; // Can't change once registered, only accessible by log-in methods
	public String accountType; //account type of the user
	
	
	//added all attributes of resident object, as we are only using one type for the user object
	
	
	public String name; //Identifier Variable
	public Facility residence; //Which facility they occupy
	public int roomNum;// Identifier Variable
	public int studentNum; //Identifier Variable
	public int age;
	public int yearLevel;

	
	public User(String UN, String PW, String aT, String fullName, int SN, int Age, int year){
		
		super(UN, PW);
		this.userName = UN;
		this.passWord = LogIn.encryptPassword(PW);
		
		//account type: upon registration, type is set to "applicant". Upon being accepted as resident, account type is switched to "resident"
		//this string determines which UI to be redirected to
		this.accountType = aT;
		
		
		this.name = fullName;
		this.studentNum = SN;
		this.age = Age;
		this.yearLevel = year;
		
		//this.residence;
		this.roomNum = 1;
	}
	
	//Changes room number.
	public void changeRoom(int newRoom){
	
	}
	
	
	public void cancelResidency(){
		
		//Drop row from Resident table
		//Drop from User table and re-add as new User to begin fresh
	}
	
	
	

}
