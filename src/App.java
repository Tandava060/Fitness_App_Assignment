
public class App {
	static User currentUser;

    public static void main(String[] args) {

    	new dbConnection();
    	new FileConnection();
        new LoginScreen();
    }

	public static void createSession(User user) {
		currentUser = user;
	}
	
	public static void terminateSession() {
		currentUser = null;
	}
}