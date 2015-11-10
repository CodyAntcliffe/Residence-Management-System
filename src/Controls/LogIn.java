package Controls;
/* This class houses all of our log-in related methods*/


import javax.faces.bean.ManagedBean;
@ManagedBean(name="logIn")
public class LogIn {

	//This bean is sessionScoped, private variables have life-span of single user session
	//userName can be used to uniquely identify rows in database that are relevant to the user
	Driver D = new Driver();
	private String userName;
	private String password;
	private String userType;//need method to get accountType from DB
	public void setUserName(final String userName) {
		this.userName=userName;
	}
	public void setPassword(final String password) {
		System.out.print("Password: " + password);
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
	public String tryLogin() {
		if (Driver.checkLogin(userName, password)) {
			System.out.println(userName + " logged in.");

			userType = D.getAccountTypeByUserName(userName);
			System.out.println("Usertype for " + userName + " :" + userType);
			return "toHome";
		}
		return null;
	}

	//Passwords should never be stored plain-text. We will use simple shift cipher as an example.
	//Returns the password in cipher text for storing.
	public static String encryptPassword(String plainPW){ 
		//Encrypt...
		char[] plainArray = plainPW.toCharArray();
		for(int i=0; i < plainPW.length(); i++){
			plainArray[i]++;
		}
		String cipherPW = new String(plainArray);

		return cipherPW;
	}

	//Returns the password in plainText
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
