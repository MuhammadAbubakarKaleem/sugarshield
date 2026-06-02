package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;

public class DashboardPanel extends JPanel {

    GuiController guiController = null;
    public DashboardPanel(GuiController guiController) {

        this.guiController = guiController;
        add(new JLabel("Dashboard"));


    }
}