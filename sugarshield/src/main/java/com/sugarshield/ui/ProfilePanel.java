package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.user.User;
import com.sugarshield.user.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProfilePanel extends JPanel {

    private GuiController guiController;
    private UserService userService;
    private JTextField fullNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField genderField;
    private JTextField diabetesTypeField;
    private JButton updateButton;

    public ProfilePanel(GuiController guiController) {

        this.guiController = guiController;
        this.userService = new UserService();

        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JButton backButton = new JButton("<");
        backButton.addActionListener(e -> guiController.switchPanel(GuiController.DASHBOARD));

        JLabel titleLabel = new JLabel("Profile");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        topPanel.add(backButton);
        topPanel.add(titleLabel);

        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        fullNameField = new JTextField(22);
        emailField = new JTextField(22);
        passwordField = new JPasswordField(22);
        ageField = new JTextField(22);
        heightField = new JTextField(22);
        weightField = new JTextField(22);
        genderField = new JTextField(22);
        diabetesTypeField = new JTextField(22);

        addFormRow(formPanel, gbc, 0, "Full Name:", fullNameField);
        addFormRow(formPanel, gbc, 1, "Email:", emailField);
        addFormRow(formPanel, gbc, 2, "Password:", passwordField);
        addFormRow(formPanel, gbc, 3, "Age:", ageField);
        addFormRow(formPanel, gbc, 4, "Height (feet.inches):", heightField);
        addFormRow(formPanel, gbc, 5, "Weight (kg):", weightField);
        addFormRow(formPanel, gbc, 6, "Gender:", genderField);
        addFormRow(formPanel, gbc, 7, "Diabetes Type:", diabetesTypeField);

        updateButton = new JButton("Update Profile");
        updateButton.addActionListener(e -> updateProfile());

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                loadCurrentUser();
            }
        });
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void loadCurrentUser() {
        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            clearFields();
            return;
        }

        User latestUser = userService.getUserById(currentUser.getId());
        if (latestUser != null) {
            guiController.setCurrentUser(latestUser);
            currentUser = latestUser;
        }

        fullNameField.setText(currentUser.getFullName());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());
        ageField.setText(String.valueOf(currentUser.getAge()));
        heightField.setText(String.valueOf(currentUser.getHeight()));
        weightField.setText(String.valueOf(currentUser.getWeight()));

        if (currentUser.getGender() == 0) {
            genderField.setText("");
        } else {
            genderField.setText(String.valueOf(currentUser.getGender()));
        }

        diabetesTypeField.setText(currentUser.getDiabetesType());
    }

    private void updateProfile() {
        User currentUser = guiController.getCurrentUser();

        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Please login first.");
            return;
        }

        try {
            User updatedUser = new User();
            updatedUser.setId(currentUser.getId());
            updatedUser.setFullName(fullNameField.getText());
            updatedUser.setEmail(emailField.getText());
            updatedUser.setPassword(new String(passwordField.getPassword()));
            updatedUser.setAge(Integer.parseInt(ageField.getText().trim()));
            updatedUser.setHeight(Double.parseDouble(heightField.getText().trim()));
            updatedUser.setWeight(Double.parseDouble(weightField.getText().trim()));

            String genderText = genderField.getText();
            if (genderText != null && !genderText.trim().isEmpty()) {
                updatedUser.setGender(genderText.trim().charAt(0));
            }

            updatedUser.setDiabetesType(diabetesTypeField.getText());

            User savedUser = userService.updateUser(updatedUser);

            if (savedUser == null) {
                JOptionPane.showMessageDialog(this, "Profile update failed. Please check your details.");
                return;
            }

            guiController.setCurrentUser(savedUser);
            JOptionPane.showMessageDialog(this, "Profile updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age, height, and weight must be numbers.");
        }
    }

    private void clearFields() {
        fullNameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        ageField.setText("");
        heightField.setText("");
        weightField.setText("");
        genderField.setText("");
        diabetesTypeField.setText("");
    }
}
