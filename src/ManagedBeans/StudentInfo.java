package ManagedBeans;

import Types.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import Controls.ManagerDriver;
import Controls.StudentDriver;

public class StudentInfo {
	Student myInfo;
	StudentDriver SD = new StudentDriver();
	ManagerDriver MD = new ManagerDriver();
	
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	@ManagedProperty (value="{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	private String userType;
	
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
	
	public void leaveRoom() {
		String previousRoom = myInfo.getRoomNum();
		String previousFacility = myInfo.getFacility();
		
		
		if (userType.equals("resident"))
			MD.removeStudentFromRoom(userName);
		else
			MD.rejectRoomRequest(userName);
		myInfo = SD.getStudentInfo(userName);
		if (myInfo.getRoomNum() == null) {
			myInfo.setRoomNum("No room assigned/requested");
		}
		if (myInfo.getFacility() == null) {
			myInfo.setFacility("N/A");
		}
		userType = SD.getAccountTypeByUserName(userName);
		emailBeanInstance.sendEmail(myInfo.email, "Vacating from " + previousFacility + " Room " + previousRoom, "You will have 3 days to vacate your room.");
	}
	
	
	
	
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
