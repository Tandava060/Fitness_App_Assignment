
public class User {
	private int id, age, weight, height;
	private String name, gender;
	
	User(int id, String name, int age, int weight, int height, String gender){
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getAge() {
		return age;
	}

	public int getHeight() {
		return height;
	}
	
	public String getGender() {
		return gender;
	}

}
