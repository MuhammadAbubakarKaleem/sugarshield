package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.HealthReportGenerater.HealthReport;
import com.sugarshield.HealthReportGenerater.HealthReportDAO;
import com.sugarshield.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class ReportPanel extends JPanel {

    private GuiController guiController;
    private HealthReportDAO healthReportDAO;
    private JPanel userInfoPanel;
    private JTable sugarAverageTable;
    private DefaultTableModel tableModel;
    private JLabel bmiLabel;
    private JLabel averageSugarLabel;
    private JLabel healthStatusLabel;
    private JTextArea summaryArea;
    private JButton refreshButton;

    public ReportPanel(GuiController guiController) {

        this.guiController = guiController;
        this.healthReportDAO = new HealthReportDAO();

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Health Report");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        refreshButton = new JButton("Refresh Report");
        topPanel.add(refreshButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(15, 15));

        userInfoPanel = new JPanel(new GridLayout(7, 2, 12, 12));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Info"));
        contentPanel.add(userInfoPanel, BorderLayout.NORTH);

        String[] columns = {"Reading Type", "Average Sugar"};
        tableModel = new DefaultTableModel(columns, 0);
        sugarAverageTable = new JTable(tableModel);
        sugarAverageTable.setEnabled(false);
        sugarAverageTable.setRowHeight(24);

        JScrollPane tableScrollPane = new JScrollPane(sugarAverageTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Sugar Average By Type"));
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        JPanel summaryPanel = new JPanel(new BorderLayout(10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));

        JPanel labelsPanel = new JPanel(new GridLayout(3, 2, 12, 8));
        bmiLabel = new JLabel();
        averageSugarLabel = new JLabel();
        healthStatusLabel = new JLabel();

        addLabelRow(labelsPanel, "BMI:", bmiLabel);
        addLabelRow(labelsPanel, "Overall Sugar Average:", averageSugarLabel);
        addLabelRow(labelsPanel, "Health Status:", healthStatusLabel);
        summaryPanel.add(labelsPanel, BorderLayout.NORTH);

        summaryArea = new JTextArea(4, 30);
        summaryArea.setEditable(false);
        summaryPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        contentPanel.add(summaryPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        refreshButton.addActionListener(e -> loadReport());

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                loadReport();
            }
        });
    }

    private void loadReport() {
        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            showEmptyReport();
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        HealthReport report = healthReportDAO.generateHealthReport(currentUser.getId());

        showUserInfo(report);
        showSugarAverages(report.getAverageSugarByType());
        bmiLabel.setText(String.format("%.2f", report.getBmi()));
        averageSugarLabel.setText(String.format("%.2f", report.getAverageSugar()));
        healthStatusLabel.setText(report.getHealthStatus());
        summaryArea.setText(report.getReportSummary());
    }

    private void showUserInfo(HealthReport report) {
        userInfoPanel.removeAll();

        addInfoRow("Name:", report.getFullName());
        addInfoRow("Age:", String.valueOf(report.getAge()));
        addInfoRow("Sugar Type:", report.getDiabetesType());
        addInfoRow("Height:", report.getHeight() + " feet.inches");
        addInfoRow("Weight:", report.getWeight() + " kg");
        addInfoRow("BMI:", String.format("%.2f", report.getBmi()));
        addInfoRow("Overall Sugar Average:", String.format("%.2f", report.getAverageSugar()));

        userInfoPanel.revalidate();
        userInfoPanel.repaint();
    }

    private void showSugarAverages(HashMap<String, Float> averageSugarByType) {
        tableModel.setRowCount(0);

        if (averageSugarByType == null || averageSugarByType.isEmpty()) {
            return;
        }

        for (String type : averageSugarByType.keySet()) {
            Object[] row = {
                    type,
                    String.format("%.2f", averageSugarByType.get(type))
            };

            tableModel.addRow(row);
        }
    }

    private void addInfoRow(String label, String value) {
        JLabel labelText = new JLabel(label);
        labelText.setFont(labelText.getFont().deriveFont(Font.BOLD));
        userInfoPanel.add(labelText);

        if (value == null) {
            value = "";
        }

        userInfoPanel.add(new JLabel(value));
    }

    private void addLabelRow(JPanel panel, String label, JLabel valueLabel) {
        JLabel labelText = new JLabel(label);
        labelText.setFont(labelText.getFont().deriveFont(Font.BOLD));
        panel.add(labelText);
        panel.add(valueLabel);
    }

    private void showEmptyReport() {
        userInfoPanel.removeAll();
        tableModel.setRowCount(0);
        bmiLabel.setText("");
        averageSugarLabel.setText("");
        healthStatusLabel.setText("");
        summaryArea.setText("");
    }
}
