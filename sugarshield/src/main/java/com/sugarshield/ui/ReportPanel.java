package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;

public class ReportPanel extends JPanel {

    GuiController guiController = null;
    public ReportPanel(GuiController guiController) {

        this.guiController = guiController;
        add(new JLabel("Reports"));
    }
}