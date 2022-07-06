import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date; 

public class WeightLiftingData {

    Date date;
	String name;
    float weight;
    int sets, reps;
    

    // Constructor:
    public WeightLiftingData(Date date, String name, float weight, int sets, int reps) {
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.date = date;
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // Constructor without date:
    public WeightLiftingData(String name, float weight, int sets, int reps) {
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // Getters:
    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }
}
