import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class dbConnection {
	static Statement stmt;
	
	dbConnection(){
		try{
			String url = "jdbc:mysql://130.61.174.76:3306/Fitness_App";
			String username = "remote";
			String password = "remote_access";
			Connection myConnection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
			
			stmt = myConnection.createStatement();
		} catch(Exception e){
			System.out.println("Cannot connect the database!");
			System.out.println(e.getMessage());
		} 
	}
	
//	WeightLifting
	public static WeightLiftingData[] getWeightLiftingValues(String from, String to) {
		try {
			String sql = "SELECT * From WEIGHTLIFTING, USER, EXERCISES WHERE USER.ID=" + App.currentUser.getId() + " AND EXERCISES.USER_ID=USER.ID AND EXERCISES.EXERCISE_TYPE='weight' AND EXERCISES.WEIGHTLIFTING_ID = WEIGHTLIFTING.ID AND (EXERCISES.DATE BETWEEN '" + from + "' AND '" + to + "')";
			ResultSet rs = stmt.executeQuery(sql);
			int rowCount = 0;
			if (rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
			WeightLiftingData[] arr = new WeightLiftingData[rowCount];
			while(rs.next()) {
				arr[rs.getRow()-1] = new WeightLiftingData(rs.getDate("DATE"), rs.getString("NAME"), rs.getFloat("WEIGHT"), rs.getInt("SETS"), rs.getInt("REPS"));
			}
			return arr;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("getWeightLiftingValues error!");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
//	CardioData
	public static CardioData[] getCardioValues(String from, String to) {
		try {
			String sql = "SELECT * From CARDIO, USER, EXERCISES WHERE USER.ID=" + App.currentUser.getId() + " AND EXERCISES.USER_ID=USER.ID AND EXERCISES.EXERCISE_TYPE='cardio' AND EXERCISES.CARDIO_ID = CARDIO.ID AND (EXERCISES.DATE BETWEEN '" + from + "' AND '" + to + "')";
			ResultSet rs = stmt.executeQuery(sql);
			int rowCount = 0;
			if (rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
			CardioData[] arr = new CardioData[rowCount];
			while(rs.next()) {
				arr[rs.getRow() - 1] = new CardioData(rs.getDate("DATE"), rs.getString("NAME"), rs.getString("DISTANCE"), rs.getString("TIME"), rs.getString("CALORIES"));
			}
			return arr;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("getCardioValues error!");
			System.out.println(e.getMessage());
			return null;
		}
	}
	
//	insertQueries
	public static int insertValues(String sql) {
		try {
			System.out.println("insertValues sql: " + sql);
			return stmt.executeUpdate(sql);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("insertValues error!");
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public static User login(String name, String password, JPanel panel) {
		try {
			ResultSet res = stmt.executeQuery("SELECT * FROM USER WHERE NAME ='" + name + "' AND PASSWORD = '" + password + "'");
			
			if (res.next()) {
				System.out.println(res.getInt(1));
				return new User(res.getInt("ID"), res.getString("NAME"), res.getInt("AGE"), res.getInt("WEIGHT"), res.getInt("HEIGHT"), res.getString("GENDER"));
//				return true;
			} else {
				res = stmt.executeQuery("SELECT * FROM USER WHERE NAME ='" + name  + "'");
				if (res.next()) {
					JOptionPane.showMessageDialog(panel, "Wrong password entered!");
				} else {
					JOptionPane.showMessageDialog(panel, "Invalid credentials!");
				}
				return null;
			}
		}catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		return null;
	}
	
	public static boolean register(String name, String password, int height, int age, String gender, int weight, JPanel panel ) {
		try {
			ResultSet res = stmt.executeQuery("SELECT * FROM USER WHERE NAME ='" + name + "'");
			if (res.next()) {
				JOptionPane.showMessageDialog(panel, "Account with name already exists!");
				return false;
			}
			int count = stmt.executeUpdate("INSERT INTO USER(NAME, PASSWORD, HEIGHT, AGE, GENDER, WEIGHT) VALUES ('" + name + "', '" + password + "', '" + height + "', '" + age + "', '" + gender + "', '" + weight + "')", stmt.RETURN_GENERATED_KEYS);
			if ( count > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
		        if (rs.next()) {
		        	System.out.println(rs.getInt(1));
		        }
		        
				return true;
			} else {
				return false;
			}
		} catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		return false;
	}
	
	//after user implementation
	public static boolean createWeightLiftingExercise(String name, int set, int reps, int weight ) {
		try {
			String sql = "INSERT INTO WEIGHTLIFTING VALUES (0, '" + name + "', " + set + " , " + reps + " , " + weight + ")";
			int count = stmt.executeUpdate(sql, stmt.RETURN_GENERATED_KEYS);
			if ( count > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
		        if (rs.next()) {
		        	int id  = rs.getInt(1);
		        	count = insertValues("INSERT INTO EXERCISES(USER_ID, WEIGHTLIFTING_ID, EXERCISE_TYPE) VALUES(" + App.currentUser.getId() + ", " + id + ", 'weight')");

					return count > 0;
		        }
			}
			return false;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("createWeightLiftingExercise error!");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean createCardioExercise(String name, int distance, int time, int calories ) {
		try {
			String sql = "INSERT INTO CARDIO VALUES (0, '" + name + "', " + distance + " , " + time + " , " + calories + ")";
			int count = stmt.executeUpdate(sql, stmt.RETURN_GENERATED_KEYS);
			if ( count > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
		        if (rs.next()) {
		        	int id  = rs.getInt(1);
		        	
		        	count = insertValues("INSERT INTO EXERCISES(USER_ID, CARDIO_ID, EXERCISE_TYPE) VALUES(" + App.currentUser.getId() + ", " + id + ", 'cardio')");

					return count > 0;
		        }
			}
			return false;
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("createCardioExercise error!");
			System.out.println(e.getMessage());
			return false;
		}
	}
}
