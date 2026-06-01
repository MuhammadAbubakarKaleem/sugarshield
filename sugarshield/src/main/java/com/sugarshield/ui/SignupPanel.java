package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.user.User;
import com.sugarshield.user.UserService;

import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton signupButton;
    GuiController guiController = null;
    UserService userService = new UserService();

    public SignupPanel(GuiController guiController) {

        this.guiController = guiController;
        setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField();
        nameField.setColumns(20);
        formPanel.add(nameField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setColumns(20);
        formPanel.add(emailField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        formPanel.add(passwordField, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        signupButton = new JButton("Sign Up");
        signupButton.setPreferredSize(new Dimension(120, 32));
        signupButton.addActionListener(e -> doSignup());
        formPanel.add(signupButton, gbc);

        JButton loginLink = new JButton("Already have an account? Login");
        loginLink.setBorderPainted(false);
        loginLink.setContentAreaFilled(false);
        loginLink.setFocusPainted(false);
        loginLink.addActionListener(e -> guiController.switchPanel(GuiController.LOGIN));
        gbc.gridy = 5;
        gbc.insets = new Insets(4, 8, 8, 8);
        formPanel.add(loginLink, gbc);

        add(formPanel);
    }

    public void doSignup() {
        User newUser = new User();
        newUser.setFullName(getNameValue());
        newUser.setEmail(getEmail());
        newUser.setPassword(getPassword());

        User user = userService.signUp(newUser);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "Signup failed. Please check your details.");
            return;
        }

        guiController.setCurrentUser(user);
        guiController.showHideMenuBar(true);
        guiController.switchPanel(GuiController.DASHBOARD);
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
