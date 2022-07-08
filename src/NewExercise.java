import javax.swing.*;
import java.awt.*;

public class NewExercise {

    NewExercise(){
        String[] types = new String[]{"Cardio", "Weightlifting"};
        String getType = (String) JOptionPane.showInputDialog
                (null,
                 "Type of Exercise ?",
                 "Add Exercise Type",
                 JOptionPane.QUESTION_MESSAGE,
                 null,
                 types,
                 "");

        if (types[0].equals(getType)) {
            new CardioForm();
        } else if (types[1].equals(getType)) {
            new WeightLiftingForm();
        } else {
            // NO
        	JOptionPane.getRootFrame().dispose();
        }
    }
}
