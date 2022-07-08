import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeightLiftingForm extends JFrame implements ChangeListener  {
    JFrame frame;
    ImageIcon image;
    JPanel panel;
    JLabel title,exercise,set,weight,reps;
    JComboBox<String> comboBoxExercise;
    JComboBox<Integer>comboBoxSet;
    JSlider slider;
    JTextField rep;
    JButton btnSave, btnCancel;

    WeightLiftingForm(){
        image = new ImageIcon("Images/ic_weightlifting.png");

        title = new JLabel("Please fill in the following weightlifting form");
        title.setBounds(70,10,400,25);

        exercise = new JLabel("Exercise");
        exercise.setBounds(20,50,400,25);
        String[] cardioExercises = {"Arnold Press", "Ab Wheel", "Around The World","Back Extension","Bench Dip","Bench Press", "Bicep Curl","Cable Crunch"};
        comboBoxExercise=new JComboBox<>(cardioExercises);
        comboBoxExercise.setBounds(260,50,100,25);   

        set = new JLabel("Set");
        set.setBounds(20,90,100,25);
        Integer[] arraySet = {1,2,3,4,5,6,7,8,9,10};
        comboBoxSet=new JComboBox<>(arraySet);
        comboBoxSet.setBounds(260,90,100,25);

        slider = new JSlider(0,500,100);
        weight = new JLabel();
        weight.setText("Weight(Kg)");
        weight.setBounds(20,130,100,25);
        slider.setBounds(20,160,350,25);
        slider.setBackground(Color.gray);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(50);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);

        reps = new JLabel("Reps");
        reps.setBounds(20,210,100,25);
        rep = new JTextField();
        rep.setBounds(260,210,100,25);

        btnSave = new JButton("SAVE");
        btnSave.setBounds(80,250,100,25);
        
      

        btnCancel = new JButton("CANCEL");
        btnCancel.setBounds(200,250,100,25);
        btnCancel.setBackground(Color.red);
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            		   new MainMenu();
                       WeightLiftingForm.this.dispose();
             
            }
        });

        panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(null);
        panel.add(title);
        panel.add(exercise);
        panel.add(comboBoxExercise);
        panel.add(set);
        panel.add(comboBoxSet);
        panel.add(weight);
        panel.add(slider);
        panel.add(reps);
        panel.add(rep);
        panel.add(btnSave);
        panel.add(btnCancel);
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (rep.getText().equals(null)  || rep.getText().equals("") || !isNumeric(rep.getText())) {
            		JOptionPane.showMessageDialog(panel, "reps is required && must be a numeric value ");
            	} 
            	else {
//            		System.out.println("exercise: " + comboBoxExercise.getSelectedItem().toString() + " set: " + comboBoxSet.getSelectedItem().toString() + " weight: " + slider.getValue() + " reps: " + rep.getText());
//            		dbConnection.insertValues("INSERT INTO WEIGHTLIFTING VALUES (0, '" + comboBoxExercise.getSelectedItem().toString() + "', " + Integer.parseInt(comboBoxSet.getSelectedItem().toString()) + ", " + Integer.parseInt(rep.getText()) + ", " + slider.getValue() +")");
//            		System.out.println(result);
            		if(dbConnection.createWeightLiftingExercise(comboBoxExercise.getSelectedItem().toString(), Integer.parseInt(comboBoxSet.getSelectedItem().toString()), Integer.parseInt(rep.getText()) , slider.getValue())) {
            			FileConnection.addWeight(comboBoxExercise.getSelectedItem().toString(), slider.getValue(), Integer.parseInt(comboBoxSet.getSelectedItem().toString()), Integer.parseInt(rep.getText()));
            			JOptionPane.showMessageDialog(panel, "Exercise added ✔");
            		} else {
            			JOptionPane.showMessageDialog(panel, "Error, please try again later");
            		}
            		new MainMenu();
                    WeightLiftingForm.this.dispose();
            	}
            }
        });


        frame = new JFrame("Weightlifting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,330);
        frame.setIconImage(image.getImage());
        frame.add(panel);
        frame.setVisible(true);
        
        slider.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e) {
            	weight.setText("Weight(Kg): " + slider.getValue());
            }
        });
        
        
    }
    
    //returns true if str is a number(int/float)
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
    }
    
}
