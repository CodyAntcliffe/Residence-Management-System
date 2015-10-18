package ManagedBeans;
public class WelcomePageBean {
	/*
	 * controls flow of welcome.xhtml page
	 * commandButton invokes either toLogin or toRegister
	 * String is returned from function, which controls whether page redirects 
	 * Check WEB-INF/faces-config.xml, go to navigation rules, click the 
	 * link between welcome and login/register
	 * See that "on outcome" and "on action" control page flow
	 * */
	public String toLogin() {
		System.out.println("User go to login");
		return "testing";
	}
	public String toRegister(){
		System.out.println("User go to register");
		return "success";
	}
}
