package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private GuiController guiController;

    public DashboardPanel(GuiController guiController) {

        this.guiController = guiController;

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        add(titleLabel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2, 3, 15, 15));
        menuPanel.add(createMenuBox("Sugar Readings", GuiController.SUGAR));
        menuPanel.add(createMenuBox("Exercise Plan", GuiController.EXERCISE));
        menuPanel.add(createMenuBox("Food Analyser", GuiController.FOOD));
        menuPanel.add(createMenuBox("BMI Calculator", GuiController.BMI));
        menuPanel.add(createMenuBox("Reports", GuiController.REPORT));
        menuPanel.add(createMenuBox("Profile", GuiController.PROFILE));

        add(menuPanel, BorderLayout.CENTER);
    }

    private JButton createMenuBox(String title, String panelName) {
        JButton menuButton = new JButton(title);
        menuButton.setFont(menuButton.getFont().deriveFont(Font.BOLD, 18f));
        menuButton.setFocusPainted(false);
        menuButton.setBackground(new Color(240, 248, 255));
        menuButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        menuButton.addActionListener(e -> guiController.switchPanel(panelName));
        return menuButton;
    }
}
