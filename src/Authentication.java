import java.sql.Connection;
import java.sql.*;

public class Authentication {
	
	private Connection con;
	private Statement s;
	private String name;
	private String password;
	static ResultSet res;
	

	public Authentication() {
		try {
			this.con = new dbConnection().myConnection;
			s = this.con.createStatement();
		} catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		
	}
	
	public boolean login(String name, String password) {
		try {
			res = s.executeQuery("SELECT * FROM USER WHERE NAME ='" + name + "' AND PASSWORD = '" + password + "'");
			
			
			if (res != null) {
				while(res.next()) {
					System.out.println(res.getString(1));
				}
					return true;
				}
			
			return false;
			}
		 catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		return false;
	}

	public boolean register(String name, String password, int height, int age, String gender, int weight ) {
		try {
			int count = s.executeUpdate("INSERT INTO USER(NAME, PASSWORD, HEIGHT, AGE, GENDER, WEIGHT) VALUES ('" + name + "', '" + password + "', '" + height + "', '" + age + "', '" + gender + "', '" + weight + "')");
			System.out.println(count);
			if ( count > 0) {
				return true;
			} else {
				return false;
			}
			}
		 catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		return false;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
