package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;

public class ExercisePanel extends JPanel {

    GuiController guiController = null;
    public ExercisePanel(GuiController guiController) {

        this.guiController = guiController;
        add(new JLabel("Exercise"));
    }
}

