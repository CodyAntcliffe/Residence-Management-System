package Types;

public class Student extends User {

	public Facility residence; //Which facility they occupy
	public String roomNum;// Identifier Variable
	public String userName;
    public String password;
    public String confirmPassword;
    public String name;
    public String email;
    public String phone;
    public String major;
    public String yearLevel;
    public String studentNumber;
    public String age;
    
	public Student(String UN, String PW, String AT, String N, String E, String P, String M, String Y, String SN, String RN, String AGE ) {
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
	}
	
	public void printStudent(){
		System.out.println("User: "+userName);
		System.out.println("PW:"+password);
		System.out.println("AccountType:"+accountType);
		System.out.println("name:"+name);
		System.out.println("email:"+email);
		System.out.println("phone:"+phone);
		System.out.println("major:"+major);
		System.out.println("yearLevel:"+yearLevel);
		System.out.println("studentNum:"+studentNumber);
		System.out.println("roomNum:"+roomNum);
		System.out.println("age:"+age);
	}
	
    
}

