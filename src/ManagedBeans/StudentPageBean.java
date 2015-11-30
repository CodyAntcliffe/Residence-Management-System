package ManagedBeans;
/**
 * This class handles all the functionality found on the Student web page
 * The class uses functions found in the ManagerDriver class
 */
import Types.Student;

import javax.faces.bean.ManagedProperty;

import Controls.ManagerDriver;

public class StudentPageBean {
	/**
	 * Variables used by StudentPageBean
	 */
	private Student[] studentList;
	private Student resultStudent;
	private String searchTerm="Search";
	private String searchCriteria;
	private String serverResponse;
	private ManagerDriver managerDriver = new ManagerDriver();
	//TODO the first name in the DB is being added to the list twice.
	
	/**
	 * creates a new instance of EmailBean
	 */
	@ManagedProperty(value="#{EmailBean}")
	EmailBean emailBeanInstance = new EmailBean();
	
	/**
	 * searches for students depending on what type of student they are
	 * and returns the requested type
	 * @return
	 */
	public String searchStudents() {
		resultStudent=null;
		if(searchCriteria.equals("Residents")) {
			studentList = managerDriver.getResidents();
		}else if(searchCriteria.equals("Applicants")) {
			studentList = managerDriver.getApplicants();
		}else if(searchCriteria.equals("All")) {
			studentList = managerDriver.getAllStudents();
		}
		return "";
	}
	
	/**
	 * evicts a student from a room and sends an email notification to that student
	 */
	public void evictStudent() {
		emailBeanInstance.sendEmail(resultStudent.getEmail(), "You are being evicted.", "You are being evicted from " + resultStudent.getFacility() + " Room: " + resultStudent.getRoomNum());
		managerDriver.removeStudentFromRoom(resultStudent.getUserName());
		setServerResponse("Student Evicted!");
	}
	
	/**
	 * call function to get students by their username
	 * @param userName
	 * @return
	 */
	public String getSearchResult(String userName) {
		for (int i=0; i< studentList.length; i++) {
			if (studentList[i].getUserName().equals(userName)){
				setResultStudent(studentList[i]);
			}
		}
		return "";
	}
	
	/**
	 * get method for studentList
	 * @return
	 */
	public Student[] getStudentList(){
		return studentList;
	}
	
	/**
	 * set method for studentList
	 * @param studentList
	 */
	public void setStudentList(final Student[] studentList){
		this.studentList = studentList;
	}

	/**
	 * get method for resultStudent
	 * @return
	 */
	public Student getResultStudent() {
		return resultStudent;
	}

	/**
	 * set method for resultStudent
	 * @param resultStudent
	 */
	public void setResultStudent(Student resultStudent) {
		this.resultStudent = resultStudent;
	}

	/**
	 * get method for searchTerm
	 * @return
	 */
	public String getSearchTerm() {
		return searchTerm;
	}

	/**
	 * set method for searchTerm
	 * @param searchTerm
	 */
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	/**
	 * get method for searchCriteria
	 * @return
	 */
	public String getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * set method for searchCriteria
	 * @param searchCriteria
	 */
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	/**
	 * get method for serverResponse
	 * @return
	 */
	public String getServerResponse() {
		return serverResponse;
	}
	
	/**
	 * set method for serverResponse
	 * @param serverResponse
	 */
	public void setServerResponse(String serverResponse) {
		this.serverResponse = serverResponse;
	}
}
