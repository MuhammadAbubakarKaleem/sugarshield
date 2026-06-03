package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.foodAnalyser.Food;
import com.sugarshield.foodAnalyser.FoodService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodAnalyserPanel extends JPanel {

    private GuiController guiController;
    private FoodService foodService;
    private JTextField foodNameField;
    private JButton searchButton;
    private JPanel resultPanel;

    public FoodAnalyserPanel(GuiController guiController) {
        this.guiController = guiController;
        this.foodService = new FoodService();

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Food Analyser");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        foodNameField = new JTextField(25);
        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Food Name:"));
        searchPanel.add(foodNameField);
        searchPanel.add(searchButton);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(resultPanel, BorderLayout.CENTER);

        showMessage("Search a food item to see its details.");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchFood();
            }
        });
    }

    private void searchFood() {
        String foodName = foodNameField.getText();

        if (foodName == null || foodName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a food name.");
            return;
        }

        Food food = foodService.searchByName(foodName);

        if (food == null) {
            showMessage("No food found with this name.");
            return;
        }

        showFoodDetails(food);
    }

    private void showFoodDetails(Food food) {
        resultPanel.removeAll();

        JPanel detailsPanel = new JPanel(new GridLayout(7, 2, 12, 12));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        addRow(detailsPanel, "Food Name:", food.getFoodName());
        addRow(detailsPanel, "Calories:", String.valueOf(food.getCalories()));
        addRow(detailsPanel, "Sugar Content:", String.valueOf(food.getSugarContent()));
        addRow(detailsPanel, "Glycemic Index:", String.valueOf(food.getGlycemicIndex()));
        addRow(detailsPanel, "Carbohydrates:", String.valueOf(food.getCarbohydrates()));
        addRow(detailsPanel, "Safe Status:", food.getSafeStatus());

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
