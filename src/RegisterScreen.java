import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class RegisterScreen extends JFrame {

    JTextField fullName;
    JTextField password;
    JTextField height;
    JComboBox<Integer> ageSelector;
    JRadioButton radioButtonMale;
    JRadioButton radioButtonFemale;

    JTextField displayWeight;
    private final JSlider weightSlider;

    JButton btnContinue;

    List<JTextField> list = new ArrayList<>();

    RegisterScreen() {

        JLabel welcomeText = new JLabel("Welcome to your fitness companion");
        welcomeText.setPreferredSize(new Dimension(400, 20));
        welcomeText.setBounds(61, 20, welcomeText.getPreferredSize().width, welcomeText.getPreferredSize().height);
        welcomeText.setFont(new Font("Optima", Font.BOLD, 15));
        welcomeText.setForeground(Color.BLACK);

        ImageIcon imageIcon = new ImageIcon("Images/ic_weightlifting.png", null);
        JLabel applicationLogo = new JLabel(imageIcon);
        applicationLogo.setPreferredSize(new Dimension(100, 50));
        applicationLogo.setBounds(140, 50, applicationLogo.getPreferredSize().width, applicationLogo.getPreferredSize().height);

        JLabel fullNameLabel = new JLabel("Full name");
        fullNameLabel.setFont(new Font("Optima", Font.BOLD, 13));
        fullNameLabel.setForeground(Color.black);
        fullNameLabel.setPreferredSize(new Dimension(80, 30));
        fullNameLabel.setBounds(60, 120, fullNameLabel.getPreferredSize().width, fullNameLabel.getPreferredSize().height);

        fullName = new JTextField();
        fullName.setEditable(true);
        fullName.setFont(new Font("Optima", Font.PLAIN, 13));
        fullName.setPreferredSize(new Dimension(190, 30));
        fullName.setBounds(140, 120, fullName.getPreferredSize().width, fullName.getPreferredSize().height);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Optima", Font.BOLD, 13));
        passwordLabel.setForeground(Color.black);
        passwordLabel.setPreferredSize(new Dimension(80, 30));
        passwordLabel.setBounds(60, 160, passwordLabel.getPreferredSize().width, passwordLabel.getPreferredSize().height);

        password = new JPasswordField();
        password.setEditable(true);
        password.setFont(new Font("Optima", Font.PLAIN, 13));
        password.setPreferredSize(new Dimension(190, 30));
        password.setBounds(140, 160, password.getPreferredSize().width, password.getPreferredSize().height);


        JLabel heightLabel = new JLabel("Height (cm)");
        heightLabel.setFont(new Font("Optima", Font.BOLD, 13));
        heightLabel.setForeground(Color.black);
        heightLabel.setPreferredSize(new Dimension(80, 30));
        heightLabel.setBounds(60, 200, heightLabel.getPreferredSize().width, heightLabel.getPreferredSize().height);

        height = new JTextField(3);
        height.setEditable(true);
        height.setFont(new Font("Optima", Font.PLAIN, 13));
        height.setPreferredSize(new Dimension(69, 30));
        height.setBounds(140, 200, height.getPreferredSize().width, height.getPreferredSize().height);
        height.setDocument(new JTextFieldLimit(3));

        list.add(fullName);
        list.add(password);
        list.add(height);

        JLabel heightLimitGuide = new JLabel("(max 3 digits)");
        heightLimitGuide.setFont(new Font("", Font.ITALIC, 10));
        heightLimitGuide.setPreferredSize(new Dimension(90, 30));
        heightLimitGuide.setBounds(215, 200, heightLimitGuide.getPreferredSize().width, heightLimitGuide.getPreferredSize().height);

        // Making textField accept only numeric values:
        height.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setFont(new Font("Optima", Font.BOLD, 13));
        ageLabel.setPreferredSize(new Dimension(80, 30));
        ageLabel.setBounds(60, 240, ageLabel.getPreferredSize().width, ageLabel.getPreferredSize().height);
        ageLabel.setForeground(Color.black);

        ageSelector = new JComboBox<>();

        // Generating age:
        int count = 15;
        while (count <= 80) {
            ageSelector.addItem(count++);
        }

        ageSelector.setSelectedIndex(0);
        ageSelector.setPreferredSize(new Dimension(80, 30));
        ageSelector.setBounds(140, 240, 80, 30);

        JLabel selectGenderLabel = new JLabel("Gender");
        selectGenderLabel.setFont(new Font("Optima", Font.BOLD, 13));
        selectGenderLabel.setPreferredSize(new Dimension(80, 30));
        selectGenderLabel.setBounds(60, 280, selectGenderLabel.getPreferredSize().width, selectGenderLabel.getPreferredSize().height);
        selectGenderLabel.setForeground(Color.black);

        radioButtonMale = new JRadioButton("Male");
        radioButtonMale.setOpaque(false);
        radioButtonMale.setFocusable(false);
        radioButtonMale.setSelected(true);
        radioButtonMale.setPreferredSize(new Dimension(60, 30));
        radioButtonMale.setBounds(140, 280, radioButtonMale.getPreferredSize().width, radioButtonMale.getPreferredSize().height);

        radioButtonFemale = new JRadioButton("Female");
        radioButtonFemale.setOpaque(false);
        radioButtonFemale.setFocusable(false);
        radioButtonFemale.setPreferredSize(new Dimension(100, 30));
        radioButtonFemale.setBounds(200, 280, radioButtonFemale.getPreferredSize().width, radioButtonFemale.getPreferredSize().height);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButtonMale);
        radioButtonGroup.add(radioButtonFemale);

        JLabel selectWeightLabel = new JLabel("Weight (kg)");
        selectWeightLabel.setForeground(Color.black);
        selectWeightLabel.setFont(new Font("Optima", Font.BOLD, 13));
        selectWeightLabel.setPreferredSize(new Dimension(160, 30));
        selectWeightLabel.setBounds(60, 320, selectWeightLabel.getPreferredSize().width, selectWeightLabel.getPreferredSize().height);

        displayWeight = new JTextField();
        displayWeight.setEditable(false);
        displayWeight.setPreferredSize(new Dimension(75, 30));
        displayWeight.setBounds(140, 320, displayWeight.getPreferredSize().width, displayWeight.getPreferredSize().height);
        displayWeight.setFont(new Font("Optima", Font.BOLD, 13));

        weightSlider = new JSlider(30, 150, 50);
        weightSlider.setOpaque(false);
        weightSlider.setOrientation(SwingConstants.HORIZONTAL);

        weightSlider.setPaintTicks(true);

        weightSlider.setPaintTrack(true);
        weightSlider.setMajorTickSpacing(20);


        weightSlider.setPaintLabels(true);

        weightSlider.setPreferredSize(new Dimension(350, 50));
        weightSlider.setBounds(20, 360, weightSlider.getPreferredSize().width, weightSlider.getPreferredSize().height);

        displayWeight.setText(String.valueOf(weightSlider.getValue()));

        weightSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                displayWeight.setText(String.valueOf(weightSlider.getValue()));
            }
        });

        btnContinue = new JButton("Continue");
        btnContinue.setBackground(Color.red);
        btnContinue.setForeground(Color.white);
        btnContinue.setEnabled(true);
        btnContinue.setFocusable(false);
        btnContinue.setBorder(BorderFactory.createEmptyBorder());
        btnContinue.setFocusPainted(true);
        btnContinue.setFont(new Font("Optima", Font.BOLD, 15));
        btnContinue.setPreferredSize(new Dimension(120, 25));
        btnContinue.setBounds(138, 420, btnContinue.getPreferredSize().width, btnContinue.getPreferredSize().height);

        JLabel alreadyHaveAnAccount = new JLabel("Already have an account?");
        alreadyHaveAnAccount.setForeground(Color.black);
        alreadyHaveAnAccount.setFont(new Font("Arial", Font.ITALIC, 12));
        alreadyHaveAnAccount.setPreferredSize(new Dimension(200, 20));
        alreadyHaveAnAccount.setBounds(130, 460, alreadyHaveAnAccount.getPreferredSize().width, alreadyHaveAnAccount.getPreferredSize().height);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.white);
        btnLogin.setBackground(Color.gray);
        btnLogin.setFocusable(false);
        btnLogin.setBorder(null);
        btnLogin.setFont(new Font("Optima", Font.BOLD, 13));
        btnLogin.setPreferredSize(new Dimension(80, 22));
        btnLogin.setBounds(160, 480, btnLogin.getPreferredSize().width, btnLogin.getPreferredSize().height);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen();
                RegisterScreen.this.dispose();
            }
        });

