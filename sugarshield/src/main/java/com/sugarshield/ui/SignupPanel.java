package com.sugarshield.ui;

import com.sugarshield.GuiController;

import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signupButton;
    GuiController guiController = null;
    public SignupPanel(GuiController guiController) {

        this.guiController = guiController;
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Full Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel());

        signupButton = new JButton("Sign Up");
        add(signupButton);
    }

    public String getNameValue() {
        return nameField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}