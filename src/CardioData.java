public class CardioData {

    String date, name, distance, time, calories;

    // Constructor:
    public CardioData(String date, String name, String distance, String time, String calories) {
        this.date = date;
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.calories = calories;
    }

    // Getters:
    public String getDate() {
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
