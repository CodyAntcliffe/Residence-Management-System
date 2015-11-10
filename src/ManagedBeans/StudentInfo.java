package ManagedBeans;

import Types.Student;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import Controls.StudentDriver;

public class StudentInfo {
	Student myInfo;
	StudentDriver SD = new StudentDriver();
	@ManagedProperty(value="#{logIn.userName}")
	private String userName;
	
	@PostConstruct
	public void init() {
		myInfo = SD.getStudentInfo(userName);
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
}
