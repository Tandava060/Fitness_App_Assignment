
public class App {
	static User currentUser;

    public static void main(String[] args) {

    	new dbConnection();
        new LoginScreen();
//        new ViewWeekly();
//    	dbConnection.createWeightLiftingExercise("name", 12,12,12);
    }

	public static void createSession(User user) {
		currentUser = user;
	}
	
	public static void terminateSession() {
		currentUser = null;
	}
}