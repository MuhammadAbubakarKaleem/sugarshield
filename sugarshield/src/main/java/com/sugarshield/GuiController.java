package com.sugarshield;

import com.sugarshield.ui.*;
import com.sugarshield.user.User;

import javax.swing.*;
import java.awt.*;

public class GuiController extends JFrame {

    // making constant of names of panel .
    public static final String DASHBOARD = "DASHBOARD";
    public static final String SUGAR = "SUGAR";
    public static final String EXERCISE = "EXERCISE";
    public static final String REPORT = "REPORT";
    public static final String PROFILE = "PROFILE";
    public static final String LOGIN = "LOGIN";
    public static final String SIGNUP = "SIGNUP";

    User currentUser = null;
    JMenuBar menuBar = null;

    private CardLayout cardLayout;

    private JPanel mainPanel;

    public GuiController() {
        initializeFrame();
        createMenuBar();
        createPanels();
        setVisible(true);
    }

    private void initializeFrame() {

        setTitle("Sugar Shield");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }


    public void switchPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void showHideMenuBar(boolean visible){
        this.menuBar.setVisible(visible);
    }

    private void createPanels() {

        mainPanel.add(new DashboardPanel(this), DASHBOARD);
        mainPanel.add(new SugarReadingPanel(this), SUGAR);
        mainPanel.add(new ExercisePanel(this), EXERCISE);
        mainPanel.add(new ReportPanel(this), REPORT);
        mainPanel.add(new ProfilePanel(this), PROFILE);
        mainPanel.add(new LoginPanel(this), LOGIN);
        mainPanel.add(new SignupPanel(this), SIGNUP);
        cardLayout.show(mainPanel, LOGIN);
    }

    private void createMenuBar() {

        this.menuBar = new JMenuBar();

        JMenu navigationMenu = new JMenu("Menu");

        JMenuItem dashboardItem = new JMenuItem("Dashboard");
        JMenuItem sugarItem = new JMenuItem("Sugar Readings");
        JMenuItem exerciseItem = new JMenuItem("Exercise Tracker");
        JMenuItem reportItem = new JMenuItem("Reports");
        JMenuItem profileItem = new JMenuItem("Profile");

        dashboardItem.addActionListener(e-> switchPanel(DASHBOARD));
        sugarItem.addActionListener(e-> switchPanel(SUGAR));
        exerciseItem.addActionListener(e-> switchPanel(EXERCISE));
        reportItem.addActionListener(e-> switchPanel(REPORT));
        profileItem.addActionListener(e-> switchPanel(PROFILE));

        navigationMenu.add(dashboardItem);
        navigationMenu.add(sugarItem);
        navigationMenu.add(exerciseItem);
        navigationMenu.add(reportItem);
        navigationMenu.add(profileItem);

        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);
        if(currentUser!=null){
            menuBar.setVisible(true);
        }else{
            menuBar.setVisible(false);
        }

    }


}
