package ManagedBeans;
/**
 * This class handles all the functionality found on the Applications web page
 * It uses functions found in the ManagerDriver class
 */

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import Types.Student;
import Controls.Driver;
import Controls.ManagerDriver;

public class ApplicationsPageBean {
	
	/**
	 * Variables used by ApplicationsPageBean
	 */
	private String applicationSelected;
	private Student[] appliedStudents;
	private String[] studentName;
	private Student acceptedStudent;
	private ManagerDriver managerDriver = new ManagerDriver();
	
	/**
	 * Creates a new instance of EmailBean
	 */
	@ManagedProperty(value="#{emailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	/**
	 * call ManagerDriver method to return list of students who have applied
	 */
	@PostConstruct
	public void init(){
		System.out.println("ApplicationsPage PostConstructor init called");
		appliedStudents = managerDriver.getAllRoomRequests();
	}

	/**
	 * Change student's account type to resident
	 * Now is assigned to specific room
	 * uses driver method to change just its user type
	 */
	public void acceptApplication(){
		System.out.println("Accepted application of selected student");
		for (int i =0; i< appliedStudents.length; i++) {
			if (appliedStudents[i].getName().equals(applicationSelected))
				acceptedStudent = appliedStudents[i];
		}
		emailBeanInstance.sendEmail(acceptedStudent.getEmail(), "Application for " + acceptedStudent.getFacility() + " Room: " + acceptedStudent.getRoomNum() + " ACCEPTED", "Were are pleased to inform you your application was accepted by the manager. Your move in data will be forwarded to you when it is available.");
		managerDriver.assignRoomToStudent(acceptedStudent.getFacility(), acceptedStudent.getRoomNum(), acceptedStudent.getUserName());
		appliedStudents = managerDriver.getAllRoomRequests();
	}

	/**
	 * Rejects an application submitted by a student by removing the selected room
	 * We can use the request room method, as all it does is set the student's room as null
	 */
	public void rejectApplication(){
		System.out.println("Rejected application of selected student");
		for (int i =0; i< appliedStudents.length; i++) {
			if (appliedStudents[i].getName().equals(applicationSelected))
				acceptedStudent = appliedStudents[i];
		}
		emailBeanInstance.sendEmail(acceptedStudent.getEmail(), "Application for " + acceptedStudent.getFacility() + " Room: " + acceptedStudent.getRoomNum() + " REJECTED", "Were are sorry to inform you your application was rejected by the manager. Feel free to make another application whenever you please.");
		managerDriver.rejectRoomRequest(acceptedStudent.getUserName());
		appliedStudents = managerDriver.getAllRoomRequests();
	}

	/**
	 * get method for applicationSelected
	 * @return
	 */
	public String getApplicationSelected(){
		return applicationSelected;
	}

	/**
	 * set method for applicationSelected
	 * @param applicationSelected
	 */
	public void setApplicationSelected(final String applicationSelected){
		this.applicationSelected = applicationSelected;
	}

	/**
	 * get method for appliedStudents
	 * @return
	 */
	public Student[] getAppliedStudents(){
		return appliedStudents;
	}

	/**
	 * set method for appliedStudents
	 * @param appliedStudents
	 */
	public void setAppliedStudents(final Student[] appliedStudents){
		this.appliedStudents = appliedStudents;
	}
	
	/**
	 * set method for studentName
	 * @param studentName
	 */
	public void setStudentName (final String[] studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * get method for studentName
	 * @return
	 */
	public String[] getStudentName () {
		studentName = new String[appliedStudents.length];
		for (int i = 0; i< appliedStudents.length; i++) {
			studentName[i] = appliedStudents[i].name;
		}
		return studentName;
	}
}
