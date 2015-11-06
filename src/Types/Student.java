package Types;

public class Student extends User {

	public Student(String UN, String PW, String AT, String N, String E, String P, String M, String Y, String SN) {
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
	}
	
	public Facility residence; //Which facility they occupy
	public int roomNum;// Identifier Variable
	public String userName;
    public String password;
    public String confirmPassword;
    public String name;
    public String email;
    public String phone;
    public String major;
    public String yearLevel;
    public String studentNumber;
    
    
}

