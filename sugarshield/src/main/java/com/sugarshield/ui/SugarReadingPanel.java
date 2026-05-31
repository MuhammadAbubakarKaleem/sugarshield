package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;

public class SugarReadingPanel extends JPanel {

    GuiController guiController = null;
    public SugarReadingPanel(GuiController guiController) {

        this.guiController = guiController;
        add(new JLabel("SugarReading"));
    }
}

