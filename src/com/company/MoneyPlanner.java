package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

public class MoneyPlanner extends JPanel implements ActionListener {

    JPanel leftPanel;
    JPanel rightPanel;
    JPanel leftPanelTop;
    JPanel leftPanelBottom;
    JScrollPane scrollPane;

    JPanel calendarPanel;
    JPanel calendarTopPanel;
    JPanel title_pan;
    JPanel titleMiddlePan;
    JPanel day_pan;
    JPanel date_pan;
    JPanel add_submit_pan;

    JPanel inputPanel;

    JTextArea incomeArea;
    JTextArea expenseArea;

    JTextField totalIncomeField;
    JTextField totalExpenseField;
    JTextField summaryField;

    JTableHeader tableHeader;


    HashMap<String,HashMap<String, Vector<String[]>>> dataManagement;
    HashMap<String, Vector<String[]>> savedData;

    Vector<String[]> dataVal;

    LocalDate currentDate = LocalDate.now();
    LocalDate selectedDate;

    String[] day = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    HashMap<String, Integer> dayMap = new HashMap<>() {{
        put("sunday", 1);
        put("monday", 2);
        put("tuesday", 3);
        put("wednesday", 4);
        put("thursday", 5);
        put("friday", 6);
        put("saturday", 7);
    }};

    Font font;
    Font day_font;


    JLabel currentDateLabel;
    JLabel title_label;
    JLabel day_label;

    JButton[] day_btn;
    JButton left_btn;
    JButton right_btn;
    JButton addBtn;
    JButton submitBtn;
    JButton todayBtn;

    int now_year;
    int now_month;
    int now_day;

    String inputDate;


    JComboBox<String> choice1;
    JComboBox<String> choice2;

    JTextField textField;
    JButton deleteBtn;
    JButton saveBtn;

    Vector<String> ieChoice = new Vector<>() {{
        add("Income");
        add("Expense");
    }};

    Vector<String>incomeList = new Vector<>() {{
        add("Salary");
        add("Investment");
        add("Gifts/Bonus");
    }};


    Vector<String> expenseList = new Vector<>(){{
        add("Water");
        add("Electricity");
        add("Insurance");
        add("Car");
        add("Food");
        add("Clothes");
        add("Rent/Morgage");
        add("Maintance");
        add("Phone");
        add("Internet");
    }};

    String[] colName = {" ", "Income/Expense", "Category", "Amount of Money"};




    MoneyPlanner(){
        init();
    }

    void init() {
        //setting selected date through current date
        selectedDate = currentDate;

        now_year = selectedDate.getYear();
        now_month = selectedDate.getMonthValue();
        now_day = selectedDate.getDayOfMonth();

        //setting font
        font = new Font("", Font.BOLD, 25);
        day_font = new Font("", Font.BOLD, 23);

    //generate each panels
        leftPanel = new JPanel();
        rightPanel = new JPanel();

        //left panel
        leftPanelTop = new JPanel();
        leftPanelBottom = new JPanel();
        leftPanelBottom.setLayout(new GridLayout(8,1));
        scrollPane = new JScrollPane();

        //generate date management tools( hashmap and vector)
        dataManagement = new HashMap<>();
        savedData = new HashMap<>();
        dataVal = new Vector<>();

        //generate table for input data.




        //setting layout and adding the panels into main panel.
        setLayout(new GridLayout(1, 2, 20, 0));

        leftPanel.setLayout(new GridLayout(2, 1));

        calendar(selectedDate);

        leftPanelBottom.setLayout(new GridLayout(20, 1));

        scrollPane = new JScrollPane(leftPanelBottom, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(leftPanelTop);
        leftPanel.add(scrollPane);

        //right panel



        //add components into main panel
        add(leftPanel);
        add(rightPanel);

    }

    void calendar(LocalDate selectedDate){

        now_year = selectedDate.getYear();
        now_month = selectedDate.getMonthValue();
        now_day = selectedDate.getDayOfMonth();

        //setting calendar part
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BorderLayout());

        calendarTopPanel = new JPanel();
        calendarTopPanel.setLayout(new BorderLayout());

        title_pan = new JPanel();
        title_pan.setLayout(new BorderLayout());

        titleMiddlePan = new JPanel();
        titleMiddlePan.setLayout(new FlowLayout());

        day_pan = new JPanel();
        day_pan.setLayout(new GridLayout(0, 7));

        date_pan = new JPanel();
        date_pan.setLayout(new GridLayout(0, 7));

    //setting for add_submit panel
        add_submit_pan = new JPanel();

        inputDate = selectedDate.getDayOfWeek() + " ( " + selectedDate.getDayOfMonth() + " , " + selectedDate.getMonth() + " ) ";
        currentDateLabel = new JLabel(inputDate);
        currentDateLabel.setFont(new Font("", Font.ITALIC, 18));
        add_submit_pan.add(currentDateLabel);

        //setting add button
        addBtn = new JButton("+");
        addBtn.setFont(new Font("", Font.BOLD, 20));
        addBtn.addActionListener(this);
        add_submit_pan.add(addBtn);

        //setting submit button
        submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("", Font.BOLD, 20));
        submitBtn.addActionListener(this);
        add_submit_pan.add(submitBtn);

