import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class FileConnection {
	
	public boolean addWeight(WeightLiftingData data) {
		String name = "";
		String user = "";
		String type = "weight";
		
		int set = 0;
		int reps = 0;
		int weight = 0;
		try {
		
			Formatter outfile = new Formatter("newFile.txt");
			outfile.format("name:%s user:%s type:%s set:%o reps:%o weight:%0",name, user, type, set, reps, weight);
			outfile.close();
			return true;

		}catch(FileNotFoundException fnfe){
			System.out.println("File Not Found");
			return false;
		}
		catch(SecurityException se){
			System.out.println("No Permission");
			return false;
		}
	}
	
	public boolean addCardio(WeightLiftingData data) {
		String name = "";
		String user = "";
		String type = "weight";
		
		int distance = 0;
		int time = 0;
		int calories = 0;
		try {
		
			Formatter outfile = new Formatter("newFile.txt");
			outfile.format("name:%s user:%s type:%s distance:%o time:%o calories:%0",name, user, type, distance, time, calories);
			outfile.close();
			return true;

		}catch(FileNotFoundException fnfe){
			System.out.println("File Not Found");
			return false;
		}
		catch(SecurityException se){
			System.out.println("No Permission");
			return false;
		}
	}
	
	public String readAllValues(String str) {
    	
    	try {
    		Scanner infile = new Scanner(new File("newFile.txt"));

//    		String empid = infile.next();
//    		infile.next();
//    		float salary = infile.nextFloat();
    		System.out.println(infile.toString());
    		String name = infile.next();
    		String type = infile.next();
    		String user = infile.next();
    		
    		return name;

    		}
    		catch (FileNotFoundException fnfe){
    			System.out.println("File Not Found");
    			return null;
    		}
	}
	
	
	
	
	
}
