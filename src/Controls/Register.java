package Controls;
import Types.*;

/*
 * Contains methods relating to:
 * Register as a new user
 * Applying for residence
 */
public class Register {
	
	/*
	 * ManagedBean standardized properties to read input from HTML file
	 * Register.java class is now a 'managed bean' class
	 * HTML code on page WebContent/register.xhtml automatically calls get and set methods depending on context of call
	 * Careful formatting of the names of get and set functions is required
	 * ManagedBean start
	 */
	private String userName;
    private String password;
    private String confirmPassword;
    
    private String name;
    
    private String email;
    private String phone;
    private String major;
    private String yearLevel;
    
    
    public String getUserName ()
    {
        return userName;
    }
    
    public void setUserName (final String userName)
    {
        this.userName = userName;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (final String password)
    {
        this.password = password;
    }
    public String getConfirmPassword ()
    {
        return confirmPassword;
    }

    public void setConfirmPassword (final String confirmPassword)
    {
        this.confirmPassword = password;
    }
    
    public String getName() {
    	return name;
    }
    public void setName(final String name) {
    	this.name=name;
    }
    
    
    public String getEmail() {
    	return email;
    }
    public void setEmail(final String email) {
    	this.email=email;
    }
    
    public String getPhone() {
    	return phone;
    }
    public void setPhone(final String phone) {
    	this.email=phone;
    }
    
    
	//Managed Bean end
	
    /*Methods can also be invoked from client.
    *This method is invoked by WebContent/register.xhtml
    *Command button calls it, returns the users info to the console as a proof of concept
    *Data input can now be manipulated within the class, inserted into database
    */
	public String registerGUITest() {
		Register.createNewAccount(userName,password);
		System.out.println("User name: " + userName);
		System.out.println("Password: " + password);
		return "void";
	}
	    
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
	
	public static Resident acceptApplicant(Applicant A){
		
		Resident R = new Resident(A.userName, LogIn.decryptPassword(A.passWord), A.name, A.studentNum, A.age, A.yearLevel); //Testing
		Driver D = new Driver();
		D.addToDB(R);
		return R;
	}
	
		
}
	
