package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;

public class ProfilePanel extends JPanel {

    GuiController guiController = null;
    public ProfilePanel(GuiController guiController) {

        this.guiController = guiController;
        add(new JLabel("Profile"));
    }
}

