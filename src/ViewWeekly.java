import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class ViewWeekly extends JFrame {
    JSplitPane splitPane;

    JPanel topPanel;
    JPanel bottomPanel;


    String[] cardioTableHeaders = {"Date", "Name", "Distance (km)", "Time (min)", "Calories Burnt (kcals)"};
    ArrayList<CardioData> cardioData;
    JTable cardioTable;


    String[] weightLiftingTableHeaders = {"Date", "Name", "Weight", "Sets", "Reps"};
    ArrayList<WeightLiftingData> weightLiftingData;
    JTable weightLiftingTable;

    
    
    ViewWeekly() {

        splitPane = new JSplitPane();

        // Top Panel of SplitPane Consists of 3 JPanels: 1: Title, 2: Date Selector, 3: Back Button
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1, 0, 0));

        //=========================================== Panel 1 ==============================================
        JPanel panel1 = new JPanel();
        JLabel title = new JLabel("View your Exercise History!");
        title.setFont(new Font("Optima", Font.BOLD, 16));
        panel1.add(title);
        //==================================================================================================

        //=========================================== Panel 2 ==============================================
        JPanel panel2 = new JPanel();

        JLabel dateSelectorLabel = new JLabel("Select a date range");
        dateSelectorLabel.setFont(new Font("Optima", Font.PLAIN, 13));

        DatePickerSettings datePickerSettings = new DatePickerSettings();

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPreferredSize(new Dimension(160, 25));

        DatePicker endDatePicker = new DatePicker(datePickerSettings);
        endDatePicker.setPreferredSize(new Dimension(160, 25));

        datePickerSettings.setDateRangeLimits(LocalDate.now(), LocalDate.MAX);

        JButton searchBtn = new JButton("Search");
        searchBtn.setFocusable(false);
        searchBtn.setPreferredSize(new Dimension(100, 25));
        searchBtn.setFont(new Font("Optima", Font.PLAIN, 14));
        
        searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(startDatePicker.getDate() != null && endDatePicker.getDate() != null) {	
				
					bottomPanel.removeAll();

					endDatePicker.getDate();

                    // Bottom Panel tables
                    bottomPanel.setLayout(new BoxLayout(bottomPanel , BoxLayout.Y_AXIS));
				    JPanel cardioTablePanel = new JPanel();
			        addCardioData(startDatePicker.getDate(),endDatePicker.getDate()); // ** FOR DEMO PURPOSES ** Can be removed later on when fetching data
			        cardioTablePanel.add(createCardioTable());
			        cardioTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cardio History",  TitledBorder.CENTER, TitledBorder.TOP));
			        
			        JPanel weightLiftingTablePanel = new JPanel();
			        addWeightLiftingData(startDatePicker.getDate(),endDatePicker.getDate());
			        weightLiftingTablePanel.add(createWeightLiftingTable());
			        weightLiftingTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "WeightLifting History",  TitledBorder.CENTER, TitledBorder.TOP));

			        bottomPanel.add(cardioTablePanel);
			        bottomPanel.add(weightLiftingTablePanel);
			        
			        splitPane.setBottomComponent(bottomPanel);
				} else {
					JOptionPane.showMessageDialog(topPanel, "DateFrom AND DateTo is required");
				}
			}
		});

        panel2.add(dateSelectorLabel);
        panel2.add(startDatePicker);
        panel2.add(endDatePicker);
        panel2.add(searchBtn);
        //=================================================================================================

        //=========================================== Panel 3 ==============================================
        JPanel panel3 = new JPanel();
        JButton backBtn = new JButton("Back");

        backBtn.setFocusable(false);
        backBtn.setPreferredSize(new Dimension(100, 25));


        panel3.setPreferredSize(new Dimension(400, 50));
        panel3.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                ViewWeekly.this.dispose();
            }
        });

        //==================================================================================================

        topPanel.add(panel1);
        topPanel.add(panel2);
        topPanel.add(panel3);

        // ============================= Bottom Half of Panel =================================
        bottomPanel = new JPanel();


        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(150);
        splitPane.setBackground(Color.gray);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("View History");
        this.setPreferredSize(new Dimension(700, 500));
        this.setLayout(new GridLayout());
        this.add(splitPane);
        this.pack();

        this.setVisible(true);

    }

    // =============================== Cardio Methods ===============================================
    private void addCardioData(LocalDate dateFrom, LocalDate dateTo){
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Input Data from CardioData Class:
        cardioData = new ArrayList<>();
        CardioData[] result = dbConnection.getCardioValues(dateFrom.format(myFormatObj), dateTo.format(myFormatObj));

        if (result != null){
            Collections.addAll(cardioData, result);
        }
        else {
            JOptionPane.showMessageDialog(bottomPanel, "No cardio data found.");
        }

    }
    private JScrollPane createCardioTable() {

        DefaultTableModel defaultTableModel = new DefaultTableModel(cardioTableHeaders, 0);

        fetchCardioData(defaultTableModel, cardioData);

        cardioTable = new JTable(defaultTableModel){

            // Prevent cell from being editable by setting the factory method from true to false
            public boolean isCellEditable(int data, int col){
                return false;
            }

            // Changing color of pattern of table:
            public Component prepareRenderer(TableCellRenderer tableCellRenderer, int data, int col){
                Component component = super.prepareRenderer(tableCellRenderer, data, col);

                if (data % 2 == 0){
                    // If row is even set to white
                    component.setBackground(Color.WHITE);
                }else {
                    // If row is odd, set to light gray
                    component.setBackground(Color.LIGHT_GRAY);
                }

                if (isCellSelected(data, col)){
                    component.setBackground(new Color(245, 176, 65));
                }
                return component;
            }

        };

        cardioTable.setRowHeight(20);
        cardioTable.setPreferredScrollableViewportSize(new Dimension(600, 150));
        cardioTable.setFillsViewportHeight(true);

        return new JScrollPane(cardioTable);
    }
    private void fetchCardioData(DefaultTableModel tableModel, ArrayList<CardioData> data){
        for (CardioData d : data) {
            Date _date = d.getDate();
            String _name = d.getName();
            String _distance = d.getDistance();
            String _time = d.getTime();
            String _calories = d.getCalories();

            Object[] cardioData = {_date, _name, _distance, _time, _calories};

            tableModel.addRow(cardioData);
        }
    }

    // ==============================================================================================


    // =============================== WeightLifting Methods ========================================

    private void addWeightLiftingData(LocalDate dateFrom, LocalDate dateTo ){
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Input Data from CardioData Class:
        weightLiftingData = new ArrayList<>();
        WeightLiftingData[] result = dbConnection.getWeightLiftingValues(dateFrom.format(myFormatObj), dateTo.format(myFormatObj));

        if (result != null){
            Collections.addAll(weightLiftingData, result);
        }else {
            JOptionPane.showMessageDialog(bottomPanel, "No Weightlifting data found.");
        }
    }

    private JScrollPane createWeightLiftingTable() {

        DefaultTableModel defaultTableModel = new DefaultTableModel(weightLiftingTableHeaders, 0);

        fetchWeightLiftingData(defaultTableModel, weightLiftingData);

        weightLiftingTable = new JTable(defaultTableModel){

            // Prevent cell from being editable by setting the factory method from true to false
            public boolean isCellEditable(int data, int col){
                return false;
            }

            // Changing color of pattern of table:
            public Component prepareRenderer(TableCellRenderer tableCellRenderer, int data, int col){
                Component component = super.prepareRenderer(tableCellRenderer, data, col);

                if (data % 2 == 0){
                    // If row is even set to white
                    component.setBackground(Color.WHITE);
                }else {
                    // If row is odd, set to light gray
                    component.setBackground(Color.LIGHT_GRAY);
                }

                if (isCellSelected(data, col)){
                    component.setBackground(new Color( 46, 204, 113));
                }
                return component;
            }

        };

        weightLiftingTable.setRowHeight(20);
        weightLiftingTable.setPreferredScrollableViewportSize(new Dimension(600, 150));
        weightLiftingTable.setFillsViewportHeight(true);

        return new JScrollPane(weightLiftingTable);
    }
    private void fetchWeightLiftingData(DefaultTableModel tableModel, ArrayList<WeightLiftingData> data){
        for (WeightLiftingData d : data) {
            Date _date = d.getDate();
            String _name = d.getName();
            float _weight = d.getWeight();
            int _sets = d.getSets();
            int _reps = d.getReps();

            Object[] cardioData = {_date, _name, _weight, _sets, _reps};

            tableModel.addRow(cardioData);
        }
    }
    // ==============================================================================================

}
