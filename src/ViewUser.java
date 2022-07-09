import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewUser extends JFrame {
	JPanel panel;

	 ViewUser(){
	        JLabel title = new JLabel("My User Details");
	        title.setFont(new Font("Optima", Font.BOLD, 18));
	        title.setForeground(Color.BLACK);
	        title.setPreferredSize(new Dimension(400, 20));
	        title.setBounds(80, 15, title.getPreferredSize().width, title.getPreferredSize().height);
	        
	        ImageIcon imageIcon;
	        if(App.currentUser.getGender().contentEquals("Female")) {
	        	imageIcon = new ImageIcon("Images/female_user.png");
	        } else {
	        	imageIcon = new ImageIcon("Images/male_user.png");
	        }
	        
	        JLabel iconLabel = new JLabel(imageIcon);
	        iconLabel.setPreferredSize(new Dimension(100, 50));
	        iconLabel.setBounds(100, 40, iconLabel.getPreferredSize().width, iconLabel.getPreferredSize().height);

	        panel = new JPanel();
	        panel.setLayout(null);
	        panel.setBackground(Color.gray);
	        
	        panel.add(title);
	        panel.add(iconLabel);
	        
	        JLabel name = new JLabel("Name: " + App.currentUser.getName());
	        name.setBounds(20, 90, 200, 25);
	        name.setFont(new Font("", Font.PLAIN, 16));

	        JLabel age = new JLabel("Age: " + App.currentUser.getAge());
	        age.setBounds(20, 110, 100, 25);
	        age.setFont(new Font("", Font.PLAIN, 16));

	        JLabel weight = new JLabel("Weight: " + App.currentUser.getWeight() + " kg");
	        weight.setBounds(20, 130, 100, 25);
	        weight.setFont(new Font("", Font.PLAIN, 16));

	        JLabel height = new JLabel("Height: " + App.currentUser.getHeight() + " cm");
	        height.setBounds(150, 110, 120, 25);
	        height.setFont(new Font("", Font.PLAIN, 16));

	        JLabel bmi = new JLabel("BMI: " + calculateBMI(App.currentUser.getWeight(), App.currentUser.getHeight()));
	        bmi.setBounds(150, 130, 100, 25);
	        bmi.setFont(new Font("", Font.BOLD, 16));
	        
	        JButton backBtn = new JButton("Back");
	        backBtn.setFont(new Font("", Font.PLAIN, 15));
	        backBtn.setPreferredSize(new Dimension(150, 40));
	        backBtn.setFocusable(false);
	        backBtn.setBounds(65, 180, backBtn.getPreferredSize().width, backBtn.getPreferredSize().height);

	        backBtn.addActionListener(e -> {
				new MainMenu();
				ViewUser.this.dispose();
	        });
	        
	        panel.add(name);
	        panel.add(age);
	        panel.add(weight);
	        panel.add(height);
	        panel.add(bmi);
	        panel.add(backBtn);
	        
	        this.setTitle("View User");
	        this.setSize(300, 300);
	        this.setResizable(false);
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        this.add(panel);
	     

	        this.setVisible(true);
	 }
	 
	 private static int calculateBMI(int w, int h) {
		 return w/((h/100)*(h/100));
	 }
}
