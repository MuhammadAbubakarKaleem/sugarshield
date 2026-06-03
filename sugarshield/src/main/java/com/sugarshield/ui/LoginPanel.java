package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.user.User;
import com.sugarshield.user.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    UserService userService = new UserService();
    GuiController guiController = null;

    public LoginPanel(GuiController guiController) {

        this.guiController = guiController;

        setLayout(new GridBagLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setColumns(20);
        formPanel.add(emailField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setColumns(20);
        formPanel.add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(120, 32));
        loginButton.addActionListener(e-> doLogin());
        formPanel.add(loginButton, gbc);

        JButton signupLink = new JButton("Don't have an account? Sign up");
        signupLink.setBorderPainted(false);
        signupLink.setContentAreaFilled(false);
        signupLink.setFocusPainted(false);
        signupLink.addActionListener(e -> guiController.switchPanel(GuiController.SIGNUP));
        gbc.gridy = 4;
        gbc.insets = new Insets(4, 8, 8, 8);
        formPanel.add(signupLink, gbc);

        emailField.setText("abubakarkaleem22@gmail.com");
        passwordField.setText("123456");
        add(formPanel);
    }

    public void doLogin(){
        User currentUser = userService.loginUser(emailField.getText(), new String(passwordField.getPassword()));
        if(currentUser==null){
            JOptionPane.showMessageDialog(this,"Invalid Credentials");
            return;
        }
        this.guiController.setCurrentUser(currentUser);
        this.guiController.showHideMenuBar(true);
        this.guiController.switchPanel(GuiController.DASHBOARD);
    }

}
