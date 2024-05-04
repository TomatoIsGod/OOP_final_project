package taskManager;

import javax.swing.*;
import java.awt.event.*;

public class AddNewGUI extends JFrame {
    private JTextField titleField, descField;
    private JButton saveButton;

    public AddNewGUI(BrowseGUI parent) {
        super("Add New Task");
        setLayout(null); 

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 10, 80, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(100, 10, 180, 25);
        add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(10, 40, 80, 25);
        add(descLabel);

        descField = new JTextField();
        descField.setBounds(100, 40, 180, 25);
        add(descField);

        saveButton = new JButton("Save");
        saveButton.setBounds(100, 75, 100, 30);
        add(saveButton);
        saveButton.addActionListener(e -> {
            Task newTask = new Task(titleField.getText(), descField.getText());
            parent.addTask(newTask);
            dispose();
        });

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

