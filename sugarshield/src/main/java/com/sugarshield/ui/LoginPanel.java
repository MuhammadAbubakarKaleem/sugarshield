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

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel());

        loginButton = new JButton("Login");
        loginButton.addActionListener(e-> doLogin());
        add(loginButton);
    }

    public void doLogin(){
        User currentUser = userService.loginUser(emailField.getText(), new String(passwordField.getPassword()));
        if(currentUser==null){
            JOptionPane.showMessageDialog(this,"Invalid Credentials");
            return;
        }
        this.guiController.showHideMenuBar(true);
        this.guiController.switchPanel(GuiController.DASHBOARD);
    }

}