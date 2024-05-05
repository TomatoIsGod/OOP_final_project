package taskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class EditGUI extends JFrame {
    private JTextField titleField, descField;
    private JButton saveButton;
    private JComboBox<Integer> yearCombo, monthCombo, dayCombo;
    private Task task;

    public EditGUI(BrowseGUI parent, Task task) {
        super("Edit Task");
        this.task = task;
        setLayout(null);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 10, 80, 25);
        add(titleLabel);

        titleField = new JTextField(task.getTitle());
        titleField.setBounds(100, 10, 280, 25);
        add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(10, 40, 80, 25);
        add(descLabel);

        descField = new JTextField(task.getDescription());
        descField.setBounds(100, 40, 280, 25);
        add(descField);

        JLabel dateLabel = new JLabel("Deadline:");
        dateLabel.setBounds(10, 70, 80, 25);
        add(dateLabel);

        yearCombo = new JComboBox<>(getYearRange());
        monthCombo = new JComboBox<>(getMonthRange());
        dayCombo = new JComboBox<>(getDayRange(31)); // Assume 31 days, adjust as needed
        yearCombo.setBounds(100, 70, 90, 25);
        monthCombo.setBounds(200, 70, 70, 25);
        dayCombo.setBounds(280, 70, 70, 25);
        add(yearCombo);
        add(monthCombo);
        add(dayCombo);

        yearCombo.setSelectedItem(task.getDeadline().getYear());
        monthCombo.setSelectedItem(task.getDeadline().getMonthValue());
        dayCombo.setSelectedItem(task.getDeadline().getDayOfMonth());

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 105, 100, 30);
        add(saveButton);
        saveButton.addActionListener(e -> {
            int year = (Integer) yearCombo.getSelectedItem();
            int month = (Integer) monthCombo.getSelectedItem();
            int day = (Integer) dayCombo.getSelectedItem();
            LocalDate deadline = LocalDate.of(year, month, day);
            Task updatedTask = new Task(titleField.getText(), descField.getText(), deadline);
            parent.updateTask(this.task, updatedTask);
            dispose();
        });

        setSize(400, 150);  // Adjusted size to better fit the layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private Integer[] getYearRange() {
        Integer[] years = new Integer[10];
        int currentYear = LocalDate.now().getYear();
        for (int i = 0; i < 10; i++) {
            years[i] = currentYear + i;
        }
        return years;
    }

    private Integer[] getMonthRange() {
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = i + 1;
        }
        return months;
    }

    private Integer[] getDayRange(int days) {
        Integer[] dayRange = new Integer[days];
        for (int i = 0; i < days; i++) {
            dayRange[i] = i + 1;
        }
        return dayRange;
    }
}
