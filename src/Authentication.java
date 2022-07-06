//import java.sql.Connection;
//
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import java.sql.*;
//
//public class Authentication {
//	
//	private Connection con;
//	private Statement s;
//	private String name;
//	private String password;
//	static ResultSet res;
//	
//
//	public Authentication() {
//		try {
//			this.con = new dbConnection().myConnection;
//			s = this.con.createStatement();
//		} catch(Exception err) {
//			
//			System.out.println("ERRORasdasd: " + err);
//		}
//		
//	}
//	
//	
//
//	
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//}
