
public class User {
	private String id;
	private String name;
	private int height;
	private int age;
	private String gender;
	private int weight;
	
	User(String id, String name, int height, int age, String gender, int weight){
		this.id = id;
		this.name = name;
		this.height = height;
		this.age = age;
		this.gender = gender;
		this.weight = weight;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void logout() {
		id = null;
		name = null;
		height = 0;
		age = 0;
		gender = null;
		weight = 0;
	}

}