        //setting calendar title panel
        title_label = new JLabel(selectedDate.getMonth() + " , " + selectedDate.getYear(), JLabel.CENTER);
        title_label.setFont(font);

        left_btn = new JButton("<");

        right_btn = new JButton(">");

        todayBtn = new JButton("Today");

        todayBtn.addActionListener(this);
        right_btn.addActionListener(this);
        left_btn.addActionListener(this);

        //presenting day list.
        for (int i = 0; i < 7; i++) {
            day_label = new JLabel(day[i], JLabel.CENTER);
            day_label.setFont(font);
            if (i == 0) {
                day_label.setForeground(Color.RED);
            } else if (i == 6) {
                day_label.setForeground(Color.BLUE);
            }
            day_pan.add(day_label);
        }

        titleMiddlePan.add(title_label);
        titleMiddlePan.add(todayBtn);

        title_pan.add(left_btn, BorderLayout.WEST);
        title_pan.add(titleMiddlePan, BorderLayout.CENTER);
        title_pan.add(right_btn, BorderLayout.EAST);


        calendarTopPanel.add(title_pan, BorderLayout.NORTH);
        calendarTopPanel.add(day_pan, BorderLayout.CENTER);

        calendarPanel.add(calendarTopPanel, BorderLayout.NORTH);
        calendarPanel.add(add_submit_pan, BorderLayout.SOUTH);

        //presenting date in calendar

        selectedDate = LocalDate.of(now_year, now_month, 1);
        String firstDay = ("" + selectedDate.getDayOfWeek()).toLowerCase();

        int last_day = selectedDate.lengthOfMonth();
        selectedDate = LocalDate.of(now_year, now_month, last_day);

        String lastDayOfWeek = ("" + selectedDate.getDayOfWeek()).toLowerCase();

        selectedDate = LocalDate.of(now_year, now_month, now_day);

        //setting date button
        day_btn = new JButton[last_day + 1];

        for (int i = 1; i < dayMap.get(firstDay); i++) {
            date_pan.add(new JButton());
        }

        for (int i = 1; i <= last_day; i++) {
            day_btn[i] = new JButton("" + (i));

            day_btn[i].setName(selectedDate.getYear() + "," + selectedDate.getMonth().getValue() + "," + i);
            day_btn[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            day_btn[i].setFont(day_font);
            date_pan.add(day_btn[i]);
        }

        for (int i = dayMap.get(lastDayOfWeek) + 1; i <= 7; i++) {
            date_pan.add(new JButton());
        }

        selectedDate = LocalDate.of(now_year, now_month, now_day);
        day_btn[now_day].setBackground(Color.red);
        calendarPanel.add(date_pan);

        leftPanelTop.add(calendarPanel);

    }
    //setting previous Button
    void prevMonth() {
        if (now_month == 1) {
            selectedDate = LocalDate.of(selectedDate.getYear() - 1, 12, 1);
        } else {
            selectedDate = LocalDate.of(selectedDate.getYear(), selectedDate.getMonthValue() - 1, 1);
        }

        leftPanelTop.removeAll();
        calendar(selectedDate);
        leftPanelTop.updateUI();

        System.out.println("prevMonth");
        System.out.println("selectedDate : " + selectedDate);

    }

