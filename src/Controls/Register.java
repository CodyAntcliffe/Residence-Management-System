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
	private String studentNumber;
	private String age;

	StudentDriver SD = new StudentDriver();

	public String getUserName ()
	{
		return userName;
	}
	public void setUserName (final String userName)
	{
		this.userName = userName;
	}
	public String getAge ()
	{
		return age;
	}
	public void setAge (final String age)
	{
		this.age = age;
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
		this.phone=phone;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(final String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getYearLevel() {
		return yearLevel;
	}
	public void setYearLevel(final String yearLevel) {
		this.yearLevel=yearLevel;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(final String major) {
		this.major=major;
	}

	//Managed Bean end
	/*Methods can also be invoked from client.
	 *This method is invoked by WebContent/register.xhtml
	 *Command button calls it, returns the users info to the console as a proof of concept
	 *Data input can now be manipulated within the class, inserted into database
	 */

	public String validateRegistration() {
		//for successful registration, the following are required:
		//1 - username does not already exist - handled in driver
		//3 - password and confirmPassword match

		//function parses string and returns relevant info for page redirect
		if (password != null) {
			SD.addRegistration(this);
			return "registered";
		}
		else{
			return "notRegistered";
		}
	}
}

