import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardioForm extends JFrame {

    JFrame frame;
    ImageIcon image;
    JPanel panel;
    JLabel title, exercise, mile, time, calories;
    JTextField mileTextField, timeTextField, caloriesTextField;
    JComboBox<String> comboBox;
    JButton btnSave, btnCancel;

    CardioForm() {
        image = new ImageIcon("Images/cardio.png");

        title = new JLabel("Please fill in the following cardio form");
        title.setBounds(70, 10, 400, 25);

        exercise = new JLabel("Exercise");
        exercise.setBounds(20, 50, 400, 25);

        String[] cardioExercises = {"Cycling", "Climbing", "Elliptical Machine", "Hiking", "Running", "Skating", "Walking"};
        comboBox = new JComboBox<>(cardioExercises);
        comboBox.setBounds(260, 50, 100, 25);

        mile = new JLabel("Distance(km)");
        mile.setBounds(20, 90, 100, 25);
        mileTextField = new JTextField();
        mileTextField.setBounds(260, 90, 100, 25);

        time = new JLabel("Time(min)");
        time.setBounds(20, 130, 100, 25);
        timeTextField = new JTextField();
        timeTextField.setBounds(260, 130, 100, 25);

        calories = new JLabel("Calories");
        calories.setBounds(20, 170, 100, 25);
        caloriesTextField = new JTextField();
        caloriesTextField.setBounds(260, 170, 100, 25);

        btnSave = new JButton("SAVE");
        btnSave.setBounds(80, 210, 100, 25);



        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(200, 210, 100, 25);
        btnCancel.setBackground(Color.red);
        btnCancel.setForeground(Color.white);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                CardioForm.this.dispose();
            }
        });

        panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(null);
        panel.add(title);
        panel.add(exercise);
        panel.add(comboBox);
        panel.add(mile);
        panel.add(mileTextField);
        panel.add(time);
        panel.add(timeTextField);
        panel.add(calories);
        panel.add(caloriesTextField);
        panel.add(btnSave);
        panel.add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mileTextField.getText() == null || mileTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Distance is required!");
                } else if (timeTextField.getText() == null || timeTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "time is required!");
                } else if (caloriesTextField.getText() == null || caloriesTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "calories is required!");
                } else {
                    CardioForm.this.dispose();
                }

            }
        });

        frame = new JFrame("Cardio Workouts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setIconImage(image.getImage());
        frame.add(panel);
        frame.setVisible(true);
    }

}