    //Setting Next Button.
    void nextMonth() {
        if (now_month == 12) {
            selectedDate = LocalDate.of(selectedDate.getYear() + 1, 1, 1);
        } else {
            selectedDate = LocalDate.of(selectedDate.getYear(), selectedDate.getMonthValue() + 1, 1);
        }

        leftPanelTop.removeAll();
        calendar(selectedDate);
        leftPanelTop.updateUI();

        System.out.println("nextMonth");
        System.out.println("current date after next month: " + selectedDate.toString());

    }

    void addInputPanel(LocalDate selectedDate){
        JPanel inputPanel = new JPanel();
        Font font = new Font("",Font.BOLD,15);
        dataVal = new Vector<>();
        //setting choice1
        choice1 = new JComboBox<>(ieChoice);
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    refresh(inputPanel);
                }
           });
        choice1.setFont(font);
        inputPanel.add(choice1);

        //setting choice 2
        choice2 = new JComboBox<String>(incomeList);
        choice2.setFont(font);
        inputPanel.add(choice2);

        //setting text field
        textField = new JTextField(15);
        textField.setFont(font);
        inputPanel.add(textField);

        //setting save button
        saveBtn = new JButton("Save");
        saveBtn.setFont(font);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patternString = "[1-9][0-9]+";
                if(textField.getText().equals("") || !Pattern.matches(patternString,textField.getText())){
                    JOptionPane.showMessageDialog(null, "Enter number in text field.");
                }else{
                    System.out.println("good!");
                    dataSet(selectedDate);
                }
            }
        });
        inputPanel.add(saveBtn);

        //setting delete button
        deleteBtn = new JButton("x");
        deleteBtn.setFont(font);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteInputPanel(inputPanel);
            }
        });
        inputPanel.add(deleteBtn);

        leftPanelBottom.add(inputPanel);
    }

    void deleteInputPanel(JPanel panel){
        panel.removeAll();
        leftPanelBottom.remove(panel);
        leftPanelBottom.updateUI();
    }


    void dataSet(LocalDate selectedDate){
        //setting data key
        String key = selectedDate.getYear() + "," + selectedDate.getMonth().getValue() + "," + selectedDate.getDayOfMonth();
        System.out.println(key);

        String[] value = new String[3];
        value[0] = choice1.getSelectedItem().toString();
        value[1] = choice2.getSelectedItem().toString();
        value[2] = textField.getText();

        dataVal.add(value);

        savedData.put(key, dataVal);

        //for checking the saved data

        System.out.println("");
        System.out.println("vector size: " + dataVal.size());
        System.out.println("Vector info: key=" + savedData.keySet() + ", size: " + savedData.get(key).size());

        for (int i = 0; i < savedData.get(key).size(); i++) {
            System.out.println(Arrays.toString(savedData.get(key).get(i)));
        }
    }

    void deleteData(LocalDate selectedDate){

    }
    void refresh(JPanel panel){
        panel.remove(3);
        panel.remove(2);
        panel.remove(1);
        if(choice1.getSelectedItem().equals("Income")) {
            choice2 = new JComboBox<>(incomeList);
        }else {
            choice2 = new JComboBox<String>(expenseList);
        }
        choice2.setFont(new Font("",Font.BOLD,15));
        panel.add(choice2);
        panel.add(textField);
        panel.add(saveBtn);
        panel.add(deleteBtn);
        panel.updateUI();
        System.out.println("refresh works");
    }


        @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(left_btn)) {
                prevMonth();
            } else if (e.getSource().equals(right_btn)) {
                nextMonth();
            } else if (e.getSource().equals(addBtn)) {
                System.out.println("add button clicked");
                addInputPanel(selectedDate);
                leftPanelBottom.updateUI();

            } else if (e.getSource().equals(submitBtn)) {
                System.out.println("submit button clicked");
                String key = selectedDate.getYear() + "," + selectedDate.getMonth().getValue() + "," + selectedDate.getDayOfMonth();
                dataManagement.put(key, savedData);

//            System.out.println(dataManagement.get(key).keySet() + dataManagement.get(key).values().toString());
            }else if(e.getSource().equals(todayBtn)){
                selectedDate = LocalDate.of(currentDate.getYear(),currentDate.getMonth(),currentDate.getDayOfMonth());
                leftPanelTop.removeAll();
                calendar(selectedDate);
                leftPanelTop.updateUI();
            }
    }
}
