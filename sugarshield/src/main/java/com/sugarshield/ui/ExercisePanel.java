package com.sugarshield.ui;

import com.sugarshield.GuiController;
import com.sugarshield.exerciseTracker.Exercise;
import com.sugarshield.exerciseTracker.WorkoutPlan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Exercise Plan");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24f));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        categoryBox = new JComboBox<>(new String[]{
                "Beginner",
                "Intermediate",
                "Advance"
        });

        generateButton = new JButton("Generate Workout Plan");

        inputPanel.add(new JLabel("Select Level:"));
        inputPanel.add(categoryBox);
        inputPanel.add(generateButton);
        topPanel.add(inputPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        workoutPanel = new JPanel();
        workoutPanel.setLayout(new BoxLayout(workoutPanel, BoxLayout.Y_AXIS));
        workoutPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JScrollPane scrollPane = new JScrollPane(workoutPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(scrollPane, BorderLayout.CENTER);

        showEmptyMessage();

        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generatePlan();
            }
        });
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

            showEmptyMessage();
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
            ArrayList<Exercise> exercises =
                    weeklyPlan.get(day);

            workoutPanel.add(createDayPanel(day, exercises));
            workoutPanel.add(Box.createVerticalStrut(10));
        }

        workoutPanel.add(createRestDayPanel());

        workoutPanel.revalidate();
        workoutPanel.repaint();
    }

    private JPanel createDayPanel(String day, ArrayList<Exercise> exercises) {
        JPanel dayPanel = new JPanel(new BorderLayout(10, 10));
        dayPanel.setBorder(BorderFactory.createTitledBorder(day));

        String[] columns = {"Exercise", "Duration", "Calories"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        int totalMinutes = 0;
        float totalCalories = 0;

        if (exercises != null) {
            for (Exercise exercise : exercises) {
                Object[] row = {
                        exercise.getExerciseName(),
                        exercise.getDurationMinutes() + " min",
                        exercise.getCaloriesBurned()
                };
                model.addRow(row);

                totalMinutes = totalMinutes + exercise.getDurationMinutes();
                totalCalories = totalCalories + exercise.getCaloriesBurned();
            }
        }

        JTable exerciseTable = new JTable(model);
        exerciseTable.setEnabled(false);
        exerciseTable.setRowHeight(24);

        JScrollPane tableScrollPane = new JScrollPane(exerciseTable);
        tableScrollPane.setPreferredSize(new Dimension(650, 100));
        dayPanel.add(tableScrollPane, BorderLayout.CENTER);

        JLabel summaryLabel = new JLabel(
                "Total Duration: " + totalMinutes + " min    Total Calories: " + totalCalories
        );
        dayPanel.add(summaryLabel, BorderLayout.SOUTH);

        return dayPanel;
    }

    private JPanel createRestDayPanel() {
        JPanel sundayPanel = new JPanel(new BorderLayout());
        sundayPanel.setBorder(BorderFactory.createTitledBorder("Sunday"));
        JLabel restLabel = new JLabel("Rest Day");
        restLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sundayPanel.add(restLabel, BorderLayout.CENTER);
        sundayPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        return sundayPanel;
    }

    private void showEmptyMessage() {
        workoutPanel.removeAll();
        JLabel messageLabel = new JLabel("Select a level and generate a workout plan.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        workoutPanel.add(messageLabel);
        workoutPanel.revalidate();
        workoutPanel.repaint();
    }
}
