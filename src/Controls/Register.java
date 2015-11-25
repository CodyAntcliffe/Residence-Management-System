package Controls;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.validator.ValidatorException;

import ManagedBeans.EmailBean;


/*
 * Contains methods relating to:
 * Register as a new user
 * Applying for residence
 */
public class Register{
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
	private String serverResponse;
	StudentDriver SD = new StudentDriver();
	
	
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	
	public String validateRegistration() {
		//function parses string and returns relevant info for page redirect
		serverResponse = SD.addRegistration(this);
		if (serverResponse.equals("Registration successful!")){
			emailBeanInstance.sendEmail(email, "Residence Managemeny System Registration", "You have registered successfully!\r\n Save this email somewhere safe so you have a copy of your personal info.\r\nUsername: " + userName + "\r\nEmail: " + email + "\r\nName: " + name+ "\r\nStudent Number: " + studentNumber);
		}
		
		System.out.println(serverResponse);
		return serverResponse;
	}
	
	//Getters and setters
	public String getServerResponse() {
		return serverResponse;
	}
	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}
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
}

