import java.util.Date;

public class CardioData {
	Date date;
    String name, distance, time, calories;

    // Constructor:
    public CardioData(Date date, String name, String distance, String time, String calories) {
        this.date = date;
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.calories = calories;
    }

    // Constructor without date:


    public CardioData(String name, String distance, String time, String calories) {
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.calories = calories;
    }

    // Getters:
    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }


    public String getTime() {
        return time;
    }

    public String getCalories() {
        return calories;
    }

    // Create Setters if needed.
}
