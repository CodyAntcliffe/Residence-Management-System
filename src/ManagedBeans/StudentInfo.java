package ManagedBeans;
/**
 * This class manipulates the information of a student
 */
import Types.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import Controls.ManagerDriver;
import Controls.StudentDriver;

public class StudentInfo {
	/**
	 * variables used by StudentInfo
	 */
	private Student myInfo;
	private StudentDriver SD = new StudentDriver();
	private ManagerDriver MD = new ManagerDriver();
	private String userType;
	
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	@ManagedProperty (value="{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	/**
	 * 	Gets the user's information from DB and changes local values so it can be displayed
	 */
	@PostConstruct
	public void init() {
		
		myInfo = SD.getStudentInfo(userName);
		if (myInfo.getRoomNum() == null) {
			myInfo.setRoomNum("No room assigned/requested");
		}
		if (myInfo.getFacility() == null) {
			myInfo.setFacility("N/A");
		}
		userType = SD.getAccountTypeByUserName(userName);
	}
	
	/**
	 * Leaves residence or cancels application depending on the status of the student's usertype
	 * Sends an email confirmation based to their email address
	 */
	public void leaveRoom() {
		String previousRoom = myInfo.getRoomNum();
		String previousFacility = myInfo.getFacility();
		
		//cancel application or leave room
		if (userType.equals("resident"))
			MD.removeStudentFromRoom(userName);
		else
			MD.rejectRoomRequest(userName);
		
		//changes local variables for displaying on the page once the room has been left
		myInfo = SD.getStudentInfo(userName);
		if (myInfo.getRoomNum() == null) {
			myInfo.setRoomNum("No room assigned/requested");
		}
		if (myInfo.getFacility() == null) {
			myInfo.setFacility("N/A");
		}
		
		//Update the user's account type
		userType = SD.getAccountTypeByUserName(userName);
		emailBeanInstance.sendEmail(myInfo.email, "Vacating from " + previousFacility + " Room " + previousRoom, "You will have 3 days to vacate your room.");
	}
	
	//Getters and setters
	public void setUserName (final String username) {
		this.userName = username;
	}
	public String getUserName() {
		return userName;
	}
	public Student getMyInfo() {
		return myInfo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setMyInfo(Student myInfo) {
		this.myInfo = myInfo;
	}
}
