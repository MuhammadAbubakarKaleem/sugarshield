package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.SugarTracker.Sugar;
import com.sugarshield.SugarTracker.SugarDao;
import com.sugarshield.SugarTracker.SugarReading;
import com.sugarshield.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SugarReadingPanel extends JPanel {

    private GuiController guiController;
    private SugarDao sugarDao;
    private JTextField sugarLevelField;
    private JComboBox<String> readingTypeBox;
    private JTextArea notesArea;
    private JButton saveButton;
    private JTable readingsTable;
    private DefaultTableModel tableModel;
    private DateTimeFormatter dateFormatter;

    public SugarReadingPanel(GuiController guiController) {

        this.guiController = guiController;
        this.sugarDao = new SugarDao();
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JButton backButton = new JButton("<");
        backButton.addActionListener(e -> guiController.switchPanel(GuiController.DASHBOARD));

        JLabel titleLabel = new JLabel("Sugar Readings");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        headerPanel.add(backButton);
        headerPanel.add(titleLabel);
        topPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        sugarLevelField = new JTextField(8);
        readingTypeBox = new JComboBox<>(new String[]{
                "Fasting",
                "Before Meal",
                "After Meal",
                "Random"
        });
        notesArea = new JTextArea(2, 20);
        saveButton = new JButton("Save Reading");

        inputPanel.add(new JLabel("Sugar Level:"));
        inputPanel.add(sugarLevelField);
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(readingTypeBox);
        inputPanel.add(new JLabel("Notes:"));
        inputPanel.add(new JScrollPane(notesArea));
        inputPanel.add(saveButton);

        topPanel.add(inputPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"Date/Time", "Sugar Level", "Type", "Notes"};
        tableModel = new DefaultTableModel(columns, 0);
        readingsTable = new JTable(tableModel);
        readingsTable.setEnabled(false);
        readingsTable.setRowHeight(24);

        JScrollPane tableScrollPane = new JScrollPane(readingsTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(tableScrollPane, BorderLayout.CENTER);

        saveButton.addActionListener(e -> saveReading());

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                loadReadings();
            }
        });
    }

    private void saveReading() {
        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        String sugarLevelText = sugarLevelField.getText();

        if (sugarLevelText == null || sugarLevelText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter sugar level.");
            return;
        }

        try {
            float sugarLevel = Float.parseFloat(sugarLevelText.trim());

            SugarReading reading = new SugarReading();
            reading.setUserId(currentUser.getId());
            reading.setSugarLevel(sugarLevel);
            reading.setReadingType(readingTypeBox.getSelectedItem().toString());
            reading.setReadingTime(LocalDateTime.now());
            reading.setNotes(notesArea.getText());

            SugarReading savedReading = sugarDao.insertSugarDetail(reading);

            if (savedReading == null) {
                JOptionPane.showMessageDialog(this, "Sugar reading was not saved.");
                return;
            }

            sugarLevelField.setText("");
            notesArea.setText("");
            loadReadings();
            JOptionPane.showMessageDialog(this, "Sugar reading saved successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Sugar level must be a number.");
        }
    }

    private void loadReadings() {
        tableModel.setRowCount(0);

        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            return;
        }

        ArrayList<Sugar> readings = sugarDao.getSugarReadingsByUserId(currentUser.getId());

        for (Sugar reading : readings) {
            String readingTime = "";

            if (reading.getReadingTime() != null) {
                readingTime = reading.getReadingTime().format(dateFormatter);
            }

            Object[] row = {
                    readingTime,
                    reading.getSugarLevel(),
                    reading.getReadingType(),
                    reading.getNotes()
            };

            tableModel.addRow(row);
        }
    }
}
