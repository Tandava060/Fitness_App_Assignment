import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;

public class FileConnection {
	
	public FileConnection() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(myFormatObj.format(now));
		
		 String line;
		 try {

			 File file = new File("daily.txt");

			 if (file.exists()) {
				 BufferedReader bufferReader = new BufferedReader(new FileReader("daily.txt"));
				 line = bufferReader.readLine();

				 if(line == null || !line.contains(myFormatObj.format(now))) {
					 new FileWriter("daily.txt", false).close();
					 FileWriter f = new FileWriter("daily.txt", true);
					 Formatter outfile = new Formatter(f);
					 outfile.format("%s", myFormatObj.format(now));
					 outfile.close();
				 }
				 bufferReader.close();
			 }
			 else {
				 FileWriter fileWriter = new FileWriter("daily.txt");
				 fileWriter.close();
			 }
		        
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
	}
	
	public static boolean addWeight(String name, float weight, int sets, int reps) {
		try {
		
			FileWriter f = new FileWriter("daily.txt", true);
			Formatter outfile = new Formatter(f);
			outfile.format("%nname:%s&&user:%s&&type:%s&&set:%s&&reps:%s&&weight:%f&&",name, App.currentUser.getName(), "weight", Integer.toString(sets), Integer.toString(reps), weight);
			outfile.close();
			return true;

		}catch(FileNotFoundException fnfe){
			System.out.println("File Not Found");
			return false;
		}
		catch(SecurityException se){
			System.out.println("No Permission");
			return false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addCardio(String name, int time, int distance, int calories) {
		try {
		
			FileWriter f = new FileWriter("daily.txt", true);
			Formatter outfile = new Formatter(f);
			outfile.format("%nname:%s&&user:%s&&type:%s&&time:%s&&distance:%s&&calories:%s&&",name, App.currentUser.getName(), "cardio", Integer.toString(time), Integer.toString(distance), Integer.toString(calories));
			outfile.close();
			return true;

		}catch(FileNotFoundException fnfe){
			System.out.println("File Not Found");
			return false;
		}
		catch(SecurityException se){
			System.out.println("No Permission");
			return false;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<WeightLiftingData> readWeightValues() {
		String line;
		ArrayList<WeightLiftingData> arr = new ArrayList<WeightLiftingData>();
	    try {
	        BufferedReader bufferReader = new BufferedReader(new FileReader("daily.txt"));
	        while ((line = bufferReader.readLine()) != null) {
	        	if(line.contains("user")) {
	        		
	        		String name = line.substring(line.indexOf("name") + "name".length() +1, line.indexOf("&&"));
	        	    line = line.substring(line.indexOf("&&")+1);
	        		
	        		String user = line.substring(line.indexOf("user") + "user".length() +1, line.indexOf("&&"));
	        	    line = line.substring(line.indexOf("&&")+1);
	        	    
	        	    if(App.currentUser.getName().contentEquals(user)){
		        	    String type = line.substring(line.indexOf("type") + "type".length() +1, line.indexOf("&&"));
		        	    line = line.substring(line.indexOf("&&")+1);
		        	    
		        	    if(type.contentEquals("weight")) {
		        	    	//set, reps, weight
		        	    	String set = line.substring(line.indexOf("set") + "set".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    String reps = line.substring(line.indexOf("reps") + "reps".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    String weight = line.substring(line.indexOf("weight") + "weight".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    arr.add(new WeightLiftingData(name, Float.parseFloat(weight), Integer.parseInt(set), Integer.parseInt(reps)));
		        	    }
		        	    
		        	    if(type.equals("cardio")) {
		        	    	
		        	    	
		        	    }
	        	    }
	        	}
	        }
	        bufferReader.close();
	        return arr;

	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public static ArrayList<CardioData> readCardioValues() {
		String line;
		ArrayList<CardioData> arr = new ArrayList<CardioData>();
	    try {
	        BufferedReader bufferReader = new BufferedReader(new FileReader("daily.txt"));
	        while ((line = bufferReader.readLine()) != null) {
	        	if(line.contains("user")) {
	        		
	        		String name = line.substring(line.indexOf("name") + "name".length() +1, line.indexOf("&&"));
	        	    line = line.substring(line.indexOf("&&")+1);
	        		
	        		String user = line.substring(line.indexOf("user") + "user".length() +1, line.indexOf("&&"));
	        	    line = line.substring(line.indexOf("&&")+1);
	        	    
	        	    if(App.currentUser.getName().contentEquals(user)){
		        	    String type = line.substring(line.indexOf("type") + "type".length() +1, line.indexOf("&&"));
		        	    line = line.substring(line.indexOf("&&")+1);
		        	    
		        	    if(type.contentEquals("cardio")) {
		        	    	//time, dist, calories
		        	    	String time = line.substring(line.indexOf("time") + "time".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    String distance = line.substring(line.indexOf("distance") + "distance".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    String calories = line.substring(line.indexOf("calories") + "calories".length() +1, line.indexOf("&&"));
			        	    line = line.substring(line.indexOf("&&")+1);
			        	    
			        	    arr.add(new CardioData(name, distance, time, calories));
		        	    }
	        	    }
	        	}
	        }
	        bufferReader.close();
	        return arr;

	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	
	
}
