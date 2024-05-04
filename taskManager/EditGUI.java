package taskManager;

import javax.swing.*;
import java.awt.event.*;

public class EditGUI extends JFrame {
    private JTextField titleField, descField;
    private JButton saveButton;
    private Task task;

    public EditGUI(BrowseGUI parent, Task task) {
        super("Edit Task");
        this.task = task;
        setLayout(null); 

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 10, 80, 25);
        add(titleLabel);

        titleField = new JTextField(task.getTitle());
        titleField.setBounds(100, 10, 180, 25);
        add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(10, 40, 80, 25);
        add(descLabel);

        descField = new JTextField(task.getDescription());
        descField.setBounds(100, 40, 180, 25);
        add(descField);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 75, 100, 30);
        add(saveButton);
        saveButton.addActionListener(e -> {
            Task updatedTask = new Task(titleField.getText(), descField.getText());
            parent.updateTask(this.task, updatedTask);
            dispose();
        });

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}