//        for (JTextField textField : list){
//            textField.getDocument().addDocumentListener(listener);
//        }


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        panel.add(welcomeText);
        panel.add(applicationLogo);
        panel.add(fullNameLabel);
        panel.add(fullName);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(heightLabel);
        panel.add(height);
        panel.add(heightLimitGuide);
        panel.add(ageLabel);
        panel.add(ageSelector);
        panel.add(selectGenderLabel);
        panel.add(radioButtonMale);
        panel.add(radioButtonFemale);
        panel.add(selectWeightLabel);
        panel.add(displayWeight);
        panel.add(weightSlider);
        panel.add(btnContinue);
        panel.add(alreadyHaveAnAccount);
        panel.add(btnLogin);


        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullName.getText() == null || fullName.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Full Name is required!");
                } else if (password.getText() == null || password.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Password is required!");
                } else if (height.getText() == null || height.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Height is required!");
                } else if (height.getText().length() > 3) {
                    JOptionPane.showMessageDialog(panel, "Height cannot be greater than 3 digits no");
                } else {
                	String radioval;
                	if (radioButtonMale.isSelected()) {
                		radioval = radioButtonMale.getText();
                	} else {
                		radioval = radioButtonFemale.getText();
                	}
                	
                	if (dbConnection.register(fullName.getText(), password.getText(), Integer.parseInt(height.getText()), Integer.parseInt(ageSelector.getSelectedItem().toString()),radioval , weightSlider.getValue(), panel)) {
                		new MainMenu();
                        RegisterScreen.this.dispose();
                	}
                    
                }


            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registration Form");
        this.setSize(400, 570);
        this.add(panel);
        this.setResizable(false);

        this.setVisible(true);
    }

    public static class JTextFieldLimit extends PlainDocument {
        private final int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }


}
