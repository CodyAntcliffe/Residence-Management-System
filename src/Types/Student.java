package Types;
/**
 * Holds all the information of a user
 */
public class Student extends User {

	public Facility residence; 		//Which facility they occupy
	public String roomNum;			//Identifier Variable
	public String facility;			//Name of the facility
	public String userName;			//The user's username
	public String password;			//The user's password
	public String accountType;		//The account type of a user
	public String confirmPassword;	//For the confirmation of a password when registering
	public String name;				//User's name
	public String email;			//User's email
	public String phone;			//User's phone number 
	public String major;			//User's major
	public String yearLevel;		//USer's year level
	public String studentNumber;	//User's student number
	public String age;				//User's age

	//get and set methods for facility
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	/**
	 * Student constructor
	 * @param UN
	 * @param PW
	 * @param AT
	 * @param N
	 * @param E
	 * @param P
	 * @param M
	 * @param Y
	 * @param SN
	 * @param RN
	 * @param AGE
	 * @param FAC
	 */
	public Student(String UN, String PW, String AT, String N, String E, String P, String M, String Y, String SN, String RN, String AGE, String FAC ) {
		super(UN, PW);
		this.userName = UN;
		this.password = PW;
		this.accountType = AT ;
		this.name = N;
		this.email = E;
		this.phone = P;
		this.major = M;
		this.yearLevel = Y;
		this.studentNumber = SN;
		this.roomNum = RN;
		this.age = AGE;
		this.facility = FAC;
	}
	
	/**
	 * Clones a student to make an identical copy
	 * @param clone
	 */
	public Student(Student clone) {
		super(clone.userName, clone.passWord);
		this.userName = clone.userName;
		this.password = clone.password;
		this.accountType = clone.accountType;
		this.name = clone.name;
		this.email = clone.email;
		this.phone = clone.phone;
		this.major = clone.major;
		this.yearLevel = clone.yearLevel;
		this.studentNumber = clone.studentNumber;
		this.roomNum = clone.roomNum;
		this.age = clone.age;
	}

	/**
	 * Prints all of the student info.  For testing purposes.
	 */
	public void printStudent(){
		System.out.println("User: "+userName);
		System.out.println("PW:"+password);
		System.out.println("AccountType:"+accountType);
		System.out.println("name:"+name);
		System.out.println("age:"+age);
		System.out.println("email:"+email);
		System.out.println("phone:"+phone);
		System.out.println("major:"+major);
		System.out.println("yearLevel:"+yearLevel);
		System.out.println("studentNum:"+studentNumber);
		System.out.println("roomNum:"+roomNum);
		System.out.println("facility:"+facility);
		
	}

	public String toString(){
		return
				"Account status: " + accountType + "<br>" +
				"Username: " + userName + "<br>" +
				"Name: " + name + "<br>" +
				"Email: " + email + "<br>" +
				"Phone: " + phone + "<br>" +
				"Major: " + major + "<br>" +
				"Year Level: " + yearLevel + "<br>" +
				"Student Number: " + studentNumber + "<br>" +
				"Room Number: " + roomNum + "<br>" +
				"Age: " + age;
	}
	
	//get and set methods
	public Facility getResidence() {
		return residence;
	}
	public void setResidence(Facility residence) {
		this.residence = residence;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getYearLevel() {
		return yearLevel;
	}
	public void setYearLevel(String yearLevel) {
		this.yearLevel = yearLevel;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}

