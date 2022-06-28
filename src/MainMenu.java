import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {

    JFrame frame;
    JButton addBtn;
    JButton daily;
    JButton weekly;
    JPanel panel;

    MainMenu(){
        JLabel title = new JLabel("Choose your Operation");
        title.setFont(new Font("Optima", Font.BOLD, 15));
        title.setForeground(Color.BLACK);

        ImageIcon imageIcon = new ImageIcon("Images/ic_gym.png");
        JLabel iconLabel = new JLabel(imageIcon);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);

        addBtn = new JButton("Add Exercise");
        daily = new JButton("View Daily");
        weekly = new JButton("View Weekly");

        iconLabel.setPreferredSize(new Dimension(100, 50));
        title.setPreferredSize(new Dimension(400, 20));
        addBtn.setPreferredSize(new Dimension(200, 40));
        daily.setPreferredSize(new Dimension(200, 40));
        weekly.setPreferredSize(new Dimension(200, 40));

        addBtn.setFocusable(false);
        daily.setFocusable(false);
        weekly.setFocusable(false);

        addBtn.addActionListener(this);
        daily.addActionListener(this);
        weekly.addActionListener(this);

        iconLabel.setBounds(143, 40, iconLabel.getPreferredSize().width, iconLabel.getPreferredSize().height);
        addBtn.setBounds(95, 100, addBtn.getPreferredSize().width, addBtn.getPreferredSize().height);
        daily.setBounds(95, 150, daily.getPreferredSize().width, daily.getPreferredSize().height);
        weekly.setBounds(95, 200, weekly.getPreferredSize().width, weekly.getPreferredSize().height);
        title.setBounds(110, 20, title.getPreferredSize().width, title.getPreferredSize().height);

        panel.add(title);
        panel.add(iconLabel);
        panel.add(this.addBtn);
        panel.add(this.daily);
        panel.add(this.weekly);

        frame = new JFrame("Main Menu");
        frame.setSize(400, 350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addBtn)) {
            new NewExercise();
            frame.dispose();
        }

        if (e.getSource().equals(daily)) {
            JOptionPane.showMessageDialog(null, "Daily Summary");
            frame.dispose();
        }

        if (e.getSource().equals(weekly)) {
            JOptionPane.showMessageDialog(null, "Weekly Summary");
            frame.dispose();
        }
    }
}