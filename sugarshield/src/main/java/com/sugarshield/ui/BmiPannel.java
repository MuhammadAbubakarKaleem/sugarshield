package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.bmiCalculator.BMI;
import com.sugarshield.bmiCalculator.BMIDAO;
import com.sugarshield.bmiCalculator.BMICalculator;
import com.sugarshield.user.User;

import javax.swing.*;
import java.awt.*;

public class BmiPannel extends JPanel {

    private GuiController guiController;
    private JTextField heightField;
    private JTextField weightField;
    private JButton calculateButton;
    private JButton loadSavedButton;
    private JPanel resultPanel;

    public BmiPannel(GuiController guiController) {
        this.guiController = guiController;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JButton backButton = new JButton("<");
        backButton.addActionListener(e -> guiController.switchPanel(GuiController.DASHBOARD));

        JLabel titleLabel = new JLabel("BMI Calculator");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        headerPanel.add(backButton);
        headerPanel.add(titleLabel);
        topPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        heightField = new JTextField(8);
        weightField = new JTextField(8);
        calculateButton = new JButton("Calculate");
        loadSavedButton = new JButton("Load Saved Data");

        inputPanel.add(new JLabel("Height (feet.inches):"));
        inputPanel.add(heightField);
        inputPanel.add(new JLabel("Weight (kg):"));
        inputPanel.add(weightField);
        inputPanel.add(calculateButton);
        inputPanel.add(loadSavedButton);

        topPanel.add(inputPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(resultPanel, BorderLayout.CENTER);

        showMessage("Enter height like 5.9 for 5 feet 9 inches.");

        calculateButton.addActionListener(e -> calculateBMI());
        loadSavedButton.addActionListener(e -> loadSavedBMI());
    }

    private void calculateBMI() {
        String heightText = heightField.getText();
        String weightText = weightField.getText();

        if (heightText == null || heightText.trim().isEmpty()
                || weightText == null || weightText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter height and weight.");
            return;
        }

        try {
            float height = Float.parseFloat(heightText.trim());
            float weight = Float.parseFloat(weightText.trim());

            float bmiValue = BMICalculator.calculateBMIFromFeetInches(weight, heightText.trim());
            String status = BMICalculator.getBMIStatus(bmiValue);

            BMI bmi = new BMI();
            bmi.setHeight(height);
            bmi.setWeight(weight);
            bmi.setBmi(bmiValue);
            bmi.setBmiStatus(status);

            showBMIDetails(bmi);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Height and weight must be numbers.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadSavedBMI() {
        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        BMIDAO bmiDao = new BMIDAO();
        BMI bmi = bmiDao.getBMIByUserId(currentUser.getId());

        if (bmi == null) {
            showMessage("No saved height and weight found for this user.");
            return;
        }

        heightField.setText(String.valueOf(bmi.getHeight()));
        weightField.setText(String.valueOf(bmi.getWeight()));
        showBMIDetails(bmi);
    }

    private void showBMIDetails(BMI bmi) {
        resultPanel.removeAll();

        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 12, 12));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        addRow(detailsPanel, "Height:", bmi.getHeight() + " feet.inches");
        addRow(detailsPanel, "Weight:", bmi.getWeight() + " kg");
        addRow(detailsPanel, "BMI:", String.format("%.2f", bmi.getBmi()));
        addRow(detailsPanel, "Status:", bmi.getBmiStatus());

        resultPanel.add(detailsPanel);
        resultPanel.revalidate();
        resultPanel.repaint();
    }

    private void addRow(JPanel panel, String label, String value) {
        JLabel labelText = new JLabel(label);
        labelText.setFont(labelText.getFont().deriveFont(Font.BOLD));
        panel.add(labelText);
        panel.add(new JLabel(value));
    }

    private void showMessage(String message) {
        resultPanel.removeAll();

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(messageLabel);

        resultPanel.revalidate();
        resultPanel.repaint();
    }
}
