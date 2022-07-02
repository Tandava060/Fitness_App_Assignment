public class WeightLiftingData {

    String date, name;
    float weight;
    int sets, reps;

    // Constructor:
    public WeightLiftingData(String date, String name, float weight, int sets, int reps) {
        this.date = date;
        this.name = name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // Getters:
    public String getDate() {
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
