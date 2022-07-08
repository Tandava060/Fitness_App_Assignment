import com.github.lgooddatepicker.components.DatePicker;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ViewDailyExercises extends JFrame {

    JSplitPane splitPane;

    JPanel topPanel;
    JPanel bottomPanel;

    String[] cardioTableHeaders = {"Name", "Distance (km)", "Time (min)", "Calories Burnt (kcals)"};
    ArrayList<CardioData> cardioData;
    JTable cardioTable;


    String[] weightLiftingTableHeaders = {"Name", "Weight", "Sets", "Reps"};
    ArrayList<WeightLiftingData> weightLiftingData;
    JTable weightLiftingTable;

    ViewDailyExercises(){

        splitPane = new JSplitPane();

        // Top panel with cardio data table
        topPanel = new JPanel();
        JPanel cardioTablePanel = new JPanel();
        cardioTablePanel.setBorder
                (BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Daily Cardio Exercises",
                        TitledBorder.CENTER, TitledBorder.TOP)
                );
        addCardioData();
        cardioTablePanel.add(createCardioTable());

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("", Font.PLAIN, 18));
        backBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setFocusable(false);

        backBtn.addActionListener(e -> {
            new MainMenu();
            ViewDailyExercises.this.dispose();
        });

        topPanel.add(backBtn);
        topPanel.add(cardioTablePanel);


        bottomPanel = new JPanel();
        JPanel weightLiftingTablePanel = new JPanel();
        weightLiftingTablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Daily WeightLifting Exercises",
                TitledBorder.CENTER, TitledBorder.TOP)
        );
        addWeightLiftingData();
        weightLiftingTablePanel.add(createWeightLiftingTable());

        bottomPanel.add(weightLiftingTablePanel);

        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(250);
        splitPane.setBackground(Color.gray);

        this.setTitle("Daily Exercises");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 600));
        this.setLayout(new GridLayout());
        this.setResizable(false);
        this.add(splitPane);
        this.pack();

        this.setVisible(true);
    }

    private void addCardioData(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Input Data from CardioData Class:
        cardioData = new ArrayList<>();
        cardioData = FileConnection.readCardioValues();


    }
    private JScrollPane createCardioTable(){

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
            String _name = d.getName();
            String _distance = d.getDistance();
            String _time = d.getTime();
            String _calories = d.getCalories();

            Object[] cardioData = {_name, _distance, _time, _calories};

            tableModel.addRow(cardioData);
        }
    }


    private void addWeightLiftingData(){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        weightLiftingData = new ArrayList<WeightLiftingData>();
        weightLiftingData = FileConnection.readWeightValues();
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
            String _name = d.getName();
            float _weight = d.getWeight();
            int _sets = d.getSets();
            int _reps = d.getReps();

            Object[] cardioData = {_name, _weight, _sets, _reps};

            tableModel.addRow(cardioData);
        }
    }
}
