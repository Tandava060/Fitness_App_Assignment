import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class App {
	static User currentUser;

    public static void main(String[] args) {
    	
    	new dbConnection();
        new LoginScreen();
//        new ViewWeekly();
    }

	public static void createSession(User user) {
		currentUser = user;
	}
	
	public static void terminateSession() {
		currentUser = null;
	}
}