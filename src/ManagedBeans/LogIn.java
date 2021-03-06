package ManagedBeans;
/**
 * this classes houses all our log-in related methods
 */

import Controls.Driver;

public class LogIn {

	/**
	 * This bean is sessionScoped, private variables have life-span of single user session
	 * UserName can be used to uniquely identify rows in database that are relevant to the user
	 */
	Driver D = new Driver();
	private String userName;
	private String password;
	private String userType;
	private String result;
	
	
	//get and set methods
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setUserName(final String userName) {
		this.userName=userName;
	}
	public void setPassword(final String password) {
		this.password=password;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword(){
		return password;
	}
	public String getUserType() {
		//System.out.println("Trying to get userType from LoginBean");
		return userType;
	}
	public void setUserType(final String userType){
		this.userType = userType;
	}
	
	/**
	 * Checks if log-in id is correct
	 * @return String
	 */
	public String tryLogin() {
		System.out.println(userName + " attempting login.");
		if (Driver.checkLogin(userName, password)) {
			System.out.println(userName + " logged in.");

			userType = D.getAccountTypeByUserName(userName);
			return "toHome";
		}
		result = "Invalid Username and/or Password";
		return null;
	}
	
	/**
	 * Logs user out of current session
	 * @return String
	 */
	public String logOut() {
		System.out.println("Logging " + userName + " out of system.");
		userName=null;
		userType=null;
		password=null;
		result=null;
		return "toLogin";
	}

	/**
	 * Passwords should never be stored plain-text. We will use simple shift cipher as an example.
	 * Returns the password in cipher text for storing.
	 * @param plainPW
	 * @return String
	 */
	public static String encryptPassword(String plainPW){ 
		//Encrypt...
		char[] plainArray = plainPW.toCharArray();
		for(int i=0; i < plainPW.length(); i++){
			plainArray[i]++;
		}
		String cipherPW = new String(plainArray);

		return cipherPW;
	}

	/**
	 * Returns the password in plainText
	 * @param cipherPW
	 * @return
	 */
	public static String decryptPassword(String cipherPW){
		//Decrypt...
		char[] cipherArray = cipherPW.toCharArray();
		for(int i=0; i < cipherPW.length(); i++){
			cipherArray[i]--;
		}
		String plainPW = new String(cipherArray);

		return plainPW;
	}
}
