import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	public User login(String name, String password, JPanel panel) {
		try {
			res = s.executeQuery("SELECT * FROM USER WHERE NAME ='" + name + "' AND PASSWORD = '" + password + "'");
			
			
			if (res.next() != false) {
				System.out.println(res.getInt(1));
				return new User(res.getString("ID"), res.getString("NAME"), res.getInt("HEIGHT"), res.getInt("AGE"), res.getString("GENDER"), res.getInt("ID"));
//				return true;
			} else {
				res = s.executeQuery("SELECT * FROM USER WHERE NAME ='" + name  + "'");
				if (res.next() != false) {
					JOptionPane.showMessageDialog(panel, "Wrong password entered!");
				} else {
					JOptionPane.showMessageDialog(panel, "Invalid credidentials!");
				}
				
				return null;
			}
			
			}
		 catch(Exception err) {
			System.out.println("ERROR: " + err);
		}
		return null;
	}

	public boolean register(String name, String password, int height, int age, String gender, int weight, JPanel panel ) {
		try {
			res = s.executeQuery("SELECT * FROM USER WHERE NAME ='" + name + "'");
			if (res.next() != false) {
				JOptionPane.showMessageDialog(panel, "Account with name already exists!");
				return false;
			}
			int count = s.executeUpdate("INSERT INTO USER(NAME, PASSWORD, HEIGHT, AGE, GENDER, WEIGHT) VALUES ('" + name + "', '" + password + "', '" + height + "', '" + age + "', '" + gender + "', '" + weight + "')", s.RETURN_GENERATED_KEYS);
			if ( count > 0) {
		        if (s.getGeneratedKeys().next()) {
		        	System.out.println(s.getGeneratedKeys().getInt(1));
		        }
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
