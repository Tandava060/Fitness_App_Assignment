import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class LoginScreen extends JFrame {
	JTextField fullName;
    JPasswordField password;

    JButton btnContinue;

    List<JTextField> list = new ArrayList<>();

    LoginScreen(){

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

        list.add(fullName);
        list.add(password);
       
        JLabel registerLabel = new JLabel("Not yet registered? ");
        registerLabel.setPreferredSize(new Dimension(200, 20));
        registerLabel.setBounds(120, 200, registerLabel.getPreferredSize().width, registerLabel.getPreferredSize().height);
        registerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        registerLabel.setForeground(Color.BLACK);
        
        JButton registerButton = new JButton("Register");
        registerButton.setForeground(Color.white);
        registerButton.setBackground(Color.gray);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setPreferredSize(new Dimension(90, 20));
        registerButton.setBounds(220, 200, registerButton.getPreferredSize().width, registerButton.getPreferredSize().height);
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterScreen();
                LoginScreen.this.dispose();
            }
        });

        btnContinue = new JButton("Continue");
        btnContinue.setBackground(Color.red);
        btnContinue.setForeground(Color.white);
        btnContinue.setEnabled(true);
        btnContinue.setBorder(BorderFactory.createEmptyBorder());
        btnContinue.setFocusPainted(true);
        btnContinue.setFont(new Font("Optima", Font.BOLD, 15));
        btnContinue.setPreferredSize(new Dimension(120, 25));
        btnContinue.setBounds(138, 230, btnContinue.getPreferredSize().width, btnContinue.getPreferredSize().height);

      
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
        panel.add(registerLabel);
        panel.add(registerButton);
        panel.add(btnContinue);

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (fullName.getText() == null || fullName.getText().equals("")) {
            		JOptionPane.showMessageDialog(panel, "Full Name is required!");
            	} else if (password.getPassword() == null || password.getText().equals("")) {
            		JOptionPane.showMessageDialog(panel, "Password is required!");
            	} else {
            		   new MainMenu();
                       LoginScreen.this.dispose();
            	}
             
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Registration Form");
        this.setSize(400, 320);
        this.add(panel);
        this.setResizable(false);

        this.setVisible(true);
}
    
//    DocumentListener listener = new DocumentListener() {
//        @Override
//        public void insertUpdate(DocumentEvent e) {
//            changedUpdate(e);
//        }
//
//        @Override
//        public void removeUpdate(DocumentEvent e) {
//            changedUpdate(e);
//        }
//
//        @Override
//        public void changedUpdate(DocumentEvent e) {
//            boolean isEnabled = true;
//            for (JTextField textField : list){
//                if (textField.getText().isEmpty()){
//                    isEnabled = false;
//                }
//            }
//
//            btnContinue.setEnabled(isEnabled);
//
//            if (btnContinue.isEnabled()){
//                btnContinue.setBackground(Color.GREEN);
//                btnContinue.setForeground(Color.BLACK);
//            }
//            else {
//                btnContinue.setBackground(Color.RED);
//                btnContinue.setForeground(Color.WHITE);
//            }
//        }
//    };
//    
    public static class JTextFieldLimit extends PlainDocument {
        private final int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if (str == null) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
    
}


    
