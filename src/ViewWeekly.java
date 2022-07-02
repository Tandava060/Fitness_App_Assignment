import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ViewWeekly extends JFrame {

    JSplitPane splitPane;

    JPanel topPanel;
    JPanel bottomPanel;

    JProgressBar progressBar;


    String[] cardioTableHeaders = {"Date", "Name", "Distance (km)", "Time (min)", "Calories Burnt (kcals)"};
    ArrayList<CardioData> cardioData;
    JTable cardioTable;


    String[] weightLiftingTableHeaders = {"Date", "Name", "Weight", "Sets", "Reps"};
    ArrayList<WeightLiftingData> weightLiftingData;
    JTable weightLiftingTable;

    ViewWeekly() {

        splitPane = new JSplitPane();

        // Top Panel of SplitPane Consists of 3 JPanels: 1: Title, 2: Date Selector, 3: Progress Bar
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

        panel2.add(dateSelectorLabel);
        panel2.add(startDatePicker);
        panel2.add(endDatePicker);
        panel2.add(searchBtn);
        //=================================================================================================

        //=========================================== Panel 3 ==============================================
        JPanel panel3 = new JPanel();

        // To work only when date submit button has been clicked.
        progressBar = new JProgressBar();
        progressBar.setValue(0);
        progressBar.setBackground(Color.black);
        progressBar.setForeground(Color.red);
        progressBar.setFont(new Font("Optima", Font.BOLD, 18));
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(400, 50));

        panel3.add(progressBar);
        //==================================================================================================

        topPanel.add(panel1);
        topPanel.add(panel2);
        topPanel.add(panel3);

        // ============================= Bottom Half of Panel =================================
        bottomPanel = new JPanel();
        // Layout
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Bottom Panel tables
        JPanel cardioTablePanel = new JPanel();
        addCardioData(); // ** FOR DEMO PURPOSES ** Can be removed later on when fetching data
        cardioTablePanel.add(createCardioTable());
        cardioTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cardio History",  TitledBorder.CENTER, TitledBorder.TOP));

        JPanel weightLiftingTablePanel = new JPanel();
        addWeightLiftingData();
        weightLiftingTablePanel.add(createWeightLiftingTable());
        weightLiftingTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "WeightLifting History",  TitledBorder.CENTER, TitledBorder.TOP));


        bottomPanel.add(cardioTablePanel);
        bottomPanel.add(weightLiftingTablePanel);

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(150);
        splitPane.setBackground(Color.gray);
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("View History");
        this.setPreferredSize(new Dimension(700, 500));
        this.setLayout(new GridLayout());
        this.add(splitPane);
        this.pack();

        this.setVisible(true);

        fill();

    }

    // Use to fill up progress bar...Edit to work with progress of fetching data from database.
    public void fill() {
        int counter = 0;

        while (counter < 101) {
            progressBar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }

        progressBar.setString("Your Exercise History Below!");

    }

    // =============================== Cardio Methods ===============================================
    private void addCardioData(){
        // Input Data from CardioData Class:
        cardioData = new ArrayList<>();
        cardioData.add(new CardioData("2/07/2022", "Jogging", "5.5", "47", "450"));
        cardioData.add(new CardioData("3/07/2022", "Walking", "10", "50", "240"));
        cardioData.add(new CardioData("4/07/2022", "Speed Walking", "7", "18", "390"));
        cardioData.add(new CardioData("5/07/2022", "Swimming", "2", "30", "500"));
        cardioData.add(new CardioData("6/07/2022", "Sprinting", "1.5", "20", "390"));
        cardioData.add(new CardioData("7/07/2022", "Water aerobics", "2.4", "30", "390"));
        cardioData.add(new CardioData("8/07/2022", "Cycling", "10", "70", "400"));
        cardioData.add(new CardioData("9/07/2022", "Burpees", "-", "20", "450"));
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
            String _date = d.getDate();
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

    private void addWeightLiftingData(){
        // Input Data from CardioData Class:
        weightLiftingData = new ArrayList<>();
        weightLiftingData.add(new WeightLiftingData("2/07/2022", "Dumbell Press", 30, 3, 10));
        weightLiftingData.add(new WeightLiftingData("3/07/2022", "Lat PullDowns", 40, 2, 15));
        weightLiftingData.add(new WeightLiftingData("4/07/2022", "Squat", 80, 3, 12));
        weightLiftingData.add(new WeightLiftingData("5/07/2022", "Leg Press", 200, 2, 10));
        weightLiftingData.add(new WeightLiftingData("6/07/2022", "Lateral Raises", 5, 3, 30));
        weightLiftingData.add(new WeightLiftingData("7/07/2022", "Face Pulls", 15, 3, 15));
        weightLiftingData.add(new WeightLiftingData("8/07/2022", "Triceps Pushdowns", 25, 3, 12));
        weightLiftingData.add(new WeightLiftingData("9/07/2022", "Biceps Curls", 25, 3, 10));
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
            String _date = d.getDate();
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
