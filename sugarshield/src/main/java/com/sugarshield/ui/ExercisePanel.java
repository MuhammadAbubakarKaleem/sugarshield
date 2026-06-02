package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.exerciseTracker.Exercise;
import com.sugarshield.exerciseTracker.WorkoutPlan;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ExercisePanel extends JPanel {

    private GuiController guiController;

    private JComboBox<String> categoryBox;
    private JButton generateButton;
    private JPanel workoutPanel;
    private WorkoutPlan workoutPlan;


    public ExercisePanel(GuiController guiController) {
        this.guiController = guiController;
        this.workoutPlan = new WorkoutPlan();
        setLayout(new BorderLayout(10, 10));
        // Top Panel
        JPanel topPanel = new JPanel();
        categoryBox = new JComboBox<>(new String[]{
                "Beginner",
                "Intermediate",
                "Advance"
        });

        generateButton = new JButton("Generate Workout Plan");

        topPanel.add(new JLabel("Level:"));
        topPanel.add(categoryBox);
        topPanel.add(generateButton);

        add(topPanel, BorderLayout.NORTH);

        // Workout Display Panel
        workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(workoutPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Button ActionListener
        generateButton.addActionListener(e -> generatePlan());
    }

    private void generatePlan() {

        workoutPanel.removeAll();

        String category =
                categoryBox.getSelectedItem().toString();

        HashMap<String, ArrayList<Exercise>> weeklyPlan =
                workoutPlan.generateWorkoutPlan(category);

        if (weeklyPlan == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No exercises found for " + category
            );

            return;
        }

        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        for (String day : days) {

            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));

            dayPanel.setBorder(
                    BorderFactory.createTitledBorder(day)
            );

            ArrayList<Exercise> exercises =
                    weeklyPlan.get(day);

            for (Exercise exercise : exercises) {

                JLabel label = new JLabel(
                        exercise.getExerciseName()
                                + " | Duration: "
                                + exercise.getDurationMinutes()
                                + " min | Calories: "
                                + exercise.getCaloriesBurned()
                );

                dayPanel.add(label);
            }

            workoutPanel.add(dayPanel);
        }

        JPanel sundayPanel = new JPanel();

        sundayPanel.setBorder(
                BorderFactory.createTitledBorder("Sunday")
        );

        sundayPanel.add(new JLabel("REST DAY"));

        workoutPanel.add(sundayPanel);

        workoutPanel.revalidate();
        workoutPanel.repaint();
    }
}